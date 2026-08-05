#!/usr/bin/env bash
#
# Prepares freshly recorded Paparazzi golden images for flex-development/gh-commit.
#
# gh-commit builds the commit through the GitHub API, so it is signed with GitHub's
# web-flow key and satisfies the "Require signed commits" ruleset that rejects a
# plain bot push (GH013). It does NOT make a local commit -- it reads the changed
# files straight from the working tree (fs.readFileSync in changes.handler.ts), so
# this script must leave the changes uncommitted for it to find them.
#
# The wrinkle: the golden images are LFS-tracked, so the working tree holds real
# PNG bytes. Letting gh-commit read those would commit ~1 MB of PNGs directly into
# git and silently take the images out of LFS, while appearing to succeed. So the
# LFS objects are uploaded here and the working-tree files are replaced with their
# pointer text, which is what actually belongs in the commit.
set -euo pipefail

git add -A

# Deletions are excluded: they have no index blob to read, and gh-commit picks them
# up from `git status` on its own.
changed=$(git diff --cached --name-only --diff-filter=d)
if [ -z "$changed" ]; then
  echo "No recorded image changes."
  exit 0
fi

# Upload the blobs before the pointers land, so the commit is never left
# referencing objects the server does not have. Kept POSIX-ish on purpose:
# `mapfile` would restrict this to bash 4+ and make it untestable on macOS.
oids=""
while IFS= read -r f; do
  [ -n "$f" ] || continue
  oid=$(git cat-file blob ":$f" | sed -n 's/^oid sha256://p')
  if [ -n "$oid" ]; then oids="$oids $oid"; fi
done <<EOF
$changed
EOF

if [ -n "$oids" ]; then
  echo "Uploading LFS object(s):$oids"
  # Word splitting is intended -- sha256 hex ids never contain spaces.
  # shellcheck disable=SC2086
  git lfs push --object-id origin $oids
fi

# Hand gh-commit pointer text rather than PNG bytes.
while IFS= read -r f; do
  [ -n "$f" ] || continue
  git cat-file blob ":$f" > "$f"
done <<EOF
$changed
EOF

# Unstage: gh-commit reads the working tree, not the index.
git reset
echo "Staged $(printf '%s\n' "$changed" | wc -l | tr -d ' ') file(s) as LFS pointers."

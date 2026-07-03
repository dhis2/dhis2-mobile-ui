git config user.name "GitHub Actions Bot"
git config user.email "<android@dhis2.org>"

# Detects both modifications to tracked files and new untracked files (e.g. newly recorded Paparazzi images)
if [ -n "$(git status --porcelain)" ]; then
  git add -A
  git commit -m "Paparazzi Golden Images"
fi

# Releasing

A release needs two manual steps. Everything else is automatic.

```
develop ─▶ Release start ─▶ release/0.9.0 ─ fixes (Capture QA on 0.9.0-SNAPSHOT) ─▶ PR → main
   │            └─ bot PR: develop → 1.0.0-SNAPSHOT                            merge ─┘
   ▲                                                                               │
   └──── back-merge PR ◀── Release finish: Maven Central 0.9.0, tag 0.9.0, GitHub release + APK
```

## Release

1. Run **Release start** from the Actions tab with type `release`.
   - It cuts `release/X.Y.Z` from `develop`, where `X.Y.Z` is the version already on `develop`.
   - `next_version` (optional) is the version `develop` moves to, e.g. `1.0.0`. It defaults to the next minor. A bot PR makes that change: merge it.
   - Use `dry_run` to see the computed versions first.
2. QA the release with `X.Y.Z-SNAPSHOT`, published on every push to `release/X.Y.Z`. Fixes are PRs into `release/X.Y.Z`.
3. Merge the `release/X.Y.Z` PR into `main`. **Release finish** then:
   - publishes `X.Y.Z` to Maven Central,
   - tags the merge commit `X.Y.Z` and publishes the GitHub release with notes and the showcase APK,
   - opens the back-merge PR `main → develop`. Merge it with **Create a merge commit**, never squash.

## Hotfix

Run **Release start** with type `hotfix`. It cuts `release/X.Y.(Z+1)` from `main`. Then continue from step 2.

## Rules

- The release branch name is the version. Release branches never change the version in `build.gradle.kts`: CI passes it as `-PreleaseVersion`.
- PR titles follow [Conventional Commits](https://www.conventionalcommits.org), e.g. `fix: [ANDROAPP-123] Crash in InputText`. They are checked by CI and grouped into the release notes.

## If something fails

Every step of **Release finish** skips what is already done. Re-run it from the Actions tab with the version, e.g. `0.9.0`, and `dry_run` to check first.

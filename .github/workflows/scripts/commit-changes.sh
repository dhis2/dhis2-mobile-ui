readonly GIT_HAS_CHANGES=1 # 0 = clean, 1 = dirty

git config user.name "GitHub Actions Bot"
git config user.email "<android@dhis2.org>"

# Check for any working tree changes (modified tracked files OR new untracked files)
git status --porcelain | grep -q .; GIT_DIFF_STATUS=$?

# If the GIT_DIFF_STATUS is 1 (GIT_HAS_CHANGES) then we know
# there are changes and we can commit the changes and push
# them to new branch
if [[ ${GIT_DIFF_STATUS} -eq ${GIT_HAS_CHANGES} ]]; then
  git add -A
  git commit -m "Paparazzi Golden Images"
fi

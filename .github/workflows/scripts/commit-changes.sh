readonly GIT_HAS_CHANGES=1 # 0 = clean, 1 = dirty

git config user.name "GitHub Actions Bot"
git config user.email "<android@dhis2.org>"

# Following git command checks the git diff of staged files
# and assigns the exit code to GIT_DIFF_STATUS variable.
git diff --quiet; GIT_DIFF_STATUS=$?

# If the GIT_DIFF_STATUS is 1 (GIT_HAS_CHANGES) then we know
# there are changes and we can commit the changes and push
# them to new branch
if [[ ${GIT_DIFF_STATUS} -eq ${GIT_HAS_CHANGES} ]]; then
  git add -A
  git commit -m "Paparazzi Golden Images"

exit 0
fi

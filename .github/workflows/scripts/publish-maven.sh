set -x

branch=$(git rev-parse --abbrev-ref HEAD)

if [ "$branch" = "main-test" ]; then
  ./gradlew :designsystem:publishToSonatype -PremoveSnapshotSuffix
else
  ./gradlew :designsystem:publishToSonatype
fi
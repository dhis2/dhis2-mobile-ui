set -x

branch=$(git rev-parse --abbrev-ref HEAD)

if [ "$branch" = "main-test" ]; then
  ./gradlew publishToSonatype -PremoveSnapshotSuffix
else
  ./gradlew publishToSonatype
fi
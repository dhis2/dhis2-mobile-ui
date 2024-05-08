set -x

branch=$(git rev-parse --abbrev-ref HEAD)

if [ "$branch" = "main" ]; then
  ./gradlew :designSystem:publishToSonatype closeAndReleaseSonatypeStagingRepository -PremoveSnapshotSuffix
else
  ./gradlew :designSystem:publishToSonatype
fi
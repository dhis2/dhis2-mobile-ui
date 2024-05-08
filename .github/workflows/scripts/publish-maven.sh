set -x

branch=$(git rev-parse --abbrev-ref HEAD)

if [ "$branch" = "main" ]; then
  ./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository -PremoveSnapshotSuffix --project-dir designSystem
else
  ./gradlew publishToSonatype --project-dir designSystem
fi
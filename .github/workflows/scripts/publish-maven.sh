set -x

branch=$(git rev-parse --abbrev-ref HEAD)

if [ "$branch" = "main" ]; then
  ./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository -PremoveSnapshotSuffix --project-dir designsystem
else
  ./gradlew publishToSonatype --project-dir designsystem
fi
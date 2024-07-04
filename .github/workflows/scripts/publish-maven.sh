set -x

branch=$(git rev-parse --abbrev-ref HEAD)

if [ "$branch" = "main" ]; then
  ./gradlew :designsystem:publishToSonatype
else
  ./gradlew :designsystem:publishToSonatype
fi
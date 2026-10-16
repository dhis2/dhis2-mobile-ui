#!/usr/bin/env bash
# Publishes the designsystem module to Maven Central.
#
#   publish-maven.sh snapshot [version]  -> snapshot repository. Version defaults to build.gradle.kts.
#   publish-maven.sh release <version>   -> staged, closed and released on Maven Central.
set -euo pipefail

usage() {
  echo "Usage: $0 snapshot [X.Y.Z-SNAPSHOT] | release X.Y.Z" >&2
  exit 1
}

mode=${1:-}
version=${2:-}

case "$mode" in
  release)
    [[ $version =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]] || usage
    ./gradlew :designsystem:publishToSonatype closeAndReleaseSonatypeStagingRepository -PreleaseVersion="$version"
    ;;
  snapshot)
    if [ -n "$version" ]; then
      [[ $version =~ -SNAPSHOT$ ]] || usage
      ./gradlew :designsystem:publishToSonatype -PreleaseVersion="$version"
    else
      ./gradlew :designsystem:publishToSonatype
    fi
    ;;
  *)
    usage
    ;;
esac

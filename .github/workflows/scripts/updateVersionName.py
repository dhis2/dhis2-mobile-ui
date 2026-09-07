import re
import sys

VERSION_PATTERN = re.compile(r'version\s*=\s*"[0-9.]+-SNAPSHOT"')


def update_version_in_gradle(file_path, new_version):
    with open(file_path, 'r') as file:
        content = file.read()

    # Use regex to find and replace the version value
    updated_content, replacements = VERSION_PATTERN.subn(f'version = "{new_version}"', content)

    # Fail loudly instead of committing an unchanged file: a typo in the input, or a version that
    # is already the requested one, would otherwise pass silently.
    if replacements == 0:
        sys.exit(f'No version declaration matching {VERSION_PATTERN.pattern} found in {file_path}.')

    if updated_content == content:
        sys.exit(f'{file_path} already declares version "{new_version}". Nothing to update.')

    with open(file_path, 'w') as file:
        file.write(updated_content)

    print(f'Updated {file_path} to version "{new_version}" ({replacements} replacement(s)).')


if len(sys.argv) != 2:
    sys.exit('Usage: updateVersionName.py <new-version>, e.g. updateVersionName.py 0.8.1-SNAPSHOT')

new_version = sys.argv[1]

if not re.fullmatch(r'[0-9]+(\.[0-9]+)*-SNAPSHOT', new_version):
    sys.exit(f'Invalid version "{new_version}". Expected a snapshot version such as 0.8.1-SNAPSHOT.')

update_version_in_gradle('build.gradle.kts', new_version)

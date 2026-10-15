import re
import sys

VERSION_PATTERN = re.compile(r'version\s*=\s*"[0-9.]+-SNAPSHOT"')


def update_version_in_gradle(file_path, new_version):
    with open(file_path, 'r') as file:
        content = file.read()

    # Use regex to find and replace the version value
    updated_content = VERSION_PATTERN.sub(f'version = "{new_version}"', content)

    # Fail loudly instead of committing an unchanged file: a typo in the input, or a version that
    # is already the requested one, would otherwise pass silently.
    if updated_content == content:
        sys.exit(f'{file_path} was not updated. It already declares version "{new_version}", '
                 f'or it has no version = "<version>-SNAPSHOT" declaration.')

    with open(file_path, 'w') as file:
        file.write(updated_content)

    print(f'Updated {file_path} to version "{new_version}".')


if len(sys.argv) != 2:
    sys.exit('Usage: updateVersionName.py <version>, e.g. updateVersionName.py 0.8.1')

# The -SNAPSHOT suffix is what routes publishing to the Maven snapshots repository, so it is always
# written to build.gradle.kts. It is optional in the input: both 0.8.1 and 0.8.1-SNAPSHOT are fine.
version = sys.argv[1].removesuffix('-SNAPSHOT')

if not re.fullmatch(r'[0-9]+(\.[0-9]+)*', version):
    sys.exit(f'Invalid version "{sys.argv[1]}". Expected a version such as 0.8.1 or 0.8.1-SNAPSHOT.')

update_version_in_gradle('build.gradle.kts', f'{version}-SNAPSHOT')

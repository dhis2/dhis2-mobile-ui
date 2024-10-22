import sys

import sys
import re

def update_version_in_gradle(file_path, new_version):
    with open(file_path, 'r') as file:
        content = file.read()

    # Use regex to find and replace the version value
    updated_content = re.sub(r'version\s*=\s*"[0-9.]+-SNAPSHOT"', f'version = "{new_version}"', content)

    with open(file_path, 'w') as file:
        file.write(updated_content)

    print("File updated successfully!")

if len(sys.argv) > 1:
    new_version = sys.argv[1]
    file_path = 'build.gradle.kts'
    update_version_in_gradle(file_path, new_version)
else:
    print("No new version provided. To update the version, pass the new version as a command-line argument.")
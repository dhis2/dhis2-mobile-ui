package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputFileResource
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource

@Composable
fun InputFileResourceScreen() {
    ColumnComponentContainer(title = ActionInputs.INPUT_FILE_RESOURCE.label) {
        var currentFileName = "filename.extension"
        var currentFileWeight = "524kb"
        val currentFileName2 = "filename.extension"
        val currentFileWeight2 = "524kb"

        var inputFileResourceState by remember { mutableStateOf(UploadFileState.ADD) }

        ColumnComponentItemContainer("Default state") {
            InputFileResource(
                title = "Label",
                buttonText = provideStringResource("add_file"),
                fileName = currentFileName,
                fileWeight = currentFileWeight,
                uploadFileState = inputFileResourceState,
                onSelectFile = {
                    inputFileResourceState = UploadFileState.LOADED
                },
                onUploadFile = {},
                onClear = {
                    inputFileResourceState = UploadFileState.ADD
                },
            )
        }

        ColumnComponentItemContainer("Selected state") {
            InputFileResource(
                title = "Label",
                buttonText = provideStringResource("add_file"),
                fileName = currentFileName2,
                fileWeight = currentFileWeight2,
                uploadFileState = UploadFileState.LOADED,
                onSelectFile = {},
                onUploadFile = {},
            )
        }

        ColumnComponentItemContainer("Loading state") {
            InputFileResource(
                title = "Label",
                buttonText = provideStringResource("add_file"),
                fileName = currentFileName,
                fileWeight = currentFileWeight,
                uploadFileState = UploadFileState.UPLOADING,
                inputShellState = InputShellState.FOCUSED,
                onSelectFile = {},
                onUploadFile = {},
            )
        }

        ColumnComponentItemContainer("Disabled state") {
            InputFileResource(
                title = "Label",
                buttonText = provideStringResource("add_file"),
                fileName = currentFileName,
                fileWeight = currentFileWeight,
                inputShellState = InputShellState.DISABLED,
                onSelectFile = {
                    currentFileName = "file"
                    currentFileWeight = "weight"
                },
                onUploadFile = {},
            )
        }

        ColumnComponentItemContainer("Disabled state with selected file") {
            InputFileResource(
                title = "Label",
                buttonText = provideStringResource("add_file"),
                fileName = currentFileName,
                fileWeight = currentFileWeight,
                inputShellState = InputShellState.DISABLED,
                uploadFileState = UploadFileState.LOADED,
                onSelectFile = {
                    currentFileName = "file"
                    currentFileWeight = "weight"
                },
                onUploadFile = {},
            )
        }
    }
}

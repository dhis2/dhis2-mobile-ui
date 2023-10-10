package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputFileResource
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource

@Composable
fun InputFileResourceScreen() {
    ColumnComponentContainer(
        title = "Input File Component",
    ) {
        val currentFileName: MutableState<String?> =
            mutableStateOf("filename.extension")
        val currentFileWeight: MutableState<String?> =
            mutableStateOf("524kb")
        val currentFileName2: MutableState<String?> =
            mutableStateOf("filename.extension")
        val currentFileWeight2: MutableState<String?> =
            mutableStateOf("524kb")

        InputFileResource(
            title = "Label",
            buttonText = provideStringResource("add_file"),
            fileName = currentFileName,
            fileWeight = currentFileWeight,
            onSelectFile = {
                currentFileName.value = "file"
                currentFileWeight.value = "weight"
            },
            onUploadFile = {},
        )
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
}

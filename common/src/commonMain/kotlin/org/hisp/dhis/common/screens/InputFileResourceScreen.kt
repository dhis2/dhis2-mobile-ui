package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputFileResource
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState

@Composable
fun InputFileResourceScreen() {
    ColumnComponentContainer {
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
            fileName = currentFileName,
            fileWeight = currentFileWeight,
            uploadFileState = UploadFileState.UPLOADING,
            onSelectFile = {},
            onUploadFile = {},
        )
        InputFileResource(
            title = "Label",
            fileName = currentFileName2,
            fileWeight = currentFileWeight2,
            uploadFileState = UploadFileState.LOADED,
            onSelectFile = {},
            onUploadFile = {},
        )
    }
}

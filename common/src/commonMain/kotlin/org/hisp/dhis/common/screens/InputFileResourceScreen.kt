package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputFileResource
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import kotlin.coroutines.coroutineContext

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
        val inputFileState = mutableStateOf(UploadFileState.LOADED)

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
            uploadFileState = mutableStateOf(UploadFileState.UPLOADING),
            onSelectFile = {},
            onUploadFile = {},
        )
        InputFileResource(
            title = "Label",
            buttonText = provideStringResource("add_file"),
            fileName = currentFileName2,
            fileWeight = currentFileWeight2,
            uploadFileState = inputFileState,
            onSelectFile = {},
            onUploadFile = {
                delay(3000)
                inputFileState.value = UploadFileState.ADD
            },
        )
    }
}

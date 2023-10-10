package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.ADD
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.LOADED
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.UPLOADING
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

const val inputFileTestTag = "INPUT_FILE_RESOURCE_"
const val clearButtonTestTag = "CLEAR_BUTTON"
const val uploadButtonTestTag = "UPLOAD_BUTTON"
const val addButtonTestTag = "ADD_BUTTON"
const val progressIndicatorTestTag = "PROGRESS_INDICATOR"
const val uploadHelperTestTag = "UPLOAD_HELPER"


@Composable
fun InputFileResource(
    title: String,
    buttonText: String,
    fileName: MutableState<String?> = mutableStateOf(null),
    fileWeight: MutableState<String?> = mutableStateOf(null),
    onSelectFile: () -> Unit,
    onUploadFile: suspend () -> Unit,
    onClear: () -> Unit = {},
    uploadFileState: MutableState<UploadFileState> = mutableStateOf(ADD),
) {

    var currentState by remember {
        uploadFileState
    }
    val scope = rememberCoroutineScope()

    val primaryButton: @Composable (() -> Unit)? = if (currentState == LOADED) {
        {
            IconButton(
                modifier = Modifier.testTag(inputFileTestTag + clearButtonTestTag),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Icon Button",
                    )
                },
                onClick = {
                    uploadFileState.value = ADD
                    currentState = ADD
                    onClear.invoke()
                },
            )
        }
    } else {
        null
    }

    val secondaryButton: @Composable (() -> Unit)? =
        if (currentState == LOADED) {
            {
                SquareIconButton(
                    modifier = Modifier.testTag(inputFileTestTag + uploadButtonTestTag),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.FileUpload,
                            contentDescription = "Upload Icon Button",
                        )
                    },
                ) {
                    scope.launch {
                        uploadFileState.value = UPLOADING
                        currentState = UPLOADING
                        onUploadFile.invoke()
                    }
                }
            }
        } else {
            null
        }

    InputShell(
        title,
        state = if (currentState == UPLOADING) InputShellState.FOCUSED else InputShellState.UNFOCUSED,
        inputField = {
            when (currentState) {
                ADD -> {
                    Button(
                        modifier = Modifier
                            .testTag(inputFileTestTag + addButtonTestTag)
                            .padding(end = Spacing.Spacing16)
                            .fillMaxWidth(),
                        style = ButtonStyle.ELEVATED,
                        text = buttonText,
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.FileUpload,
                                contentDescription = "Upload Icon Button",
                            )
                        },
                    ) {
                        uploadFileState.value = LOADED
                        currentState = LOADED
                        onSelectFile.invoke()

                    }
                }
                UPLOADING -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        ProgressIndicator(
                            modifier = Modifier.testTag(inputFileTestTag + progressIndicatorTestTag),
                            type = ProgressIndicatorType.CIRCULAR
                        )
                    }
                }
                LOADED -> {
                    fileName.value?.let {
                        BasicTextField(
                            modifier = Modifier.testTag(inputFileTestTag + uploadHelperTestTag),
                            helper = fileWeight.value,
                            helperStyle = InputStyle.WITH_HELPER_AFTER,
                            inputText = it,
                            onInputChanged = { }
                        )
                    }
                }
            }
        },
        primaryButton = primaryButton,
        secondaryButton = secondaryButton,
    )
}

enum class UploadFileState {
    ADD,
    UPLOADING,
    LOADED,
}

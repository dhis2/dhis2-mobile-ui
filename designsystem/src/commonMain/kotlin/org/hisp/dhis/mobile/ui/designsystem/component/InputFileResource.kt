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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.LOADED
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.UPLOADING
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.ADD
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun InputFileResource(
    title: String,
    fileName: String? = null,
    fileWeight: String? = null,
    onSelectFile: () -> Unit,
    onUploadFile: () -> Unit,
    onClear: () -> Unit = {},
    uploadFileState: UploadFileState = ADD
) {
    var currentState by remember {
        mutableStateOf(uploadFileState)
    }

    var primaryButton: @Composable (() -> Unit)? = if (currentState == LOADED) {
        {
            IconButton(
                modifier = Modifier.testTag("INPUT_FILE_RESOURCE_CLEAR_BUTTON"),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Icon Button",
                    )
                },
                onClick = {
                    currentState = ADD
                    onClear.invoke()
                },
            )
        }
    } else {
        null
    }

    InputShell(
        title,
        state = if(currentState == UPLOADING) InputShellState.FOCUSED else InputShellState.UNFOCUSED,
        inputField = {
            when(currentState) {
                ADD -> {
                    Button(
                        modifier = Modifier.padding(end = Spacing.Spacing16).fillMaxWidth(),
                        style = ButtonStyle.ELEVATED,
                        text = "Add file",
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.FileUpload,
                                contentDescription = "Upload Icon Button",
                            )
                        }
                    ) {
                        currentState = LOADED
                        onSelectFile.invoke()
                    }
                }
                UPLOADING -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ProgressIndicator(type = ProgressIndicatorType.CIRCULAR)
                    }
                }
                LOADED -> {
                    Row() {
                        fileName?.let { BasicTextField(helper = fileWeight, helperStyle = InputStyle.WITH_HELPER_AFTER, inputText = it, onInputChanged = { }) }
                    }
                }
            }
        },
        primaryButton = primaryButton,
        secondaryButton = {
            if (currentState == LOADED) {
                SquareIconButton(
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.FileUpload,
                            contentDescription = "Upload Icon Button",
                        )
                    }
                ) {
                    currentState = UPLOADING
                    onUploadFile.invoke()
                }
            }
        },
    )
}

enum class UploadFileState {
    ADD,
    UPLOADING,
    LOADED
}



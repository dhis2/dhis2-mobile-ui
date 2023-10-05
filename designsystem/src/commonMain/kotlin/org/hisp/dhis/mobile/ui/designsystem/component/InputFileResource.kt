package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.LOADED
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.UPLOADING
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.ADD
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun InputFileResource(
    title: String,
    fileName: String? = null,
    fileWeight: String? = null,
    uploadFileState: UploadFileState = ADD
) {
    var currentState by remember {
        mutableStateOf(uploadFileState)
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
                    ) { currentState = LOADED }
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
                    fileName?.let { BasicTextField(helper = fileWeight, helperStyle = InputStyle.WITH_HELPER_AFTER, inputText = it, onInputChanged = { }) }
                }
            }
        }
    )
}

enum class UploadFileState {
    ADD,
    UPLOADING,
    LOADED
}



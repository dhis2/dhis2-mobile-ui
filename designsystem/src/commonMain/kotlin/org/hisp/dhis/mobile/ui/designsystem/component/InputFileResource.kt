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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.ADD
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.LOADED
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.UPLOADING
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

const val INPUT_FILE_TEST_TAG = "INPUT_FILE_RESOURCE_"
const val CLEAR_BUTTON_TEST_TAG = "CLEAR_BUTTON"
const val UPLOAD_BUTTON_TEST_TAG = "UPLOAD_BUTTON"
const val ADD_BUTTON_TEST_TAG = "ADD_BUTTON"
const val PROGRESS_INDICATOR_TEST_TAG = "PROGRESS_INDICATOR"
const val UPLOAD_HELPER_TEST_TAG = "UPLOAD_HELPER"
const val SUPPORTING_TEXT_TEST_TAG = "SUPPORTING_TEXT"

@Composable
fun InputFileResource(
    title: String,
    buttonText: String,
    fileName: MutableState<String?> = mutableStateOf(null),
    fileWeight: MutableState<String?> = mutableStateOf(null),
    onSelectFile: () -> Unit,
    onUploadFile: () -> Unit,
    onClear: () -> Unit = {},
    uploadFileState: UploadFileState = ADD,
    inputShellState: InputShellState = InputShellState.UNFOCUSED,
    supportingText: List<SupportingTextData>? = null,
    modifier: Modifier = Modifier,
) {
    var currentState by remember {
        mutableStateOf(uploadFileState)
    }

    val primaryButton: @Composable (() -> Unit)? = if (currentState == LOADED) {
        {
            IconButton(
                modifier = Modifier.testTag(INPUT_FILE_TEST_TAG + CLEAR_BUTTON_TEST_TAG),
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

    val secondaryButton: @Composable (() -> Unit)? =
        if (currentState == LOADED) {
            {
                SquareIconButton(
                    modifier = Modifier.testTag(INPUT_FILE_TEST_TAG + UPLOAD_BUTTON_TEST_TAG),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.FileUpload,
                            contentDescription = "Upload Icon Button",
                        )
                    },
                ) {
                    currentState = UPLOADING
                    onUploadFile.invoke()
                }
            }
        } else {
            null
        }

    InputShell(
        title,
        state = inputShellState,
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = modifier.testTag(INPUT_FILE_TEST_TAG + SUPPORTING_TEXT_TEST_TAG),
                )
            }
        },
        inputField = {
            when (currentState) {
                ADD -> {
                    Button(
                        modifier = Modifier
                            .testTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG)
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
                            modifier = Modifier.testTag(INPUT_FILE_TEST_TAG + PROGRESS_INDICATOR_TEST_TAG),
                            type = ProgressIndicatorType.CIRCULAR,
                        )
                    }
                }
                LOADED -> {
                    fileName.value?.let {
                        BasicTextField(
                            modifier = Modifier.testTag(INPUT_FILE_TEST_TAG + UPLOAD_HELPER_TEST_TAG),
                            helper = fileWeight.value,
                            helperStyle = InputStyle.WITH_HELPER_AFTER,
                            inputText = it,
                            onInputChanged = { },
                        )
                    }
                }
            }
        },
        primaryButton = primaryButton,
        secondaryButton = secondaryButton,
        modifier = modifier,
    )
}

enum class UploadFileState {
    ADD,
    UPLOADING,
    LOADED,
}
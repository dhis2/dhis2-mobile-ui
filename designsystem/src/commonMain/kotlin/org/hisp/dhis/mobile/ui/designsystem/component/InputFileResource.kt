package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.ADD
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.LOADED
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState.UPLOADING
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

const val INPUT_FILE_TEST_TAG = "INPUT_FILE_RESOURCE_"
const val CLEAR_BUTTON_TEST_TAG = "CLEAR_BUTTON"
const val UPLOAD_BUTTON_TEST_TAG = "UPLOAD_BUTTON"
const val ADD_BUTTON_TEST_TAG = "ADD_BUTTON"
const val PROGRESS_INDICATOR_TEST_TAG = "PROGRESS_INDICATOR"
const val UPLOAD_TEXT_FILE_NAME_TEST_TAG = "UPLOAD_TEXT_FILE_NAME"
const val UPLOAD_TEXT_FILE_WEIGHT_TEST_TAG = "UPLOAD_TEXT_FILE_WEIGHT"
const val SUPPORTING_TEXT_TEST_TAG = "SUPPORTING_TEXT"

@Composable
fun InputFileResource(
    title: String,
    buttonText: String,
    fileName: String? = null,
    fileWeight: String? = null,
    onSelectFile: () -> Unit,
    onUploadFile: () -> Unit,
    onClear: () -> Unit = {},
    uploadFileState: UploadFileState = ADD,
    inputShellState: InputShellState = InputShellState.UNFOCUSED,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    modifier: Modifier = Modifier,
) {
    var currentState by remember(uploadFileState) {
        mutableStateOf(uploadFileState)
    }

    val primaryButton: @Composable (() -> Unit)? = if (currentState == LOADED && inputShellState != InputShellState.DISABLED) {
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
                            imageVector = Icons.Outlined.FileDownload,
                            contentDescription = "Download Icon Button",
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
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_FILE_RESOURCE_LEGEND"))
            }
        }, supportingText = {
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
                    ButtonBlock(
                        primaryButton = {
                            Button(
                                enabled = inputShellState != InputShellState.DISABLED,
                                modifier = Modifier
                                    .testTag(INPUT_FILE_TEST_TAG + ADD_BUTTON_TEST_TAG)
                                    .padding(end = Spacing.Spacing16)
                                    .fillMaxWidth(),
                                style = ButtonStyle.KEYBOARDKEY,
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
                        },
                    )
                }
                UPLOADING -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            Modifier
                                .padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8)
                                .size(Spacing.Spacing48),
                        ) {
                            ProgressIndicator(
                                modifier = Modifier.testTag(INPUT_FILE_TEST_TAG + PROGRESS_INDICATOR_TEST_TAG),
                                type = ProgressIndicatorType.CIRCULAR,
                            )
                        }
                    }
                }
                LOADED -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        fileName?.let {
                            Text(
                                text = it,
                                color = if (inputShellState != InputShellState.DISABLED) TextColor.OnSurface else TextColor.OnDisabledSurface,
                                maxLines = 1,
                                modifier = Modifier.testTag(INPUT_FILE_TEST_TAG + UPLOAD_TEXT_FILE_NAME_TEST_TAG),
                            )
                        }
                        fileWeight?.let {
                            Text(
                                text = " $it",
                                color = TextColor.OnDisabledSurface,
                                maxLines = 1,
                                modifier = Modifier.testTag(INPUT_FILE_TEST_TAG + UPLOAD_TEXT_FILE_WEIGHT_TEST_TAG),
                            )
                        }
                    }
                }
            }
        },
        primaryButton = primaryButton,
        secondaryButton = secondaryButton,
        isRequiredField = isRequired,
        modifier = modifier,
    )
}

enum class UploadFileState {
    ADD,
    UPLOADING,
    LOADED,
}

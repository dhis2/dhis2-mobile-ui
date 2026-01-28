package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 BasicInputImage. Wraps DHIS · [ImageBlock].
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param inputStyle: [InputStyle] Manages the InputShell style.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param uploadState: controls whether the image is added, loading, or need to be added.
 * @param downloadButtonVisible: controls whether the download button is visible or not.
 * @param isRequired: controls whether the field is mandatory or not.
 * @param load: to load an image stored in the resource, device memory or from network
 * we can use loadPainter, loadImageBitmap, loadSvgPainter or loadXmlImageVector.
 * @param painterFor: is a composable function which controls how to paint the load param.
 * @param testTag: optional tag for testing purposes.
 * @param addButtonText: controls the text to be shown for the add button.
 * @param addButtonIcon: controls the icon to be shown for the add button.
 * @param onDownloadButtonClick: callback to when download button is clicked.
 * @param onShareButtonClick: callback to when share button is clicked.
 * @param onResetButtonClicked: callback to when reset button is clicked.
 * @param onAddButtonClicked: callback to when add button is clicked.
 * @param modifier: optional modifier.
 */
@Composable
internal fun <T> BasicInputImage(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    uploadState: UploadState = UploadState.ADD,
    downloadButtonVisible: Boolean = true,
    isRequired: Boolean = false,
    load: suspend () -> T,
    painterFor: (@Composable (T) -> Painter)? = null,
    testTag: String = "",
    addButtonText: String,
    addButtonIcon: ImageVector,
    modifier: Modifier = Modifier,
    onDownloadButtonClick: () -> Unit,
    onShareButtonClick: () -> Unit,
    onResetButtonClicked: () -> Unit,
    onAddButtonClicked: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    InputShell(
        modifier = modifier.testTag("INPUT_$testTag").focusRequester(focusRequester),
        title = title,
        state = state,
        isRequiredField = isRequired,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_" + testTag + "_LEGEND"))
            }
        },
        supportingText = supportingText,
        supportingTextTestTag = "INPUT_" + testTag + "_SUPPORTING_TEXT",
        inputField = {
            when (uploadState) {
                UploadState.ADD -> {
                    ButtonBlock(
                        modifier = Modifier.padding(vertical = Spacing.Spacing8),
                        primaryButton = {
                            Button(
                                enabled = state != InputShellState.DISABLED,
                                style = ButtonStyle.KEYBOARDKEY,
                                text = addButtonText,
                                icon = {
                                    Icon(
                                        imageVector = addButtonIcon,
                                        contentDescription = "Icon Button",
                                        tint = if (state != InputShellState.DISABLED) SurfaceColor.Primary else TextColor.OnDisabledSurface,
                                    )
                                },
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(end = Spacing.Spacing16)
                                        .testTag("INPUT_" + testTag + "_ADD_BUTTON"),
                            ) {
                                onAddButtonClicked.invoke()
                                focusRequester.requestFocus()
                            }
                        },
                    )
                }

                UploadState.UPLOADING -> {
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8).size(InternalSizeValues.Size48),
                            contentAlignment = Alignment.Center,
                        ) {
                            ProgressIndicator(
                                type = ProgressIndicatorType.CIRCULAR,
                            )
                        }
                    }
                }

                UploadState.LOADED -> {
                    if (painterFor != null) {
                        ImageBlock(
                            title = title,
                            load = load,
                            painterFor = painterFor,
                            onDownloadButtonClick = {
                                onDownloadButtonClick.invoke()
                                focusRequester.requestFocus()
                            },
                            onShareButtonClick = onShareButtonClick,
                            downloadButtonVisible = downloadButtonVisible,
                            modifier =
                                Modifier.padding(
                                    end =
                                        if (state == InputShellState.DISABLED) {
                                            Spacing.Spacing12
                                        } else {
                                            Spacing.Spacing0
                                        },
                                ),
                        )
                    }
                }
            }
        },
        primaryButton =
            if (
                uploadState == UploadState.LOADED &&
                state != InputShellState.DISABLED
            ) {
                {
                    IconButton(
                        modifier = Modifier.testTag("INPUT_" + testTag + "_RESET_BUTTON"),
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Cancel,
                                contentDescription = "Reset Button",
                            )
                        },
                        onClick = onResetButtonClicked,
                    )
                }
            } else {
                null
            },
        inputStyle = inputStyle,
    )
}

enum class UploadState {
    ADD,
    UPLOADING,
    LOADED,
}

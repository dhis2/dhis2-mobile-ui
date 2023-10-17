package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Draw
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Input image. Wraps DHIS · [ImageBlock].
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param supportingText is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param uploadState controls whether the image is added, loading, or need to be added.
 * @param downloadButtonVisible controls whether the download button is visible or not
 * @param isRequired controls whether the field is mandatory or not
 * @param load to load an image stored in the resource, device memory or from network
 * we can use loadPainter, loadImageBitmap, loadSvgPainter or loadXmlImageVector
 * @param painterFor is a composable function which controls how to paint the load param,
 * @param addButtonText controls the text to be shown for the add button
 * @param modifier allows a modifier to be passed externally
 * @param onDownloadButtonClick callback to when download button is clicked
 * @param onResetButtonClicked callback to when reset button is clicked
 * @param onAddButtonClicked callback to when add button is clicked
 */
@Composable
internal fun <T> InputImage(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    uploadState: UploadState = UploadState.ADD,
    downloadButtonVisible: Boolean = true,
    isRequired: Boolean = false,
    load: suspend () -> T,
    painterFor: (@Composable (T) -> Painter)? = null,
    testTag: String = "",
    addButtonText: String,
    modifier: Modifier = Modifier,
    onDownloadButtonClick: () -> Unit,
    onResetButtonClicked: () -> Unit,
    onAddButtonClicked: () -> Unit,
) {
    InputShell(
        modifier = modifier.testTag("INPUT_$testTag"),
        title = title,
        state = state,
        isRequiredField = isRequired,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_" + testTag + "_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = modifier.testTag("INPUT_" + testTag + "_SUPPORTING_TEXT"),
                )
            }
        },
        inputField = {
            when (uploadState) {
                UploadState.ADD -> {
                    Button(
                        enabled = state != InputShellState.DISABLED,
                        style = ButtonStyle.KEYBOARDKEY,
                        text = addButtonText,
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Draw,
                                contentDescription = "Icon Button",
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = Spacing.Spacing12)
                            .testTag("INPUT_" + testTag + "_ADD_BUTTON"),
                    ) {
                        onAddButtonClicked.invoke()
                    }
                }

                UploadState.UPLOADING -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Spacing.Spacing4),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        ProgressIndicator(
                            type = ProgressIndicatorType.CIRCULAR,
                        )
                    }
                }

                UploadState.LOADED -> {
                    if (painterFor != null) {
                        ImageBlock(
                            load = load,
                            painterFor = painterFor,
                            onClick = onDownloadButtonClick,
                            downloadButtonVisible = downloadButtonVisible,
                            modifier = Modifier.padding(
                                end = if (state == InputShellState.DISABLED) {
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
        primaryButton = if (
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
    )
}

enum class UploadState {
    ADD,
    UPLOADING,
    LOADED,
}

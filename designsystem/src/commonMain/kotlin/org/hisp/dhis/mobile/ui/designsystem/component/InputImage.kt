package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.ui.designsystem.component.internal.UploadState
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun <T> InputImage(
    inputShellState: InputShellState = InputShellState.UNFOCUSED,
    title: String,
    buttonText: String,
    state: UploadState = UploadState.ADD,
    onSelectFile: () -> Unit,
    onLoadImageFile: suspend () -> T,
    onPainterFor: @Composable (T) -> Painter,
    onUploadImage: () -> Unit,
    onClear: () -> Unit = {},
    supportingText: List<SupportingTextData>? = null,
    modifier: Modifier = Modifier
) {
    var currentState by remember {
        mutableStateOf(state)
    }

    val primaryButton: @Composable (() -> Unit)? = if (currentState == UploadState.LOADED) {
        {
            IconButton(
                enabled = inputShellState != InputShellState.DISABLED,
                modifier = Modifier,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Icon Button",
                    )
                },
                onClick = {
                    currentState = UploadState.ADD
                    onClear.invoke()
                },
            )
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
                    modifier = modifier,
                )
            }
        },
        inputField = {
            when (currentState) {
                UploadState.ADD -> {
                    Button(
                        enabled = inputShellState != InputShellState.DISABLED,
                        modifier = Modifier
                            //.testTag()
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
                        currentState = UploadState.LOADED
                        onSelectFile.invoke()
                    }
                }
                UploadState.UPLOADING -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            Modifier
                                .padding(top = Spacing.Spacing4, bottom = Spacing.Spacing4)
                                .size(Spacing.Spacing48),
                        ) {
                            ProgressIndicator(
                                modifier = Modifier,
                                type = ProgressIndicatorType.CIRCULAR,
                            )
                        }
                    }
                }
                UploadState.LOADED -> {
                    ImageBlock(
                        load = { onLoadImageFile.invoke() },
                        painterFor = { onPainterFor.invoke(it) },
                        onClick = {
                            currentState = UploadState.UPLOADING
                            onUploadImage.invoke() },
                    )
                }
            }
        },
        primaryButton = primaryButton,
        modifier = modifier,
    )
}
package org.hisp.dhis.mobile.ui.designsystem.component.internal.signature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetShell
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.internal.dashedBorder
import org.hisp.dhis.mobile.ui.designsystem.component.state.BottomSheetShellDefaults
import org.hisp.dhis.mobile.ui.designsystem.component.state.BottomSheetShellUIState
import org.hisp.dhis.mobile.ui.designsystem.resource.Signature
import org.hisp.dhis.mobile.ui.designsystem.resource.SignatureCanvas
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SignatureBottomSheet(
    title: String,
    drawHereText: String = provideStringResource("draw_here"),
    resetButtonText: String = provideStringResource("reset"),
    doneButtonText: String = provideStringResource("done"),
    windowInsets: @Composable () -> WindowInsets = { BottomSheetShellDefaults.windowInsets() },
    onDismiss: () -> Unit,
    onSave: (ImageBitmap) -> Unit,
) {
    var signature: Signature? = null
    var isSigning by rememberSaveable { mutableStateOf(false) }

    BottomSheetShell(
        uiState =
            BottomSheetShellUIState(
                title = title,
                showTopSectionDivider = false,
            ),
        modifier = Modifier.testTag("INPUT_SIGNATURE_BOTTOM_SHEET"),
        content = {
            Box(
                modifier =
                    Modifier
                        .dashedBorder(
                            Border.Thin,
                            Color.Ash600,
                            Radius.S,
                        ).height(200.dp),
            ) {
                SignatureCanvas(
                    onReady = {
                        signature = it
                    },
                    onStartedSigning = {
                        isSigning = true
                    },
                )

                Text(
                    modifier =
                        Modifier
                            .padding(Spacing.Spacing8)
                            .align(Alignment.TopEnd)
                            .background(
                                SurfaceColor.Surface,
                                RoundedCornerShape(
                                    topStart = Radius.S,
                                    topEnd = Radius.S,
                                    bottomStart = Radius.NoRounding,
                                    bottomEnd = Radius.S,
                                ),
                            ).padding(Spacing.Spacing8, Spacing.Spacing4),
                    text = drawHereText,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextColor.OnSurfaceLight,
                )
            }
        },
        windowInsets = windowInsets,
        buttonBlock = {
            ButtonBlock(
                modifier = Modifier.padding(BottomSheetShellDefaults.buttonBlockPaddings()),
                primaryButton = {
                    Button(
                        style = ButtonStyle.OUTLINED,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.RestartAlt,
                                contentDescription = "Reset Button",
                            )
                        },
                        enabled = isSigning,
                        text = resetButtonText,
                        onClick = {
                            signature?.clear()
                            isSigning = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                secondaryButton = {
                    Button(
                        style = ButtonStyle.FILLED,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done Button",
                            )
                        },
                        enabled = isSigning,
                        text = doneButtonText,
                        onClick = {
                            signature?.getBitmap()?.let { onSave.invoke(it) }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
            )
        },
        onDismiss = onDismiss,
    )
}

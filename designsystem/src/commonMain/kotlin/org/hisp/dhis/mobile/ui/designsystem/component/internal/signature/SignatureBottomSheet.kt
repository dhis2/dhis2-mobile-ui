package org.hisp.dhis.mobile.ui.designsystem.component.internal.signature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.RestartAlt
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetShell
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.resource.createBitmap
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.utils.dashedBorder

@Composable
internal fun SignatureBottomSheet(
    title: String,
    drawHereText: String = provideStringResource("draw_here"),
    resetButtonText: String = provideStringResource("reset"),
    doneButtonText: String = provideStringResource("done"),
    onDismiss: () -> Unit,
    onSave: (ImageBitmap) -> Unit,
) {
    val drawing = rememberSaveable { mutableStateOf<Offset?>(null) }
    var capturingViewBounds by rememberSaveable { mutableStateOf<Rect?>(null) }
    var capturing by rememberSaveable { mutableStateOf(false) }
    BottomSheetShell(
        modifier = Modifier.testTag("INPUT_SIGNATURE_BOTTOM_SHEET"),
        title = title,
        showSectionDivider = false,
        content = {
            Box {
                SignatureCanvas(
                    modifier = Modifier
                        .dashedBorder(
                            Border.Thin,
                            Color.Ash600,
                            Radius.S,
                        )
                        .height(200.dp)
                        .onGloballyPositioned {
                            capturingViewBounds = it.boundsInRoot()
                        },
                    drawing = drawing,
                )
                Text(
                    modifier = Modifier
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
                        )
                        .padding(Spacing.Spacing8, Spacing.Spacing4),
                    text = drawHereText,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextColor.OnSurfaceLight,
                )
            }
        },
        onDismiss = onDismiss,
        buttonBlock = {
            ButtonBlock(
                primaryButton = {
                    Button(
                        style = ButtonStyle.OUTLINED,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.RestartAlt,
                                contentDescription = "Reset Button",
                            )
                        },
                        enabled = drawing.value != null,
                        text = resetButtonText,
                        onClick = {
                            drawing.value = null
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
                        enabled = true,
                        text = doneButtonText,
                        onClick = {
                            capturing = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
            )
        },
    )

    if (capturing) {
        capturingViewBounds
            ?.createBitmap()
            ?.let { onSave(it) }
    }
}


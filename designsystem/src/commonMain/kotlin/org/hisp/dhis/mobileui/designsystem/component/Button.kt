package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Radius
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

/**
 * DHIS2 button with generic icon slot.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param style Controls style of the button. Will be OUTLINED by default, but can be
 * FILLED, TEXT, TONAL, ELEVATED or KEYBOARDKEY
 * @param text The text to display within.
 * @param icon The button icon content.
 * @param onClick Will be called when the user clicks the button.
 */
@Composable
fun Button(
    enabled: Boolean = true,
    style: ButtonStyle = ButtonStyle.OUTLINED,
    text: String,
    icon: @Composable
    (() -> Unit)? = null,
    onClick: () -> Unit
) {
    val paddingValues = getPaddingValues(icon != null)

    when (style) {
        ButtonStyle.FILLED -> {
            val textColor = if (enabled) TextColor.OnPrimary else TextColor.OnDisabledSurface

            Button(
                onClick = { onClick() },
                modifier = Modifier,
                enabled = enabled,
                colors = ButtonDefaults.filledTonalButtonColors(SurfaceColor.Primary, TextColor.OnPrimary, SurfaceColor.DisabledSurface, TextColor.OnDisabledSurface),
                shape = ButtonDefaults.outlinedShape,
                contentPadding = paddingValues
            ) {
                if (icon != null) {
                    icon()
                    Spacer(Modifier.size(Spacing.Spacing8))
                }

                Text(text, color = textColor, textAlign = TextAlign.Center)
            }
        }
        ButtonStyle.TEXT -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface

            Button(
                onClick = { onClick() },
                modifier = Modifier,
                enabled = enabled,
                colors = ButtonDefaults.filledTonalButtonColors(Color.Transparent, SurfaceColor.Primary, Color.Transparent, TextColor.OnDisabledSurface),
                shape = ButtonDefaults.outlinedShape,
                contentPadding = paddingValues
            ) {
                if (icon != null) {
                    icon()
                    Spacer(Modifier.size(Spacing.Spacing8))
                }

                Text(text, color = textColor, textAlign = TextAlign.Center)
            }
        }
        ButtonStyle.ELEVATED -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface

            ElevatedButton(
                onClick = { onClick() },
                modifier = Modifier,
                elevation = ButtonDefaults.elevatedButtonElevation(),
                enabled = enabled,
                colors = ButtonDefaults.filledTonalButtonColors(SurfaceColor.ContainerLow, SurfaceColor.Primary, SurfaceColor.DisabledSurface, TextColor.OnDisabledSurface),
                shape = ButtonDefaults.outlinedShape,
                contentPadding = paddingValues
            ) {
                if (icon != null) {
                    icon()
                    Spacer(Modifier.size(Spacing.Spacing8))
                }

                Text(text, color = textColor, textAlign = TextAlign.Center)
            }
        }
        ButtonStyle.TONAL -> {
            val textColor = if (enabled) TextColor.OnPrimaryContainer else TextColor.OnDisabledSurface

            Button(
                onClick = { onClick() },
                modifier = Modifier,
                enabled = enabled,
                colors = ButtonDefaults.filledTonalButtonColors(SurfaceColor.PrimaryContainer, TextColor.OnPrimaryContainer, SurfaceColor.DisabledSurface, TextColor.OnDisabledSurface),
                shape = ButtonDefaults.outlinedShape,
                contentPadding = paddingValues
            ) {
                if (icon != null) {
                    icon()
                    Spacer(Modifier.size(Spacing.Spacing8))
                }

                Text(text, color = textColor, textAlign = TextAlign.Center)
            }
        }
        ButtonStyle.KEYBOARDKEY -> {
            // TODO refactor when Icon Buttons issue branch is merged
            val shadowSize = if (icon != null) 101.dp else 83.dp
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface
            val shadowColor = if (enabled) SurfaceColor.ContainerHighest else Color.Transparent
            Button(
                onClick = { onClick() },
                modifier = Modifier.shadow(shadowColor, Radius.Full),
                enabled = enabled,
                colors = ButtonDefaults.filledTonalButtonColors(SurfaceColor.Container, SurfaceColor.Primary, SurfaceColor.DisabledSurface, TextColor.OnDisabledSurface),
                shape = ButtonDefaults.outlinedShape,
                contentPadding = paddingValues
            ) {
                if (icon != null) {
                    icon()
                    Spacer(Modifier.size(Spacing.Spacing8))
                }

                Text(text, color = textColor, textAlign = TextAlign.Center)
            }
        }
        else -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface
            OutlinedButton(
                onClick = { onClick() },
                enabled = enabled,
                shape = ButtonDefaults.outlinedShape,
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent, SurfaceColor.Primary, Color.Transparent, TextColor.OnDisabledSurface),
                border = BorderStroke(1.dp, Outline.Dark),
                contentPadding = paddingValues
            ) {
                if (icon != null) {
                    icon()
                    Spacer(Modifier.size(Spacing.Spacing8))
                }
                Text(text, color = textColor, textAlign = TextAlign.Center)
            }
        }
    }
}

private fun getPaddingValues(hasIcon: Boolean): PaddingValues {
    val buttonWithIconPaddingValues = PaddingValues(Spacing.Spacing16, Spacing.Spacing10, Spacing.Spacing24, Spacing.Spacing10)
    val buttonWithoutIconPaddingValues = PaddingValues(Spacing.Spacing24, Spacing.Spacing10, Spacing.Spacing24, Spacing.Spacing10)

    return if (hasIcon) buttonWithIconPaddingValues else buttonWithoutIconPaddingValues
}
private const val DEFAULT_SPREAD = 0.5f
private const val DEFAULT_SIZE = 20
private const val DEFAULT_PADDING = 0.5f

// TODO remove and use external class after IconButtons issue has been merged
internal fun Modifier.shadow(
    color: Color,
    borderRadius: Dp = 0.dp,
    spread: Dp = DEFAULT_SPREAD.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = DEFAULT_PADDING
            val topPixel = 15f
            val rightPixel = (this.size.width - DEFAULT_PADDING.dp.toPx())
            val bottomPixel = (this.size.height + 0.5f.dp.toPx())

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)

enum class ButtonStyle {
    FILLED,
    OUTLINED,
    TEXT,
    ELEVATED,
    TONAL,
    KEYBOARDKEY
}

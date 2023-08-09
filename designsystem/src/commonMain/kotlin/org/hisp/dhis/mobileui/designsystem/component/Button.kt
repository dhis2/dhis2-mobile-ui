package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Radius
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor
import org.hisp.dhis.mobileui.designsystem.theme.buttonShadow

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
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface
            val shadowColor = if (enabled) SurfaceColor.ContainerHighest else Color.Transparent
            Button(
                onClick = { onClick() },
                modifier = Modifier.buttonShadow(shadowColor, Radius.Full, icon != null),
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

enum class ButtonStyle {
    FILLED,
    OUTLINED,
    TEXT,
    ELEVATED,
    TONAL,
    KEYBOARDKEY
}

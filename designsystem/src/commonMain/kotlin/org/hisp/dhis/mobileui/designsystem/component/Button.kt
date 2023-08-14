package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Radius
import org.hisp.dhis.mobileui.designsystem.theme.Ripple
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

            SimpleButton(
                onClick = { onClick() },
                enabled = enabled,
                buttonColors = ButtonDefaults.filledTonalButtonColors(
                    SurfaceColor.Primary,
                    TextColor.OnPrimary,
                    SurfaceColor.DisabledSurface,
                    TextColor.OnDisabledSurface
                ),
                text = text,
                textColor = textColor,
                icon = icon

            )
        }
        ButtonStyle.TEXT -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface
            SimpleButton(
                onClick = { onClick() },
                enabled = enabled,
                buttonColors = ButtonDefaults.filledTonalButtonColors(
                    Color.Transparent,
                    SurfaceColor.Primary,
                    Color.Transparent,
                    TextColor.OnDisabledSurface
                ),
                text = text,
                textColor = textColor,
                icon = icon
            )
        }
        ButtonStyle.ELEVATED -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface

            ElevatedButton(
                onClick = { onClick() },
                elevation = ButtonDefaults.elevatedButtonElevation(),
                enabled = enabled,
                colors = ButtonDefaults.filledTonalButtonColors(
                    SurfaceColor.ContainerLow,
                    SurfaceColor.Primary,
                    SurfaceColor.DisabledSurface,
                    TextColor.OnDisabledSurface
                ),
                shape = ButtonDefaults.outlinedShape,
                contentPadding = paddingValues
            ) {
                ButtonText(text, textColor, icon)
            }
        }
        ButtonStyle.TONAL -> {
            val textColor = if (enabled) TextColor.OnPrimaryContainer else TextColor.OnDisabledSurface
            CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
                SimpleButton(
                    onClick = { onClick() },
                    enabled = enabled,
                    buttonColors = ButtonDefaults.filledTonalButtonColors(
                        SurfaceColor.PrimaryContainer,
                        TextColor.OnPrimaryContainer,
                        SurfaceColor.DisabledSurface,
                        TextColor.OnDisabledSurface
                    ),
                    text = text,
                    textColor = textColor,
                    icon = icon
                )
            }
        }
        ButtonStyle.KEYBOARDKEY -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface
            val shadowColor = if (enabled) SurfaceColor.ContainerHighest else Color.Transparent

            SimpleButton(
                onClick = { onClick() },
                modifier = Modifier.buttonShadow(shadowColor, Radius.Full, icon != null),
                enabled = enabled,
                buttonColors = ButtonDefaults.filledTonalButtonColors(
                    SurfaceColor.Container,
                    SurfaceColor.Primary,
                    SurfaceColor.DisabledSurface,
                    TextColor.OnDisabledSurface
                ),
                text = text,
                textColor = textColor,
                icon = icon
            )
        }
        ButtonStyle.OUTLINED -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface
            OutlinedButton(
                onClick = { onClick() },
                enabled = enabled,
                shape = ButtonDefaults.outlinedShape,
                colors = ButtonDefaults.outlinedButtonColors(
                    Color.Transparent,
                    SurfaceColor.Primary,
                    Color.Transparent,
                    TextColor.OnDisabledSurface
                ),
                border = BorderStroke(Spacing.Spacing1, Outline.Dark),
                contentPadding = paddingValues
            ) {
                ButtonText(text, textColor, icon)
            }
        }
    }
}

@Composable
private fun SimpleButton(
    enabled: Boolean = true,
    buttonColors: ButtonColors,
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    icon: @Composable
    (() -> Unit)? = null,
    onClick: () -> Unit
) {
    val paddingValues = getPaddingValues(icon != null)

    Button(
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled,
        colors = buttonColors,
        shape = ButtonDefaults.outlinedShape,
        contentPadding = paddingValues
    ) {
        ButtonText(text, textColor, icon)
    }
}

private fun getPaddingValues(hasIcon: Boolean): PaddingValues {
    val buttonWithIconPaddingValues = PaddingValues(
        Spacing.Spacing16,
        Spacing.Spacing10,
        Spacing.Spacing24,
        Spacing.Spacing10
    )
    val buttonWithoutIconPaddingValues = PaddingValues(
        Spacing.Spacing24,
        Spacing.Spacing10,
        Spacing.Spacing24,
        Spacing.Spacing10
    )

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

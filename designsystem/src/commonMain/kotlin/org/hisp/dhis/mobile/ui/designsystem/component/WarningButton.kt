package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

/**
 * DHIS2 warning button with generic icon slot.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param style Controls style of the button. Will be OUTLINED by default, but can be
 * FILLED or TEXT
 * @param text The text to display within.
 * @param icon The button icon content.
 * @param onClick Will be called when the user clicks the button.
 */
@Composable
fun WarningButton(
    enabled: Boolean = true,
    style: WarningButtonStyle = WarningButtonStyle.OUTLINED,
    text: String,
    icon: @Composable
    (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = getPaddingValues(icon != null),
    onClick: () -> Unit,
) {
    when (style) {
        WarningButtonStyle.FILLED -> {
            val textColor = if (enabled) TextColor.OnError else TextColor.OnDisabledSurface

            SimpleButton(
                modifier = modifier,
                onClick = { onClick() },
                enabled = enabled,
                buttonColors = ButtonDefaults.filledTonalButtonColors(
                    SurfaceColor.Error,
                    TextColor.OnError,
                    SurfaceColor.DisabledSurface,
                    TextColor.OnDisabledSurface,
                ),
                text = text,
                textColor = textColor,
                icon = icon,
                paddingValues = paddingValues,
            )
        }

        WarningButtonStyle.TEXT, WarningButtonStyle.TEXT_LIGHT -> {
            val textColor = when {
                !enabled -> TextColor.OnDisabledSurface
                style == WarningButtonStyle.TEXT_LIGHT -> TextColor.OnError
                else -> SurfaceColor.Error
            }

            CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISWarningRippleTheme) {
                OutlinedButton(
                    modifier = modifier,
                    onClick = { onClick() },
                    enabled = enabled,
                    colors = ButtonDefaults.filledTonalButtonColors(
                        Color.Transparent,
                        SurfaceColor.Error,
                        SurfaceColor.DisabledSurface,
                        TextColor.OnDisabledSurface,
                    ),
                    border = BorderStroke(Border.Thin, Color.Transparent),
                ) {
                    ButtonText(text, textColor, icon, enabled)
                }
            }
        }

        WarningButtonStyle.OUTLINED -> {
            val textColor = if (enabled) SurfaceColor.Error else TextColor.OnDisabledSurface
            OutlinedButton(
                modifier = modifier.hoverPointerIcon(enabled).height(Spacing.Spacing40),
                onClick = { onClick() },
                enabled = enabled,
                shape = ButtonDefaults.outlinedShape,
                colors = ButtonDefaults.outlinedButtonColors(
                    Color.Transparent,
                    SurfaceColor.Error,
                    Color.Transparent,
                    TextColor.OnDisabledSurface,
                ),
                border = BorderStroke(Border.Thin, if (enabled) Outline.Dark else SurfaceColor.DisabledSurface),
                contentPadding = paddingValues,
            ) {
                ButtonText(text, textColor, icon, enabled)
            }
        }
    }
}

enum class WarningButtonStyle {
    FILLED,
    OUTLINED,
    TEXT,
    TEXT_LIGHT,
}
package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Radius
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

/**
 * DHIS2 square icon button with generic icon slot. Wraps Material 3 [ElevatedButton].
 *
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon The button icon content.
 * @param onClick Will be called when the user clicks the button.
 */
@Composable
fun SquareIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        elevation =  ButtonDefaults.elevatedButtonElevation(Spacing.Spacing1),
        modifier = Modifier
            .size(Spacing.Spacing48)
            .padding(Spacing.Spacing4),
        enabled = enabled,
        shape = RoundedCornerShape(Radius.S),
        colors = ButtonDefaults.elevatedButtonColors(
            disabledContainerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(Spacing.Spacing8)
    ) {
        icon()
    }
}

/**
 * DHIS2 icon button with generic icon slot. Wraps Material 3 [IconButton].
 *
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon The button icon content.
 * @param onClick Will be called when the user clicks the button.
 * @param style controls the button style, will be Standard by default,
 * but can be Tonal, Filled, or Outlined too.
 */
@Composable
fun IconButton(
    style: IconStyle = IconStyle.STANDARD,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    val iconButtonColors = getIconButtonColors(style)
    val borderColor = getBorderColor(style, enabled)


    if (style == IconStyle.FILLED) {
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier
                .size(Spacing.Spacing48)
                .padding(Spacing.Spacing4),
            enabled = enabled,
            shape = CircleShape,
            border = BorderStroke(Spacing.Spacing1, borderColor),
            contentPadding = PaddingValues(Spacing.Spacing8),
            colors = iconButtonColors,
        ) {
            icon()
        }
    } else {
        CompositionLocalProvider(LocalRippleTheme provides  CustomDHISRippleTheme) {
            OutlinedButton(
                onClick = onClick,
                modifier = Modifier
                    .size(Spacing.Spacing48)
                    .padding(Spacing.Spacing4),
                enabled = enabled,
                shape = CircleShape,
                border = BorderStroke(Spacing.Spacing1, borderColor),
                contentPadding = PaddingValues(Spacing.Spacing8),
                colors = iconButtonColors,
            ) {
                icon()
            }
        }
    }
}
@Composable
fun getIconButtonColors(style: IconStyle): ButtonColors {
    val iconButtonColors =  when (style) {
        IconStyle.FILLED -> ButtonDefaults.filledTonalButtonColors(SurfaceColor.Primary, TextColor.OnPrimary)
        IconStyle.OUTLINED -> ButtonDefaults.outlinedButtonColors(Color.Transparent,TextColor.OnPrimaryContainer)
        IconStyle.TONAL -> ButtonDefaults.filledTonalButtonColors(SurfaceColor.PrimaryContainer, TextColor.OnPrimaryContainer)
        else -> ButtonDefaults.buttonColors(Color.Transparent,TextColor.OnSurfaceVariant, Color.Transparent,TextColor.OnDisabledSurface)
    }
    return iconButtonColors
}
@Composable
fun getBorderColor(style: IconStyle, enabled: Boolean): Color {
    val borderColor =  if (style == IconStyle.OUTLINED) {
        if (enabled) Outline.Dark else SurfaceColor.DisabledSurface
    }else {
        Color.Transparent
    }
    return borderColor
}

 enum class IconStyle {
    STANDARD,FILLED, TONAL, OUTLINED
}

 object CustomDHISRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color = SurfaceColor.Primary

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        SurfaceColor.Primary,
        lightTheme = true
    )
}

package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Radius
import org.hisp.dhis.mobileui.designsystem.theme.Ripple
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor
import org.hisp.dhis.mobileui.designsystem.theme.shadow

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
    val shadowColor = if (enabled) SurfaceColor.ContainerHighest else Color.Transparent
    ElevatedButton(
        onClick = onClick,
        elevation = ButtonDefaults.elevatedButtonElevation(0.dp),
        modifier = Modifier
            .size(Spacing.Spacing48)
            .padding(Spacing.Spacing4)
            .shadow(shadowColor, Radius.S),
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
 * DHIS2 icon button with generic icon slot.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon The button icon content.
 * @param onClick Will be called when the user clicks the button.
 * @param style controls the button style, will be Standard by default,
 * but can be Tonal, Filled, or Outlined too.
 */
@Composable
fun IconButton(
    style: IconButtonStyle = IconButtonStyle.STANDARD,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    when (style) {
        IconButtonStyle.FILLED -> FilledIconButton(enabled, icon, onClick)
        IconButtonStyle.TONAL -> FilledTonalIconButton(enabled, icon, onClick)
        IconButtonStyle.OUTLINED -> OutlinedIconButton(enabled, icon, onClick)
        else -> StandardIconButton(enabled, icon, onClick)
    }
}

@Composable
private fun StandardIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        FilledIconButton(
            onClick = onClick,
            modifier = Modifier
                .size(Spacing.Spacing48)
                .padding(Spacing.Spacing4),
            enabled = enabled,
            colors = IconButtonDefaults.iconButtonColors(Color.Transparent, TextColor.OnSurfaceVariant, Color.Transparent, TextColor.OnDisabledSurface)
        ) {
            icon()
        }
    }
}

@Composable
private fun FilledIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    FilledIconButton(
        onClick = onClick,
        modifier = Modifier
            .size(Spacing.Spacing48)
            .padding(Spacing.Spacing4),
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(SurfaceColor.Primary, TextColor.OnPrimary, SurfaceColor.DisabledSurface, TextColor.OnDisabledSurface)
    ) {
        icon()
    }
}

@Composable
private fun FilledTonalIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        FilledTonalIconButton(
            onClick = onClick,
            modifier = Modifier
                .size(Spacing.Spacing48)
                .padding(Spacing.Spacing4),
            enabled = enabled,
            shape = CircleShape,
            colors = IconButtonDefaults.filledTonalIconButtonColors(SurfaceColor.PrimaryContainer, TextColor.OnPrimaryContainer, SurfaceColor.DisabledSurface, TextColor.OnDisabledSurface)

        ) {
            icon()
        }
    }
}

@Composable
private fun OutlinedIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        OutlinedIconButton(
            onClick = onClick,
            modifier = Modifier
                .size(Spacing.Spacing48)
                .padding(Spacing.Spacing4),
            enabled = enabled,
            shape = CircleShape,
            border = BorderStroke(Spacing.Spacing1, if (enabled) Outline.Dark else SurfaceColor.DisabledSurface),
            colors = IconButtonDefaults.outlinedIconButtonColors(Color.Transparent, TextColor.OnPrimaryContainer)
        ) {
            icon()
        }
    }
}

enum class IconButtonStyle {
    STANDARD, FILLED, TONAL, OUTLINED
}

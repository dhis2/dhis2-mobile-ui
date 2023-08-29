package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Size
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon
import org.hisp.dhis.mobile.ui.designsystem.theme.iconButtonshadow

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
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val shadowColor = if (enabled) SurfaceColor.ContainerHighest else Color.Transparent
    ElevatedButton(
        onClick = onClick,
        elevation = ButtonDefaults.elevatedButtonElevation(0.dp),
        modifier = modifier
            .size(InternalSizeValues.Size48)
            .padding(Spacing.Spacing4)
            .iconButtonshadow(shadowColor, Radius.S)
            .hoverPointerIcon(enabled),
        enabled = enabled,
        shape = Shape.Small,
        colors = ButtonDefaults.elevatedButtonColors(
            disabledContainerColor = Color.Transparent,
            containerColor = SurfaceColor.Container,
        ),
        contentPadding = PaddingValues(Spacing.Spacing8),
    ) {
        Box(Modifier.size(Spacing.Spacing24)) {
            icon()
        }
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
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    when (style) {
        IconButtonStyle.FILLED -> FilledIconButton(enabled, icon, modifier = modifier, onClick = onClick)
        IconButtonStyle.TONAL -> FilledTonalIconButton(enabled, icon, modifier, onClick)
        IconButtonStyle.OUTLINED -> OutlinedIconButton(enabled, icon, modifier = modifier, onClick = onClick)
        else -> StandardIconButton(enabled, icon, modifier = modifier, onClick)
    }
}

@Composable
private fun StandardIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        FilledIconButton(
            onClick = onClick,
            modifier = modifier
                .size(InternalSizeValues.Size48)
                .padding(Spacing.Spacing4)
                .hoverPointerIcon(enabled),
            enabled = enabled,
            colors = IconButtonDefaults.iconButtonColors(Color.Transparent, TextColor.OnSurfaceVariant, Color.Transparent, TextColor.OnDisabledSurface),
        ) {
            Box(Modifier.size(Spacing.Spacing24)) {
                icon()
            }
        }
    }
}

@Composable
private fun FilledIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
            .size(InternalSizeValues.Size48)
            .padding(Spacing.Spacing4)
            .hoverPointerIcon(enabled),
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(SurfaceColor.Primary, TextColor.OnPrimary, SurfaceColor.DisabledSurface, TextColor.OnDisabledSurface),
    ) {
        Box(Modifier.size(Spacing.Spacing24)) {
            icon()
        }
    }
}

@Composable
private fun FilledTonalIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        FilledTonalIconButton(
            onClick = onClick,
            modifier = modifier
                .size(InternalSizeValues.Size48)
                .padding(Spacing.Spacing4)
                .hoverPointerIcon(enabled),
            enabled = enabled,
            shape = CircleShape,
            colors = IconButtonDefaults.filledTonalIconButtonColors(SurfaceColor.PrimaryContainer, TextColor.OnPrimaryContainer, SurfaceColor.DisabledSurface, TextColor.OnDisabledSurface),

        ) {
            Box(Modifier.size(Spacing.Spacing24)) {
                icon()
            }
        }
    }
}

@Composable
private fun OutlinedIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        OutlinedIconButton(
            onClick = onClick,
            modifier = modifier
                .size(InternalSizeValues.Size48)
                .padding(Spacing.Spacing4)
                .hoverPointerIcon(enabled),
            enabled = enabled,
            shape = CircleShape,
            border = BorderStroke(Border.Thin, if (enabled) Outline.Dark else SurfaceColor.DisabledSurface),
            colors = IconButtonDefaults.outlinedIconButtonColors(Color.Transparent, TextColor.OnPrimaryContainer),
        ) {
            Box(Modifier.size(Spacing.Spacing24)) {
                icon()
            }
        }
    }
}

enum class IconButtonStyle {
    STANDARD, FILLED, TONAL, OUTLINED
}

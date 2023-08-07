package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
        elevation = ButtonDefaults.elevatedButtonElevation(Spacing.Spacing1),
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
    when (style) {
        IconStyle.FILLED -> CustomFilledIconButton(enabled, icon, onClick)
        IconStyle.TONAL -> CustomFilledTonalIconButton(enabled, icon, onClick)
        IconStyle.OUTLINED -> CustomOutlinedIconButton(enabled, icon, onClick)
        else -> StandardIconButton(enabled, icon, onClick)
    }
}

@Composable
fun StandardIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides CustomDHISRippleTheme) {
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
fun CustomFilledIconButton(
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
fun CustomFilledTonalIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides CustomDHISRippleTheme) {
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
fun CustomOutlinedIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides CustomDHISRippleTheme) {
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

enum class IconStyle {
    STANDARD, FILLED, TONAL, OUTLINED
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

package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        modifier = Modifier,
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
 */
@Composable
fun IconButton(
    style: IconStyle = IconStyle.STANDARD,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {

    val rippleColor = when (style) {
        IconStyle.FILLED -> SurfaceColor.ContainerLowest
        IconStyle.OUTLINED -> SurfaceColor.Primary
        IconStyle.TONAL -> SurfaceColor.Primary
        else -> SurfaceColor.Primary
    }
    val iconButtonColors = when (style) {
        IconStyle.FILLED -> ButtonDefaults.filledTonalButtonColors(SurfaceColor.Primary, TextColor.OnPrimary)
        IconStyle.OUTLINED -> ButtonDefaults.outlinedButtonColors(Color.Transparent,TextColor.OnPrimaryContainer)
        IconStyle.TONAL -> ButtonDefaults.filledTonalButtonColors(SurfaceColor.PrimaryContainer, TextColor.OnPrimaryContainer)
        else -> ButtonDefaults.buttonColors(Color.Transparent,TextColor.OnSurfaceVariant, Color.Transparent,TextColor.OnDisabledSurface)
    }
    val borderColor = if( style == IconStyle.OUTLINED) {
        if(enabled) {
            Outline.Dark
        }else{
            SurfaceColor.DisabledSurface
        }
    }else {
        Color.Transparent
    }
    val interactionSource = remember { MutableInteractionSource() }
    val shape = RoundedCornerShape(Spacing.Spacing40)
    OutlinedButton(onClick = onClick,
        modifier= Modifier
            .clip(shape = shape)
            .size(Spacing.Spacing48)
            .padding(Spacing.Spacing4),
        enabled = enabled,
        shape = CircleShape,
        border= BorderStroke(Spacing.Spacing1, borderColor),
        contentPadding = PaddingValues(Spacing.Spacing8),
        colors = iconButtonColors,
        interactionSource = interactionSource
    ) {
        icon()
    }


}
 enum class IconStyle {
    STANDARD,FILLED, TONAL, OUTLINED
}

package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobileui.designsystem.theme.Radius
import org.hisp.dhis.mobileui.designsystem.theme.Spacing

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
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        icon()
    }
}

package org.hisp.dhis.mobile.ui.designsystem.component.internal.modifiers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
internal fun Modifier.clickableWithRipple(
    role: Role = Role.Button,
    color: Color = SurfaceColor.Primary,
    onClick: () -> Unit,
): Modifier = this.then(
    Modifier.clickable(
        role = role,
        interactionSource = remember { MutableInteractionSource() },
        indication = ripple(color = color),
        onClick = onClick,
    ),
)

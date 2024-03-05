package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.shadow

@Composable
fun FAB(
    modifier: Modifier = Modifier,
    style: FABStyle = FABStyle.PRIMARY,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier
            .shadow(
                blur = Spacing.Spacing4,
            )
            .testTag("FAB"),
        onClick = onClick,
        shape = MaterialTheme.shapes.small.copy(CornerSize(Radius.L)),
        containerColor = getContainerColor(style),
        contentColor = getContentColor(style),
        content = icon,
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
    )
}

@Composable
fun ExtendedFAB(
    modifier: Modifier = Modifier,
    style: FABStyle = FABStyle.PRIMARY,
    text: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    ExtendedFloatingActionButton(
        modifier = modifier
            .shadow(
                blur = Spacing.Spacing8,
            )
            .testTag("EXTENDED_FAB_$text"),
        icon = icon,
        text = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                color = getContentColor(style),
            )
        },
        onClick = onClick,
        shape = MaterialTheme.shapes.small.copy(CornerSize(Radius.L)),
        containerColor = getContainerColor(style),
        contentColor = getContentColor(style),
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
    )
}

private fun getContainerColor(style: FABStyle): Color {
    return when (style) {
        FABStyle.SURFACE -> SurfaceColor.Surface
        FABStyle.PRIMARY -> SurfaceColor.Primary
        FABStyle.SECONDARY -> SurfaceColor.PrimaryContainer
    }
}

private fun getContentColor(style: FABStyle): Color {
    return when (style) {
        FABStyle.SURFACE -> TextColor.OnSurfaceVariant
        FABStyle.PRIMARY -> TextColor.OnPrimary
        FABStyle.SECONDARY -> TextColor.OnPrimaryContainer
    }
}

enum class FABStyle {
    SURFACE, PRIMARY, SECONDARY
}

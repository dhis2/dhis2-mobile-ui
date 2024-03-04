package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun FAB(
    modifier: Modifier = Modifier,
    style: FABStyle = FABStyle.PRIMARY,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier.testTag("FAB"),
        onClick = onClick,
        shape = MaterialTheme.shapes.small.copy(CornerSize(Radius.L)),
        containerColor = getContainerColor(style),
        contentColor = getContentColor(style),
        content = icon,
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
        modifier = modifier.testTag("EXTENDED_FAB_$text"),
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

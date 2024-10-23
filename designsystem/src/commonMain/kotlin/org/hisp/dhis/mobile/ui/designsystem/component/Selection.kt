package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

enum class SelectionState {
    SELECTABLE,
    SELECTED,
    NONE,
    ;

    fun changeState(): SelectionState {
        return when (this) {
            SELECTABLE -> SELECTED
            SELECTED -> SELECTABLE
            NONE -> SELECTED
        }
    }
}

@Composable
fun UnselectedItemIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(Spacing.Spacing40)
            .background(
                color = SurfaceColor.PrimaryContainer,
                shape = RoundedCornerShape(Radius.Full),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.CheckBoxOutlineBlank,
            contentDescription = "Unselected",
            tint = SurfaceColor.ContainerHighest,
        )
    }
}

@Composable
fun SelectedItemIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(Spacing.Spacing40)
            .background(
                color = SurfaceColor.Primary,
                shape = RoundedCornerShape(Radius.Full),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription = "Selected",
            tint = TextColor.OnPrimary,
        )
    }
}

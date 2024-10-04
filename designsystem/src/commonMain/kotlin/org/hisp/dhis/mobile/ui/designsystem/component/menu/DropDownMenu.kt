package org.hisp.dhis.mobile.ui.designsystem.component.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun <T> DropDownMenu(
    modifier: Modifier = Modifier,
    items: List<MenuItemData<T>>,
    expanded: Boolean = false,
    selectedItemIndex: Int? = null,
    onDismissRequest: () -> Unit,
    onItemClick: (T) -> Unit,
) {
    MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(Radius.S))) {
        DropdownMenu(
            modifier = modifier
                .background(SurfaceColor.ContainerLow)
                .widthIn(min = 270.dp),
            expanded = expanded,
            onDismissRequest = onDismissRequest,
        ) {
            items.forEachIndexed { index, item ->
                MenuItem(
                    menuItemData = item.copy(
                        state = if (selectedItemIndex == index) {
                            MenuItemState.SELECTED
                        } else {
                            item.state
                        },
                    ),
                ) {
                    onItemClick(item.id)
                }
            }
        }
    }
}

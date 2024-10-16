package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A composable function that renders a top app bar. Depending on the [type], it can display either a
 * default aligned top app bar or a center-aligned top app bar.
 *
 * @param modifier The [Modifier] to be applied to the TopBar.
 * @param type The type of the TopBar, either [TopBarType.DEFAULT] for a default aligned top app bar or [TopBarType.CENTERED] for a center-aligned top app bar.
 * @param navigationIcon A composable function that represents the navigation icon displayed in the TopBar, typically a back arrow or a menu icon.
 * @param actions A composable function that represents the actions (e.g., icons, menus) displayed on the right side of the TopBar.
 * @param title A composable function that represents the title content of the TopBar.
 * @param colors A [TopAppBarColors] that defines the color scheme for the TopBar. Default is [TopAppBarDefaults.topAppBarColors].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    type: TopBarType = TopBarType.DEFAULT,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit,
    title: @Composable () -> Unit,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
) {
    if (type == TopBarType.DEFAULT) {
        TopAppBar(
            modifier = modifier,
            title = title,
            navigationIcon = navigationIcon,
            actions = actions,
            colors = colors,
        )
    } else {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = title,
            navigationIcon = navigationIcon,
            actions = actions,
            colors = colors,
        )
    }
}

/**
 * A composable function that renders an action icon within the TopBar.
 *
 * @param icon The [ImageVector] representing the icon to be displayed.
 * @param tint The tint color for the icon. Default is [Color.Unspecified].
 * @param contentDescription A description of the icon for accessibility purposes.
 * @param onClick The callback to be invoked when the icon is clicked.
 */
@Composable
fun TopBarActionIcon(
    icon: ImageVector,
    tint: Color = Color.Unspecified,
    contentDescription: String = "",
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = tint,
            )
        },
    )
}

/**
 * A composable function that renders an icon button which toggles a dropdown menu.
 *
 * @param iconTint The tint color for the dropdown icon. Default is [Color.Unspecified].
 * @param dropDownMenu A composable function that renders the dropdown menu, receiving the current state (shown or hidden) and a callback to dismiss the menu.
 */
@Composable
fun TopBarDropdownMenuIcon(
    iconTint: Color = Color.Unspecified,
    dropDownMenu: @Composable (showMenu: Boolean, onDismissRequest: () -> Unit) -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(
        onClick = { showMenu = !showMenu },
        icon = {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
                tint = iconTint,
            )
        },
    )
    dropDownMenu(showMenu) { showMenu = false }
}

/**
 * Enum class representing the type of the TopBar.
 *
 * @property DEFAULT The default TopBar alignment with the title left-aligned.
 * @property CENTERED A center-aligned TopBar with the title centered.
 */
enum class TopBarType {
    DEFAULT,
    CENTERED,
}

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

enum class TopBarType {
    DEFAULT,
    CENTERED,
}

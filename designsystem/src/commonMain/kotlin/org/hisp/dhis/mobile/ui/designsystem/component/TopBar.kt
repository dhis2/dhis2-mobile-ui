package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    type: TopBarType = TopBarType.DEFAULT,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit,
    title: String,
    color: Color = SurfaceColor.PrimaryContainer,
) {
    if (type == TopBarType.DEFAULT) {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(text = title)
            },
            navigationIcon = navigationIcon,
            actions = actions,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = color,
            ),
        )
    } else {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            navigationIcon = navigationIcon,
            actions = actions,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = color,
            ),
        )
    }
}

@Composable
fun TopBarActionIcon(
    icon: ImageVector,
    contentDescription: String = "",
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
        },
    )
}

@Composable
fun TopBarDropdownMenuIcon(
    dropDownMenu: @Composable (showMenu: Boolean, onDismissRequest: () -> Unit) -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(
        onClick = { showMenu = !showMenu },
        icon = {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
            )
        },
    )
    dropDownMenu(showMenu) { showMenu = false }
}

enum class TopBarType {
    DEFAULT,
    CENTERED,
}

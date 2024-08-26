package org.hisp.dhis.common.screens.others

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.Groups
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.TopBar
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarActionIcon
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarDropdownMenuIcon
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarType

@Composable
fun TopBarScreen() {
    ColumnScreenContainer(
        title = Groups.TOP_BAR.label,
    ) {
        ColumnComponentContainer("Default") {
            TopBar(
                type = TopBarType.DEFAULT,
                title = "Title",
                navigationIcon = {
                    IconButton(
                        onClick = { },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Menu,
                                contentDescription = "Menu Button",
                            )
                        },
                    )
                },
                actions = {
                    TopBarActionIcon(
                        icon = Icons.Outlined.Share,
                        onClick = { },
                    )
                    TopBarActionIcon(
                        icon = Icons.Outlined.FileDownload,
                        onClick = { },
                    )
                    TopBarDropdownMenuIcon { showMenu, onDismissRequest ->
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = onDismissRequest,
                        ) {
                            DropdownMenuItem(
                                text = { Text("Action 1") },
                                onClick = {},
                                leadingIcon = {
                                    IconButton(
                                        onClick = {
                                            onDismissRequest()
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = Icons.Outlined.Delete,
                                                contentDescription = "Edit Button",
                                            )
                                        },
                                    )
                                },
                            )
                        }
                    }
                },
            )
        }

        ColumnComponentContainer("Back") {
            TopBar(
                type = TopBarType.DEFAULT,
                title = "Title",
                navigationIcon = {
                    IconButton(
                        onClick = { },
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = "Back Button",
                            )
                        },
                    )
                },
                actions = {
                    TopBarActionIcon(
                        icon = Icons.Outlined.Share,
                        onClick = { },
                    )
                },
            )
        }

        ColumnComponentContainer("Without Icons") {
            TopBar(
                type = TopBarType.DEFAULT,
                title = "Title",
                navigationIcon = {
                    IconButton(
                        onClick = { },
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = "Back Button",
                            )
                        },
                    )
                },
                actions = {
                },
            )
        }

        ColumnComponentContainer("Centered") {
            TopBar(
                type = TopBarType.CENTERED,
                title = "Title",
                navigationIcon = {
                    IconButton(
                        onClick = { },
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = "Back Button",
                            )
                        },
                    )
                },
                actions = {
                    TopBarDropdownMenuIcon { showMenu, onDismissRequest ->
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = onDismissRequest,
                        ) {
                            DropdownMenuItem(
                                text = { Text("Action 1") },
                                onClick = {},
                                leadingIcon = {
                                    IconButton(
                                        onClick = {
                                            onDismissRequest()
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = Icons.Outlined.Delete,
                                                contentDescription = "Edit Button",
                                            )
                                        },
                                    )
                                },
                            )
                        }
                    }
                },
            )
        }
    }
}

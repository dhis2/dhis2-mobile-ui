package org.hisp.dhis.common.screens.others

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.common.screens.Groups
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.TopBar
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarActionIcon
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarDropdownMenuIcon
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen() {
    ColumnScreenContainer(
        title = Groups.TOP_BAR.label,
    ) {
        ColumnComponentContainer("Default") {
            TopBar(
                type = TopBarType.DEFAULT,
                title = {
                    Text(
                        text = "Title",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
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
                title = {
                    Text(
                        text = "Title",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
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

        ColumnComponentContainer("Back") {
            TopBar(
                type = TopBarType.DEFAULT,
                title = {
                    Text(
                        text = "Title",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { },
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = "Back Button",
                                tint = Color.White,
                            )
                        },
                    )
                },
                actions = {
                    TopBarActionIcon(
                        icon = Icons.Outlined.Share,
                        tint = Color.White,
                        onClick = { },
                    )
                    TopBarDropdownMenuIcon(
                        iconTint = Color.White,
                    ) { showMenu, onDismissRequest ->
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White,
                ),
            )
        }

        ColumnComponentContainer("Without Icons") {
            TopBar(
                type = TopBarType.DEFAULT,
                title = {
                    Text(
                        text = "Title",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
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
                title = {
                    Text(
                        text = "Title",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
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

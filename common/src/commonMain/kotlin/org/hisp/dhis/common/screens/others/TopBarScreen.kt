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
import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.Groups
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.TopBar
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarAction
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarData
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarType
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen() {
    val defaultTopBarData = TopBarData(
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
        primaryAction = TopBarAction(
            icon = Icons.Outlined.Share,
            onClick = { },
        ),
        secondaryAction = TopBarAction(
            icon = Icons.Outlined.FileDownload,
            onClick = { },
        ),
        dropdownMenu = {
            DropdownMenu(
                expanded = false,
                onDismissRequest = { },
            ) {
                DropdownMenuItem(
                    text = { Text("Action 1") },
                    onClick = {},
                    leadingIcon = {
                        IconButton(
                            onClick = { },
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
        },
        color = SurfaceColor.PrimaryContainer,
    )

    ColumnScreenContainer(
        title = Groups.TOP_BAR.label,
    ) {
        ColumnComponentContainer("Default") {
            TopBar(
                topBarData = defaultTopBarData,
            )
        }

        ColumnComponentContainer("Back") {
            val backTopBarData = defaultTopBarData
                .copy(
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
                    primaryAction = TopBarAction(
                        icon = Icons.Outlined.Delete,
                        onClick = {},
                    ),
                    secondaryAction = null,
                )
            TopBar(
                topBarData = backTopBarData,
            )
        }

        ColumnComponentContainer("Without Icons") {
            val backTopBarData = defaultTopBarData
                .copy(
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
                    primaryAction = null,
                    secondaryAction = null,
                    dropdownMenu = null,
                )
            TopBar(
                topBarData = backTopBarData,
            )
        }

        ColumnComponentContainer("Centered") {
            val centeredTopBarData = defaultTopBarData
                .copy(
                    type = TopBarType.CENTERED,
                    primaryAction = null,
                    secondaryAction = null,
                    dropdownMenu = {
                        DropdownMenu(
                            expanded = false,
                            onDismissRequest = { },
                        ) {
                            DropdownMenuItem(
                                text = { Text("Action 1") },
                                onClick = {},
                                leadingIcon = {
                                    IconButton(
                                        onClick = { },
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
                    },
                )

            TopBar(
                topBarData = centeredTopBarData,
            )
        }
    }
}

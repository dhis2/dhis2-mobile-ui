package org.hisp.dhis.common.screens.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.Workspaces
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.common.screens.Groups
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.menu.DropDownMenu
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemData
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemStyle
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuLeadingElement
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

enum class EnrollmentMenuItem {
    SYNC,
    FOLLOW_UP,
    GROUP_BY_STAGE,
    HELP,
    ENROLLMENTS,
    SHARE,
    DEACTIVATE,
    COMPLETE,
    DELETE,
    REMOVE,
}

@Composable
fun DropDownMenuScreen() {
    ColumnScreenContainer(Groups.MENU.label) {
        ColumnComponentContainer(
            "Enrollment dashboard menu",
        ) {
            val enrollmentMenuItems by remember {
                mutableStateOf(
                    listOf(
                        MenuItemData(
                            id = EnrollmentMenuItem.SYNC,
                            label = "Refresh this record",
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.Sync),
                        ),
                        MenuItemData(
                            id = EnrollmentMenuItem.FOLLOW_UP,
                            label = "Mark for follow-up",
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.Flag),
                        ),
                        MenuItemData(
                            id = EnrollmentMenuItem.GROUP_BY_STAGE,
                            label = "Group by stage",
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.Workspaces),
                        ),
                        MenuItemData(
                            id = EnrollmentMenuItem.HELP,
                            label = "Show help",
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.AutoMirrored.Outlined.HelpOutline),
                        ),
                        MenuItemData(
                            id = EnrollmentMenuItem.ENROLLMENTS,
                            label = "More enrollments",
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.AutoMirrored.Outlined.Assignment),
                        ),
                        MenuItemData(
                            id = EnrollmentMenuItem.SHARE,
                            label = "Share",
                            supportingText = "Using QR code",
                            showDivider = true,
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.Share),
                        ),
                        MenuItemData(
                            id = EnrollmentMenuItem.COMPLETE,
                            label = "Complete",
                            leadingElement = MenuLeadingElement.Icon(
                                icon = Icons.Outlined.CheckCircle,
                                defaultTintColor = SurfaceColor.CustomGreen,
                                selectedTintColor = SurfaceColor.CustomGreen,
                            ),
                        ),
                        MenuItemData(
                            id = EnrollmentMenuItem.DEACTIVATE,
                            label = "Deactivate",
                            showDivider = true,
                            leadingElement = MenuLeadingElement.Icon(
                                icon = Icons.Outlined.Cancel,
                                defaultTintColor = TextColor.OnDisabledSurface,
                                selectedTintColor = TextColor.OnDisabledSurface,
                            ),
                        ),
                        MenuItemData(
                            id = EnrollmentMenuItem.REMOVE,
                            label = "Remove from [program]",
                            style = MenuItemStyle.ALERT,
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.DeleteOutline),
                        ),
                        MenuItemData(
                            id = EnrollmentMenuItem.DELETE,
                            label = "Delete [TEI Type]",
                            style = MenuItemStyle.ALERT,
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.DeleteForever),
                        ),
                    ),
                )
            }

            var selectedItemIndex by remember { mutableStateOf<Int?>(null) }
            var expanded by remember { mutableStateOf(false) }

            Box {
                Button(
                    enabled = true,
                    ButtonStyle.FILLED,
                    text = "Show Dropdown menu",
                ) {
                    expanded = !expanded
                }

                DropDownMenu(
                    items = enrollmentMenuItems,
                    expanded = expanded,
                    selectedItemIndex = selectedItemIndex,
                    onDismissRequest = {
                        expanded = false
                    },
                    onItemClick = { itemId ->
                        expanded = !expanded
                        selectedItemIndex = enrollmentMenuItems.indexOfFirst { it.id == itemId }
                    },
                )
            }
        }
    }
}

package org.hisp.dhis.common.screens.others

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowRight
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.Workspaces
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.screens.Groups
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItem
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemData
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemState
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemStyle
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuLeadingElement
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuTrailingElement
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.dropShadow

@Composable
fun MenuItemScreen() {
    ColumnScreenContainer(Groups.MENU.label) {
        ColumnComponentContainer(
            "Enrollment dashboard menu",
        ) {
            var menuItems by remember {
                mutableStateOf(
                    listOf(
                        MenuItemData(
                            label = "Refresh this record",
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.Sync),
                        ),
                        MenuItemData(
                            label = "Mark for follow-up",
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.Flag),
                        ),
                        MenuItemData(
                            label = "Group by stage",
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.Workspaces),
                        ),
                        MenuItemData(
                            label = "Show help",
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.AutoMirrored.Outlined.HelpOutline),
                        ),
                        MenuItemData(
                            label = "More enrollments",
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.AutoMirrored.Outlined.Assignment),
                        ),
                        MenuItemData(
                            label = "Share",
                            supportingText = "Using QR code",
                            showDivider = true,
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.Share),
                        ),
                        MenuItemData(
                            label = "Complete",
                            leadingElement = MenuLeadingElement.Icon(
                                icon = Icons.Outlined.CheckCircle,
                                defaultTintColor = SurfaceColor.CustomGreen,
                                selectedTintColor = SurfaceColor.CustomGreen,
                            ),
                        ),
                        MenuItemData(
                            label = "Deactivate",
                            showDivider = true,
                            leadingElement = MenuLeadingElement.Icon(
                                icon = Icons.Outlined.Cancel,
                                defaultTintColor = TextColor.OnDisabledSurface,
                                selectedTintColor = TextColor.OnDisabledSurface,
                            ),
                        ),
                        MenuItemData(
                            label = "Remove from [program]",
                            style = MenuItemStyle.ALERT,
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.DeleteOutline),
                        ),
                        MenuItemData(
                            label = "Delete [TEI Type]",
                            style = MenuItemStyle.ALERT,
                            leadingElement = MenuLeadingElement.Icon(icon = Icons.Outlined.DeleteForever),
                        ),
                    ),
                )
            }

            Column(
                modifier = Modifier
                    .dropShadow(
                        shape = RoundedCornerShape(Radius.XS),
                        blur = Spacing.Spacing2,
                        spread = Spacing.Spacing0,
                        color = Color(0x4D007DEB),
                        offsetY = Spacing.Spacing1,
                    )
                    .width(270.dp)
                    .background(SurfaceColor.ContainerLow)
                    .padding(vertical = Spacing.Spacing8),
            ) {
                menuItems.forEachIndexed { index, menuItemData ->
                    MenuItem(
                        menuItemData = menuItemData,
                    ) {
                        menuItems = menuItems.mapIndexed { i, item ->
                            if (i == index) {
                                item.copy(state = MenuItemState.SELECTED)
                            } else {
                                item.copy(state = MenuItemState.ENABLED)
                            }
                        }
                    }
                }
            }
        }

        ColumnComponentContainer("Menu list item") {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    MenuItem(
                        modifier = Modifier.weight(1f),
                        menuItemData = MenuItemData(
                            label = "Menu Item",
                            supportingText = "Support Text",
                            showDivider = true,
                            leadingElement = MenuLeadingElement.Icon(
                                icon = Icons.Outlined.Done,
                            ),
                            trailingElement = MenuTrailingElement.Icon(
                                icon = Icons.AutoMirrored.Outlined.ArrowRight,
                            ),
                        ),
                    ) {}

                    MenuItem(
                        modifier = Modifier.weight(1f),
                        menuItemData = MenuItemData(
                            label = "Menu Item",
                            supportingText = "Support Text",
                            showDivider = true,
                            style = MenuItemStyle.ALERT,
                            leadingElement = MenuLeadingElement.Icon(
                                icon = Icons.Outlined.Done,
                            ),
                            trailingElement = MenuTrailingElement.Icon(
                                icon = Icons.AutoMirrored.Outlined.ArrowRight,
                            ),
                        ),
                    ) {}
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    MenuItem(
                        modifier = Modifier.weight(1f),
                        menuItemData = MenuItemData(
                            label = "Menu Item",
                            supportingText = "Support Text",
                            showDivider = true,
                            state = MenuItemState.SELECTED,
                            leadingElement = MenuLeadingElement.Icon(
                                icon = Icons.Outlined.Done,
                            ),
                            trailingElement = MenuTrailingElement.Icon(
                                icon = Icons.AutoMirrored.Outlined.ArrowRight,
                            ),
                        ),
                    ) {}

                    MenuItem(
                        modifier = Modifier.weight(1f),
                        menuItemData = MenuItemData(
                            label = "Menu Item",
                            supportingText = "Support Text",
                            showDivider = true,
                            state = MenuItemState.SELECTED,
                            style = MenuItemStyle.ALERT,
                            leadingElement = MenuLeadingElement.Icon(
                                icon = Icons.Outlined.Done,
                            ),
                            trailingElement = MenuTrailingElement.Icon(
                                icon = Icons.AutoMirrored.Outlined.ArrowRight,
                            ),
                        ),
                    ) {}
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    MenuItem(
                        modifier = Modifier.weight(1f),
                        menuItemData = MenuItemData(
                            label = "Menu Item",
                            supportingText = "Support Text",
                            showDivider = true,
                            state = MenuItemState.DISABLED,
                            leadingElement = MenuLeadingElement.Icon(
                                icon = Icons.Outlined.Done,
                            ),
                            trailingElement = MenuTrailingElement.Icon(
                                icon = Icons.AutoMirrored.Outlined.ArrowRight,
                            ),
                        ),
                    ) {}

                    MenuItem(
                        modifier = Modifier.weight(1f),
                        menuItemData = MenuItemData(
                            label = "Menu Item",
                            supportingText = "Support Text",
                            showDivider = true,
                            state = MenuItemState.DISABLED,
                            style = MenuItemStyle.ALERT,
                            leadingElement = MenuLeadingElement.Icon(
                                icon = Icons.Outlined.Done,
                            ),
                            trailingElement = MenuTrailingElement.Icon(
                                icon = Icons.AutoMirrored.Outlined.ArrowRight,
                            ),
                        ),
                    ) {}
                }
            }
        }

        ColumnComponentContainer("Menu item with leading element variations") {
            MenuItem(
                menuItemData = MenuItemData(
                    label = "No Leading Element",
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Indent Leading Element",
                    leadingElement = MenuLeadingElement.Indent,
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Icon Leading Element",
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Selected Indent Leading Element",
                    leadingElement = MenuLeadingElement.Indent,
                    state = MenuItemState.SELECTED,
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Selected Icon Leading Element",
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    state = MenuItemState.SELECTED,
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Disabled Indent Leading Element",
                    leadingElement = MenuLeadingElement.Indent,
                    state = MenuItemState.DISABLED,
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Disabled Icon Leading Element",
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    state = MenuItemState.DISABLED,
                ),
            ) {}
        }

        ColumnComponentContainer("Menu item with divider") {
            MenuItem(
                menuItemData = MenuItemData(
                    label = "Menu Item",
                    showDivider = true,
                ),
            ) {}
        }

        ColumnComponentContainer("Menu item with trailing element variations") {
            MenuItem(
                menuItemData = MenuItemData(
                    label = "No Trailing Element",
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Icon Trailing Element",
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Icon(
                        icon = Icons.AutoMirrored.Outlined.ArrowRight,
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Text Trailing Element",
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Text(
                        text = "⌘C",
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Selected Icon Trailing Element",
                    state = MenuItemState.SELECTED,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Icon(
                        icon = Icons.AutoMirrored.Outlined.ArrowRight,
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Selected Text Trailing Element",
                    state = MenuItemState.SELECTED,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Text(
                        text = "⌘C",
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Disabled Icon Trailing Element",
                    state = MenuItemState.DISABLED,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Icon(
                        icon = Icons.AutoMirrored.Outlined.ArrowRight,
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Disabled Text Trailing Element",
                    state = MenuItemState.DISABLED,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Text(
                        text = "⌘C",
                    ),
                ),
            ) {}
        }

        ColumnComponentContainer("Alert Menu item with leading element variations") {
            MenuItem(
                menuItemData = MenuItemData(
                    label = "No Leading Element",
                    style = MenuItemStyle.ALERT,
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Indent Leading Element",
                    leadingElement = MenuLeadingElement.Indent,
                    style = MenuItemStyle.ALERT,
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Icon Leading Element",
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    style = MenuItemStyle.ALERT,
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Selected Indent Leading Element",
                    leadingElement = MenuLeadingElement.Indent,
                    style = MenuItemStyle.ALERT,
                    state = MenuItemState.SELECTED,
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Selected Icon Leading Element",
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    style = MenuItemStyle.ALERT,
                    state = MenuItemState.SELECTED,
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Disabled Indent Leading Element",
                    leadingElement = MenuLeadingElement.Indent,
                    style = MenuItemStyle.ALERT,
                    state = MenuItemState.DISABLED,
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Diasbled Icon Leading Element",
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    style = MenuItemStyle.ALERT,
                    state = MenuItemState.DISABLED,
                ),
            ) {}
        }

        ColumnComponentContainer("Alert Menu item with divider") {
            MenuItem(
                menuItemData = MenuItemData(
                    label = "Menu Item",
                    showDivider = true,
                    style = MenuItemStyle.ALERT,
                ),
            ) {}
        }

        ColumnComponentContainer("Alert Menu item with trailing element variations") {
            MenuItem(
                menuItemData = MenuItemData(
                    label = "No Trailing Element",
                    style = MenuItemStyle.ALERT,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Icon Trailing Element",
                    style = MenuItemStyle.ALERT,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Icon(
                        icon = Icons.AutoMirrored.Outlined.ArrowRight,
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Text Trailing Element",
                    style = MenuItemStyle.ALERT,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Text(
                        text = "⌘C",
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Selected Icon Trailing Element",
                    style = MenuItemStyle.ALERT,
                    state = MenuItemState.SELECTED,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Icon(
                        icon = Icons.AutoMirrored.Outlined.ArrowRight,
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Selected Text Trailing Element",
                    style = MenuItemStyle.ALERT,
                    state = MenuItemState.SELECTED,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Text(
                        text = "⌘C",
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Disabled Icon Trailing Element",
                    state = MenuItemState.DISABLED,
                    style = MenuItemStyle.ALERT,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Icon(
                        icon = Icons.AutoMirrored.Outlined.ArrowRight,
                    ),
                ),
            ) {}

            MenuItem(
                menuItemData = MenuItemData(
                    label = "Disabled Text Trailing Element",
                    state = MenuItemState.DISABLED,
                    style = MenuItemStyle.ALERT,
                    leadingElement = MenuLeadingElement.Icon(
                        icon = Icons.Outlined.Check,
                    ),
                    trailingElement = MenuTrailingElement.Text(
                        text = "⌘C",
                    ),
                ),
            ) {}
        }
    }
}

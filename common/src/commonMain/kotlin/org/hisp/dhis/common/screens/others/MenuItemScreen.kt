package org.hisp.dhis.common.screens.others

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowRight
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItem
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemData
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemState
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuItemStyle
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuLeadingElement
import org.hisp.dhis.mobile.ui.designsystem.component.menuItem.MenuTrailingElement

@Composable
fun MenuItemScreen() {
    ColumnScreenContainer("Menu Item") {
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

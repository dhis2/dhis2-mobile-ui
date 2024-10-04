package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowRight
import androidx.compose.material.icons.outlined.Done
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItem
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemData
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemState
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemStyle
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuLeadingElement
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuTrailingElement
import org.junit.Rule
import org.junit.Test

class MenuItemSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchMenuItemTest() {
        paparazzi.snapshot {
            ColumnScreenContainer("Menu Item") {
                ColumnComponentContainer("Menu item") {
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
                                    id = "menu_item",
                                    label = "Label",
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
                                    id = "menu_item",
                                    label = "Label",
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
                                    id = "menu_item",
                                    label = "Label",
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
                                    id = "menu_item",
                                    label = "Label",
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
                                    id = "menu_item",
                                    label = "Label",
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
                                    id = "menu_item",
                                    label = "Label",
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
            }
        }
    }
}

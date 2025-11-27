package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItem
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemData
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemTestTags.MENU_ITEM_CONTAINER
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemTestTags.MENU_ITEM_DIVIDER
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemTestTags.MENU_ITEM_LEADING_ICON
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemTestTags.MENU_ITEM_LEADING_INDENT
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemTestTags.MENU_ITEM_SUPPORTING_TEXT
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemTestTags.MENU_ITEM_TEXT
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemTestTags.MENU_ITEM_TRAILING_ICON
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuItemTestTags.MENU_ITEM_TRAILING_TEXT
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuLeadingElement
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuTrailingElement
import org.junit.Rule
import org.junit.Test

class MenuItemTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayMenuItemWithLabelCorrectly() {
        rule.setContent {
            MenuItem(
                menuItemData =
                    MenuItemData(
                        id = "menu_item",
                        label = "Menu Item",
                    ),
            ) {}
        }
        rule.onNodeWithTag(MENU_ITEM_CONTAINER).assertExists()
        rule.onNodeWithTag(MENU_ITEM_TEXT, true).assertExists()
    }

    @Test
    fun shouldDisplaySupportingTextCorrectly() {
        rule.setContent {
            MenuItem(
                menuItemData =
                    MenuItemData(
                        id = "menu_item",
                        label = "Menu Item",
                        supportingText = "Supporting Text",
                    ),
            ) {}
        }
        rule.onNodeWithTag(MENU_ITEM_CONTAINER).assertExists()
        rule.onNodeWithTag(MENU_ITEM_SUPPORTING_TEXT, true).assertExists()
    }

    @Test
    fun shouldDisplayDividerCorrectly() {
        rule.setContent {
            MenuItem(
                menuItemData =
                    MenuItemData(
                        id = "menu_item",
                        label = "Menu Item",
                        showDivider = true,
                    ),
            ) {}
        }
        rule.onNodeWithTag(MENU_ITEM_CONTAINER).assertExists()
        rule.onNodeWithTag(MENU_ITEM_DIVIDER, true).assertExists()
    }

    @Test
    fun shouldDisplayLeadingIndentCorrectly() {
        rule.setContent {
            MenuItem(
                menuItemData =
                    MenuItemData(
                        id = "menu_item",
                        label = "Menu Item",
                        leadingElement = MenuLeadingElement.Indent,
                    ),
            ) {}
        }
        rule.onNodeWithTag(MENU_ITEM_CONTAINER).assertExists()
        rule.onNodeWithTag(MENU_ITEM_LEADING_INDENT, true).assertExists()
    }

    @Test
    fun shouldDisplayLeadingIconCorrectly() {
        rule.setContent {
            MenuItem(
                menuItemData =
                    MenuItemData(
                        id = "menu_item",
                        label = "Menu Item",
                        leadingElement =
                            MenuLeadingElement.Icon(
                                icon = Icons.Outlined.Done,
                            ),
                    ),
            ) {}
        }
        rule.onNodeWithTag(MENU_ITEM_CONTAINER).assertExists()
        rule.onNodeWithTag(MENU_ITEM_LEADING_ICON, true).assertExists()
    }

    @Test
    fun shouldDisplayTrailingIconCorrectly() {
        rule.setContent {
            MenuItem(
                menuItemData =
                    MenuItemData(
                        id = "menu_item",
                        label = "Menu Item",
                        trailingElement =
                            MenuTrailingElement.Icon(
                                icon = Icons.Outlined.Done,
                            ),
                    ),
            ) {}
        }
        rule.onNodeWithTag(MENU_ITEM_CONTAINER).assertExists()
        rule.onNodeWithTag(MENU_ITEM_TRAILING_ICON, true).assertExists()
    }

    @Test
    fun shouldDisplayTrailingTextCorrectly() {
        rule.setContent {
            MenuItem(
                menuItemData =
                    MenuItemData(
                        id = "menu_item",
                        label = "Menu Item",
                        trailingElement =
                            MenuTrailingElement.Text(
                                text = "Trailing Text",
                            ),
                    ),
            ) {}
        }
        rule.onNodeWithTag(MENU_ITEM_CONTAINER).assertExists()
        rule.onNodeWithTag(MENU_ITEM_TRAILING_TEXT, true).assertExists()
    }
}

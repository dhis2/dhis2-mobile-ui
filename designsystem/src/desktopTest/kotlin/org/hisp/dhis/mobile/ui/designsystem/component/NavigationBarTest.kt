package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class NavigationBarTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayNavigationBarCorrectly() {
        rule.setContent {
            val items = listOf(
                NavigationBarItem(
                    defaultIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description"),
                            imageVector = Icons.Outlined.Description,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description"),
                            imageVector = Icons.Filled.Description,
                            contentDescription = null,
                        )
                    },
                    label = "Description",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Charts"),
                            imageVector = Icons.Outlined.BarChart,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Charts"),
                            imageVector = Icons.Filled.BarChart,
                            contentDescription = null,
                        )
                    },
                    label = "Charts",
                    showBadge = true,
                ),
            )
            NavigationBar(
                items = items,
                selectedItemIndex = null,
            ) {
            }
        }

        rule.onNodeWithTag("NAVIGATION_BAR_CONTAINER").assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_BORDER").assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR").assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_LABEL_Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description", true).assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_BADGE_Description", true).assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_Charts", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_LABEL_Charts", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Charts", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Charts", true).assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_BADGE_Charts", true).assertExists()
    }

    @Test
    fun shouldUpdateNavigationBarIconCorrectlyOnSelection() {
        rule.setContent {
            val items = listOf(
                NavigationBarItem(
                    defaultIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description"),
                            imageVector = Icons.Outlined.Description,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description"),
                            imageVector = Icons.Filled.Description,
                            contentDescription = null,
                        )
                    },
                    label = "Description",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Charts"),
                            imageVector = Icons.Outlined.BarChart,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Charts"),
                            imageVector = Icons.Filled.BarChart,
                            contentDescription = null,
                        )
                    },
                    label = "Charts",
                    showBadge = true,
                ),
            )
            var selectedItemIndex by remember { mutableStateOf<Int?>(null) }
            NavigationBar(
                items = items,
                selectedItemIndex = selectedItemIndex,
            ) {
                selectedItemIndex = it
            }
        }

        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description", true).assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_Description", true).performClick()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description", true).assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description", true).assertExists()
    }

    @Test
    fun shouldResetSelectedIconToDefaultWhenNewItemIsClicked() {
        rule.setContent {
            val items = listOf(
                NavigationBarItem(
                    defaultIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description"),
                            imageVector = Icons.Outlined.Description,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description"),
                            imageVector = Icons.Filled.Description,
                            contentDescription = null,
                        )
                    },
                    label = "Description",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Charts"),
                            imageVector = Icons.Outlined.BarChart,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            modifier = Modifier.testTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Charts"),
                            imageVector = Icons.Filled.BarChart,
                            contentDescription = null,
                        )
                    },
                    label = "Charts",
                    showBadge = true,
                ),
            )
            var selectedItemIndex by remember { mutableStateOf(0) }
            NavigationBar(
                items = items,
                selectedItemIndex = selectedItemIndex,
            ) {
                selectedItemIndex = it
            }
        }

        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description", true).assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Charts", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Charts", true).assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_Charts", true).performClick()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description", true).assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Charts", true).assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Charts", true).assertExists()
    }
}

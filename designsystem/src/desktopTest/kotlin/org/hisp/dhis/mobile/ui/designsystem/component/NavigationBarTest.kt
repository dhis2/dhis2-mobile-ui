package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Description
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBar
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarItem
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_BORDER
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_CONTAINER
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_ITEM_BADGE_PREFIX
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_ITEM_LABEL_PREFIX
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarTestTags.NAVIGATION_BAR_ITEM_PREFIX
import org.junit.Rule
import org.junit.Test

class NavigationBarTest {

    @get:Rule
    val rule = createComposeRule()

    enum class NavigationItem {
        DESCRIPTION,
        VISUALIZATION,
        LIST,
        MAPS,
        RELATIONSHIPS,
        NOTES,
        ASSIGNMENT,
    }

    @Test
    fun shouldDisplayNavigationBarCorrectly() {
        rule.setContent {
            val items = listOf(
                NavigationBarItem(
                    id = NavigationItem.DESCRIPTION,
                    icon = Icons.Outlined.Description,
                    selectedIcon = Icons.Filled.Description,
                    label = "Description",
                ),
                NavigationBarItem(
                    id = NavigationItem.VISUALIZATION,
                    icon = Icons.Outlined.BarChart,
                    selectedIcon = Icons.Filled.BarChart,
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

        rule.onNodeWithTag(NAVIGATION_BAR_CONTAINER).assertExists()
        rule.onNodeWithTag(NAVIGATION_BAR_BORDER).assertExists()
        rule.onNodeWithTag(NAVIGATION_BAR).assertExists()
        rule.onNodeWithTag("${NAVIGATION_BAR_ITEM_PREFIX}Description", true).assertExists()
        rule.onNodeWithTag("${NAVIGATION_BAR_ITEM_LABEL_PREFIX}Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description", true)
            .assertDoesNotExist()
        rule.onNodeWithTag("${NAVIGATION_BAR_ITEM_BADGE_PREFIX}Description", true)
            .assertDoesNotExist()
        rule.onNodeWithTag("${NAVIGATION_BAR_ITEM_PREFIX}Charts", true).assertExists()
        rule.onNodeWithTag("${NAVIGATION_BAR_ITEM_LABEL_PREFIX}Charts", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Charts", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Charts", true).assertDoesNotExist()
        rule.onNodeWithTag("${NAVIGATION_BAR_ITEM_BADGE_PREFIX}Charts", true).assertExists()
    }

    @Test
    fun shouldUpdateNavigationBarIconCorrectlyOnSelection() {
        rule.setContent {
            val items = listOf(
                NavigationBarItem(
                    id = NavigationItem.DESCRIPTION,
                    icon = Icons.Outlined.Description,
                    selectedIcon = Icons.Filled.Description,
                    label = "Description",
                ),
                NavigationBarItem(
                    id = NavigationItem.VISUALIZATION,
                    icon = Icons.Outlined.BarChart,
                    selectedIcon = Icons.Filled.BarChart,
                    label = "Charts",
                    showBadge = true,
                ),
            )
            var selectedItemIndex by remember { mutableStateOf<Int?>(null) }
            NavigationBar(
                items = items,
                selectedItemIndex = selectedItemIndex,
            ) { navigationItemId ->
                selectedItemIndex = items.indexOfFirst { it.id == navigationItemId }
            }
        }

        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description", true)
            .assertDoesNotExist()
        rule.onNodeWithTag("${NAVIGATION_BAR_ITEM_PREFIX}Description", true).performClick()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description", true)
            .assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description", true).assertExists()
    }

    @Test
    fun shouldResetSelectedIconToDefaultWhenNewItemIsClicked() {
        rule.setContent {
            val items = listOf(
                NavigationBarItem(
                    id = NavigationItem.DESCRIPTION,
                    icon = Icons.Outlined.Description,
                    label = "Description",
                ),
                NavigationBarItem(
                    id = NavigationItem.VISUALIZATION,
                    icon = Icons.Outlined.BarChart,
                    selectedIcon = Icons.Filled.BarChart,
                    label = "Charts",
                    showBadge = true,
                ),
            )
            var selectedItemIndex by remember { mutableStateOf(0) }
            NavigationBar(
                items = items,
                selectedItemIndex = selectedItemIndex,
            ) { navigationItemId ->
                selectedItemIndex = items.indexOfFirst { it.id == navigationItemId }
            }
        }

        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description", true)
            .assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Charts", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Charts", true).assertDoesNotExist()
        rule.onNodeWithTag("${NAVIGATION_BAR_ITEM_PREFIX}Charts", true).performClick()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Description", true).assertExists()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Description", true)
            .assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_DEFAULT_ICON_Charts", true).assertDoesNotExist()
        rule.onNodeWithTag("NAVIGATION_BAR_ITEM_SELECTED_ICON_Charts", true).assertExists()
    }
}

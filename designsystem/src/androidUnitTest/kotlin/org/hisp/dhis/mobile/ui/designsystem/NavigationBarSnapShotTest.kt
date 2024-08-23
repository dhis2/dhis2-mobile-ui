package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.StickyNote2
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Hub
import androidx.compose.material.icons.outlined.Map
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBar
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarItem
import org.junit.Rule
import org.junit.Test

class NavigationBarSnapShotTest {
    @get:Rule
    val paparazzi = paparazzi()

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
    fun launchNavigationBar() {
        paparazzi.snapshot {
            val homeItems = listOf(
                NavigationBarItem(
                    id = NavigationItem.DESCRIPTION,
                    icon = Icons.Outlined.Description,
                    label = "Description",
                ),
                NavigationBarItem(
                    id = NavigationItem.VISUALIZATION,
                    icon = Icons.Outlined.BarChart,
                    label = "Charts",
                    showBadge = true,
                ),
            )

            val programItems = listOf(
                NavigationBarItem(
                    id = NavigationItem.LIST,
                    icon = Icons.AutoMirrored.Outlined.List,
                    label = "List",
                ),
                NavigationBarItem(
                    id = NavigationItem.MAPS,
                    icon = Icons.Outlined.Map,
                    selectedIcon = Icons.Filled.Map,
                    label = "Maps",
                ),
                NavigationBarItem(
                    id = NavigationItem.VISUALIZATION,
                    icon = Icons.Outlined.BarChart,
                    selectedIcon = Icons.Filled.BarChart,
                    label = "Charts",
                    showBadge = true,
                    badgeText = "32",
                ),
            )

            val enrollmentItems = listOf(
                NavigationBarItem(
                    id = NavigationItem.ASSIGNMENT,
                    icon = Icons.AutoMirrored.Outlined.Assignment,
                    selectedIcon = Icons.AutoMirrored.Filled.Assignment,
                    label = "Details",
                ),
                NavigationBarItem(
                    id = NavigationItem.VISUALIZATION,
                    icon = Icons.Outlined.BarChart,
                    selectedIcon = Icons.Filled.BarChart,
                    label = "Charts",
                ),
                NavigationBarItem(
                    id = NavigationItem.RELATIONSHIPS,
                    icon = Icons.Outlined.Hub,
                    selectedIcon = Icons.Filled.Hub,
                    label = "Relationships",
                ),
                NavigationBarItem(
                    id = NavigationItem.NOTES,
                    icon = Icons.AutoMirrored.Outlined.StickyNote2,
                    selectedIcon = Icons.AutoMirrored.Filled.StickyNote2,
                    label = "Notes",
                    showBadge = true,
                ),
            )

            val formItems = listOf(
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
                ),
                NavigationBarItem(
                    id = NavigationItem.NOTES,
                    icon = Icons.AutoMirrored.Outlined.StickyNote2,
                    selectedIcon = Icons.AutoMirrored.Filled.StickyNote2,
                    label = "Notes",
                    showBadge = true,
                    badgeText = "3",
                ),
            )

            ColumnScreenContainer(
                title = "Navigation Bar",
            ) {
                ColumnComponentContainer("Home") {
                    var selectedHomeItemIndex by remember { mutableStateOf<Int?>(null) }
                    NavigationBar(
                        items = homeItems,
                        selectedItemIndex = selectedHomeItemIndex,
                    ) { itemId ->
                        selectedHomeItemIndex = homeItems.indexOfFirst { it.id == itemId }
                    }
                }
                ColumnComponentContainer("Program dashboard") {
                    var selectedProgramItemIndex by remember { mutableStateOf<Int?>(null) }
                    NavigationBar(
                        items = programItems,
                        selectedItemIndex = selectedProgramItemIndex,
                    ) { itemId ->
                        selectedProgramItemIndex = programItems.indexOfFirst { it.id == itemId }
                    }
                }

                ColumnComponentContainer("Enrollment dashboard") {
                    var selectedEnrollmentItemIndex by remember { mutableStateOf<Int?>(null) }
                    NavigationBar(
                        items = enrollmentItems,
                        selectedItemIndex = selectedEnrollmentItemIndex,
                    ) { itemId ->
                        selectedEnrollmentItemIndex =
                            enrollmentItems.indexOfFirst { it.id == itemId }
                    }
                }

                ColumnComponentContainer("Form") {
                    var selectedFormItemIndex by remember { mutableStateOf<Int?>(null) }
                    NavigationBar(
                        items = formItems,
                        selectedItemIndex = selectedFormItemIndex,
                    ) { itemId ->
                        selectedFormItemIndex = formItems.indexOfFirst { it.id == itemId }
                    }
                }
            }
        }
    }
}

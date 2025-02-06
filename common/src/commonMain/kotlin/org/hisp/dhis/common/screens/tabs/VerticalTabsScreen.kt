package org.hisp.dhis.common.screens.tabs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.VerticalTabs
import org.hisp.dhis.mobile.ui.designsystem.component.model.IconData
import org.hisp.dhis.mobile.ui.designsystem.component.model.Tab
import org.hisp.dhis.mobile.ui.designsystem.component.model.TabColorStyle
import org.hisp.dhis.mobile.ui.designsystem.component.model.TabStyle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun VerticalTabsScreen(
    tabStyle: TabStyle,
    tabColorStyle: TabColorStyle,
) {
    val modifier = if (tabStyle is TabStyle.IconOnly) {
        Modifier.width(100.dp)
    } else {
        Modifier.fillMaxSize()
    }

    ColumnScreenContainer(scrollable = false) {
        VerticalTabs(
            modifier = modifier
                .padding(Spacing.Spacing16),
            tabs = buildList {
                repeat(drawableOutlinedList.size) { index ->
                    add(
                        Tab(
                            id = "${index + 1}",
                            label = "Tab ${index + 1}",
                            iconData = getIcon(index),
                        ),
                    )
                }
            },
            tabStyle = tabStyle,
            tabColorStyle = tabColorStyle,
            onSectionSelected = {
            },
            contentPadding = PaddingValues(vertical = Spacing.Spacing12),
        )
    }
}

private val drawableOutlinedList = listOf(
    Icons.Outlined.Home,
    Icons.Outlined.Bookmarks,
    Icons.Outlined.Event,
    Icons.Outlined.AccountCircle,
    Icons.Outlined.Settings,
)

private val drawableFilledList = listOf(
    Icons.Filled.Home,
    Icons.Filled.Bookmarks,
    Icons.Filled.Event,
    Icons.Filled.AccountCircle,
    Icons.Filled.Settings,
)

private fun getIcon(index: Int) = IconData(drawableOutlinedList[index], drawableFilledList[index])

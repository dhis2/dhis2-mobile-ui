package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.VerticalTabs
import org.hisp.dhis.mobile.ui.designsystem.component.model.Tab
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun VerticalTabsScreen() {
    VerticalTabs(
        modifier = Modifier.fillMaxSize()
            .padding(Spacing.Spacing16),
        tabs = listOf(
            Tab(id = "1", label = "Tab 1"),
            Tab(id = "2", label = "Tab 2"),
            Tab(id = "3", label = "Tab 3"),
        ),
        onSectionSelected = {
        },
    )
}

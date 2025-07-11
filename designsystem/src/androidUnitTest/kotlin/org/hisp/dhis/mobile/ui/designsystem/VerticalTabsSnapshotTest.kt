package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.VerticalTabs
import org.hisp.dhis.mobile.ui.designsystem.component.model.Tab
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class VerticalTabsSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchVerticalTabsTest() {
        paparazzi.snapshot {
            DHIS2Theme {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(Spacing.Spacing16),
                ) {
                    VerticalTabs(
                        modifier =
                            Modifier
                                .fillMaxSize(),
                        initialSelectedTabIndex = 1,
                        tabs =
                            listOf(
                                Tab(id = "1", label = "Tab 1"),
                                Tab(id = "2", label = "Tab 2"),
                                Tab(id = "3", label = "Tab 3"),
                                Tab(id = "3", label = "Tab 4"),
                                Tab(id = "3", label = "Tab 5"),
                            ),
                        onSectionSelected = {
                        },
                    )
                }
            }
        }
    }
}

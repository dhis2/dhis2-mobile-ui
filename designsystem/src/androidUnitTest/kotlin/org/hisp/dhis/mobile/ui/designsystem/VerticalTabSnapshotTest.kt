package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.VerticalTabs
import org.hisp.dhis.mobile.ui.designsystem.component.model.Tab
import org.hisp.dhis.mobile.ui.designsystem.component.model.TabColorStyle
import org.hisp.dhis.mobile.ui.designsystem.component.model.TabStyle
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class VerticalTabSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchVerticalTabsTest() {
        val tabStyles = listOf(TabStyle.LabelOnly, TabStyle.IconAndLabel, TabStyle.IconOnly)
        val tabColorStyle = listOf(TabColorStyle.Tonal, TabColorStyle.Primary)
        val selection = listOf(true, false)
        paparazzi.snapshot {
            DHIS2Theme {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(Spacing.Spacing16),
                    verticalArrangement = spacedBy(Spacing.Spacing16),
                ) {
                    tabStyles.forEach { tabStyle ->
                        tabColorStyle.forEach { tabColorStyle ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .requiredHeight(50.dp),
                                horizontalArrangement = spacedBy(Spacing.Spacing16),
                            ) {
                                selection.forEach { selected ->
                                    Box(
                                        Modifier
                                            .weight(1f)
                                            .requiredHeight(50.dp),
                                    ) {
                                        VerticalTabs(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            tabColorStyle = tabColorStyle,
                                            tabStyle = tabStyle,
                                            initialSelectedTabIndex = if (selected) 0 else 1,
                                            tabs = listOf(Tab(id = "1", label = "Tab")),
                                            onSectionSelected = {
                                            },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

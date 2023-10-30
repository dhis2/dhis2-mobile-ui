package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class LegendTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun legendShouldBeClickableIfItHasPopupDescription() {
        rule.setContent {
            Legend(
                legendData = LegendData(
                    SurfaceColor.CustomGreen,
                    "Legend",
                    popUpLegendDescriptionData = listOf(
                        LegendDescriptionData(
                            color = SurfaceColor.CustomGreen,
                            text = "Item 1",
                            range = 0..300,
                        ),
                    ),
                ),
            )
        }

        rule.onNodeWithTag("LEGEND").assertHasClickAction()
    }

    @Test
    fun legendShouldNotBeClickableIfThereIsNoPopupDescription() {
        rule.setContent {
            Legend(
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend", popUpLegendDescriptionData = null),
            )
        }

        rule.onNodeWithTag("LEGEND").assertHasNoClickAction()
    }
}

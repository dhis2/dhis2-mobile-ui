package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Indicator
import org.junit.Rule
import org.junit.Test

class InputIndicatorSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchIndicatorInput() {
        paparazzi.snapshot {
            ColumnScreenContainer {
                Indicator(
                    title = "Heart Rate",
                    content = "160 bpm",
                    indicatorColor = Color(0xFFE12F58),
                )

                Indicator(
                    title = "Cholesterol",
                    content = "190 mg/dL",
                    indicatorColor = Color(0xFFFADB14),
                )

                Indicator(
                    title = "Cholesterol",
                    content = "190 mg/dL",
                )
            }
        }
    }
}

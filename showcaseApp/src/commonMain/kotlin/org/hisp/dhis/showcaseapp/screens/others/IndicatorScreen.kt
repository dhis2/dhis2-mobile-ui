package org.hisp.dhis.showcaseapp.screens.others

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Indicator

@Composable
fun IndicatorScreen() {
    ColumnScreenContainer(title = "Indicator component") {
        Indicator(
            title = "Systolic and diastolic pressure",
            content = "120 mmHg / 80 mmHg",
            indicatorColor = Color(0xFF00A940),
        )
        Indicator(
            title = "Heart rate",
            content = "160 bpm",
            indicatorColor = Color(0xFFE12F58),
        )
        Indicator(
            title = "Cholesterol",
            content = "190 mg/dL",
            indicatorColor = Color(0xFFFADB14),
        )
        Indicator(
            title = "Blood Oxygen Level",
            content = "96%",
            indicatorColor = Color(0xFFFFF7C7),
        )
        Indicator(
            title = "Cholesterol",
            content = "190 mg/dL",
        )
    }
}

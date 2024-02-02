package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.IndicatorInput

@Composable
fun IndicatorInputScreen() {
    ColumnComponentContainer {
        IndicatorInput(
            title = "Systolic and diastolic pressure",
            content = "120 mmHg / 80 mmHg",
            indicatorColor = Color(0xFF00A940),
        )
        IndicatorInput(
            title = "Heart rate",
            content = "160 bpm",
            indicatorColor = Color(0xFFE12F58),
        )
        IndicatorInput(
            title = "Cholesterol",
            content = "190 mg/dL",
            indicatorColor = Color(0xFFFADB14),
        )
        IndicatorInput(
            title = "Blood Oxygen Level",
            content = "96%",
            indicatorColor = Color(0xFFFFF7C7),
        )
        IndicatorInput(
            title = "Cholesterol",
            content = "190 mg/dL",
        )
    }
}

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
        Indicator(
            title = "Lorem Ipsum is simply dummy text of the printing",
            content = "Lorem Ipsum is simply dummy text of the printing",
        )
        Indicator(
            title = """
            **Provider asks reason for visiting health facility:**
            - **Initial visit**: assess and treat.
            - **Follow-up visit**: assess and *advise* to complete medication, change medication or refer
            """.trimIndent(),
        )
        Indicator(
            title =  """
            # H1 - Roboto - Medium 24/28 . 0
            ## H2 - Roboto - Medium 22/26 . 0
            ### H3 - Roboto - Medium 20/24 . 0
            #### H4 - Roboto - Medium 18/22 . 0
            ##### H5 - Roboto - Medium 16/20 . 0
            ###### H6 - Roboto - Medium 14/20 . 0
            p  - Roboto - Regular 14/20 . 0
            """.trimIndent(),
        )
        Indicator(
            title = """
                | ID | Name | Age |
                | --: | ----: | :--- |
                | 1 | John Doe | 18 |
                | 2 | Mary Smith| 24 |
            """.trimIndent(),
        )

    }
}
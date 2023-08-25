package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.LegendDescription
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun LegendDescriptionScreen() {
    ColumnComponentContainer {
        Text("Legend description")
        LegendDescription(SurfaceColor.CustomGreen, "Legend", IntRange(0, 30))
        LegendDescription(
            SurfaceColor.CustomGreen,
            "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Maecenas dolor lacus, " +
                "aliquam. Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Maecenas dolor lacus, " +
                "aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            IntRange(0, 30)
        )
        Text("Legend Block")

        Column {
            LegendDescription(SurfaceColor.CustomGreen, "Low", IntRange(0, 5))
            LegendDescription(SurfaceColor.CustomYellow, "Medium", IntRange(5, 10))
            LegendDescription(SurfaceColor.CustomOrange, "High", IntRange(10, 20))
            LegendDescription(SurfaceColor.CustomPink, "Very high", IntRange(20, 40))
            LegendDescription(SurfaceColor.CustomBrown, "Extreme", IntRange(40, 120))
            LegendDescription(
                SurfaceColor.CustomGray,
                "Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit. Fusce convallis",
                IntRange(120, 1000)
            )
        }
    }
}

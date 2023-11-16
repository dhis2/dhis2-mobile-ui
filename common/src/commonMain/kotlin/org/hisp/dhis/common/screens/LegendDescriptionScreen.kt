package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.LegendDescriptionData
import org.hisp.dhis.mobile.ui.designsystem.component.LegendRange
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun LegendDescriptionScreen() {
    ColumnComponentContainer(title = "LegendDescription") {
        LegendRange(
            listOf(
                LegendDescriptionData(
                    SurfaceColor.CustomGreen,
                    "Legend",
                    IntRange(0, 30),
                ),
                LegendDescriptionData(
                    SurfaceColor.CustomGreen,
                    "Lorem ipsum dolor sit amet, " +
                        "consectetur adipiscing elit. Maecenas dolor lacus, " +
                        "aliquam. Lorem ipsum dolor sit amet, " +
                        "consectetur adipiscing elit. Maecenas dolor lacus, " +
                        "aliquam.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    IntRange(0, 30),
                ),
            ),
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Legend Block")
        LegendRange(
            listOf(
                LegendDescriptionData(
                    SurfaceColor.CustomGreen,
                    "Low",
                    IntRange(0, 5),
                ),
                LegendDescriptionData(SurfaceColor.CustomYellow, "Medium", IntRange(5, 10)),
                LegendDescriptionData(SurfaceColor.Warning, "High", IntRange(10, 20)),
                LegendDescriptionData(SurfaceColor.CustomPink, "Very high", IntRange(20, 40)),
                LegendDescriptionData(SurfaceColor.CustomBrown, "Extreme", IntRange(40, 120)),
                LegendDescriptionData(
                    SurfaceColor.CustomGray,
                    "Lorem ipsum dolor sit amet, " +
                        "consectetur adipiscing elit. Fusce convallis",
                    IntRange(120, 1000),
                ),
            ),
        )
    }
}

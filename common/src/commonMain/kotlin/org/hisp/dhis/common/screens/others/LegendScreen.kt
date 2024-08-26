package org.hisp.dhis.common.screens.others

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Legend
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.LegendDescriptionData
import org.hisp.dhis.mobile.ui.designsystem.component.LegendRange
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun LegendScreen() {
    ColumnScreenContainer(title = "Legend component") {
        ColumnComponentContainer("Green Legend") {
            Legend(LegendData(SurfaceColor.CustomGreen, "Legend"))
        }

        ColumnComponentContainer("Orange Legend") {
            Legend(LegendData(SurfaceColor.Warning, "Legend"))
        }

        ColumnComponentContainer("Pink Legend") {
            Legend(
                LegendData(
                    SurfaceColor.CustomPink,
                    "Lorem ipsum dolor sit amet," +
                        " consectetur adipiscing elit. Maecenas dolor lacus," +
                        " aliquam. Lorem ipsum dolor sit amet," +
                        " consectetur adipiscing elit. Maecenas dolor lacus,",
                ),
            )
        }

        ColumnComponentContainer("Legend with popup description") {
            Legend(
                LegendData(
                    color = SurfaceColor.CustomGreen,
                    title = "Legend with popup",
                    popUpLegendDescriptionData = listOf(
                        LegendDescriptionData(
                            color = SurfaceColor.CustomGreen,
                            text = "Item 1",
                            range = 0..300,
                        ),
                        LegendDescriptionData(
                            color = SurfaceColor.CustomGreen,
                            text = "Item 2",
                            range = 301..600,
                        ),
                    ),
                ),
            )

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
        }

        ColumnComponentContainer("Legend Range ") {
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
}

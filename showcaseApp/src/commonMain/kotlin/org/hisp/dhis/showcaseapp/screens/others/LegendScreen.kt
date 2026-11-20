package org.hisp.dhis.showcaseapp.screens.others

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Legend
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.LegendDescriptionData
import org.hisp.dhis.mobile.ui.designsystem.component.LegendRange
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme

@Composable
fun LegendScreen() {
    ColumnScreenContainer(title = "Legend component") {
        ColumnComponentContainer("Green Legend") {
            Legend(LegendData(DHIS2Theme.extendedColors.customGreen, "Legend"))
        }

        ColumnComponentContainer("Orange Legend") {
            Legend(LegendData(DHIS2Theme.extendedColors.warning, "Legend"))
        }

        ColumnComponentContainer("Pink Legend") {
            Legend(
                LegendData(
                    DHIS2Theme.extendedColors.customPink,
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
                    color = DHIS2Theme.extendedColors.customGreen,
                    title = "Legend with popup",
                    popUpLegendDescriptionData =
                        listOf(
                            LegendDescriptionData(
                                color = DHIS2Theme.extendedColors.customGreen,
                                text = "Item 1",
                                range = 0..300,
                            ),
                            LegendDescriptionData(
                                color = DHIS2Theme.extendedColors.customGreen,
                                text = "Item 2",
                                range = 301..600,
                            ),
                        ),
                ),
            )

            LegendRange(
                listOf(
                    LegendDescriptionData(
                        DHIS2Theme.extendedColors.customGreen,
                        "Legend",
                        IntRange(0, 30),
                    ),
                    LegendDescriptionData(
                        DHIS2Theme.extendedColors.customGreen,
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
                        DHIS2Theme.extendedColors.customGreen,
                        "Low",
                        IntRange(0, 5),
                    ),
                    LegendDescriptionData(DHIS2Theme.extendedColors.customYellow, "Medium", IntRange(5, 10)),
                    LegendDescriptionData(DHIS2Theme.extendedColors.warning, "High", IntRange(10, 20)),
                    LegendDescriptionData(DHIS2Theme.extendedColors.customPink, "Very high", IntRange(20, 40)),
                    LegendDescriptionData(DHIS2Theme.extendedColors.customBrown, "Extreme", IntRange(40, 120)),
                    LegendDescriptionData(
                        DHIS2Theme.extendedColors.customGray,
                        "Lorem ipsum dolor sit amet, " +
                            "consectetur adipiscing elit. Fusce convallis",
                        IntRange(120, 1000),
                    ),
                ),
            )
        }
    }
}

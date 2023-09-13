package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.lorem
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetShell
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.LegendDescriptionData
import org.hisp.dhis.mobile.ui.designsystem.component.LegendRange
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun BottomSheetScreen() {
    var showBottomSheetShell by rememberSaveable { mutableStateOf(false) }

    if (showBottomSheetShell) {
        BottomSheetShell(
            title = "Legend name ",
            buttonBlock = {
                ButtonBlock(
                    primaryButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Button",
                                )
                            },
                            enabled = true,
                            text = "Label",
                            onClick = {
                                showBottomSheetShell = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            content = {
                Column() {
                    LegendRange(
                        listOf(
                            LegendDescriptionData(
                                SurfaceColor.CustomGreen,
                                "Low",
                                IntRange(0, 5),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomYellow,
                                "Medium",
                                IntRange(5, 10),
                            ),
                            LegendDescriptionData(
                                TextColor.OnWarning,
                                "High",
                                IntRange(10, 20),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomPink,
                                "Very high",
                                IntRange(20, 40),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomBrown,
                                "Extreme",
                                IntRange(40, 120),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomGray,
                                text = lorem,
                                IntRange(120, 1000),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomGreen,
                                "Low",
                                IntRange(0, 5),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomYellow,
                                "Medium",
                                IntRange(5, 10),
                            ),
                            LegendDescriptionData(
                                TextColor.OnWarning,
                                "High",
                                IntRange(10, 20),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomPink,
                                "Very high",
                                IntRange(20, 40),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomBrown,
                                "Extreme",
                                IntRange(40, 120),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomGray,
                                text = lorem,
                                IntRange(120, 1000),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomGreen,
                                "Low",
                                IntRange(0, 5),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomYellow,
                                "Medium",
                                IntRange(5, 10),
                            ),
                            LegendDescriptionData(
                                TextColor.OnWarning,
                                "High",
                                IntRange(10, 20),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomPink,
                                "Very high",
                                IntRange(20, 40),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomBrown,
                                "Extreme",
                                IntRange(40, 120),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomGray,
                                text = lorem,
                                IntRange(120, 1000),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomGreen,
                                "Low",
                                IntRange(0, 5),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomYellow,
                                "Medium",
                                IntRange(5, 10),
                            ),
                            LegendDescriptionData(
                                TextColor.OnWarning,
                                "High",
                                IntRange(10, 20),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomPink,
                                "Very high",
                                IntRange(20, 40),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomBrown,
                                "Extreme",
                                IntRange(40, 120),
                            ),
                            LegendDescriptionData(
                                SurfaceColor.CustomGray,
                                text = lorem,
                                IntRange(120, 1000),
                            ),
                        ),
                    )
                }
            },
        ) {
            showBottomSheetShell = false
        }
    } else {}

    ColumnComponentContainer {
        Button(
            enabled = true,
            ButtonStyle.FILLED,
            text = "Show Modal",
        ) {
            showBottomSheetShell = !showBottomSheetShell
        }
    }
}

package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.InputDialog
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesNoField
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesNoFieldValues
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.LegendDescriptionData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.PreviewContextConfigurationEffect
import org.junit.Rule
import org.junit.Test

class InputDialogSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @OptIn(ExperimentalResourceApi::class)
    @Test
    fun launchInputDialog() {
        val regularLegendList =
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
                    SurfaceColor.Warning,
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
                    text = "Lorem ipsum dolor sit amet",
                    IntRange(120, 1000),
                ),
            )

        paparazzi.snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewContextConfigurationEffect()
            }
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.Spacing16)) {
                InputDialog(
                    input = {
                        InputText(
                            modifier = Modifier,
                            title = "Label",
                            inputTextFieldValue = TextFieldValue(text = "Label"),
                            onValueChanged = {
                            },
                            state = InputShellState.FOCUSED,
                            onFocusChanged = {
                            },
                        )
                    },
                    details = {
                        Text("Some Content")
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Done",
                            onClick = {
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onDismiss = {
                    },
                    modifier = Modifier,
                )
                InputDialog(
                    input = {
                        InputYesNoField(
                            title = "Label",
                            state = InputShellState.ERROR,
                            supportingText =
                                listOf(
                                    SupportingTextData(
                                        "Error text",
                                        SupportingTextState.ERROR,
                                    ),
                                ),
                            itemSelected = InputYesNoFieldValues.YES,
                            onItemChange = {
                            },
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Done",
                            onClick = {
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onDismiss = {
                    },
                    modifier = Modifier,
                )

                InputDialog(
                    input = {
                        InputYesNoField(
                            title = "Label",
                            state = InputShellState.FOCUSED,
                            itemSelected = InputYesNoFieldValues.YES,
                            onItemChange = {
                            },
                            supportingText =
                                listOf(
                                    SupportingTextData(
                                        "Supporting text",
                                        SupportingTextState.DEFAULT,
                                    ),
                                ),
                            legendData =
                                LegendData(
                                    SurfaceColor.CustomGreen,
                                    "Legend",
                                    popUpLegendDescriptionData = regularLegendList,
                                ),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Done",
                            onClick = {
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onDismiss = {
                    },
                    modifier = Modifier,
                )
            }
        }
    }
}

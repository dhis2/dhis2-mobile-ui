@file:Suppress("DEPRECATION")

package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
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
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.jetbrains.compose.resources.PreviewContextConfigurationEffect
import org.junit.Rule
import org.junit.Test

class InputDialogSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchInputDialog() {
        paparazzi.themedSnapshots {
            val regularLegendList =
                listOf(
                    LegendDescriptionData(
                        DHIS2Theme.extendedColors.customGreen,
                        "Low",
                        IntRange(0, 5),
                    ),
                    LegendDescriptionData(
                        DHIS2Theme.extendedColors.customYellow,
                        "Medium",
                        IntRange(5, 10),
                    ),
                    LegendDescriptionData(
                        DHIS2Theme.extendedColors.warning,
                        "High",
                        IntRange(10, 20),
                    ),
                    LegendDescriptionData(
                        DHIS2Theme.extendedColors.customPink,
                        "Very high",
                        IntRange(20, 40),
                    ),
                    LegendDescriptionData(
                        DHIS2Theme.extendedColors.customBrown,
                        "Extreme",
                        IntRange(40, 120),
                    ),
                    LegendDescriptionData(
                        DHIS2Theme.extendedColors.customGray,
                        text = "Lorem ipsum dolor sit amet",
                        IntRange(120, 1000),
                    ),
                )

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
                                    DHIS2Theme.extendedColors.customGreen,
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

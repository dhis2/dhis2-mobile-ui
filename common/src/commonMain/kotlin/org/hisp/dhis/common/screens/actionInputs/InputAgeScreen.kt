package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.common.screens.previews.regularLegendList
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputAge
import org.hisp.dhis.mobile.ui.designsystem.component.InputAgeModel
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.TimeUnitValues
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun InputAgeScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_AGE.label) {
        var inputType by remember { mutableStateOf<AgeInputType>(AgeInputType.None) }

        ColumnComponentContainer("Input Age Component - Idle") {
            InputAge(
                InputAgeModel(
                    title = "Label",
                    inputType = inputType,
                    onValueChanged = { newInputType ->
                        inputType = newInputType
                    },
                ),
            )
        }

        ColumnComponentContainer("Input Age Component - Idle Disabled") {
            InputAge(
                InputAgeModel(
                    title = "Label",
                    inputType = AgeInputType.None,
                    state = InputShellState.DISABLED,
                    onValueChanged = { newInputType ->
                        inputType = newInputType
                    },
                ),
            )
        }

        ColumnComponentContainer("Input Age Component - Date Of Birth") {
            InputAge(
                InputAgeModel(
                    title = "Label",
                    inputType = AgeInputType.DateOfBirth(TextFieldValue("01011985")),
                    state = InputShellState.DISABLED,

                    onValueChanged = { newInputType ->
                        inputType = newInputType
                    },
                ),
            )
        }

        ColumnComponentContainer("Input Age Component - Date Of Birth Required Error") {
            InputAge(
                InputAgeModel(
                    title = "Label",
                    inputType = AgeInputType.DateOfBirth(TextFieldValue("010")),
                    state = InputShellState.ERROR,
                    isRequired = true,

                    onValueChanged = {
                        // no-op
                    },
                ),
            )
        }

        ColumnComponentContainer("Input Age Component - Age Disabled") {
            InputAge(
                InputAgeModel(
                    title = "Label",
                    inputType = AgeInputType.Age(value = TextFieldValue("56"), unit = TimeUnitValues.YEARS),
                    state = InputShellState.DISABLED,

                    onValueChanged = { newInputType ->
                        inputType = newInputType
                    },
                ),
            )
        }

        ColumnComponentContainer("Input Age Component - Age Required Error") {
            InputAge(
                InputAgeModel(
                    title = "Label",
                    inputType = AgeInputType.Age(value = TextFieldValue("56"), unit = TimeUnitValues.YEARS),
                    state = InputShellState.ERROR,
                    isRequired = true,

                    onValueChanged = {
                        // no-op
                    },
                ),
            )
        }

        ColumnComponentContainer("Input Age Component - Legend") {
            InputAge(
                InputAgeModel(
                    title = "Label",
                    inputType = AgeInputType.Age(value = TextFieldValue("56"), unit = TimeUnitValues.YEARS),
                    state = InputShellState.ERROR,
                    isRequired = true,

                    onValueChanged = {
                        // no-op
                    },
                    legendData = LegendData(SurfaceColor.CustomGreen, "Legend", popUpLegendDescriptionData = regularLegendList),
                ),
            )
        }
    }
}

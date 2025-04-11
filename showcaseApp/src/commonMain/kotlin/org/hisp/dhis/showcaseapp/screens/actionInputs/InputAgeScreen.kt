package org.hisp.dhis.showcaseapp.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputAge
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.TimeUnitValues
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputAgeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberInputAgeState
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.showcaseapp.screens.previews.regularLegendList

@Composable
fun InputAgeScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_AGE.label) {
        var inputType by remember { mutableStateOf<AgeInputType>(AgeInputType.DateOfBirth(TextFieldValue("2024-11-12"))) }

        ColumnComponentContainer("Input Age Component - Idle") {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = inputType,
                ),
                onValueChanged = { newInputType ->
                    inputType = newInputType ?: AgeInputType.None
                },
            )
        }

        ColumnComponentContainer("Input Age Component - Idle Disabled") {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputState = InputShellState.DISABLED,
                ),
                onValueChanged = { newInputType ->
                    inputType = newInputType ?: AgeInputType.None
                },
            )
        }

        ColumnComponentContainer("Input Age Component - Date Of Birth") {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = AgeInputType.DateOfBirth(TextFieldValue("01011985")),
                    inputState = InputShellState.DISABLED,
                ),
                onValueChanged = { newInputType ->
                    inputType = newInputType ?: AgeInputType.None
                },
            )
        }

        ColumnComponentContainer("Input Age Component - Date Of Birth Required Error") {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                        isRequired = true,
                    ),
                    inputType = AgeInputType.DateOfBirth(TextFieldValue("010")),
                    inputState = InputShellState.ERROR,
                ),
                onValueChanged = {
                    // no-op
                },
            )
        }

        ColumnComponentContainer("Input Age Component - Age Disabled") {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = AgeInputType.Age(value = TextFieldValue("56"), unit = TimeUnitValues.YEARS),
                    inputState = InputShellState.DISABLED,
                ),
                onValueChanged = { newInputType ->
                    inputType = newInputType ?: AgeInputType.None
                },
            )
        }

        ColumnComponentContainer("Input Age Component - Age Required Error") {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                        isRequired = true,
                    ),
                    inputType = AgeInputType.Age(value = TextFieldValue("56"), unit = TimeUnitValues.YEARS),
                    inputState = InputShellState.ERROR,
                ),
                onValueChanged = {
                    // no-op
                },
            )
        }

        ColumnComponentContainer("Input Age Component - Legend") {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                        isRequired = true,
                    ),
                    inputType = AgeInputType.Age(value = TextFieldValue("56"), unit = TimeUnitValues.YEARS),
                    inputState = InputShellState.ERROR,
                    legendData = LegendData(SurfaceColor.CustomGreen, "Legend", popUpLegendDescriptionData = regularLegendList),
                ),
                onValueChanged = {
                    // no-op
                },
            )
        }
    }
}

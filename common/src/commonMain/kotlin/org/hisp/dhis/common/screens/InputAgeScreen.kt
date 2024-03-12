package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.common.screens.previews.regularLegendList
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputAge
import org.hisp.dhis.mobile.ui.designsystem.component.InputAgeModel
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.TimeUnitSelector
import org.hisp.dhis.mobile.ui.designsystem.component.TimeUnitValues
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun InputAgeScreen() {
    ColumnComponentContainer {
        SubTitle("Horizontal Age Field Helper")
        var selectedFieldHorizontal by remember {
            mutableStateOf(RadioButtonData("0", selected = true, enabled = true, textInput = TimeUnitValues.YEARS.value))
        }
        TimeUnitSelector(Orientation.HORIZONTAL, TimeUnitValues.YEARS.value) {
            selectedFieldHorizontal = it
        }

        SubTitle("Input Age Component - Idle")
        var inputType by remember { mutableStateOf<AgeInputType>(AgeInputType.None) }

        InputAge(
            InputAgeModel(
                title = "Label",
                inputType = inputType,
                onValueChanged = { newInputType ->
                    inputType = newInputType
                },
            ),

        )

        SubTitle("Input Age Component - Idle Disabled")
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

        SubTitle("Input Age Component - Date Of Birth")
        InputAge(
            InputAgeModel(
                title = "Label",
                inputType = AgeInputType.DateOfBirth("01011985"),
                state = InputShellState.DISABLED,

                onValueChanged = { newInputType ->
                    inputType = newInputType
                },
            ),

        )

        SubTitle("Input Age Component - Date Of Birth Required Error")
        InputAge(
            InputAgeModel(
                title = "Label",
                inputType = AgeInputType.DateOfBirth("010"),
                state = InputShellState.ERROR,
                isRequired = true,

                onValueChanged = {
                    // no-op
                },
            ),

        )

        SubTitle("Input Age Component - Age Disabled")
        InputAge(
            InputAgeModel(
                title = "Label",
                inputType = AgeInputType.Age(value = "56", unit = TimeUnitValues.YEARS),
                state = InputShellState.DISABLED,

                onValueChanged = { newInputType ->
                    inputType = newInputType
                },
            ),

        )

        SubTitle("Input Age Component - Age Required Error")
        InputAge(
            InputAgeModel(
                title = "Label",
                inputType = AgeInputType.Age(value = "56", unit = TimeUnitValues.YEARS),
                state = InputShellState.ERROR,
                isRequired = true,

                onValueChanged = {
                    // no-op
                },
            ),

        )

        SubTitle("Input Age Component - Legend")
        InputAge(
            InputAgeModel(
                title = "Label",
                inputType = AgeInputType.Age(value = "56", unit = TimeUnitValues.YEARS),
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

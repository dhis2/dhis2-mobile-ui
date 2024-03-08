package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputAge
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.TimeUnitValues
import org.junit.Rule
import org.junit.Test

class InputAgeSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchInputAgeSnapshot() {
        paparazzi.snapshot {
            ColumnComponentContainer {
                SubTitle("Input Age Component - Idle")
                InputAge(
                    title = "Label",
                    inputType =  AgeInputType.None,
                    onCalendarActionClicked = {
                        // no-op
                    },
                    onValueChanged = {
                    },
                )

                SubTitle("Input Age Component - Idle Disabled")
                InputAge(
                    title = "Label",
                    inputType = AgeInputType.None,
                    state = InputShellState.DISABLED,
                    onCalendarActionClicked = {
                        // no-op
                    },
                    onValueChanged = {
                    },
                )

                SubTitle("Input Age Component - Date Of Birth")
                InputAge(
                    title = "Label",
                    inputType = AgeInputType.DateOfBirth("01011985"),
                    state = InputShellState.DISABLED,
                    onCalendarActionClicked = {
                        // no-op
                    },
                    onValueChanged = {
                    },
                )

                SubTitle("Input Age Component - Date Of Birth Required Error")
                InputAge(
                    title = "Label",
                    inputType = AgeInputType.DateOfBirth("010"),
                    state = InputShellState.ERROR,
                    isRequired = true,
                    onCalendarActionClicked = {
                        // no-op
                    },
                    onValueChanged = {
                        // no-op
                    },
                )

                SubTitle("Input Age Component - Age Disabled")
                InputAge(
                    title = "Label",
                    inputType = AgeInputType.Age(value = "56", unit = TimeUnitValues.YEARS),
                    state = InputShellState.DISABLED,
                    onCalendarActionClicked = {
                        // no-op
                    },
                    onValueChanged = {
                    },
                )

                SubTitle("Input Age Component - Age Required Error")
                InputAge(
                    title = "Label",
                    inputType = AgeInputType.Age(value = "56", unit = TimeUnitValues.YEARS),
                    state = InputShellState.ERROR,
                    isRequired = true,
                    onCalendarActionClicked = {
                        // no-op
                    },
                    onValueChanged = {
                        // no-op
                    },
                )
            }
        }
    }
}

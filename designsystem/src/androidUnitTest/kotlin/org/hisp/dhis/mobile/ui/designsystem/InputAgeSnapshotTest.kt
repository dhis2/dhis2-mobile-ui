package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputAge
import org.hisp.dhis.mobile.ui.designsystem.component.InputAgeModel
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.TimeUnitValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class InputAgeSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchInputAgeSnapshot() {
        paparazzi.snapshot {
            ColumnComponentContainer(modifier = Modifier.padding(Spacing.Spacing10)) {
                SubTitle("Input Age Component - Idle")
                InputAge(
                    InputAgeModel(
                        title = "Label",
                        inputType = AgeInputType.None,

                        onValueChanged = {
                        },
                    ),
                )

                SubTitle("Input Age Component - Idle Disabled")
                InputAge(
                    InputAgeModel(
                        title = "Label",
                        inputType = AgeInputType.None,
                        state = InputShellState.DISABLED,
                        onValueChanged = {
                        },
                    ),
                )

                SubTitle("Input Age Component - Date Of Birth")
                InputAge(
                    InputAgeModel(
                        title = "Label",
                        inputType = AgeInputType.DateOfBirth(
                            TextFieldValue("01011985"),
                        ),
                        state = InputShellState.DISABLED,
                        onValueChanged = {
                        },
                    ),
                )

                SubTitle("Input Age Component - Date Of Birth Required Error")
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

                SubTitle("Input Age Component - Age Disabled")
                InputAge(
                    InputAgeModel(
                        title = "Label",
                        inputType = AgeInputType.Age(value = TextFieldValue("56"), unit = TimeUnitValues.YEARS),
                        state = InputShellState.DISABLED,
                        onValueChanged = {
                        },
                    ),
                )

                SubTitle("Input Age Component - Age Required Error")
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
        }
    }
}

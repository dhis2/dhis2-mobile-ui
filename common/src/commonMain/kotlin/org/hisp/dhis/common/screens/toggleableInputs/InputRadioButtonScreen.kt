package org.hisp.dhis.common.screens.toggleableInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.common.screens.previews.lorem
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputRadioButton
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputRadioButtonScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"
    val option5 = "Option 5"
    val option6 = "Option 6"

    val radioButtonDataItemsVertical = listOf(
        RadioButtonData("0", selected = true, enabled = true, textInput = option1),
        RadioButtonData("1", selected = false, enabled = true, textInput = option2),
        RadioButtonData("2", selected = false, enabled = true, textInput = option3),
    )

    val radioButtonDataItemsError = listOf(
        RadioButtonData("3", selected = false, enabled = true, textInput = option1),
        RadioButtonData("4", selected = false, enabled = true, textInput = option2),
        RadioButtonData("5", selected = false, enabled = true, textInput = option3),
    )

    val radioButtonDataItemsDisabled = listOf(
        RadioButtonData("6", selected = true, enabled = true, textInput = option1),
        RadioButtonData("7", selected = false, enabled = true, textInput = option2),
        RadioButtonData("8", selected = false, enabled = true, textInput = option3),
    )

    val radioButtonDataItemsHorizontal = listOf(
        RadioButtonData("9", selected = true, enabled = true, textInput = option1),
        RadioButtonData("10", selected = false, enabled = true, textInput = option2),
        RadioButtonData("11", selected = false, enabled = true, textInput = option3),
        RadioButtonData("12", selected = false, enabled = true, textInput = option4),
        RadioButtonData("13", selected = false, enabled = true, textInput = option5),
        RadioButtonData("14", selected = false, enabled = true, textInput = option6),
    )

    var selectedItemVertical by remember {
        mutableStateOf<RadioButtonData?>(radioButtonDataItemsVertical[0])
    }

    var selectedItemError by remember {
        mutableStateOf<RadioButtonData?>(null)
    }

    var selectedItemDisabled by remember {
        mutableStateOf<RadioButtonData?>(null)
    }

    var selectedItemHorizontal by remember {
        mutableStateOf<RadioButtonData?>(radioButtonDataItemsHorizontal[0])
    }
    var showSupportingText by remember { mutableStateOf(false) }
    ColumnComponentContainer(ToggleableInputs.INPUT_RADIO_BUTTON.label) {
        Button(text = "Click to show/Hide supporting text") {
            showSupportingText = !showSupportingText
        }

        ColumnComponentItemContainer("Basic state with vertical orientation") {
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonDataItemsVertical,
                itemSelected = selectedItemVertical,
                onItemChange = {
                    selectedItemVertical = it
                },
                state = InputShellState.UNFOCUSED,
                supportingText = if (showSupportingText) {
                    listOf(
                        SupportingTextData("Required", state = SupportingTextState.ERROR),
                        SupportingTextData(lorem + lorem + lorem, state = SupportingTextState.WARNING),
                    )
                } else {
                    emptyList()
                },
            )
        }
        ColumnComponentItemContainer("Error state with vertical orientation") {
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonDataItemsError,
                state = InputShellState.ERROR,
                itemSelected = selectedItemError,
                onItemChange = {
                    selectedItemError = it
                },
            )
        }
        ColumnComponentItemContainer("Disabled state with vertical orientation") {
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonDataItemsDisabled,
                state = InputShellState.DISABLED,
                onItemChange = {
                    selectedItemDisabled = it
                },
            )
        }
        ColumnComponentItemContainer("Basic state with horizontal orientation") {
            InputRadioButton(
                title = "Label",
                radioButtonData = radioButtonDataItemsHorizontal,
                orientation = Orientation.HORIZONTAL,
                itemSelected = selectedItemHorizontal,
                onItemChange = {
                    selectedItemHorizontal = it
                },
                state = InputShellState.UNFOCUSED,
            )
        }
    }
}

package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonInput
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle

@Composable
fun RadioButtonInputScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"
    val option5 = "Option 5"
    val option6 = "Option 6"

    val radioButtonDataItemsVertical = remember {
        mutableStateListOf(
            RadioButtonData("0", selected = true, enabled = true, textInput = option1),
            RadioButtonData("1", selected = false, enabled = true, textInput = option2),
            RadioButtonData("2", selected = false, enabled = true, textInput = option3),
        )
    }

    val radioButtonDataItemsError = remember {
        mutableStateListOf(
            RadioButtonData("3", selected = false, enabled = true, textInput = option1),
            RadioButtonData("4", selected = false, enabled = true, textInput = option2),
            RadioButtonData("5", selected = false, enabled = true, textInput = option3),
        )
    }

    val radioButtonDataItemsDisabled = remember {
        mutableStateListOf(
            RadioButtonData("6", selected = true, enabled = true, textInput = option1),
            RadioButtonData("7", selected = false, enabled = true, textInput = option2),
            RadioButtonData("8", selected = false, enabled = true, textInput = option3),
        )
    }

    val radioButtonDataItemsHorizontal = remember {
        mutableStateListOf(
            RadioButtonData("9", selected = true, enabled = true, textInput = option1),
            RadioButtonData("10", selected = false, enabled = true, textInput = option2),
            RadioButtonData("11", selected = false, enabled = true, textInput = option3),
            RadioButtonData("12", selected = false, enabled = true, textInput = option4),
            RadioButtonData("13", selected = false, enabled = true, textInput = option5),
            RadioButtonData("14", selected = false, enabled = true, textInput = option6),
        )
    }

    ColumnComponentContainer("Radio Buttons") {
        SubTitle("Vertical")
        RadioButtonInput(
            title = "Label",
            radioButtonData = radioButtonDataItemsVertical,
            onSelectionChanged = { uid ->
                radioButtonDataItemsVertical.replaceAll {
                    it.copy(selected = it.uid == uid)
                }
            },
            onClearSelection = {
                radioButtonDataItemsVertical.replaceAll { it.copy(selected = false) }
            },
        )
        RadioButtonInput(
            title = "Label",
            radioButtonData = radioButtonDataItemsError,
            state = InputShellState.ERROR,
            onSelectionChanged = { uid ->
                radioButtonDataItemsError.replaceAll {
                    it.copy(selected = it.uid == uid)
                }
            },
            onClearSelection = {
                radioButtonDataItemsError.replaceAll { it.copy(selected = false) }
            },
        )
        RadioButtonInput(
            title = "Label",
            radioButtonData = radioButtonDataItemsDisabled,
            state = InputShellState.DISABLED,
            onSelectionChanged = { uid ->
                radioButtonDataItemsDisabled.replaceAll {
                    it.copy(selected = it.uid == uid)
                }
            },
        )
        SubTitle("Horizontal")
        RadioButtonInput(
            title = "Label",
            radioButtonData = radioButtonDataItemsHorizontal,
            orientation = Orientation.HORIZONTAL,
            onSelectionChanged = { uid ->
                radioButtonDataItemsHorizontal.replaceAll {
                    it.copy(selected = it.uid == uid)
                }
            },
            onClearSelection = {
                radioButtonDataItemsHorizontal.replaceAll { it.copy(selected = false) }
            },
        )
    }
}

package org.hisp.dhis.showcaseapp.screens.toggleableInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputCheckBox
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation

@Composable
fun InputCheckBoxScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"
    val option5 = "Option 5"
    val option6 = "Option 6"

    val checkBoxDataItemsVertical = remember {
        mutableStateListOf(
            CheckBoxData("0", checked = true, enabled = true, textInput = option1),
            CheckBoxData("1", checked = false, enabled = true, textInput = option2),
            CheckBoxData("2", checked = false, enabled = true, textInput = option3),
        )
    }

    val checkBoxDataItemsError = remember {
        mutableStateListOf(
            CheckBoxData("3", checked = true, enabled = true, textInput = option1),
            CheckBoxData("4", checked = false, enabled = true, textInput = option2),
            CheckBoxData("5", checked = false, enabled = true, textInput = option3),
        )
    }

    val checkBoxDataItemsDisabled = remember {
        mutableStateListOf(
            CheckBoxData("6", checked = true, enabled = true, textInput = option1),
            CheckBoxData("7", checked = false, enabled = true, textInput = option2),
            CheckBoxData("8", checked = false, enabled = true, textInput = option3),
        )
    }

    val checkBoxDataItemsHorizontal = remember {
        mutableStateListOf(
            CheckBoxData("9", checked = true, enabled = true, textInput = option1),
            CheckBoxData("10", checked = false, enabled = true, textInput = option2),
            CheckBoxData("11", checked = false, enabled = true, textInput = option3),
            CheckBoxData("12", checked = false, enabled = true, textInput = option4),
            CheckBoxData("13", checked = false, enabled = true, textInput = option5),
            CheckBoxData("14", checked = false, enabled = true, textInput = option6),
        )
    }

    ColumnScreenContainer(ToggleableInputs.INPUT_CHECK_BOX.label) {
        ColumnComponentContainer("Basic state with vertical orientation") {
            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataItemsVertical,
                onItemChange = { checkBoxData ->
                    val index = checkBoxDataItemsVertical.withIndex().first { it.value.uid == checkBoxData.uid }.index
                    checkBoxDataItemsVertical[index] = checkBoxData.copy(checked = !checkBoxData.checked)
                },
                onClearSelection = { checkBoxDataItemsVertical.replaceAll { it.copy(checked = false) } },
                state = InputShellState.UNFOCUSED,
            )
        }
        ColumnComponentContainer("Error state with vertical orientation") {
            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataItemsError,
                state = InputShellState.ERROR,
                onItemChange = { checkBoxData ->
                    val index = checkBoxDataItemsError.withIndex().first { it.value.uid == checkBoxData.uid }.index
                    checkBoxDataItemsError[index] = checkBoxData.copy(checked = !checkBoxData.checked)
                },
                onClearSelection = { checkBoxDataItemsError.replaceAll { it.copy(checked = false) } },
            )
        }
        ColumnComponentContainer("Disabled state with vertical orientation") {
            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataItemsDisabled,
                state = InputShellState.DISABLED,
                onItemChange = { },
                onClearSelection = { },
            )
        }
        ColumnComponentContainer("Basic state with horizontal orientation") {
            InputCheckBox(
                title = "Label",
                checkBoxData = checkBoxDataItemsHorizontal,
                orientation = Orientation.HORIZONTAL,
                onItemChange = { checkBoxData ->
                    val index = checkBoxDataItemsHorizontal.withIndex().first { it.value.uid == checkBoxData.uid }.index
                    checkBoxDataItemsHorizontal[index] = checkBoxData.copy(checked = !checkBoxData.checked)
                },
                onClearSelection = { checkBoxDataItemsHorizontal.replaceAll { it.copy(checked = false) } },
                state = InputShellState.UNFOCUSED,
            )
        }
    }
}

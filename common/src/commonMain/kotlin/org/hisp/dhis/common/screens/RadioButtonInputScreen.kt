package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonInput
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun RadioButtonInputScreen() {
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

    var selectedItemHorizontal by remember {
        mutableStateOf<RadioButtonData?>(radioButtonDataItemsHorizontal[0])
    }
    ColumnComponentContainer("Radio Buttons") {
        SubTitle("Vertical")
        RadioButtonInput(
            title = "Label",
            radioButtonData = radioButtonDataItemsVertical,
            itemSelected = selectedItemVertical,
            onItemChange = {
                selectedItemVertical = it
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))
        RadioButtonInput(
            title = "Label",
            radioButtonData = radioButtonDataItemsError,
            state = InputShellState.ERROR,
            itemSelected = selectedItemError,
            onItemChange = {
                selectedItemError = it
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))
        RadioButtonInput(
            title = "Label",
            radioButtonData = radioButtonDataItemsDisabled,
            state = InputShellState.DISABLED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Horizontal")
        RadioButtonInput(
            title = "Label",
            radioButtonData = radioButtonDataItemsHorizontal,
            orientation = Orientation.HORIZONTAL,
            itemSelected = selectedItemHorizontal,
            onItemChange = {
                selectedItemHorizontal = it
            },
        )
    }
}

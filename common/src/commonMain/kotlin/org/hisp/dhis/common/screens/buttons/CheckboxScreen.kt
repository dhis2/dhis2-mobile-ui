package org.hisp.dhis.common.screens.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.common.screens.previews.CheckboxPreview
import org.hisp.dhis.common.screens.previews.TextCheckboxPreview
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxBlock
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation

@Composable
fun CheckboxScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"

    var state1 by remember { mutableStateOf(true) }
    var state2 by remember { mutableStateOf(false) }
    var state3 by remember { mutableStateOf(true) }
    var state4 by remember { mutableStateOf(false) }

    var state5 by remember { mutableStateOf(true) }
    var state6 by remember { mutableStateOf(true) }
    var state7 by remember { mutableStateOf(false) }
    var state8 by remember { mutableStateOf(false) }

    val checkBoxesStatesHorizontal = remember {
        mutableStateListOf(
            CheckBoxData("4", checked = true, enabled = true, textInput = option1),
            CheckBoxData("5", checked = false, enabled = true, textInput = option2),
            CheckBoxData("6", checked = true, enabled = false, textInput = option3),
            CheckBoxData("7", checked = false, enabled = false, textInput = option4),
        )
    }

    val checkBoxesStatesVertical = remember {
        mutableStateListOf(
            CheckBoxData("0", checked = true, enabled = true, textInput = option1),
            CheckBoxData("1", checked = false, enabled = true, textInput = option2),
            CheckBoxData("2", checked = true, enabled = false, textInput = option3),
            CheckBoxData("3", checked = false, enabled = false, textInput = option4),
        )
    }

    ColumnScreenContainer(
        title = ButtonScreens.CHECK_BOX.label,
        content = {
            ColumnComponentContainer("Text Check Box") {
                TextCheckboxPreview(state1, true, option1) {
                    state1 = it
                }
                TextCheckboxPreview(state2, true, option2) { state2 = it }
                TextCheckboxPreview(state3, false, option3) { state3 = it }
                TextCheckboxPreview(state4, enabled = false, text = option4) { state4 = it }
            }

            ColumnComponentContainer("Simple Check Box") {
                Row {
                    CheckboxPreview(state5, enabled = true) { state5 = it }
                    CheckboxPreview(state6, enabled = false) { state6 = it }
                }
                Row {
                    CheckboxPreview(state7, enabled = true) { state7 = it }
                    CheckboxPreview(state8, enabled = false) { state8 = it }
                }
            }

            ColumnComponentContainer("Horizontal Check Box Block") {
                CheckBoxBlock(
                    Orientation.HORIZONTAL,
                    checkBoxesStatesHorizontal,
                    onItemChange = { checkBoxData ->
                        val index = checkBoxesStatesHorizontal.withIndex().first { it.value.uid == checkBoxData.uid }.index
                        checkBoxesStatesHorizontal[index] = checkBoxData.copy(checked = !checkBoxData.checked)
                    },
                )
            }

            ColumnComponentContainer("Vertical Check Box Block") {
                CheckBoxBlock(
                    Orientation.VERTICAL,
                    checkBoxesStatesVertical,
                    onItemChange = { checkBoxData ->
                        val index = checkBoxesStatesVertical.withIndex().first { it.value.uid == checkBoxData.uid }.index
                        checkBoxesStatesVertical[index] = checkBoxData.copy(checked = !checkBoxData.checked)
                    },
                )
            }
        },
    )
}

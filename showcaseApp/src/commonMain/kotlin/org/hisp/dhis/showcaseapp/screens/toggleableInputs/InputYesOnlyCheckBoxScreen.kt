package org.hisp.dhis.showcaseapp.screens.toggleableInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesOnlyCheckBox
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputYesOnlyCheckBoxScreen() {
    ColumnScreenContainer(title = ToggleableInputs.INPUT_YES_ONLY_CHECKBOX.label) {
        var checkboxData by remember {
            mutableStateOf(CheckBoxData(uid = "0", checked = false, enabled = true, textInput = "Label"))
        }
        var checkboxData1 by remember {
            mutableStateOf(CheckBoxData(uid = "0", checked = true, enabled = true, textInput = "Label"))
        }
        var checkboxData2 by remember {
            mutableStateOf(CheckBoxData(uid = "0", checked = false, enabled = true, textInput = "Label"))
        }
        val checkboxData3 by remember {
            mutableStateOf(CheckBoxData(uid = "0", checked = false, enabled = true, textInput = "Label"))
        }
        val checkboxData4 by remember {
            mutableStateOf(CheckBoxData(uid = "0", checked = true, enabled = true, textInput = "Label"))
        }
        ColumnComponentContainer("Basic state") {
            InputYesOnlyCheckBox(
                checkBoxData = checkboxData,
                state = InputShellState.UNFOCUSED,
            ) {
                checkboxData = checkboxData.copy(checked = !checkboxData.checked)
            }
        }

        ColumnComponentContainer("Selected state") {
            InputYesOnlyCheckBox(
                checkBoxData = checkboxData1,
                state = InputShellState.UNFOCUSED,
            ) {
                checkboxData1 = checkboxData1.copy(checked = !checkboxData.checked)
            }
        }

        ColumnComponentContainer("Error state") {
            InputYesOnlyCheckBox(
                checkBoxData = checkboxData2,
                state = InputShellState.ERROR,
                supportingText = listOf(SupportingTextData("Error text", SupportingTextState.ERROR)),
            ) {
                checkboxData2 = checkboxData2.copy(checked = !checkboxData.checked)
            }
        }

        ColumnComponentContainer("Disabled selected state") {
            InputYesOnlyCheckBox(
                checkBoxData = checkboxData3,
                state = InputShellState.DISABLED,
            ) {
            }
        }

        ColumnComponentContainer("Disabled state") {
            InputYesOnlyCheckBox(
                checkBoxData = checkboxData4,
                state = InputShellState.DISABLED,
            ) {
            }
        }
    }
}

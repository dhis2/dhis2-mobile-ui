package org.hisp.dhis.showcaseapp.screens.toggleableInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesOnlySwitch
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputYesOnlySwitchScreen() {
    ColumnScreenContainer(title = ToggleableInputs.INPUT_YES_ONLY_SWITCH.label) {
        var isSelected by rememberSaveable { mutableStateOf(false) }
        var isSelected1 by rememberSaveable { mutableStateOf(true) }
        var isSelected2 by rememberSaveable { mutableStateOf(false) }
        var isSelected3 by rememberSaveable { mutableStateOf(true) }
        var isSelected4 by rememberSaveable { mutableStateOf(false) }
        ColumnComponentContainer("Basic state") {
            InputYesOnlySwitch(
                title = "Label",
                isChecked = isSelected,
                state = InputShellState.UNFOCUSED,
            ) {
                isSelected = !isSelected
            }
        }

        ColumnComponentContainer("Selected state") {
            InputYesOnlySwitch(
                title = "Label",
                isChecked = isSelected1,
                state = InputShellState.UNFOCUSED,
            ) {
                isSelected1 = !isSelected1
            }
        }

        ColumnComponentContainer("Error state") {
            InputYesOnlySwitch(
                title = "Label",
                isChecked = isSelected2,
                state = InputShellState.ERROR,
                supportingText = listOf(SupportingTextData("Error text", SupportingTextState.ERROR)),
            ) {
                isSelected2 = !isSelected2
            }
        }

        ColumnComponentContainer("Disabled selected state") {
            InputYesOnlySwitch(
                title = "Label",
                isChecked = isSelected3,
                state = InputShellState.DISABLED,
            ) {
                isSelected3 = !isSelected3
            }
        }

        ColumnComponentContainer("Disabled state") {
            InputYesOnlySwitch(
                title = "Label",
                isChecked = isSelected4,
                state = InputShellState.DISABLED,
            ) {
                isSelected4 = !isSelected4
            }
        }
    }
}

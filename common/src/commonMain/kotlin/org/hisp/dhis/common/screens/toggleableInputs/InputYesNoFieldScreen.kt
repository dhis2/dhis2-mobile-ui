package org.hisp.dhis.common.screens.toggleableInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesNoField
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesNoFieldValues
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputYesNoFieldScreen() {
    var selectedItem by remember {
        mutableStateOf<InputYesNoFieldValues?>(null)
    }

    var selectedItem1 by remember {
        mutableStateOf<InputYesNoFieldValues?>(InputYesNoFieldValues.YES)
    }

    var selectedItemError by remember {
        mutableStateOf<InputYesNoFieldValues?>(null)
    }

    ColumnScreenContainer(ToggleableInputs.INPUT_YES_NO_FIELD.label) {
        ColumnComponentContainer("Basic state") {
            InputYesNoField(
                title = "Label",
                itemSelected = selectedItem,
                onItemChange = {
                    selectedItem = it
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        ColumnComponentContainer("Selected state") {
            InputYesNoField(
                title = "Label",
                itemSelected = selectedItem1,
                onItemChange = {
                    selectedItem1 = it
                },
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer("Error state") {
            InputYesNoField(
                title = "Label",
                state = InputShellState.ERROR,
                supportingText = listOf(SupportingTextData("Error text", SupportingTextState.ERROR)),
                itemSelected = selectedItemError,
                onItemChange = {
                    selectedItemError = it
                },
            )
        }

        ColumnComponentContainer("Disabled state") {
            InputYesNoField(
                title = "Label",
                state = InputShellState.DISABLED,
                onItemChange = {
                },
            )
        }
    }
}

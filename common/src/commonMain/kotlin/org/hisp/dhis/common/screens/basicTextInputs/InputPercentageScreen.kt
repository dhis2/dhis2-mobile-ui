package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputPercentage
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState

@Composable
fun InputPercentageScreen() {
    ColumnComponentContainer(title = BasicTextInputs.INPUT_PERCENTAGE.label) {
        ColumnComponentItemContainer("Basic Percentage ") {
            var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("12")) }
            InputPercentage(
                title = "Label",
                inputTextFieldValue = inputValue1,
                onValueChanged = {
                    if (it != null) {
                        inputValue1 = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentItemContainer("Basic Percentage required field") {
            var inputValueRequired by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputPercentage(
                title = "Label",
                inputTextFieldValue = inputValueRequired,
                onValueChanged = {
                    if (it != null) {
                        inputValueRequired = it
                    }
                },
                state = InputShellState.ERROR,
                isRequiredField = true,
            )
        }

        ColumnComponentItemContainer("Disabled Percentage  ") {
            var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputPercentage(
                title = "Label",
                inputTextFieldValue = inputValue6,
                state = InputShellState.DISABLED,
                onValueChanged = {
                    if (it != null) {
                        inputValue6 = it
                    }
                },
            )
        }

        ColumnComponentItemContainer("Disabled Percentage with content ") {
            var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("1234")) }
            InputPercentage(
                title = "Label",
                inputTextFieldValue = inputValue7,
                state = InputShellState.DISABLED,
                onValueChanged = {
                    if (it != null) {
                        inputValue7 = it
                    }
                },
            )
        }
    }
}

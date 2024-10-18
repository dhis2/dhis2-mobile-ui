package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputNumber
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.model.RegExValidations

@Composable
fun InputNumberScreen() {
    ColumnScreenContainer(title = BasicTextInputs.INPUT_NUMBER.label) {
        ColumnComponentContainer("Basic Input Number") {
            var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputNumber(
                title = "Label",
                inputTextFieldValue = inputValue1,
                onValueChanged = {
                    if (it != null) {
                        inputValue1 = it
                    }
                },
                notation = RegExValidations.BRITISH_DECIMAL_NOTATION,
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer("European decimal notation") {
            var inputValueEuropean by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputNumber(
                title = "Label",
                inputTextFieldValue = inputValueEuropean,
                onValueChanged = {
                    if (it != null) {
                        inputValueEuropean = it
                    }
                },
                notation = RegExValidations.EUROPEAN_DECIMAL_NOTATION,
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer("Disabled Input Number ") {
            var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputNumber(
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

        ColumnComponentContainer("Disabled Input Number with content ") {
            var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("86")) }
            InputNumber(
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

package org.hisp.dhis.showcaseapp.screens.basicTextInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputNegativeInteger
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputNegativeIntegerScreen() {
    ColumnScreenContainer(title = BasicTextInputs.INPUT_NEGATIVE_INTEGER.label) {
        ColumnComponentContainer("Basic Input Negative Integer") {
            var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("-12")) }
            InputNegativeInteger(
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

        ColumnComponentContainer("Basic Input Integer with error") {
            var inputValueError by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputNegativeInteger(
                title = "Label",
                inputTextFieldValue = inputValueError,
                onValueChanged = {
                    if (it != null) {
                        inputValueError = it
                    }
                },
                state = InputShellState.ERROR,
                supportingText = listOf(SupportingTextData("Numbers only", SupportingTextState.ERROR)),
            )
        }

        ColumnComponentContainer("Disabled Input Negative Integer ") {
            var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputNegativeInteger(
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

        ColumnComponentContainer("Disabled Input Negative Integer with content ") {
            var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("1234")) }
            InputNegativeInteger(
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

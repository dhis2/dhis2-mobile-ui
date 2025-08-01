package org.hisp.dhis.showcaseapp.screens.basicTextInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputInteger
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputIntegerScreen() {
    ColumnScreenContainer(title = BasicTextInputs.INPUT_INTEGER.label) {
        ColumnComponentContainer("Basic Input Integer") {
            var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("12")) }
            InputInteger(
                title = "Label",
                inputTextFieldValue = inputValue1,
                onValueChanged = {
                    inputValue1 = it ?: TextFieldValue()
                },
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer("Basic Input Integer with error") {
            var inputValueError by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
            InputInteger(
                title = "Label",
                inputTextFieldValue = inputValueError,
                onValueChanged = {
                    inputValueError = it ?: TextFieldValue()
                },
                state = InputShellState.ERROR,
                supportingText = listOf(SupportingTextData("Numbers only", SupportingTextState.ERROR)),
            )
        }

        ColumnComponentContainer("Disabled Input Integer ") {
            var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputInteger(
                title = "Label",
                inputTextFieldValue = inputValue6,
                state = InputShellState.DISABLED,
                onValueChanged = {
                    inputValue6 = it ?: TextFieldValue()
                },
            )
        }

        ColumnComponentContainer("Disabled Input Integer with content ") {
            var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("1234")) }
            InputInteger(
                title = "Label",
                inputTextFieldValue = inputValue7,
                state = InputShellState.DISABLED,
                onValueChanged = {
                    inputValue7 = it ?: TextFieldValue()
                },
            )
        }
    }
}

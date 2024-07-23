package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputLetter
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState

@Composable
fun InputLetterScreen() {
    ColumnScreenContainer(title = BasicTextInputs.INPUT_LETTER.label) {
        ColumnComponentContainer(" Basic Input Letter") {
            var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputLetter(
                title = "Label",
                inputTextFieldValue = inputValue1,
                onValueChanged = {
                    inputValue1 = it ?: TextFieldValue()
                },
                state = InputShellState.UNFOCUSED,
            )
        }

        ColumnComponentContainer(" Basic Input Letter with error") {
            var inputValueError by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputLetter(
                title = "Label",
                inputTextFieldValue = inputValueError,
                onValueChanged = {
                    inputValueError = it ?: TextFieldValue()
                },
                supportingText = listOf(SupportingTextData("Letters only. eg. A, B, C", SupportingTextState.ERROR)),
                state = InputShellState.ERROR,
            )
        }

        ColumnComponentContainer("Disabled Input Letter ") {
            var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputLetter(
                title = "Label",
                inputTextFieldValue = inputValue6,
                state = InputShellState.DISABLED,
                onValueChanged = {
                    inputValue6 = it ?: TextFieldValue()
                },
            )
        }

        ColumnComponentContainer("Disabled Input Letter with content ") {
            var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("A")) }
            InputLetter(
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

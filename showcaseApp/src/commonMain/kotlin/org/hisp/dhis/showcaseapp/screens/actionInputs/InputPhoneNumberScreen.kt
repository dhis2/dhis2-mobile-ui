package org.hisp.dhis.showcaseapp.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputPhoneNumber
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState

@Composable
fun InputPhoneNumberScreen() {
    ColumnScreenContainer(
        title = ActionInputs.INPUT_PHONE_NUMBER.label,
    ) {
        var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
        ColumnComponentContainer("Default Input Phone Number") {
            InputPhoneNumber(
                title = "Label",
                inputTextFieldValue = inputValue1,
                onValueChanged = {
                    if (it != null) {
                        inputValue1 = it
                    }
                },
                onCallActionClicked = {
                    // no-op
                },
                onFocusChanged = {},
                state = InputShellState.UNFOCUSED,
            )
        }

        var inputValue2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
        ColumnComponentContainer("Disabled Input Phone Number Without Phone Number") {
            InputPhoneNumber(
                title = "Label",
                inputTextFieldValue = inputValue2,
                state = InputShellState.DISABLED,
                onValueChanged = {
                    if (it != null) {
                        inputValue2 = it
                    }
                },
                onCallActionClicked = {
                    // no-op
                },
                onFocusChanged = {},
            )
        }

        var inputValue3 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("1111111")) }
        ColumnComponentContainer("Disabled Input Phone Number With Phone Number") {
            InputPhoneNumber(
                title = "Label",
                inputTextFieldValue = inputValue3,
                state = InputShellState.DISABLED,
                onValueChanged = {
                    if (it != null) {
                        inputValue3 = it
                    }
                },
                onCallActionClicked = {
                    // no-op
                },
                onFocusChanged = {},
            )
        }

        var inputValue4 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
        ColumnComponentContainer("Error Input Phone Number") {
            InputPhoneNumber(
                title = "Label",
                inputTextFieldValue = inputValue4,
                state = InputShellState.ERROR,
                isRequiredField = true,
                onValueChanged = {
                    if (it != null) {
                        inputValue4 = it
                    }
                },
                onCallActionClicked = {
                    // no-op
                },
                onFocusChanged = {},
            )
        }
    }
}

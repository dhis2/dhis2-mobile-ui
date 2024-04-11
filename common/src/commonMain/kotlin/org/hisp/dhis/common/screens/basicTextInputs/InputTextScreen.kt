package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputTextScreen() {
    ColumnComponentContainer {
        Title("Input text component", textColor = TextColor.OnSurfaceVariant)
        SubTitle(" Basic Input text", textColor = TextColor.OnSurfaceVariant)
        var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(
                TextFieldValue("Input"),
            )
        }
        val autoCompleteList = listOf("red", "yellow", "blue", "orange", "purple", "green")

        InputText(
            autoCompleteList = autoCompleteList,
            title = "Label",
            inputTextFieldValue = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
            },
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Input text with error ")
        var inputValueError by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }

        InputText(
            title = "Label",
            inputTextFieldValue = inputValueError,
            onValueChanged = {
                if (it != null) {
                    inputValueError = it
                }
            },
            state = InputShellState.ERROR,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

        SubTitle("Disabled Input text ", textColor = TextColor.OnSurfaceVariant)
        InputText(
            title = "Label",
            inputTextFieldValue = inputValue6,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue6 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Content")) }

        SubTitle("Disabled Input text with content ", textColor = TextColor.OnSurfaceVariant)
        InputText(
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

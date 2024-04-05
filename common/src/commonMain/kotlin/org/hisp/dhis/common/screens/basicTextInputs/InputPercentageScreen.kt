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
import org.hisp.dhis.mobile.ui.designsystem.component.InputPercentage
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputPercentageScreen() {
    ColumnComponentContainer {
        Title("Input Percentage component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Basic Percentage ", textColor = TextColor.OnSurfaceVariant)
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
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Basic Percentage required field", textColor = TextColor.OnSurfaceVariant)
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
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

        SubTitle("Disabled Percentage  ", textColor = TextColor.OnSurfaceVariant)
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
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("1234")) }

        SubTitle("Disabled Percentage with content ", textColor = TextColor.OnSurfaceVariant)
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

package org.hisp.dhis.common.screens

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
import org.hisp.dhis.mobile.ui.designsystem.component.InputPositiveIntegerOrZero
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputPositiveIntegerOrZeroScreen() {
    ColumnComponentContainer {
        Title("Input Positive or Zero Integer component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Basic Input Integer", textColor = TextColor.OnSurfaceVariant)
        var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("123")) }

        InputPositiveIntegerOrZero(
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

        SubTitle("Basic Input Integer with error", textColor = TextColor.OnSurfaceVariant)
        var inputValueError by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

        InputPositiveIntegerOrZero(
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
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("1234")) }

        SubTitle("Disabled Input Integer with content ", textColor = TextColor.OnSurfaceVariant)
        InputPositiveIntegerOrZero(
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

package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputNegativeInteger
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputNegativeIntegerScreen() {
    ColumnComponentContainer {
        Title("Input Negative Integer component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Basic Input Negative Integer", textColor = TextColor.OnSurfaceVariant)
        var inputValue1 by rememberSaveable { mutableStateOf("-12") }

        InputNegativeInteger(
            title = "Label",
            inputText = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
            },
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Basic Input Integer with error", textColor = TextColor.OnSurfaceVariant)
        var inputValueError by rememberSaveable { mutableStateOf("") }

        InputNegativeInteger(
            title = "Label",
            inputText = inputValueError,
            onValueChanged = {
                if (it != null) {
                    inputValueError = it
                }
            },
            state = InputShellState.ERROR,
            supportingText = listOf(SupportingTextData("Numbers only", SupportingTextState.ERROR)),
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue6 by rememberSaveable { mutableStateOf("") }

        SubTitle("Disabled Input Negative Integer ", textColor = TextColor.OnSurfaceVariant)
        InputNegativeInteger(
            title = "Label",
            inputText = inputValue6,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue6 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue7 by rememberSaveable { mutableStateOf("1234") }

        SubTitle("Disabled Input Negative Integer with content ", textColor = TextColor.OnSurfaceVariant)
        InputNegativeInteger(
            title = "Label",
            inputText = inputValue7,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue7 = it
                }
            },
        )
    }
}

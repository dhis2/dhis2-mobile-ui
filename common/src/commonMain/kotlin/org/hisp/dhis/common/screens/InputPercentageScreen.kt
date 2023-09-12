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
        var inputValue1 by rememberSaveable { mutableStateOf("12") }

        InputPercentage(
            title = "Label",
            inputText = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing8))

        SubTitle("Basic Percentage required field", textColor = TextColor.OnSurfaceVariant)
        var inputValueRequired by rememberSaveable { mutableStateOf("") }

        InputPercentage(
            title = "Label",
            inputText = inputValueRequired,
            onValueChanged = {
                if (it != null) {
                    inputValueRequired = it
                }
            },
            state = InputShellState.ERROR,
            isRequiredField = true,
        )
        Spacer(Modifier.size(Spacing.Spacing8))

        var inputValue6 by rememberSaveable { mutableStateOf("") }

        SubTitle("Disabled Percentage  ", textColor = TextColor.OnSurfaceVariant)
        InputPercentage(
            title = "Label",
            inputText = inputValue6,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue6 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing8))

        var inputValue7 by rememberSaveable { mutableStateOf("1234") }

        SubTitle("Disabled Percentage with content ", textColor = TextColor.OnSurfaceVariant)
        InputPercentage(
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

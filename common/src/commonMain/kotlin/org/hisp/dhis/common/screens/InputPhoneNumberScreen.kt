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
import org.hisp.dhis.mobile.ui.designsystem.component.Description
import org.hisp.dhis.mobile.ui.designsystem.component.InputPhoneNumber
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputPhoneNumberScreen() {
    ColumnComponentContainer(
        title = "Input Phone Number",
    ) {
        var inputValue1 by rememberSaveable { mutableStateOf("") }
        Description("Default Input Phone Number", textColor = TextColor.OnSurfaceVariant)
        InputPhoneNumber(
            title = "Label",
            inputText = inputValue1,
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
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue2 by rememberSaveable { mutableStateOf("") }
        Description("Disabled Input Phone Number Without Phone Number", textColor = TextColor.OnSurfaceVariant)
        InputPhoneNumber(
            title = "Label",
            inputText = inputValue2,
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
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue3 by rememberSaveable { mutableStateOf("1111111111") }
        Description("Disabled Input Phone Number With Phone Number", textColor = TextColor.OnSurfaceVariant)
        InputPhoneNumber(
            title = "Label",
            inputText = inputValue3,
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
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue4 by rememberSaveable { mutableStateOf("") }
        Description("Error Input Phone Number", textColor = TextColor.OnSurfaceVariant)
        InputPhoneNumber(
            title = "Label",
            inputText = inputValue4,
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
        Spacer(Modifier.size(Spacing.Spacing18))
    }
}

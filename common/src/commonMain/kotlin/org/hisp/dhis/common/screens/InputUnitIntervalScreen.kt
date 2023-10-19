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
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputUnitInterval
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputUnitIntervalScreen() {
    ColumnComponentContainer {
        Title("Unit Interval", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Keyboard: Numbers. Range between 0 and 1", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Basic unit interval ", textColor = TextColor.OnSurfaceVariant)
        var inputValue1 by rememberSaveable { mutableStateOf("0.25") }

        InputUnitInterval(
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

        SubTitle("Basic unit interval required field", textColor = TextColor.OnSurfaceVariant)
        var inputValueRequired by rememberSaveable { mutableStateOf("") }

        InputUnitInterval(
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
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue6 by rememberSaveable { mutableStateOf("") }

        SubTitle("Disabled unit interval  ", textColor = TextColor.OnSurfaceVariant)
        InputUnitInterval(
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

        var inputValue7 by rememberSaveable { mutableStateOf("0.19") }

        SubTitle("Disabled unit interval with content ", textColor = TextColor.OnSurfaceVariant)
        InputUnitInterval(
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

package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.BasicInput
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun InputScreen() {
    var inputValue1: String by rememberSaveable { mutableStateOf("Input") }
    var inputValue2: String by rememberSaveable { mutableStateOf("Input") }
    var inputValue3: String by rememberSaveable { mutableStateOf("Input") }
    var inputValue4: String by rememberSaveable { mutableStateOf("Input") }

    ColumnComponentContainer(
        title = "Input",
        content = {
            SubTitle("With helper before")
            BasicInput("Helper", helperStyle = InputStyle.WITH_HELPER_BEFORE, inputText = inputValue1, onInputChanged = { inputValue1 = it })
            Spacer(Modifier.size(Spacing.Spacing18))

            SubTitle("With helper after")
            BasicInput("Helper", helperStyle = InputStyle.WITH_HELPER_AFTER, inputText = inputValue2, onInputChanged = { inputValue2 = it })
            Spacer(Modifier.size(Spacing.Spacing18))
            SubTitle("No helper")
            BasicInput(inputText = inputValue3, onInputChanged = {
                inputValue3 = it
            })
            Spacer(Modifier.size(Spacing.Spacing18))
            SubTitle("Disabled")
            BasicInput(enabled = false, inputText = inputValue4, onInputChanged = { inputValue4 = it })
        },
    )
}

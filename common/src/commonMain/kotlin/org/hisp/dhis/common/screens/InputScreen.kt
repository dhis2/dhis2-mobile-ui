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
import org.hisp.dhis.mobile.ui.designsystem.component.BasicTextField
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.HelperStyle
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
            BasicTextField("Helper", helperStyle = HelperStyle.WITH_HELPER_BEFORE, inputTextValue = TextFieldValue(inputValue1), onInputChanged = { inputValue1 = it.text })
            Spacer(Modifier.size(Spacing.Spacing18))

            SubTitle("With helper after")
            BasicTextField("Helper", helperStyle = HelperStyle.WITH_HELPER_AFTER, inputTextValue = TextFieldValue(inputValue2), onInputChanged = { inputValue2 = it.text })
            Spacer(Modifier.size(Spacing.Spacing18))
            SubTitle("No helper")
            BasicTextField(inputTextValue = TextFieldValue(inputValue3), onInputChanged = {
                inputValue3 = it.text
            })
            Spacer(Modifier.size(Spacing.Spacing18))
            SubTitle("Disabled")
            BasicTextField(enabled = false, inputTextValue = TextFieldValue(inputValue4), onInputChanged = { inputValue4 = it.text })
        },
    )
}

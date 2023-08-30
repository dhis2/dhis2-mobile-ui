package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.BasicInput
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle

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
            BasicInput("Helper", helperStyle = InputStyle.WITH_HELPER_BEFORE, inputText = inputValue1) {
                inputValue1 = it
            }
            SubTitle("With helper after")
            BasicInput("Helper", helperStyle = InputStyle.WITH_HELPER_AFTER, inputText = inputValue2) {
                inputValue2 = it
            }
            SubTitle("No helper")
            BasicInput(inputText = inputValue3, onInputChanged = {
                inputValue3 = it
            })
            SubTitle("Disabled")
            BasicInput(enabled = false, inputText = inputValue4) {
                inputValue4 = it
            }
        },
    )
}

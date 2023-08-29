package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.BasicInput
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle

@Composable
fun InputScreen() {
    ColumnComponentContainer(
        title = "Input",
        content = {
            SubTitle("With helper before")
            BasicInput("Helper", helperStyle = InputStyle.WITH_HELPER_BEFORE, inputText = "Input") {}
            SubTitle("With helper after")
            BasicInput("Helper", helperStyle = InputStyle.WITH_HELPER_AFTER, inputText = "Input") {}
            SubTitle("No helper")
            BasicInput(inputText = "Input", onInputChanged = {})
            SubTitle("Disabled")
            BasicInput(enabled = false, inputText = "Input",  onInputChanged = {})
        }
    )
}

package org.hisp.dhis.common.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.BasicInput
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle

@Composable
fun InputScreen() {
    ColumnComponentContainer(
        content = {
            Text("With helper before")
            BasicInput("Helper", helperStyle = InputStyle.WITH_HELPER_BEFORE, inputText = "Input") {}
            Text("With helper after")
            BasicInput("Helper", helperStyle = InputStyle.WITH_HELPER_AFTER, inputText = "Input") {}
            Text("No helper")
            BasicInput(inputText = "Input") {}
            Text("Disabled")
            BasicInput(enabled = false, inputText = "Input") {}
        },
    )
}

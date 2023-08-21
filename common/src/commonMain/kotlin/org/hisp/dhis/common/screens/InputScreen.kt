package org.hisp.dhis.common.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobileui.designsystem.component.BasicInput
import org.hisp.dhis.mobileui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobileui.designsystem.component.InputStyle

@Composable
fun InputScreen() {
    ColumnComponentContainer(
        content = {
            Text("With helper before")
            BasicInput("Helper", helperStyle = InputStyle.WITH_HELPER_BEFORE)
            Text("With helper after")
            BasicInput("Helper", helperStyle = InputStyle.WITH_HELPER_AFTER)
            Text("No helper")
            BasicInput()
            Text("Disabled")
            BasicInput(enabled = false)
        }
    )
}

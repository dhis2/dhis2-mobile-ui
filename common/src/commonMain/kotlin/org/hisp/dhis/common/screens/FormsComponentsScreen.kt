package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.previews.InputShellPreview
import org.hisp.dhis.mobile.ui.designsystem.component.BasicInput
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle

@Composable
fun FormsComponentsScreen() {
    ColumnComponentContainer("Input Shell") {
        SubTitle("Sample functional Input Shell ")
        InputShellPreview("Label", inputField = { BasicInput("Helper", true, helperStyle = InputStyle.WITH_HELPER_BEFORE, onInputChanged = {}) })
        SubTitle("Unfocused Input shell ")
        InputShellPreview("Label")
        SubTitle("Focused ")
        InputShellPreview("Label", state = InputShellState.FOCUSED)
        SubTitle("Error ")
        InputShellPreview("Label", state = InputShellState.ERROR)
        SubTitle("Disabled  ")
        InputShellPreview("Label", state = InputShellState.DISABLED)
    }
}

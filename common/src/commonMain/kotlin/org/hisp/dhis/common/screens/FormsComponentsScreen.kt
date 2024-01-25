package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.InputShellPreview
import org.hisp.dhis.mobile.ui.designsystem.component.BasicTextField
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun FormsComponentsScreen() {
    ColumnComponentContainer("Input Shell") {
        SubTitle("Sample functional Input Shell ")
        InputShellPreview("Label", inputField = { BasicTextField("Helper", true, helperStyle = InputStyle.WITH_HELPER_BEFORE, onInputChanged = {}) })
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Unfocused Input shell ")
        InputShellPreview("Label")
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Focused ")
        InputShellPreview("Label", state = InputShellState.FOCUSED)
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Error ")
        InputShellPreview("Label", state = InputShellState.ERROR)
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Disabled  ")
        InputShellPreview("Label", state = InputShellState.DISABLED)

        SubTitle("Transparent Input Sheet")
        var inputField by remember { mutableStateOf("") }
        InputShellPreview(
            title = "Label",
            state = InputShellState.UNFOCUSED,
            hasTransparentBackground = true,
            onInputClear = {
                inputField = ""
            },
            inputField = {
                BasicTextField(
                    helper = "Helper",
                    inputText = inputField,
                    enabled = true,
                    helperStyle = InputStyle.WITH_HELPER_BEFORE,
                    onInputChanged = {
                        inputField = it
                    },
                )
            },
        )
    }
}

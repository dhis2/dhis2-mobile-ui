package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.InputShellPreview
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SimpleTextInputField
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun FormsComponentsScreen() {
    ColumnComponentContainer("Input Shell") {
        Text("Sample functional Input Shell ", style = MaterialTheme.typography.titleSmall)
        InputShellPreview("Label", inputField = { SimpleTextInputField() })
        Text("Unfocused Input shell ", style = MaterialTheme.typography.titleSmall)
        InputShellPreview("Label")
        Text("Focused ", style = MaterialTheme.typography.titleSmall)
        InputShellPreview("Label", state = InputShellState.FOCUSED)
        Text("Error ", style = MaterialTheme.typography.titleSmall)
        InputShellPreview("Label", state = InputShellState.ERROR)
        Text("Disabled  ", style = MaterialTheme.typography.titleSmall)
        InputShellPreview("Label", state = InputShellState.DISABLED)
        Spacer(Modifier.size(Spacing.Spacing10))
    }
}

package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobileui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobileui.designsystem.theme.Spacing

@Composable
fun FormsComponentsScreen() {
    ColumnComponentContainer("Input Shell") {
        Spacer(Modifier.size(Spacing.Spacing10))

        InputShellPreview("Label")
        Spacer(Modifier.size(Spacing.Spacing10))
        Text("Disabled InputShell")
        Spacer(Modifier.size(Spacing.Spacing10))

        InputShellPreview("Label", false)

        Spacer(Modifier.size(Spacing.Spacing10))
        Text("Error InputShell")
        Spacer(Modifier.size(Spacing.Spacing10))

        InputShellPreview("Label", showError = true)
    }
}

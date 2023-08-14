package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.component.EmptyInput
import org.hisp.dhis.mobileui.designsystem.component.InputShell
import org.hisp.dhis.mobileui.designsystem.icon.Icons
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

@Composable
internal fun InputShellPreview(title: String, enabled: Boolean = true, showError: Boolean = false) {
    InputShell(
        title, primaryIcon = {
            Icon(
                imageVector = Icons.Cancel,
                contentDescription = "Icon Button"
            )
        },
        enabled = enabled,
        secondaryIcon = {
            Icon(
                imageVector = Icons.FileDownload,
                contentDescription = "Icon Button"
            )
        },
        inputField = { EmptyInput() },
        supportingText = {
            Text(
                "Supporting text",
                color = TextColor.OnSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(Spacing.Spacing16, Spacing.Spacing4, Spacing.Spacing16, 0.dp)
            )
        },
        showError = showError,
        onClickPrimary = { onPrimaryClick() },
        onClickSecondary = { onSecondaryClick() }
    )
}

fun onPrimaryClick() {
}

fun onSecondaryClick() {
}

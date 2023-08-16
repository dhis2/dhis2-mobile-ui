package org.hisp.dhis.common.screens.previews

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.component.EmptyInput
import org.hisp.dhis.mobileui.designsystem.component.IconButton
import org.hisp.dhis.mobileui.designsystem.component.InputShell
import org.hisp.dhis.mobileui.designsystem.component.InputShellState
import org.hisp.dhis.mobileui.designsystem.component.SquareIconButton
import org.hisp.dhis.mobileui.designsystem.icon.Icons
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

@Composable
internal fun InputShellPreview(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    inputField: @Composable (() -> Unit)? = null
) {
    InputShell(
        title,
        primaryButton = {
            IconButton(
                icon = {
                    Icon(
                        imageVector = Icons.Cancel,
                        contentDescription = "Icon Button"
                    )
                },
                onClick = { onPrimaryClick() },
                enabled = state != InputShellState.DISABLED
            )
        },
        secondaryButton = {
            SquareIconButton(
                enabled = state != InputShellState.DISABLED,
                icon = {
                    Icon(
                        imageVector = Icons.FileDownload,
                        contentDescription = "Icon Button"
                    )
                },
                onClick = { onSecondaryClick() }
            )
        },
        inputField = { if (inputField != null) inputField.invoke() else EmptyInput() },
        supportingText = {
            Text(
                "Supporting text",
                color = TextColor.OnSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(Spacing.Spacing16, Spacing.Spacing4, Spacing.Spacing16, 0.dp)
            )
        },
        state = state
    )
}

fun onPrimaryClick() {
}

fun onSecondaryClick() {
}

package org.hisp.dhis.common.screens.previews

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.EmptyInput
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.InputShell
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SquareIconButton
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
internal fun InputShellPreview(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    inputField: @Composable (() -> Unit)? = null,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    onInputClear: () -> Unit = { },
) {
    InputShell(
        title,
        primaryButton = {
            IconButton(
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Icon Button",
                    )
                },
                onClick = { onInputClear() },
                enabled = state != InputShellState.DISABLED,
            )
        },
        secondaryButton = {
            SquareIconButton(
                enabled = state != InputShellState.DISABLED,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.FileDownload,
                        contentDescription = "Icon Button",
                    )
                },
                onClick = { },
            )
        },
        inputField = { if (inputField != null) inputField.invoke() else EmptyInput() },
        supportingText = {
            Text(
                "Supporting text",
                color = TextColor.OnSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(
                    Spacing.Spacing16,
                    Spacing.Spacing4,
                    Spacing.Spacing16,
                    0.dp,
                ),
            )
        },
        state = state,
        inputStyle = inputStyle,
        legend = null,
        isRequiredField = false,
    )
}

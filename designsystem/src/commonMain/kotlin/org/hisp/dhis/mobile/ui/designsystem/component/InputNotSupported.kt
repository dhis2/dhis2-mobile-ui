package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * Input filed for not supported components
 *
 * @param title: Label of the component
 */
@Composable
fun InputNotSupported(
    title: String,
    modifier: Modifier = Modifier,
    notSupportedString: String = provideStringResource("not_supported"),
) {
    InputShell(
        modifier = modifier,
        title = title,
        state = InputShellState.DISABLED,
        inputField = {
            Text(
                text = notSupportedString,
                color = TextColor.OnDisabledSurface,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
    )
}

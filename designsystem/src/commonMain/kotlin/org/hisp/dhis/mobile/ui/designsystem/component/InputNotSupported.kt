package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * Input for unsupported components
 *
 * @param title: label of the component.
 * @param modifier: optional modifier.
 * @param notSupportedString: text to be used for not supported text.
 * @param inputStyle: manages the InputShell style.
 */
@Composable
fun InputNotSupported(
    title: String,
    modifier: Modifier = Modifier,
    notSupportedString: String = provideStringResource("not_supported"),
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
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
        inputStyle = inputStyle,
        supportingText = null,
        legend = null,
        isRequiredField = false,
    )
}

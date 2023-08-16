package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor

/**
 * DHIS2 Text Input Field. Wraps Material· [BasicTextField].
 * @param enabled Controls the enabled state of the component. When `false`, this component will not be
 * clickable and will appear disabled to accessibility services.
 */
@Composable
fun SimpleTextInputField(
    enabled: Boolean = true
) {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        modifier = Modifier
            .background(
                SurfaceColor.Surface
            )
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
        },
        enabled = enabled,
        textStyle = MaterialTheme.typography.titleMedium,
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.weight(1f)) {
                    innerTextField()
                }
            }
        }
    )
}

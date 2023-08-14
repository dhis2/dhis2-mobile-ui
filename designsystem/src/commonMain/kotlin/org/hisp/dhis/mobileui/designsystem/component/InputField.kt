package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import org.hisp.dhis.mobileui.designsystem.icon.Icons
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

@Composable
fun BasicInput(
    title: String,
    enabled: Boolean = true,
    inputComponent: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    TextInputField(title, enabled, onClick = onClick)
}

/**
 * DHIS2 Text Input Field. Wraps Material· [TextField].
 * ValueType will allways be TEXT
 * @param title Controls the selected option state for multiple options.
 * @param enabled Controls the enabled state of the component. When `false`, this component will not be
 * clickable and will appear disabled to accessibility services.
 * @param showResetButton Controls reset button visibility
 * @param showSeparator Controls separator visibility
 * @param showActionButton Controls action button visibility
 * @param showLegend Controls action button visibility
 *
 * @param onClick Will be called when the user clicks the action button.
 */
@Composable
fun SimpleTextInputField(
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    placeholderText: String = "Placeholder",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(
    title: String,
    enabled: Boolean = true,
    showResetButton: Boolean = true,
    showSeparator: Boolean = true,
    showActionButton: Boolean = true,
    showLegend: Boolean = false,
    onClick: () -> Unit
) {
    var value by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier,
        value = value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = {
            value = it
        },
        enabled = enabled,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = if (enabled) SurfaceColor.Surface else SurfaceColor.DisabledSurface,
            errorIndicatorColor = SurfaceColor.Error,
            focusedIndicatorColor = SurfaceColor.Primary,
            disabledTextColor = TextColor.OnDisabledSurface,
            focusedLabelColor = SurfaceColor.Primary,
            unfocusedLabelColor = TextColor.OnSurface,
            disabledLabelColor = TextColor.OnDisabledSurface

        ),
        label = { Text(text = title) },
        supportingText = { if (enabled) Text(text = "Supporting text") },
        placeholder = { },

        trailingIcon = {
            Row() {
                IconButton(
                    style = IconButtonStyle.STANDARD,
                    enabled = enabled,
                    icon = {
                        Icon(
                            imageVector = Icons.Cancel,
                            contentDescription = "Icon Button"
                        )
                    }
                ) { }
                Divider(
                    color = Outline.Medium,
                    modifier = Modifier
                        .fillMaxHeight() // fill the max height
                        .width(Spacing.Spacing1)
                )
                SquareIconButton(enabled = enabled, icon = {
                    Icon(
                        imageVector = Icons.FileDownload,
                        contentDescription = "Icon Button"
                    )
                }, onClick = onClick)
            }
        }

    )
}

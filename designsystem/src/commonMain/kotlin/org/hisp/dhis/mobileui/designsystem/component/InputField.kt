package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobileui.designsystem.component.internal.ValueType
import org.hisp.dhis.mobileui.designsystem.icon.Icons
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

@Composable
internal fun BasicInput(valueType: ValueType, title: String) {
    when (valueType) {
        ValueType.TEXT -> TextInputField(title)
        else -> TextInputField(title)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(title: String) {
    Column() {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        Row(modifier = Modifier) {
            TextField(
                modifier = Modifier,
                value = text,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = {
                    text = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = SurfaceColor.Surface,
                    errorIndicatorColor = SurfaceColor.Error,
                    focusedIndicatorColor = SurfaceColor.Primary,
                    disabledTextColor = TextColor.OnDisabledSurface,
                    focusedLabelColor = SurfaceColor.Primary,
                    unfocusedLabelColor = TextColor.OnSurface
                ),
                trailingIcon = {
                    Row() {
                        IconButton(
                            style = IconButtonStyle.STANDARD,
                            enabled = true,
                            icon = {
                                Icon(
                                    imageVector = Icons.Cancel,
                                    contentDescription = "Icon Button"
                                )
                            }
                        ) {}
                        Divider(
                            color = Outline.Medium,
                            modifier = Modifier
                                .fillMaxHeight() // fill the max height
                                .width(Spacing.Spacing1)
                        )
                        SquareIconButton(enabled = true, icon = {
                            Icon(
                                imageVector = Icons.FileDownload,
                                contentDescription = "Icon Button"
                            )
                        }) {}
                    }
                },
                label = { Text(text = title) },
                supportingText = { Text(text = "Supporting text") },
                placeholder = { }
            )
        }
    }
}

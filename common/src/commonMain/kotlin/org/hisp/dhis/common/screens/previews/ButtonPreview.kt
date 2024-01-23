package org.hisp.dhis.common.screens.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColorStyle

@Composable
internal fun ButtonPreview(
    text: String,
    style: ButtonStyle = ButtonStyle.OUTLINED,
    enabled: Boolean = true,
    colorStyle: ColorStyle = ColorStyle.DEFAULT,
) {
    Button(
        enabled = enabled,
        style = style,
        colorStyle = colorStyle,
        text = text,
    ) { }
}

@Composable
internal fun ButtonPreviewWithIcon(
    text: String,
    style: ButtonStyle = ButtonStyle.OUTLINED,
    enabled: Boolean = true,
    colorStyle: ColorStyle = ColorStyle.DEFAULT,
) {
    Button(
        enabled = enabled,
        style = style,
        colorStyle = colorStyle,
        text = text,
        icon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Button",
            )
        },
    ) { }
}

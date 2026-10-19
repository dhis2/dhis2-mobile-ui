@file:Suppress("DEPRECATION")

package org.hisp.dhis.showcaseapp.screens.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonState
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
internal fun ButtonPreview(
    text: String,
    style: ButtonStyle,
    enabled: Boolean = true,
    textColor: Color,
) {
    Button(
        state = ButtonState(
            text = text,
            textColor = textColor,
            enabled = enabled,
            style = style,
            colorStyle = ColorStyle.DEFAULT,
        ),
    ){ }
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

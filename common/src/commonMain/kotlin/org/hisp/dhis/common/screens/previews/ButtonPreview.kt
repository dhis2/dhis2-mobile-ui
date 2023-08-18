package org.hisp.dhis.common.screens.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobileui.designsystem.component.Button
import org.hisp.dhis.mobileui.designsystem.component.ButtonStyle

@Composable
internal fun ButtonPreview(text: String, style: ButtonStyle = ButtonStyle.OUTLINED, enabled: Boolean = true) {
    Button(
        enabled = enabled,
        style,
        text
    ) { }
}

@Composable
internal fun ButtonPreviewWithIcon(text: String, style: ButtonStyle = ButtonStyle.OUTLINED, enabled: Boolean = true) {
    Button(
        enabled = enabled,
        style,
        text,
        icon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Button"
            )
        }
    ) { }
}

package org.hisp.dhis.common.screens

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobileui.designsystem.component.Button
import org.hisp.dhis.mobileui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobileui.designsystem.icon.Icons

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
                imageVector = Icons.Plus,
                contentDescription = "Button"
            )
        }
    ) { }
}

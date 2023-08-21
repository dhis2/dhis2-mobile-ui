package org.hisp.dhis.common.screens.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.IconButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SquareIconButton

@Composable
internal fun SquareIconButtonPreview(enabled: Boolean = true) {
    SquareIconButton(
        enabled = enabled,
        icon = {
            Icon(
                imageVector = Icons.Outlined.FileDownload,
                contentDescription = "Square Icon Button"
            )
        }
    ) { }
}

@Composable
internal fun IconButtonPreview(enabled: Boolean = true, style: IconButtonStyle = IconButtonStyle.STANDARD) {
    IconButton(
        style = style,
        enabled = enabled,
        icon = {
            Icon(
                imageVector = Icons.Outlined.FileDownload,
                contentDescription = "Icon Button"
            )
        }
    ) { }
}

package org.hisp.dhis.common.screens

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.common.designsystem.component.IconButton
import org.hisp.dhis.common.designsystem.component.SquareIconButton
import org.hisp.dhis.common.designsystem.icon.Icons

@Composable
internal fun SquareIconButtonPreview(enabled: Boolean = true) {
    SquareIconButton(
        enabled = enabled,
        icon = {
            Icon(
                imageVector = Icons.FileDownload,
                contentDescription = "Square Icon Button"
            )
        }
    ) { }
}

@Composable
internal fun IconButtonPreview(enabled: Boolean = true) {
    IconButton(
        enabled = enabled,
        icon = {
            Icon(
                imageVector = Icons.FileDownload,
                contentDescription = "Icon Button"
            )
        }
    ) { }
}

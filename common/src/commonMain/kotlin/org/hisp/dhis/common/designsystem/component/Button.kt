package org.hisp.dhis.common.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.designsystem.icon.Icons
import org.hisp.dhis.common.designsystem.theme.Dimen

@Composable
fun SquareIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = Modifier,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(Dimen.spacing4)
    ) {
        icon()
    }
}

@Composable
internal fun SquareIconButtonPreview(enabled: Boolean = true) {
    SquareIconButton(
        enabled = enabled,
        icon = {
            Icon(
                imageVector = Icons.FileDownload,
                contentDescription = ""
            )
        } // TODO("Do we need content description?")
    ) { }
}

@Composable
fun IconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        icon()
    }
}

@Composable
internal fun IconButtonPreview(enabled: Boolean = true) {
    IconButton(
        enabled = enabled,
        icon = {
            Icon(
                imageVector = Icons.FileDownload,
                contentDescription = ""
            )
        } // TODO("Do we need content description?")
    ) { }
}

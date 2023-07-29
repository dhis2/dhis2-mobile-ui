package org.hisp.dhis.common.designsystem.component

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.designsystem.icon.Icons

@Composable
fun SquareIconButton(
    icon: @Composable (() -> Unit),
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier,
        onClick = onClick
    ) {
        icon()
    }
}

@Composable
internal fun SquareIconButtonPreview() {
    SquareIconButton(
        icon = {
            Icon(
                imageVector = Icons.FileDownload,
                contentDescription = ""
            )
        } // TODO("Do we need content description?")
    ) { }
}

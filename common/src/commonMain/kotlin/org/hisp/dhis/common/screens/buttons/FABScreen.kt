package org.hisp.dhis.common.screens.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ExtendedFAB
import org.hisp.dhis.mobile.ui.designsystem.component.FAB
import org.hisp.dhis.mobile.ui.designsystem.component.FABStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun FABScreen() {
    ColumnComponentContainer(
        title = "FABs",
    ) {
        FAB(
            style = FABStyle.SURFACE,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.FileDownload,
                    contentDescription = "File download Button",
                )
            },
        )

        FAB(
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.FileDownload,
                    contentDescription = "File download Button",
                )
            },
        )

        FAB(
            style = FABStyle.SECONDARY,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.FileDownload,
                    contentDescription = "File download Button",
                )
            },
        )

        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle(text = "Extended FABs buttons")

        ExtendedFAB(
            style = FABStyle.SURFACE,
            text = "Label",
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.FileDownload,
                    contentDescription = "File download Button",
                )
            },
        )

        ExtendedFAB(
            text = "Label",
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.FileDownload,
                    contentDescription = "File download Button",
                )
            },
        )

        ExtendedFAB(
            text = "Label",
            style = FABStyle.SECONDARY,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Outlined.FileDownload,
                    contentDescription = "File download Button",
                )
            },
        )

        Spacer(Modifier.size(Spacing.Spacing18))
    }
}

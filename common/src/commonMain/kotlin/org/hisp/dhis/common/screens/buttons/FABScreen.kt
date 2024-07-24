package org.hisp.dhis.common.screens.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ExtendedFAB
import org.hisp.dhis.mobile.ui.designsystem.component.FAB
import org.hisp.dhis.mobile.ui.designsystem.component.FABStyle

@Composable
fun FABScreen() {
    ColumnScreenContainer(
        title = ButtonScreens.FAB.label,
    ) {
        ColumnComponentContainer("Default FABs") {
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
        }

        ColumnComponentContainer("Extended FABs buttons") {
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
        }
    }
}

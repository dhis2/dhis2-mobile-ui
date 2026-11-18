package org.hisp.dhis.showcaseapp.screens.others

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBar
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme

const val LOREM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"

@Composable
fun InfoBarScreen() {
    ColumnScreenContainer(title = "Info Bar") {
        InfoBar(
            text = "Label",
            textColor = DHIS2Theme.extendedColors.onSurfaceLight,
            backgroundColor = DHIS2Theme.colorScheme.surface,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "label",
                    tint = DHIS2Theme.extendedColors.onSurfaceLight,
                )
            },
        )
        InfoBar(
            text = LOREM,
            textColor = DHIS2Theme.extendedColors.onSurfaceLight,
            backgroundColor = DHIS2Theme.colorScheme.surface,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "lorem",
                    tint = DHIS2Theme.extendedColors.onSurfaceLight,
                )
            },
        )

        InfoBar(
            text = "Enrollment completed",
            textColor = AdditionalInfoItemColor.SUCCESS.color,
            backgroundColor = AdditionalInfoItemColor.SUCCESS.color.copy(alpha = 0.1f),
            icon = {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "enrollment completed",
                    tint = AdditionalInfoItemColor.SUCCESS.color,
                )
            },
        )

        InfoBar(
            text = "Enrollment cancelled",
            textColor = DHIS2Theme.extendedColors.onSurfaceLight,
            backgroundColor = DHIS2Theme.colorScheme.surface,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Block,
                    contentDescription = "enrollment cancelled",
                    tint = DHIS2Theme.extendedColors.onSurfaceLight,
                )
            },
        )

        InfoBar(
            text = "Not synced",
            textColor = DHIS2Theme.extendedColors.onSurfaceLight,
            backgroundColor = DHIS2Theme.colorScheme.surface,
            actionText = "Sync",
            icon = {
                Icon(
                    imageVector = Icons.Filled.Sync,
                    contentDescription = "not synced",
                    tint = DHIS2Theme.extendedColors.onSurfaceLight,
                )
            },
            onActionClick = {},
        )

        InfoBar(
            text = "Not synced",
            textColor = DHIS2Theme.colorScheme.onErrorContainer,
            backgroundColor = DHIS2Theme.colorScheme.errorContainer,
            actionText = "Retry sync",
            icon = {
                Icon(
                    imageVector = Icons.Filled.Sync,
                    contentDescription = "not synced error",
                    tint = DHIS2Theme.colorScheme.onErrorContainer,
                )
            },
            onActionClick = {},
        )

        InfoBar(
            text = "Synce warning",
            textColor = DHIS2Theme.extendedColors.onWarningContainer,
            backgroundColor = DHIS2Theme.extendedColors.warningContainer,
            actionText = "Retry sync",
            icon = {
                Icon(
                    imageVector = Icons.Filled.Sync,
                    contentDescription = "not synced",
                    tint = DHIS2Theme.extendedColors.onWarningContainer,
                )
            },
            onActionClick = {},
        )

        InfoBar(
            text = "Marked for follow-up",
            textColor = DHIS2Theme.extendedColors.onSurfaceLight,
            backgroundColor = DHIS2Theme.colorScheme.surface,
            actionText = "Remove",
            icon = {
                Icon(
                    imageVector = Icons.Filled.Flag,
                    contentDescription = "not synced",
                    tint = DHIS2Theme.extendedColors.customOrange,
                )
            },
            onActionClick = {},
        )

        InfoBar(
            text = "Downloading file resources...",
            textColor = DHIS2Theme.extendedColors.onSurfaceLight,
            backgroundColor = DHIS2Theme.colorScheme.surface,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "info",
                    tint = DHIS2Theme.extendedColors.onSurfaceLight,
                )
            },
            displayProgress = true,
        )

        InfoBar(
            text = LOREM,
            textColor = DHIS2Theme.extendedColors.onSurfaceLight,
            backgroundColor = DHIS2Theme.colorScheme.surface,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "info",
                    tint = DHIS2Theme.extendedColors.onSurfaceLight,
                )
            },
            displayProgress = true,
        )

        InfoBar(
            text = LOREM,
            textColor = DHIS2Theme.extendedColors.onSurfaceLight,
            backgroundColor = DHIS2Theme.colorScheme.surface,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "info",
                    tint = DHIS2Theme.extendedColors.onSurfaceLight,
                )
            },
            actionText = "Action",
            onActionClick = {},
        )
    }
}

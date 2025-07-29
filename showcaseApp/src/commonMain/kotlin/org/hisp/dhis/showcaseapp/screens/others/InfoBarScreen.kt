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
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

const val LOREM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"

@Composable
fun InfoBarScreen() {
    ColumnScreenContainer(title = "Info Bar") {
        InfoBar(
            text = "Label",
            textColor = TextColor.OnSurfaceLight,
            backgroundColor = SurfaceColor.Surface,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "label",
                    tint = TextColor.OnSurfaceLight,
                )
            },
        )
        InfoBar(
            text = LOREM,
            textColor = TextColor.OnSurfaceLight,
            backgroundColor = SurfaceColor.Surface,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "lorem",
                    tint = TextColor.OnSurfaceLight,
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
            textColor = TextColor.OnSurfaceLight,
            backgroundColor = SurfaceColor.Surface,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Block,
                    contentDescription = "enrollment cancelled",
                    tint = TextColor.OnSurfaceLight,
                )
            },
        )

        InfoBar(
            text = "Not synced",
            textColor = TextColor.OnSurfaceLight,
            backgroundColor = SurfaceColor.Surface,
            actionText = "Sync",
            icon = {
                Icon(
                    imageVector = Icons.Filled.Sync,
                    contentDescription = "not synced",
                    tint = TextColor.OnSurfaceLight,
                )
            },
            onActionClick = {},
        )

        InfoBar(
            text = "Not synced",
            textColor = TextColor.OnErrorContainer,
            backgroundColor = SurfaceColor.ErrorContainer,
            actionText = "Retry sync",
            icon = {
                Icon(
                    imageVector = Icons.Filled.Sync,
                    contentDescription = "not synced error",
                    tint = TextColor.OnErrorContainer,
                )
            },
            onActionClick = {},
        )

        InfoBar(
            text = "Synce warning",
            textColor = TextColor.OnWarningContainer,
            backgroundColor = SurfaceColor.WarningContainer,
            actionText = "Retry sync",
            icon = {
                Icon(
                    imageVector = Icons.Filled.Sync,
                    contentDescription = "not synced",
                    tint = TextColor.OnWarningContainer,
                )
            },
            onActionClick = {},
        )

        InfoBar(
            text = "Marked for follow-up",
            textColor = TextColor.OnSurfaceLight,
            backgroundColor = SurfaceColor.Surface,
            actionText = "Remove",
            icon = {
                Icon(
                    imageVector = Icons.Filled.Flag,
                    contentDescription = "not synced",
                    tint = SurfaceColor.CustomOrange,
                )
            },
            onActionClick = {},
        )

        InfoBar(
            text = "Downloading file resources...",
            textColor = TextColor.OnSurfaceLight,
            backgroundColor = SurfaceColor.Surface,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "info",
                    tint = TextColor.OnSurfaceLight,
                )
            },
            displayProgress = true,
        )

        InfoBar(
            text = LOREM,
            textColor = TextColor.OnSurfaceLight,
            backgroundColor = SurfaceColor.Surface,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "info",
                    tint = TextColor.OnSurfaceLight,
                )
            },
            displayProgress = true,
        )

        InfoBar(
            text = LOREM,
            textColor = TextColor.OnSurfaceLight,
            backgroundColor = SurfaceColor.Surface,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "info",
                    tint = TextColor.OnSurfaceLight,
                )
            },
            actionText = "Action",
            onActionClick = {},
        )
    }
}

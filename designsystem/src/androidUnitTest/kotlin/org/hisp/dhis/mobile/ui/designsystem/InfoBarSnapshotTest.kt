package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBar
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.junit.Rule
import org.junit.Test

const val LOREM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"

class InfoBarSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @OptIn(ExperimentalResourceApi::class)
    @Test
    fun launchInfoBarTest() {
        paparazzi.snapshot {
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
    }
}

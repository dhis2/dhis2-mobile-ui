package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBar
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.junit.Rule
import org.junit.Test

const val LOREM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"

class InfoBarSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchInfoBarTest() {
        paparazzi.themedSnapshots {
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
    }
}

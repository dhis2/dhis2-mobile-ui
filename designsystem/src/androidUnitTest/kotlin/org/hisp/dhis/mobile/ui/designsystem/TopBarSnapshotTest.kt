package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.TopBar
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarActionIcon
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarDropdownMenuIcon
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarType
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class TopBarSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchTopBar() {
        paparazzi.snapshot {
            ColumnScreenContainer(title = "Top bars") {
                ColumnComponentContainer(subTitle = "Default") {
                    TopBar(
                        type = TopBarType.DEFAULT,
                        title = "Title",
                        navigationIcon = {
                            IconButton(
                                onClick = { },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Menu,
                                        contentDescription = "Menu Button",
                                    )
                                },
                            )
                        },
                        actions = {
                            TopBarActionIcon(
                                icon = Icons.Outlined.Share,
                                onClick = { },
                            )
                            TopBarActionIcon(
                                icon = Icons.Outlined.FileDownload,
                                onClick = { },
                            )
                            TopBarDropdownMenuIcon {
                            }
                        },
                        color = SurfaceColor.PrimaryContainer,
                    )
                }

                ColumnComponentContainer(subTitle = "Centered") {
                    TopBar(
                        type = TopBarType.CENTERED,
                        title = "Title",
                        navigationIcon = {
                            IconButton(
                                onClick = { },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Menu,
                                        contentDescription = "Menu Button",
                                    )
                                },
                            )
                        },
                        actions = {
                            TopBarActionIcon(
                                icon = Icons.Outlined.Share,
                                onClick = { },
                            )
                            TopBarActionIcon(
                                icon = Icons.Outlined.FileDownload,
                                onClick = { },
                            )
                        },
                        color = SurfaceColor.PrimaryContainer,
                    )
                }
            }
        }
    }
}

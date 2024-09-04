package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.TopBar
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarActionIcon
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarDropdownMenuIcon
import org.hisp.dhis.mobile.ui.designsystem.component.TopBarType
import org.junit.Rule
import org.junit.Test

class TopBarSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun launchTopBar() {
        paparazzi.snapshot {
            ColumnScreenContainer(title = "Top bars") {
                ColumnComponentContainer(subTitle = "Default") {
                    TopBar(
                        type = TopBarType.DEFAULT,
                        title = {
                            Text(
                                text = "Title",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
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
                            TopBarDropdownMenuIcon { _, _ ->
                            }
                        },
                    )
                }

                ColumnComponentContainer(subTitle = "Centered") {
                    TopBar(
                        type = TopBarType.CENTERED,
                        title = {
                            Text(
                                text = "Title",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
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
                    )
                }
            }
        }
    }
}

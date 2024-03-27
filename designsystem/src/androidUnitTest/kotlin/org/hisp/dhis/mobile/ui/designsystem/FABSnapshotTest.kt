package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ExtendedFAB
import org.hisp.dhis.mobile.ui.designsystem.component.FAB
import org.hisp.dhis.mobile.ui.designsystem.component.FABStyle
import org.junit.Rule
import org.junit.Test

class FABSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchFAB() {
        paparazzi.snapshot {
            ColumnComponentContainer {
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
}

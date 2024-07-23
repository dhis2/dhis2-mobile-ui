package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ExtendedFAB
import org.hisp.dhis.mobile.ui.designsystem.component.FAB
import org.hisp.dhis.mobile.ui.designsystem.component.FABStyle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class FABSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchFAB() {
        paparazzi.snapshot {
            ColumnScreenContainer(modifier = Modifier.padding(Spacing.Spacing10)) {
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

package org.hisp.dhis.common.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.CarouselButton
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Title

@Composable
fun ButtonCarouselScreen() {
    ColumnComponentContainer {
        Title("Carousel Buttons")
        RowComponentContainer(
            title = "Simple Carousel Button",
        ) {
            CarouselButton(
                enabled = true,
                textInput = "label",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.FileDownload,
                        contentDescription = "Carousel Button",
                    )
                },
                onClick = {}
            )
        }
    }
}
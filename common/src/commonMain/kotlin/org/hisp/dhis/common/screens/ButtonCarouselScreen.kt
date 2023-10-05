package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.fiveButtonCarousel
import org.hisp.dhis.common.screens.previews.overflowButtonCarousel
import org.hisp.dhis.common.screens.previews.threeButtonCarousel
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonCarousel
import org.hisp.dhis.mobile.ui.designsystem.component.CarouselButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title

@Composable
fun ButtonCarouselScreen() {
    ColumnComponentContainer {
        Title("Carousel Buttons")
        RowComponentContainer(
            title = "Simple Carousel Button",
        ) {
            ButtonCarousel(
                carouselButtonList = listOf(
                    CarouselButtonData(
                        enabled = true,
                        text = "Label",
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.FileDownload,
                                contentDescription = "Carousel Button",
                            )
                        },
                        onClick = {},
                    ),
                ),
            )
        }
        RowComponentContainer(
            title = "Buttons Carousel",
        ) {
            Column(
                Modifier.fillMaxWidth(),
            ) {
                ButtonCarousel(
                    carouselButtonList = threeButtonCarousel,
                )

                ButtonCarousel(
                    carouselButtonList = fiveButtonCarousel,
                )

                SubTitle("Overflow case")
                ButtonCarousel(
                    carouselButtonList = overflowButtonCarousel,
                )
            }
        }
    }
}

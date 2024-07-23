package org.hisp.dhis.common.screens.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.previews.fiveButtonCarousel
import org.hisp.dhis.common.screens.previews.overflowButtonCarousel
import org.hisp.dhis.common.screens.previews.threeButtonCarousel
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonCarousel
import org.hisp.dhis.mobile.ui.designsystem.component.CarouselButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer

@Composable
fun ButtonCarouselScreen() {
    ColumnComponentContainer(title = ButtonScreens.CAROUSEL_BUTTONS.label) {
        ColumnComponentItemContainer("Simple Carousel Button") {
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

        ColumnComponentItemContainer("Buttons Carousel") {
            ButtonCarousel(
                carouselButtonList = threeButtonCarousel,
            )

            ButtonCarousel(
                carouselButtonList = fiveButtonCarousel,
            )
        }

        ColumnComponentItemContainer("Overflow case") {
            ButtonCarousel(
                carouselButtonList = overflowButtonCarousel,
            )
        }
    }
}

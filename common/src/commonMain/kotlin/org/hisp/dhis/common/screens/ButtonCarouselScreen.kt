package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.AddTask
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonCarousel
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
                textInput = "Label",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.FileDownload,
                        contentDescription = "Carousel Button",
                    )
                },
                onClick = {},
            )
        }
        RowComponentContainer(
            title = "Buttons Carousel",
        ) {
            Column(
                Modifier.fillMaxWidth(),
            ) {
                ButtonCarousel(
                    carouselButtonList = {
                        Box(
                            Modifier.weight(1f),
                            contentAlignment = Alignment.Center,
                        ) {
                            CarouselButton(
                                enabled = true,
                                textInput = "Share",
                                icon = {
                                    Icon(
                                        Icons.Filled.Share,
                                        contentDescription = "",
                                    )
                                },
                                onClick = {},
                            )
                        }
                        Box(
                            Modifier.weight(1f),
                            contentAlignment = Alignment.Center,
                        ) {
                            CarouselButton(
                                enabled = true,
                                textInput = "Scan",
                                icon = {
                                    Icon(
                                        Icons.Filled.QrCodeScanner,
                                        contentDescription = "",
                                    )
                                },
                                onClick = {},
                            )
                        }
                        Box(
                            Modifier.weight(1f),
                            contentAlignment = Alignment.Center,
                        ) {
                            CarouselButton(
                                enabled = true,
                                textInput = "Download",
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.FileDownload,
                                        contentDescription = "",
                                    )
                                },
                                onClick = {},
                            )
                        }
                    },

                )

                ButtonCarousel(
                    carouselButtonList = {
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            CarouselButton(
                                enabled = true,
                                textInput = "Two lines share label",
                                icon = {
                                    Icon(
                                        Icons.Filled.Share,
                                        contentDescription = "",
                                    )
                                },
                                onClick = {},
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            CarouselButton(
                                enabled = true,
                                textInput = "Scan Barcode",
                                icon = {
                                    Icon(
                                        Icons.Filled.DocumentScanner,
                                        contentDescription = "",
                                    )
                                },
                                onClick = {},
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            CarouselButton(
                                enabled = true,
                                textInput = "Download files",
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.FileDownload,
                                        contentDescription = "",
                                    )
                                },
                                onClick = {},
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            CarouselButton(
                                enabled = true,
                                textInput = "Filter list",
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.FilterAlt,
                                        contentDescription = "",
                                    )
                                },
                                onClick = {},
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            CarouselButton(
                                enabled = true,
                                textInput = "Add task to done",
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.AddTask,
                                        contentDescription = "",
                                    )
                                },
                                onClick = {},
                            )
                        }
                    },

                )
            }
        }
    }
}

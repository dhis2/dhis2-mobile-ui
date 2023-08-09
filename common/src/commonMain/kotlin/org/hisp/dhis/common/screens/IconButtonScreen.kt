package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.screens.previews.IconButtonPreview
import org.hisp.dhis.common.screens.previews.SquareIconButtonPreview
import org.hisp.dhis.mobileui.designsystem.component.IconButtonStyle

@Composable
fun IconButtonScreen() {
    Column(modifier = Modifier.padding(10.dp)) {
        // SquareIconButton
        ComponentContainer(
            title = "SquareIconButton",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(10.dp)
                ) {
                    SquareIconButtonPreview()
                    SquareIconButtonPreview(false)
                }
            }
        )
        // IconButton
        ComponentContainer(
            title = "IconButton",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ComponentContainer(
                        title = "Standard",
                        content = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                IconButtonPreview()
                                IconButtonPreview(false)
                            }
                        }
                    )
                }
                ComponentContainer(
                    title = "Filled",
                    content = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            IconButtonPreview(true, IconButtonStyle.FILLED)
                            IconButtonPreview(false, IconButtonStyle.FILLED)
                        }
                    }
                )
                ComponentContainer(
                    title = "Tonal",
                    content = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            IconButtonPreview(true, IconButtonStyle.TONAL)
                            IconButtonPreview(false, IconButtonStyle.TONAL)
                        }
                    }
                )
                ComponentContainer(
                    title = "Outlined",
                    content = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            IconButtonPreview(true, IconButtonStyle.OUTLINED)
                            IconButtonPreview(false, IconButtonStyle.OUTLINED)
                        }
                    }
                )
            }
        )
    }
}

@Composable
fun ComponentContainer(
    title: String,
    content: @Composable (() -> Unit)
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = title, fontWeight = FontWeight.Bold)
        content()
    }
}

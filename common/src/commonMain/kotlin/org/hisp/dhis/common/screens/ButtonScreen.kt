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

@Composable
fun ButtonScreen() {
    Column(modifier = Modifier.padding(10.dp)) {
        // SquareIconButton
        ComponentContainer(
            title = "SquareIconButton",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
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
                    IconButtonPreview()
                    IconButtonPreview(false)
                }
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

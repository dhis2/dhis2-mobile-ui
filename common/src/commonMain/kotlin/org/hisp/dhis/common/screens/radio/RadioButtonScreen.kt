package org.hisp.dhis.common.screens.radio

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
fun RadioButtonScreen() {
    Column(modifier = Modifier.padding(10.dp)) {
        // RadioButton
        ComponentContainer(
            title = "RadioButton",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    RadioButtonPreview()
                    RadioButtonDisabledPreview()
                    TextRadioButtonPreview()
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
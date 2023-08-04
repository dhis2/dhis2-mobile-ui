package org.hisp.dhis.common.screens.radio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonScreen() {

    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"

    var selected by remember {
        mutableStateOf(option1)
    }

    Column(modifier = Modifier.padding(10.dp)) {
        // RadioButton
        ComponentContainer(
            title = "Text Radio Button",
            content = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                        TextRadioButtonPreview(selected == option1, true, option1) {
                            selected = option1
                        }
                        TextRadioButtonPreview(selected == option2, true, option2) {
                            selected = option2
                        }
                        TextRadioButtonPreview(selected == option3, true, option3) {
                            selected = option3
                        }
                        TextRadioButtonPreview(selected == option4, false, option4) {
                            selected = option1
                        }

                    }
            }
        )
        ComponentContainer(
            title = "Radio Button",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    RadioButtonPreview(true, true)
                    RadioButtonPreview(false, true)
                    RadioButtonPreview(true, false)
                    RadioButtonPreview(false, false)
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
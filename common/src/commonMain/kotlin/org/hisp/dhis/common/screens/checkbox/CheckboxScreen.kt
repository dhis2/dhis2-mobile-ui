package org.hisp.dhis.common.screens.checkbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.screens.ComponentContainer

@Composable
fun CheckboxScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"

    val state = mutableStateOf(true)

    val state5 = mutableStateOf(false)

    val state6 = mutableStateOf(true)

    val state7 = mutableStateOf(false)

    Column(modifier = Modifier.padding(10.dp)) {
        ComponentContainer(
            title = "Text Check Box",
            content = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TextCheckboxPreview(state, true, option1)
                    TextCheckboxPreview(state5, true, option2)
                    TextCheckboxPreview(state6, false, option3)
                    TextCheckboxPreview(state7, false, option4)
                }
            }
        )
        ComponentContainer(
            title = "Check Box",
            content = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row {
                        CheckboxPreview(mutableStateOf(true), enabled = true)
                        CheckboxPreview(mutableStateOf(true), enabled = false)
                    }
                    Row {
                        CheckboxPreview(mutableStateOf(false), enabled = true)
                        CheckboxPreview(mutableStateOf(false), enabled = false)
                    }
                }
            }
        )
    }
}

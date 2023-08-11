package org.hisp.dhis.common.screens.checkbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.screens.ComponentContainer

@Composable
fun CheckboxScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"

    var state1 by rememberSaveable {
        mutableStateOf(true)
    }
    var state2 by rememberSaveable {
        mutableStateOf(true)
    }
    var state3 by rememberSaveable {
        mutableStateOf(true)
    }
    var state4 by rememberSaveable {
        mutableStateOf(true)
    }

    Column(modifier = Modifier.padding(10.dp)) {
        // RadioButton
        ComponentContainer(
            title = "Text Radio Button",
            content = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TextCheckboxPreview(state1, true, option1) {
                        state1 = !state1
                    }
                    TextCheckboxPreview(!state2, true, option2) {
                        state2 = !state2
                    }
                    TextCheckboxPreview(state3, false, option3) {
                        state3 = !state3
                    }
                    TextCheckboxPreview(!state4, enabled = false, text = option4) {
                        state4 = !state4
                    }
                }
            }
        )
        ComponentContainer(
            title = "Radio Button",
            content = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row {
                        CheckboxPreview(checked = true, enabled = true)
                        CheckboxPreview(checked = true, enabled = false)
                    }
                    Row {
                        CheckboxPreview(checked = false, enabled = true)
                        CheckboxPreview(checked = false, enabled = false)
                    }
                }
            }
        )
    }
}
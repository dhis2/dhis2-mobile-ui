package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.screens.previews.CheckboxPreview
import org.hisp.dhis.common.screens.previews.TextCheckboxPreview
import org.hisp.dhis.mobileui.designsystem.component.ColumnComponentContainer

@Composable
fun CheckboxScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"

    val state1 = mutableStateOf(true)
    val state2 = mutableStateOf(false)
    val state3 = mutableStateOf(true)
    val state4 = mutableStateOf(false)

    Column(modifier = Modifier.padding(10.dp)) {
        ColumnComponentContainer(
            title = "Text Check Box",
            content = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TextCheckboxPreview(state1, true, option1)
                    TextCheckboxPreview(state2, true, option2)
                    TextCheckboxPreview(state3, false, option3)
                    TextCheckboxPreview(state4, enabled = false, text = option4)
                }
            }
        )
        ColumnComponentContainer(
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

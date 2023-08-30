package org.hisp.dhis.common.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.common.screens.previews.RadioButtonPreview
import org.hisp.dhis.common.screens.previews.TextRadioButtonPreview
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer

@Composable
fun RadioButtonScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"

    var selected by remember {
        mutableStateOf(option1)
    }

    ColumnComponentContainer("Radio Buttons") {
        Text("Text Radio Button")

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
        // RadioButton
        Text("Radio Button")
        RowComponentContainer {
            RadioButtonPreview(true, true)
            RadioButtonPreview(true, false)
        }
        RowComponentContainer {
            RadioButtonPreview(false, true)
            RadioButtonPreview(false, false)
        }
    }
}

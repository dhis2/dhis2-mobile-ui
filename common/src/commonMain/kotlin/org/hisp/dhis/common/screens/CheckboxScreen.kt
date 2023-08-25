package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.previews.CheckboxPreview
import org.hisp.dhis.common.screens.previews.TextCheckboxPreview
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxBlock
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation

@Composable
fun CheckboxScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"

    val state1 = true
    val state2 = false
    val state3 = true
    val state4 = false

    val checkBoxesStatesHorizontal = listOf(
        CheckBoxData(checked = true, enabled = true, textInput = option1),
        CheckBoxData(checked = false, enabled = true, textInput = option2),
        CheckBoxData(checked = true, enabled = false, textInput = option3),
        CheckBoxData(checked = false, enabled = false, textInput = option4)
    )

    val checkBoxesStatesVertical = listOf(
        CheckBoxData(checked = true, enabled = true, textInput = option1),
        CheckBoxData(checked = false, enabled = true, textInput = option2),
        CheckBoxData(checked = true, enabled = false, textInput = option3),
        CheckBoxData(checked = false, enabled = false, textInput = option4)
    )

    ColumnComponentContainer(
        content = {
            Text(
                text = "Text Check Box",
            )
            TextCheckboxPreview(state1, true, option1)
            TextCheckboxPreview(state2, true, option2)
            TextCheckboxPreview(state3, false, option3)
            TextCheckboxPreview(state4, enabled = false, text = option4)
            Text(
                text = "Check Box",
            )
            Row {
                CheckboxPreview(true, enabled = true)
                CheckboxPreview(true, enabled = false)
            }
            Row {
                CheckboxPreview(false, enabled = true)
                CheckboxPreview(false, enabled = false)
            }
            Text(
                text = "Horizontal Check Box Block"
            )
            CheckBoxBlock(Orientation.HORIZONTAL, checkBoxesStatesHorizontal)
            Text(
                text = "Vertical Check Box Block"
            )
            CheckBoxBlock(Orientation.VERTICAL, checkBoxesStatesVertical)
        },
    )
}

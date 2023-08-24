package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import org.hisp.dhis.common.screens.previews.CheckboxBlockPreview
import org.hisp.dhis.common.screens.previews.CheckboxPreview
import org.hisp.dhis.common.screens.previews.TextCheckboxPreview
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxState
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation

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

    val checkBoxesStatesHorizontal = listOf(
        CheckBoxState(mutableStateOf(true), true, option1),
        CheckBoxState(mutableStateOf(false), true, option2),
        CheckBoxState(mutableStateOf(true), false, option3),
        CheckBoxState(mutableStateOf(false), false, option4)
    )

    val checkBoxesStatesVertical = listOf(
        CheckBoxState(mutableStateOf(true), true, option1),
        CheckBoxState(mutableStateOf(false), true, option2),
        CheckBoxState(mutableStateOf(true), false, option3),
        CheckBoxState(mutableStateOf(false), false, option4)
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
                CheckboxPreview(mutableStateOf(true), enabled = true)
                CheckboxPreview(mutableStateOf(true), enabled = false)
            }
            Row {
                CheckboxPreview(mutableStateOf(false), enabled = true)
                CheckboxPreview(mutableStateOf(false), enabled = false)
            }
            Text(
                text = "Horizontal Check Box Block"
            )
            CheckboxBlockPreview(Orientation.HORIZONTAL, checkBoxesStatesHorizontal)
            Text(
                text = "Vertical Check Box Block"
            )
            CheckboxBlockPreview(Orientation.VERTICAL, checkBoxesStatesVertical)
        },
    )
}

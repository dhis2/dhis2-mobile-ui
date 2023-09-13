package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.RadioButtonPreview
import org.hisp.dhis.common.screens.previews.TextRadioButtonPreview
import org.hisp.dhis.mobile.ui.designsystem.component.AgeFieldHelper
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun RadioButtonScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"

    var selected by remember {
        mutableStateOf(option1)
    }

    val radioButtonDataItemsHorizontal = listOf(
        RadioButtonData("4", selected = true, enabled = true, textInput = option1),
        RadioButtonData("5", selected = false, enabled = true, textInput = option2),
        RadioButtonData("6", selected = true, enabled = false, textInput = option3),
        RadioButtonData("7", selected = false, enabled = false, textInput = option4),
    )

    val radioButtonDataItemsVertical = listOf(
        RadioButtonData("0", selected = true, enabled = true, textInput = option1),
        RadioButtonData("1", selected = false, enabled = true, textInput = option2),
        RadioButtonData("2", selected = true, enabled = false, textInput = option3),
        RadioButtonData("3", selected = false, enabled = false, textInput = option4),
    )

    val ageFieldHelperHorizontal = listOf(
        RadioButtonData("0", selected = true, enabled = true, textInput = "Years"),
        RadioButtonData("1", selected = false, enabled = true, textInput = "Months"),
        RadioButtonData("2", selected = false, enabled = true, textInput = "Days"),
    )

    var selectedItemVertical by remember {
        mutableStateOf(radioButtonDataItemsVertical[0])
    }

    var selectedItemHorizontal by remember {
        mutableStateOf(radioButtonDataItemsHorizontal[0])
    }

    var selectedFieldHorizontal by remember {
        mutableStateOf(ageFieldHelperHorizontal[0])
    }

    ColumnComponentContainer("Radio Buttons") {
        SubTitle("Text Radio Button")

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
        Spacer(Modifier.size(Spacing.Spacing18))
        // RadioButton
        SubTitle("Radio Button")
        RowComponentContainer {
            RadioButtonPreview(selected = true, enabled = true)
            RadioButtonPreview(selected = true, enabled = false)
        }
        RowComponentContainer {
            RadioButtonPreview(selected = false, enabled = true)
            RadioButtonPreview(selected = false, enabled = false)
        }
        Spacer(Modifier.size(Spacing.Spacing18))
        // RadioButtonBlock
        SubTitle("Horizontal Radio Button Block")
        RadioButtonBlock(Orientation.HORIZONTAL, radioButtonDataItemsHorizontal, selectedItemHorizontal) {
            selectedItemHorizontal = it
        }
        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Vertical Radio Button Block")
        RadioButtonBlock(Orientation.VERTICAL, radioButtonDataItemsVertical, selectedItemVertical) {
            selectedItemVertical = it
        }
        SubTitle("Horizontal Age Field Helper")
        AgeFieldHelper(Orientation.HORIZONTAL, ageFieldHelperHorizontal, selectedFieldHorizontal) {
            selectedFieldHorizontal = it
        }
    }
}

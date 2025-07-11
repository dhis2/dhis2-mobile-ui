package org.hisp.dhis.showcaseapp.screens.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.showcaseapp.screens.previews.RadioButtonPreview
import org.hisp.dhis.showcaseapp.screens.previews.TextRadioButtonPreview

@Composable
fun RadioButtonScreen() {
    val option1 = "Option 1"
    val option2 = "Option 2"
    val option3 = "Option 3"
    val option4 = "Option 4"

    var selected by remember {
        mutableStateOf(option1)
    }

    val radioButtonDataItemsHorizontal =
        listOf(
            RadioButtonData("4", selected = true, enabled = true, textInput = option1),
            RadioButtonData("5", selected = false, enabled = true, textInput = option2),
            RadioButtonData("6", selected = true, enabled = false, textInput = option3),
            RadioButtonData("7", selected = false, enabled = false, textInput = option4),
        )

    val radioButtonDataItemsVertical =
        listOf(
            RadioButtonData("0", selected = true, enabled = true, textInput = option1),
            RadioButtonData("1", selected = false, enabled = true, textInput = option2),
            RadioButtonData("2", selected = true, enabled = false, textInput = option3),
            RadioButtonData("3", selected = false, enabled = false, textInput = option4),
        )

    var selectedItemVertical by remember {
        mutableStateOf(radioButtonDataItemsVertical[0])
    }

    var selectedItemHorizontal by remember {
        mutableStateOf(radioButtonDataItemsHorizontal[0])
    }

    ColumnScreenContainer(title = ButtonScreens.RADIO.label) {
        ColumnComponentContainer("Text Radio Button") {
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

        ColumnComponentContainer("Radio Button") {
            Column {
                RowComponentContainer {
                    RadioButtonPreview(selected = true, enabled = true)
                    RadioButtonPreview(selected = true, enabled = false)
                }
                RowComponentContainer {
                    RadioButtonPreview(selected = false, enabled = true)
                    RadioButtonPreview(selected = false, enabled = false)
                }
            }
        }

        ColumnComponentContainer("Horizontal Radio Button Block") {
            RadioButtonBlock(Orientation.HORIZONTAL, radioButtonDataItemsHorizontal, selectedItemHorizontal) {
                selectedItemHorizontal = it
            }
        }
        ColumnComponentContainer("Vertical Radio Button Block") {
            RadioButtonBlock(Orientation.VERTICAL, radioButtonDataItemsVertical, selectedItemVertical) {
                selectedItemVertical = it
            }
        }
    }
}

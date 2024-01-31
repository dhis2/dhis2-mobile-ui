package org.hisp.dhis.common.screens.parameter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.Coordinates
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputAge
import org.hisp.dhis.mobile.ui.designsystem.component.InputBarCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputCheckBox
import org.hisp.dhis.mobile.ui.designsystem.component.InputCoordinate
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputEmail
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.ParameterSelectorItem
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.EmptyParameter
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.InputParameter

@Composable
fun ParameterSelectorScreen() {
//    val focusRequester = remember { FocusRequester() }
//    val modifier = Modifier.focusRequester(focusRequester)

    var ageInputType by remember { mutableStateOf<AgeInputType>(AgeInputType.None) }
    var coordinates by remember { mutableStateOf<Coordinates?>(null) }

    val items = listOf(
        EmptyParameter(
            label = "Label",
            helper = "Optional",
            onClick = {},
        ),
        EmptyParameter(
            label = "Label",
            helper = "Optional",
            onClick = {},
        ),
        InputParameter(
            inputField = {
                InputText(
                    title = "Text parameter",
                    state = InputShellState.UNFOCUSED,
                    inputText = "unfocused",
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
        ),
        InputParameter(
            inputField = {
                InputText(
                    title = "Text parameter",
                    state = InputShellState.FOCUSED,
                    inputText = "focused",
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
        ),
        InputParameter(
            inputField = {
                InputEmail(
                    title = "Email parameter",
                    state = InputShellState.UNFOCUSED,
                    inputText = "android@dhis2.org",
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onEmailActionCLicked = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputAge(
                    title = "Age parameter",
                    inputType = ageInputType,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onCalendarActionClicked = {},
                    onValueChanged = {
                        ageInputType = it
                    },
                )
            },
        ),
        InputParameter(
            inputField = {
                InputBarCode(
                    title = "Barcode parameter",
                    inputText = "dF87sjiuH87s",
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onActionButtonClicked = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputCheckBox(
                    title = "CheckBox parameter",
                    state = InputShellState.UNFOCUSED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    checkBoxData = listOf(
                        CheckBoxData(
                            uid = "uid1",
                            checked = true,
                            enabled = true,
                            textInput = "option 1",
                        ),
                        CheckBoxData(
                            uid = "uid1",
                            checked = false,
                            enabled = true,
                            textInput = "option 2",
                        ),

                    ),
                    onClearSelection = {},
                    onItemChange = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputCoordinate(
                    title = "Coordinate parameter",
                    coordinates = coordinates,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onResetButtonClicked = {},
                    onUpdateButtonClicked = {
                        coordinates = Coordinates(latitude = 39.46263, longitude = -0.33617)
                    },
                )
            },
        ),
        InputParameter(
            inputField = {
                InputDateTime(
                    title = "DateTime parameter",
                    inputStyle = InputStyle.ParameterInputStyle(),
                    value = "",
                    onActionClicked = {},
                    onValueChanged = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputDropDown(
                    title = "DropDown parameter",
                    state = InputShellState.UNFOCUSED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    dropdownItems = listOf(
                        DropdownItem("Item 1"),
                        DropdownItem("Item 2"),
                    ),
                    onItemSelected = {},
                    onResetButtonClicked = {},
                )
            },
        ),
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
    ) {
        items.forEach {
            ParameterSelectorItem(
                model = it,
            )
        }
    }
}

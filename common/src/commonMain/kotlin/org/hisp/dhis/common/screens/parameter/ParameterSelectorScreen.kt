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
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputAge
import org.hisp.dhis.mobile.ui.designsystem.component.InputBarCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputCheckBox
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputEmail
import org.hisp.dhis.mobile.ui.designsystem.component.InputInteger
import org.hisp.dhis.mobile.ui.designsystem.component.InputLongText
import org.hisp.dhis.mobile.ui.designsystem.component.InputMatrix
import org.hisp.dhis.mobile.ui.designsystem.component.InputNotSupported
import org.hisp.dhis.mobile.ui.designsystem.component.InputOrgUnit
import org.hisp.dhis.mobile.ui.designsystem.component.InputPhoneNumber
import org.hisp.dhis.mobile.ui.designsystem.component.InputQRCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputRadioButton
import org.hisp.dhis.mobile.ui.designsystem.component.InputSequential
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesNoField
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesOnlyCheckBox
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesOnlySwitch
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.internal.IconCardData
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.ParameterSelectorItem
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.EmptyParameter
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.InputParameter

@Composable
fun ParameterSelectorScreen() {
//    val focusRequester = remember { FocusRequester() }
//    val modifier = Modifier.focusRequester(focusRequester)

    var ageInputType by remember { mutableStateOf<AgeInputType>(AgeInputType.None) }

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
                InputInteger(
                    title = "Integer parameter",
                    state = InputShellState.UNFOCUSED,
                    inputText = "123456",
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
        ),
        InputParameter(
            inputField = {
                InputLongText(
                    title = "Long text parameter",
                    state = InputShellState.UNFOCUSED,
                    inputText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
        ),
        InputParameter(
            inputField = {
                InputMatrix(
                    title = "Matrix parameter",
                    state = InputShellState.UNFOCUSED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    data = listOf(
                        IconCardData(
                            uid = "7e0cb105-c276-4f12-9f56-a26af8314121",
                            label = "Stethoscope",
                            iconRes = "dhis2_stethoscope_positive",
                            iconTint = Color(0xFFFF8400),
                        ),
                        IconCardData(
                            uid = "72269f6b-6b99-4d2e-a667-09f20c2097e0",
                            label = "Medicines",
                            iconRes = "dhis2_medicines_positive",
                            iconTint = Color(0xFFEB0085),
                        ),
                    ),
                    onSelectionChanged = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputNotSupported(
                    title = "Not supported parameter",
                    notSupportedString = "Not supported",
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
        ),
        InputParameter(
            inputField = {
                InputOrgUnit(
                    title = "Org unit parameter",
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onOrgUnitActionCLicked = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputPhoneNumber(
                    title = "Phone number parameter",
                    state = InputShellState.UNFOCUSED,
                    inputText = "999 666 888",
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onCallActionClicked = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputQRCode(
                    title = "QRCode parameter",
                    state = InputShellState.UNFOCUSED,
                    inputText = "wqlfqwlfjweghqge",
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onQRButtonClicked = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputRadioButton(
                    title = "Radio button parameter",
                    state = InputShellState.UNFOCUSED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    radioButtonData = listOf(
                        RadioButtonData(
                            uid = "uid1",
                            selected = false,
                            enabled = true,
                            textInput = "option1",
                        ),
                        RadioButtonData(
                            uid = "uid2",
                            selected = true,
                            enabled = true,
                            textInput = "option2",
                        ),
                    ),
                    onItemChange = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputSequential(
                    title = "Sequential parameter",
                    state = InputShellState.UNFOCUSED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    data = listOf(
                        IconCardData(
                            uid = "7e0cb105-c276-4f12-9f56-a26af8314121",
                            label = "Stethoscope",
                            iconRes = "dhis2_stethoscope_positive",
                            iconTint = Color(0xFFFF8400),
                        ),
                        IconCardData(
                            uid = "72269f6b-6b99-4d2e-a667-09f20c2097e0",
                            label = "Medicines",
                            iconRes = "dhis2_medicines_positive",
                            iconTint = Color(0xFFEB0085),
                        ),
                    ),
                    onSelectionChanged = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputYesNoField(
                    title = "Yes No parameter",
                    state = InputShellState.UNFOCUSED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onItemChange = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputYesOnlyCheckBox(
                    state = InputShellState.UNFOCUSED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    checkBoxData = CheckBoxData(
                        uid = "uid1",
                        checked = true,
                        enabled = true,
                        textInput = "option 1",
                    ),
                    onClick = {},
                )
            },
        ),
        InputParameter(
            inputField = {
                InputYesOnlySwitch(
                    title = "Yes only switch parameter",
                    state = InputShellState.UNFOCUSED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    isChecked = true,
                    onClick = {},
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

package org.hisp.dhis.common.screens.parameter

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.QrCode2
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.CustomIntentState
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionType
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.ImageCardData
import org.hisp.dhis.mobile.ui.designsystem.component.InputAge
import org.hisp.dhis.mobile.ui.designsystem.component.InputBarCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputCheckBox
import org.hisp.dhis.mobile.ui.designsystem.component.InputCustomIntent
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputEmail
import org.hisp.dhis.mobile.ui.designsystem.component.InputInteger
import org.hisp.dhis.mobile.ui.designsystem.component.InputLink
import org.hisp.dhis.mobile.ui.designsystem.component.InputLongText
import org.hisp.dhis.mobile.ui.designsystem.component.InputMatrix
import org.hisp.dhis.mobile.ui.designsystem.component.InputNotSupported
import org.hisp.dhis.mobile.ui.designsystem.component.InputOrgUnit
import org.hisp.dhis.mobile.ui.designsystem.component.InputPhoneNumber
import org.hisp.dhis.mobile.ui.designsystem.component.InputQRCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputRadioButton
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.ProgressIndicator
import org.hisp.dhis.mobile.ui.designsystem.component.ProgressIndicatorType
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTimeTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.ParameterSelectorItem
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.Status.CLOSED
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.Status.FOCUSED
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.Status.UNFOCUSED
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputAgeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputDateTimeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberInputAgeState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberInputDateTimeState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun ParameterSelectorScreen() {
    var inputTextValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    var inputTextStatus by remember(inputTextValue.text) {
        mutableStateOf(
            if (inputTextValue.text.isEmpty()) {
                CLOSED
            } else {
                UNFOCUSED
            },
        )
    }

    var inputQRCodeValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue("889026a1-d01e-4d34-8209-81e8ed5c614b"),
        )
    }
    var inputQRStatus by remember(inputQRCodeValue.text) {
        mutableStateOf(
            if (inputQRCodeValue.text.isEmpty()) {
                CLOSED
            } else {
                UNFOCUSED
            },
        )
    }

    var ageInputType by remember {
        mutableStateOf<AgeInputType>(AgeInputType.None)
    }

    var inputCustomIntentValue by remember { mutableStateOf(emptyList<String>()) }
    var inputCustomIntentState by remember { mutableStateOf(CustomIntentState.LAUNCH) }
    var inputCustomIntentStatus by remember(inputCustomIntentState) {
        mutableStateOf(
            if (inputCustomIntentState == CustomIntentState.LAUNCH) {
                CLOSED
            } else {
                UNFOCUSED
            },
        )
    }

    val items = listOf(
        ParameterSelectorItemModel(
            label = "Text parameter",
            helper = "Optional",
            inputField = {
                InputText(
                    title = "Text parameter",
                    state = InputShellState.UNFOCUSED,
                    inputTextFieldValue = inputTextValue,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onValueChanged = {
                        inputTextValue = it ?: TextFieldValue()
                    },
                )
            },
            status = inputTextStatus,
            onExpand = {
                inputTextStatus = FOCUSED
            },
        ),
        ParameterSelectorItemModel(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.QrCode2,
                    contentDescription = "Icon Button",
                    tint = SurfaceColor.Primary,
                )
            },
            label = "QRCode parameter",
            helper = "Optional",
            inputField = {
                InputQRCode(
                    title = "QRCode parameter",
                    state = InputShellState.UNFOCUSED,
                    inputTextFieldValue = inputQRCodeValue,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onQRButtonClicked = {},
                    onValueChanged = {
                        inputQRCodeValue = it ?: TextFieldValue()
                    },
                )
            },
            status = inputQRStatus,
            onExpand = {
                inputQRStatus = FOCUSED
            },
        ),
        ParameterSelectorItemModel(
            label = "Age parameter",
            helper = "Optional",
            inputField = {
                InputAge(
                    state = rememberInputAgeState(
                        inputAgeData = InputAgeData(
                            title = "Age parameter",
                            inputStyle = InputStyle.ParameterInputStyle(),
                        ),
                        inputType = ageInputType,
                    ),
                    onValueChanged = {
                        ageInputType = it ?: AgeInputType.None
                    },
                )
            },
            status = when (ageInputType) {
                AgeInputType.None -> CLOSED
                else -> UNFOCUSED
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            icon = {
                Icon(
                    painter = provideDHIS2Icon("material_barcode_scanner"),
                    contentDescription = "Icon Button",
                    tint = SurfaceColor.Primary,
                )
            },
            label = "Barcode parameter",
            helper = "Optional",
            inputField = {
                InputBarCode(
                    title = "Barcode parameter",
                    inputTextFieldValue = TextFieldValue("12345678900"),
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onActionButtonClicked = {},
                    onValueChanged = {},
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            icon = {
                if (inputCustomIntentState != CustomIntentState.LOADING) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                        contentDescription = "Icon Button",
                        tint = SurfaceColor.Primary,
                    )
                } else {
                    ProgressIndicator(
                        type = ProgressIndicatorType.CIRCULAR,
                    )
                }
            },
            label = "Custom intent parameter",
            helper = "Optional",
            inputField = {
                InputCustomIntent(
                    title = "Custom intent parameter",
                    values = inputCustomIntentValue,
                    customIntentState = CustomIntentState.LOADED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onLaunch = {
                        inputCustomIntentValue = listOf("option 1", "option 2", "option 3")
                    },
                    onClear = {
                        inputCustomIntentValue = emptyList()
                        inputCustomIntentStatus = CLOSED
                    },
                    buttonText = "Launch",
                )
            },
            status = inputCustomIntentStatus,
            onExpand = {
                inputCustomIntentState = CustomIntentState.LOADED
                inputCustomIntentStatus = FOCUSED
            },
        ),
        ParameterSelectorItemModel(
            label = "CheckBox parameter",
            helper = "Optional",
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
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "DateTime parameter",
            helper = "Optional",
            inputField = {
                InputDateTime(
                    state = rememberInputDateTimeState(
                        inputDateTimeData =
                        InputDateTimeData(
                            title = "DateTime parameter",
                            visualTransformation = DateTimeTransformation(),
                            actionType = DateTimeActionType.DATE_TIME,
                            inputStyle = InputStyle.ParameterInputStyle(),
                        ),
                        inputTextFieldValue = TextFieldValue(),
                    ),

                    onValueChanged = {
                        // no op
                    },
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "DropDown parameter",
            helper = "Optional",
            inputField = {
                InputDropDown(
                    title = "DropDown parameter",
                    state = InputShellState.UNFOCUSED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    itemCount = 2,
                    onSearchOption = {},
                    fetchItem = { index ->
                        DropdownItem("Item $index")
                    },
                    onItemSelected = { _, _ -> },
                    onResetButtonClicked = {},
                    loadOptions = {
                        /*no-op*/
                    },
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "Email parameter",
            helper = "Optional",
            inputField = {
                InputEmail(
                    title = "Email parameter",
                    state = InputShellState.UNFOCUSED,
                    inputTextFieldValue = TextFieldValue("android@dhis2.org"),
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onEmailActionCLicked = {},
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "Link parameter",
            helper = "Optional",
            inputField = {
                InputLink(
                    title = "Link parameter",
                    state = InputShellState.UNFOCUSED,
                    inputTextFieldValue = TextFieldValue("http://dhis2.org"),
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onLinkActionCLicked = {},
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "Integer parameter",
            helper = "Optional",
            inputField = {
                InputInteger(
                    title = "Integer parameter",
                    state = InputShellState.UNFOCUSED,
                    inputTextFieldValue = TextFieldValue("123456"),
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "Long text parameter",
            helper = "Optional",
            inputField = {
                InputLongText(
                    title = "Long text parameter",
                    state = InputShellState.UNFOCUSED,
                    inputTextFieldValue = TextFieldValue("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "Matrix parameter",
            helper = "Optional",
            inputField = {
                InputMatrix(
                    title = "Matrix parameter",
                    state = InputShellState.UNFOCUSED,
                    inputStyle = InputStyle.ParameterInputStyle(),
                    data = listOf(
                        ImageCardData.IconCardData(
                            uid = "7e0cb105-c276-4f12-9f56-a26af8314121",
                            label = "Stethoscope",
                            iconRes = "dhis2_stethoscope_positive",
                            iconTint = Color(0xFFFF8400),
                        ),
                        ImageCardData.IconCardData(
                            uid = "72269f6b-6b99-4d2e-a667-09f20c2097e0",
                            label = "Medicines",
                            iconRes = "dhis2_medicines_positive",
                            iconTint = Color(0xFFEB0085),
                        ),
                    ),
                    onSelectionChanged = {},
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "Not supported parameter",
            helper = "Optional",
            inputField = {
                InputNotSupported(
                    title = "Not supported parameter",
                    notSupportedString = "Not supported",
                    inputStyle = InputStyle.ParameterInputStyle(),
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "Org unit parameter",
            helper = "Optional",
            inputField = {
                InputOrgUnit(
                    title = "Org unit parameter",
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onOrgUnitActionCLicked = {},
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "Phone number parameter",
            helper = "Optional",
            inputField = {
                InputPhoneNumber(
                    title = "Phone number parameter",
                    state = InputShellState.UNFOCUSED,
                    inputTextFieldValue = TextFieldValue("999 666 888"),
                    inputStyle = InputStyle.ParameterInputStyle(),
                    onCallActionClicked = {},
                )
            },
            onExpand = {},
        ),
        ParameterSelectorItemModel(
            label = "Radio button parameter",
            helper = "Optional",
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
            onExpand = {},
        ),
    )

    ColumnScreenContainer(title = "Parameter Selector component") {
        items.forEach {
            ParameterSelectorItem(
                model = it,
            )
        }
    }
}

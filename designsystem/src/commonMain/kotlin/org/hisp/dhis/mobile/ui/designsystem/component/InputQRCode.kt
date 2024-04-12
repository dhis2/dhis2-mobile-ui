package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCode2
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue

/**
 * DHIS2 Input QR Code. Wraps DHIS · [BasicTextInput].
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param onQRButtonClicked: gives access to the action button event.
 * @param supportingText: is a list of SupportingTextData that.
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param inputTextFieldValue: manages the value of the text in the input field.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param autoCompleteList: List of strings to be used for autocomplete dropdown.
 * @param autoCompleteItemSelected: gives access to the autocomplete item selection.
 * @param onNextClicked: gives access to the imeAction event.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused,
 * @param imeAction: controls the imeAction button to be shown.
 * @param modifier: allows a modifier to be passed externally.
 */
@Composable
fun InputQRCode(
    title: String,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    onQRButtonClicked: () -> Unit,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputTextFieldValue: TextFieldValue? = null,
    isRequiredField: Boolean = false,
    autoCompleteList: List<String>? = null,
    autoCompleteItemSelected: ((String?) -> Unit)? = null,
    onNextClicked: (() -> Unit)? = null,
    onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
) {
    val actionButtonIconVector =
        mutableStateOf(if (!inputTextFieldValue?.text.isNullOrEmpty()) Icons.Outlined.QrCode2 else Icons.Outlined.QrCodeScanner)
    BasicTextInput(
        title = title,
        state = state,
        inputStyle = inputStyle,
        supportingText = supportingText,
        legendData = legendData,
        inputTextFieldValue = inputTextFieldValue,
        isRequiredField = isRequiredField,
        onNextClicked = onNextClicked,
        onValueChanged = onValueChanged,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        modifier = modifier,
        testTag = "QR_CODE",
        onFocusChanged = onFocusChanged,
        actionButton = {
            SquareIconButton(
                modifier = Modifier.testTag("INPUT_QR_CODE_BUTTON"),
                enabled = isButtonEnabled(inputStyle, state, inputTextFieldValue?.text),
                icon = {
                    Icon(
                        imageVector = actionButtonIconVector.value,
                        contentDescription = null,
                    )
                },
                onClick = onQRButtonClicked,
            )
        },
        autoCompleteList = autoCompleteList,
        autoCompleteItemSelected = autoCompleteItemSelected,
    )
}

private fun isButtonEnabled(inputStyle: InputStyle, state: InputShellState, inputText: String?) =
    when (inputStyle) {
        is InputStyle.DataInputStyle -> state != InputShellState.DISABLED
        is InputStyle.ParameterInputStyle -> inputText.isNullOrEmpty()
    }

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCode2
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
 * @param onImeActionClick: gives access to the imeAction event.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused,
 * @param imeAction: controls the imeAction button to be shown.
 * @param displayQRCapturedIcon: controls if should change button icon when text is not empty.
 * @param modifier: allows a modifier to be passed externally.
 * @param showDeleteButton: controls whether the delete button is shown or not.
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
    onImeActionClick: ((ImeAction) -> Unit)? = null,
    onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    displayQRCapturedIcon: Boolean = true,
    modifier: Modifier = Modifier,
    showDeleteButton: Boolean = true,
) {
    val actionButtonIconVector =
        if (!inputTextFieldValue?.text.isNullOrEmpty() && displayQRCapturedIcon) {
            Icons.Outlined.QrCode2
        } else {
            Icons.Outlined.QrCodeScanner
        }

    BasicTextInput(
        title = title,
        state = state,
        inputStyle = inputStyle,
        supportingText = supportingText,
        legendData = legendData,
        inputTextFieldValue = inputTextFieldValue,
        isRequiredField = isRequiredField,
        onImeActionClick = onImeActionClick,
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
                        imageVector = actionButtonIconVector,
                        contentDescription = null,
                    )
                },
                onClick = onQRButtonClicked,
            )
        },
        autoCompleteList = autoCompleteList,
        autoCompleteItemSelected = autoCompleteItemSelected,
        showDeleteButton = showDeleteButton,
    )
}

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
 * @param displayQRCapturedIcon: controls if should change button icon when text is not empty.
 * @param modifier: allows a modifier to be passed externally.
 * @param showDeleteButton: controls whether the delete button is shown or not.
 */
@Deprecated("Use with onImeActionClick instead of onNextClicked")
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
    onNextClicked: (() -> Unit)?,
    onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    displayQRCapturedIcon: Boolean = true,
    modifier: Modifier = Modifier,
    showDeleteButton: Boolean = true,
) {
    InputQRCode(
        title = title,
        state = state,
        inputStyle = inputStyle,
        onQRButtonClicked = onQRButtonClicked,
        supportingText = supportingText,
        legendData = legendData,
        inputTextFieldValue = inputTextFieldValue,
        isRequiredField = isRequiredField,
        autoCompleteList = autoCompleteList,
        autoCompleteItemSelected = autoCompleteItemSelected,
        onImeActionClick = { imeAction ->
            if (imeAction == ImeAction.Next) {
                onNextClicked?.invoke()
            }
        },
        onValueChanged = onValueChanged,
        onFocusChanged = onFocusChanged,
        imeAction = imeAction,
        displayQRCapturedIcon = displayQRCapturedIcon,
        modifier = modifier,
        showDeleteButton = showDeleteButton,
    )
}

private fun isButtonEnabled(
    inputStyle: InputStyle,
    state: InputShellState,
    inputText: String?,
) = when (inputStyle) {
    is InputStyle.DataInputStyle -> state != InputShellState.DISABLED
    is InputStyle.ParameterInputStyle -> inputText.isNullOrEmpty()
}

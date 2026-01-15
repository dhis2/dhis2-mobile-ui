package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.model.RegExValidations

/**
 * DHIS2 Input Phone Number. Wraps DHIS · [BasicTextInput].
 * Input that allows only numeric values for entering phone number.
 *
 * @param title: controls the text to be shown for the title.
 * @param onCallActionClicked: callback to when call phone number button is clicked.
 * @param modifier: allows a modifier to be passed externally.
 * @param maxLength: number of characters/digits that can be entered.
 * @param minLength: number of characters/digits that needs be entered to enable phone button.
 * @param state: Manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param legendData: manages the legendComponent.
 * @param inputTextFieldValue: manages the value of the text in the input field.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param autoCompleteList: List of strings to be used for autocomplete dropdown.
 * @param autoCompleteItemSelected: gives access to the autocomplete item selection.
 * @param onImeActionClick: gives access to the imeAction event.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused.
 * @param imeAction: controls the imeAction button to be shown.
 * @param allowedCharacters: the characters to allow.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param showDeleteButton: controls whether the delete button is shown or not.
 */
@Composable
fun InputPhoneNumber(
    title: String,
    onCallActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    maxLength: Int = 12,
    minLength: Int = 4,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    legendData: LegendData? = null,
    inputTextFieldValue: TextFieldValue? = null,
    isRequiredField: Boolean = false,
    autoCompleteList: List<String>? = null,
    autoCompleteItemSelected: ((String?) -> Unit)? = null,
    onImeActionClick: ((ImeAction) -> Unit)? = null,
    onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit) = {},
    imeAction: ImeAction = ImeAction.Next,
    supportingText: List<SupportingTextData>? = emptyList(),
    allowedCharacters: RegExValidations = RegExValidations.PHONE_NUMBER,
    showDeleteButton: Boolean = true,
) {
    val hasMinimumPhoneNumberInput = inputTextFieldValue?.text.orEmpty().length >= minLength
    BasicTextInput(
        title = title,
        state = state,
        inputStyle = inputStyle,
        supportingText = supportingText,
        legendData = legendData,
        inputTextFieldValue = inputTextFieldValue,
        isRequiredField = isRequiredField,
        onImeActionClick = onImeActionClick,
        onValueChanged = {
            if ((it.text.length) <= maxLength) {
                onValueChanged?.invoke(it)
            } else {
                // no-op
            }
        },
        keyboardOptions =
            KeyboardOptions(
                imeAction = imeAction,
                keyboardType = KeyboardType.Phone,
            ),
        allowedCharacters = allowedCharacters.regex,
        modifier = modifier,
        testTag = "PHONE_NUMBER",
        actionButton = {
            SquareIconButton(
                modifier = Modifier.testTag("CALL_PHONE_NUMBER_BUTTON"),
                enabled = isButtonEnabled(inputStyle, hasMinimumPhoneNumberInput),
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = null,
                    )
                },
                onClick = {
                    onCallActionClicked.invoke()
                },
            )
        },
        onFocusChanged = onFocusChanged,
        autoCompleteList = autoCompleteList,
        autoCompleteItemSelected = autoCompleteItemSelected,
        showDeleteButton = showDeleteButton,
    )
}

/**
 * DHIS2 Input Phone Number. Wraps DHIS · [BasicTextInput].
 * Input that allows only numeric values for entering phone number.
 *
 * @param title: controls the text to be shown for the title.
 * @param onCallActionClicked: callback to when call phone number button is clicked.
 * @param modifier: allows a modifier to be passed externally.
 * @param maxLength: number of characters/digits that can be entered.
 * @param minLength: number of characters/digits that needs be entered to enable phone button.
 * @param state: Manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param legendData: manages the legendComponent.
 * @param inputTextFieldValue: manages the value of the text in the input field.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param autoCompleteList: List of strings to be used for autocomplete dropdown.
 * @param autoCompleteItemSelected: gives access to the autocomplete item selection.
 * @param onNextClicked: gives access to the imeAction event.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused.
 * @param imeAction: controls the imeAction button to be shown.
 * @param allowedCharacters: the characters to allow.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param showDeleteButton: controls whether the delete button is shown or not.
 */
@Deprecated("Use with onImeActionClick instead of onNextClicked")
@Composable
fun InputPhoneNumber(
    title: String,
    onCallActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    maxLength: Int = 12,
    minLength: Int = 4,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    legendData: LegendData? = null,
    inputTextFieldValue: TextFieldValue? = null,
    isRequiredField: Boolean = false,
    autoCompleteList: List<String>? = null,
    autoCompleteItemSelected: ((String?) -> Unit)? = null,
    onNextClicked: (() -> Unit)?,
    onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit) = {},
    imeAction: ImeAction = ImeAction.Next,
    supportingText: List<SupportingTextData>? = emptyList(),
    allowedCharacters: RegExValidations = RegExValidations.PHONE_NUMBER,
    showDeleteButton: Boolean = true,
) {
    InputPhoneNumber(
        title = title,
        onCallActionClicked = onCallActionClicked,
        modifier = modifier,
        maxLength = maxLength,
        minLength = minLength,
        state = state,
        inputStyle = inputStyle,
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
        supportingText = supportingText,
        allowedCharacters = allowedCharacters,
        showDeleteButton = showDeleteButton,
    )
}

private fun isButtonEnabled(
    inputStyle: InputStyle,
    hasMinimumPhoneNumberInput: Boolean,
) = when (inputStyle) {
    is InputStyle.DataInputStyle -> hasMinimumPhoneNumberInput
    is InputStyle.ParameterInputStyle -> false
}

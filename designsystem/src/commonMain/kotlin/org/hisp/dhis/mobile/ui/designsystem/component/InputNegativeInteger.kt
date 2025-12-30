package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.model.RegExValidations

/**
 * DHIS2 Input negative Integer. Wraps DHIS · [BasicTextInput].
 * Only negative integers allowed.
 * @param title: controls the text to be shown for the title.
 * @param state: manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param inputTextFieldValue: manages the value of the text in the input field.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param autoCompleteList: list of strings to be used for autocomplete dropdown.
 * @param autoCompleteItemSelected: gives access to the autocomplete item selection.
 * @param onImeActionClick: gives access to the imeAction event.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused.
 * @param imeAction: controls the imeAction button to be shown.
 * @param modifier: allows a modifier to be passed externally.
 * @param showDeleteButton: controls whether the delete button is shown or not.
 */
@Composable
fun InputNegativeInteger(
    title: String,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
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
    modifier: Modifier = Modifier,
    showDeleteButton: Boolean = true,
) {
    var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(inputTextFieldValue ?: TextFieldValue()) }
    inputValue = TextFieldValue(inputValue.text.replaceFirst("-", ""), TextRange(inputValue.text.length), inputValue.composition)
    BasicTextInput(
        title = title,
        state = state,
        inputStyle = inputStyle,
        supportingText = supportingText,
        legendData = legendData,
        inputTextFieldValue = inputValue,
        isRequiredField = isRequiredField,
        onImeActionClick = onImeActionClick,
        onValueChanged = {
            onValueChanged?.invoke(TextFieldValue(if (it.text.startsWith("-") || it.text.isEmpty()) it.text else "-${it.text}"))
            inputValue = TextFieldValue(if (it.text.startsWith("-")) inputValue.text.replaceFirst("-", "") else it.text)
        },
        helperStyle = HelperStyle.WITH_HELPER_BEFORE,
        helper = "-",
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = KeyboardType.Number),
        allowedCharacters = RegExValidations.NEGATIVE_INTEGERS.regex,
        modifier = modifier,
        testTag = "NEGATIVE_INTEGER",
        onFocusChanged = onFocusChanged,
        autoCompleteList = autoCompleteList,
        autoCompleteItemSelected = autoCompleteItemSelected,
        showDeleteButton = showDeleteButton,
    )
}

/**
 * DHIS2 Input negative Integer. Wraps DHIS · [BasicTextInput].
 * Only negative integers allowed.
 * @param title: controls the text to be shown for the title.
 * @param state: manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param inputTextFieldValue: manages the value of the text in the input field.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param autoCompleteList: list of strings to be used for autocomplete dropdown.
 * @param autoCompleteItemSelected: gives access to the autocomplete item selection.
 * @param onNextClicked: gives access to the imeAction event.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused.
 * @param imeAction: controls the imeAction button to be shown.
 * @param modifier: allows a modifier to be passed externally.
 * @param showDeleteButton: controls whether the delete button is shown or not.
 */
@Deprecated("Use with onImeActionClick instead of onNextClicked")
@Composable
fun InputNegativeInteger(
    title: String,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
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
    modifier: Modifier = Modifier,
    showDeleteButton: Boolean = true,
) {
    InputNegativeInteger(
        title = title,
        state = state,
        inputStyle = inputStyle,
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
        modifier = modifier,
        showDeleteButton = showDeleteButton,
    )
}

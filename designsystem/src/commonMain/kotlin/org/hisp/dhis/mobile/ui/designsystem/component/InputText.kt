package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue

/**
 * DHIS2 Input Text. Wraps DHIS · [BasicTextInput].
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param supportingText: is a list of SupportingTextData that.
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param inputTextFieldValue: manages the value of the text in the input field.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param onImeActionClick: gives access to the imeAction event.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused.
 * @param autoCompleteList: List of strings to be used for autocomplete dropdown.
 * @param onAutoCompleteItemSelected: gives access to the autocomplete item selection.
 * @param imeAction: controls the imeAction button to be shown.
 * @param modifier: allows a modifier to be passed externally.
 * @param inputStyle: manages the InputShell style.
 * @param showDeleteButton: controls whether the delete button is shown or not.
 */
@Composable
fun InputText(
    title: String,
    state: InputShellState,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputTextFieldValue: TextFieldValue? = null,
    isRequiredField: Boolean = false,
    onImeActionClick: ((ImeAction) -> Unit)? = null,
    onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    autoCompleteList: List<String>? = null,
    onAutoCompleteItemSelected: ((String?) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    showDeleteButton: Boolean = true,
) {
    BasicTextInput(
        title = title,
        state = state,
        autoCompleteList = autoCompleteList,
        supportingText = supportingText,
        legendData = legendData,
        inputTextFieldValue = inputTextFieldValue,
        isRequiredField = isRequiredField,
        onImeActionClick = onImeActionClick,
        onValueChanged = onValueChanged,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        modifier = modifier,
        testTag = "TEXT",
        onFocusChanged = onFocusChanged,
        autoCompleteItemSelected = onAutoCompleteItemSelected,
        inputStyle = inputStyle,
        showDeleteButton = showDeleteButton,
    )
}

/**
 * DHIS2 Input Text. Wraps DHIS · [BasicTextInput].
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param supportingText: is a list of SupportingTextData that.
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param inputTextFieldValue: manages the value of the text in the input field.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param onNextClicked: gives access to the imeAction event.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused.
 * @param autoCompleteList: List of strings to be used for autocomplete dropdown.
 * @param onAutoCompleteItemSelected: gives access to the autocomplete item selection.
 * @param imeAction: controls the imeAction button to be shown.
 * @param modifier: allows a modifier to be passed externally.
 * @param inputStyle: manages the InputShell style.
 * @param showDeleteButton: controls whether the delete button is shown or not.
 */
@Deprecated("Use with onImeActionClick instead of onNextClicked")
@Composable
fun InputText(
    title: String,
    state: InputShellState,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputTextFieldValue: TextFieldValue? = null,
    isRequiredField: Boolean = false,
    onNextClicked: (() -> Unit)?,
    onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    autoCompleteList: List<String>? = null,
    onAutoCompleteItemSelected: ((String?) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    showDeleteButton: Boolean = true,
) {
    InputText(
        title = title,
        state = state,
        supportingText = supportingText,
        legendData = legendData,
        inputTextFieldValue = inputTextFieldValue,
        isRequiredField = isRequiredField,
        onImeActionClick = { imeAction ->
            if (imeAction == ImeAction.Next) {
                onNextClicked?.invoke()
            }
        },
        onValueChanged = onValueChanged,
        onFocusChanged = onFocusChanged,
        autoCompleteList = autoCompleteList,
        onAutoCompleteItemSelected = onAutoCompleteItemSelected,
        imeAction = imeAction,
        modifier = modifier,
        inputStyle = inputStyle,
        showDeleteButton = showDeleteButton,
    )
}

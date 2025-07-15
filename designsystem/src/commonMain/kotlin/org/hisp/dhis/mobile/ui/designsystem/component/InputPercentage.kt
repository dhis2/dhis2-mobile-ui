package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.model.RegExValidations

/**
 * DHIS2 Input percentage. Wraps DHIS · [BasicTextInput].
 * Only integers allowed.
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
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
 * @param modifier: allows a modifier to be passed externally.
 */
@Composable
fun InputPercentage(
    title: String,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
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
    BasicTextInput(
        title = title,
        state = state,
        inputStyle = inputStyle,
        supportingText = supportingText,
        legendData = legendData,
        inputTextFieldValue =
            inputTextFieldValue?.copy(
                text = inputTextFieldValue.text.replace("%", ""),
            ),
        isRequiredField = isRequiredField,
        onNextClicked = onNextClicked,
        onValueChanged = onValueChanged,
        helperStyle = HelperStyle.WITH_HELPER_AFTER,
        helper = "%",
        keyboardOptions =
            KeyboardOptions(
                imeAction = imeAction,
                keyboardType = KeyboardType.Number,
            ),
        allowedCharacters = RegExValidations.PERCENTAGE.regex,
        modifier = modifier,
        testTag = "PERCENTAGE",
        onFocusChanged = onFocusChanged,
        autoCompleteList = autoCompleteList,
        autoCompleteItemSelected = autoCompleteItemSelected,
    )
}

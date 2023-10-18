package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations

/**
 * DHIS2 Input negative Integer. Wraps DHIS · [BasicTextInput].
 * Only negative integers allowed
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param supportingText is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param inputText manages the value of the text in the input field
 * @param isRequiredField controls whether the field is mandatory or not
 * @param autoCompleteList List of strings to be used for autocomplete dropdown
 * @param onNextClicked gives access to the imeAction event
 * @param onValueChanged gives access to the onValueChanged event
 * @param onFocusChanged gives access to the onFocusChanged returns true if
 * item is focused
 * @param imeAction controls the imeAction button to be shown
 * @param modifier allows a modifier to be passed externally
 */
@Composable
fun InputNegativeInteger(
    title: String,
    state: InputShellState,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputText: String? = null,
    isRequiredField: Boolean = false,
    autoCompleteList: List<String>? = null,
    onNextClicked: (() -> Unit)? = null,
    onValueChanged: ((String?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
) {
    var inputValue by rememberSaveable { mutableStateOf(inputText ?: "") }
    inputValue = inputValue.replaceFirst("-", "")
    BasicTextInput(
        title = title,
        state = state,
        supportingText = supportingText,
        legendData = legendData,
        inputText = inputValue,
        isRequiredField = isRequiredField,
        onNextClicked = onNextClicked,
        onValueChanged = {
            onValueChanged?.invoke(if (it?.startsWith("-") == true || it?.isEmpty() == true) it else "-$it")
            inputValue = if (it?.startsWith("-") == true) inputValue.replaceFirst("-", "") else it.toString()
        },
        helperStyle = InputStyle.WITH_HELPER_BEFORE,
        helper = "-",
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = KeyboardType.Number),
        allowedCharacters = RegExValidations.NEGATIVE_INTEGERS.regex,
        modifier = modifier,
        testTag = "NEGATIVE_INTEGER",
        onFocusChanged = onFocusChanged,
        autoCompleteList = autoCompleteList,
    )
}

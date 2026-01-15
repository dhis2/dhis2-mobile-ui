package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.model.RegExValidations

/**
 * DHIS2 Input Unit Interval. Wraps DHIS · [BasicTextInput].
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param supportingText: is a list of SupportingTextData that.
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param inputTextFieldValue: manages the value of the text in the input field.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param onImeActionClick: gives access to the imeAction event.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param imeAction: controls the imeAction button to be shown.
 * @param modifier: allows a modifier to be passed externally.
 * @param inputStyle: manages the InputShell style.
 * @param showDeleteButton: controls whether the delete button is shown or not.
 */
@Composable
fun InputUnitInterval(
    title: String,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputTextFieldValue: TextFieldValue? = null,
    isRequiredField: Boolean = false,
    onImeActionClick: ((ImeAction) -> Unit)? = null,
    onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
    showDeleteButton: Boolean = true,
) {
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
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = KeyboardType.Decimal),
        allowedCharacters = RegExValidations.UNIT_INTERVAL_BETWEEN_0_AND_1.regex,
        modifier = modifier,
        testTag = "UNIT_INTERVAL",
        showDeleteButton = showDeleteButton,
    )
}

/**
 * DHIS2 Input Unit Interval. Wraps DHIS · [BasicTextInput].
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param supportingText: is a list of SupportingTextData that.
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param inputTextFieldValue: manages the value of the text in the input field.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param onNextClicked: gives access to the imeAction event.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param imeAction: controls the imeAction button to be shown.
 * @param modifier: allows a modifier to be passed externally.
 * @param inputStyle: manages the InputShell style.
 * @param showDeleteButton: controls whether the delete button is shown or not.
 */
@Deprecated("Use with onImeActionClick instead of onNextClicked")
@Composable
fun InputUnitInterval(
    title: String,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputTextFieldValue: TextFieldValue? = null,
    isRequiredField: Boolean = false,
    onNextClicked: (() -> Unit)?,
    onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
    showDeleteButton: Boolean = true,
) {
    InputUnitInterval(
        title = title,
        state = state,
        inputStyle = inputStyle,
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
        imeAction = imeAction,
        modifier = modifier,
        showDeleteButton = showDeleteButton,
    )
}

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations

/**
 * DHIS2 Input email. Wraps DHIS · [BasicTextInput].
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
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
 * @param onEmailActionCLicked: callback to when email button is clicked.
 * @param inputStyle: manages the InputShell style.
 */
@Composable
fun InputEmail(
    title: String,
    state: InputShellState,
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
    onEmailActionCLicked: () -> Unit,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
) {
    val isValidEmailAddress = RegExValidations.EMAIL.regex.matches(inputTextFieldValue?.text.orEmpty())
    BasicTextInput(
        title = title,
        state = state,
        supportingText = supportingText,
        legendData = legendData,
        inputTextFieldValue = inputTextFieldValue,
        isRequiredField = isRequiredField,
        onNextClicked = onNextClicked,
        onValueChanged = onValueChanged,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = KeyboardType.Email,
        ),
        modifier = modifier,
        testTag = "EMAIL",
        onFocusChanged = onFocusChanged,
        actionButton = {
            SquareIconButton(
                modifier = Modifier.testTag("EMAIL_BUTTON"),
                enabled = isButtonEnabled(inputStyle, isValidEmailAddress),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "email_icon",
                    )
                },
                onClick = onEmailActionCLicked,
            )
        },
        autoCompleteList = autoCompleteList,
        autoCompleteItemSelected = autoCompleteItemSelected,
        inputStyle = inputStyle,
    )
}

private fun isButtonEnabled(inputStyle: InputStyle, isValidEmailAddress: Boolean) =
    when (inputStyle) {
        is InputStyle.DataInputStyle -> isValidEmailAddress
        is InputStyle.ParameterInputStyle -> false
    }

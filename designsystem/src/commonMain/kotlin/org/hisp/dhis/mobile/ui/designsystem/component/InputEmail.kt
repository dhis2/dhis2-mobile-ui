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
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations

/**
 * DHIS2 Input email. Wraps DHIS · [BasicTextInput].
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param supportingText is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param inputText manages the value of the text in the input field
 * @param isRequiredField controls whether the field is mandatory or not
 * @param onNextClicked gives access to the imeAction event
 * @param onValueChanged gives access to the onValueChanged event
 * @param onFocusChanged gives access to the onFocusChanged returns true if
 * item is focused
 * @param imeAction controls the imeAction button to be shown
 * @param modifier allows a modifier to be passed externally
 * @param onEmailActionCLicked callback to when email button is clicked
 */
@Composable
fun InputEmail(
    title: String,
    state: InputShellState,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputText: String? = null,
    isRequiredField: Boolean = false,
    onNextClicked: (() -> Unit)? = null,
    onValueChanged: ((String?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
    onEmailActionCLicked: () -> Unit,
) {
    val isValidEmailAddress = RegExValidations.EMAIL.regex.matches(inputText.orEmpty())
    BasicTextInput(
        title = title,
        state = state,
        supportingText = supportingText,
        legendData = legendData,
        inputText = inputText,
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
                enabled = isValidEmailAddress,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "email_icon",
                    )
                },
                onClick = onEmailActionCLicked,
            )
        },
    )
}

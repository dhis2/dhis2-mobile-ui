package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource

/**
 * DHIS2 Input Phone Number
 * Input that allows only numeric values for entering phone number.
 *
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param legendData manages the legendComponent
 * @param inputText manages the value of the text in the input field
 * @param modifier allows a modifier to be passed externally
 * @param isRequiredField controls whether the field is mandatory or not
 * @param onNextClicked gives access to the imeAction event
 * @param onValueChanged gives access to the onValueChanged event
 * @param imeAction controls the imeAction button to be shown
 * @param notation controls the decimal notation to be used, will be European
 * by default
 * @param onCallActionClicked callback to when call phone number button is clicked
 * @param maxLength number of characters/digits that can be entered
 */
@Composable
fun InputPhoneNumber(
    title: String,
    onCallActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    maxLength: Int = 12,
    state: InputShellState = InputShellState.UNFOCUSED,
    legendData: LegendData? = null,
    inputText: String? = null,
    isRequiredField: Boolean = false,
    onNextClicked: (() -> Unit)? = null,
    onValueChanged: ((String?) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    notation: RegExValidations = RegExValidations.PHONE_NUMBER,
) {
    val hasMinimumPhoneNumberInput = inputText.orEmpty().length > 2
    val supportingText = if (state == InputShellState.ERROR) {
        listOf(
            SupportingTextData(
                text = provideStringResource("enter_phone_number"),
                state = SupportingTextState.ERROR,
            ),
        )
    } else {
        emptyList()
    }

    BasicTextInput(
        title = title,
        state = state,
        supportingText = supportingText,
        legendData = legendData,
        inputText = inputText,
        isRequiredField = isRequiredField,
        onNextClicked = onNextClicked,
        onValueChanged = {
            if ((it?.length ?: 0) <= maxLength) {
                onValueChanged?.invoke(it)
            } else {
                // no-op
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = KeyboardType.Number),
        allowedCharacters = notation.regex,
        modifier = modifier,
        testTag = "PHONE_NUMBER",
        actionButton = {
            SquareIconButton(
                modifier = Modifier.testTag("CALL_PHONE_NUMBER_BUTTON"),
                enabled = hasMinimumPhoneNumberInput,
                icon = {
                    Icon(
                        painter = provideDHIS2Icon("dhis2_phone_positive"),
                        contentDescription = null,
                    )
                },
                onClick = onCallActionClicked,
            )
        },
    )
}

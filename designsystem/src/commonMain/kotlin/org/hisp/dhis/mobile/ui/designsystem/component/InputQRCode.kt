package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCode2
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction

/**
 * DHIS2 Input positive Integer. Wraps DHIS · [BasicTextInput].
 * Only positive integers allowed, excluding 0
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
 */
@Composable
fun InputQRCode(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    onQRButtonClicked: () -> Unit,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputText: String? = null,
    isRequiredField: Boolean = false,
    onNextClicked: (() -> Unit)? = null,
    onValueChanged: ((String?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
) {
    BasicTextInput(
        title = title,
        state = state,
        supportingText = supportingText,
        legendData = legendData,
        inputText = inputText,
        isRequiredField = isRequiredField,
        onNextClicked = onNextClicked,
        onValueChanged = onValueChanged,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        modifier = modifier,
        testTag = "TEXT",
        onFocusChanged = onFocusChanged,
        actionButton = {
            SquareIconButton(
                modifier = Modifier.testTag("CALL_PHONE_NUMBER_BUTTON"),
                enabled = state != InputShellState.DISABLED,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.QrCode2,
                        contentDescription = null,
                    )
                },
                onClick = onQRButtonClicked,
            )
        },
    )
}

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Input Long Text. Wraps DHIS · [BasicTextInput].
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param supportingText is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param inputText manages the value of the text in the input field
 * @param isRequiredField manages whether the field is mandatory or not
 * @param onNextClicked gives access to the ImeAction event
 * @param onValueChanged gives access to the onValueChanged event
 * @param onFocusChanged gives access to the onFocusChanged returns true if
 * item is focused
 * @param imeAction controls the ImeAction to show in the keyboard
 * @param modifier allows a modifier to be passed externally
 */
@Composable
fun InputLongText(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
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
        isSingleLine = false,
        modifier = modifier.scrollable(
            orientation = Orientation.Vertical,
            state = rememberScrollState(),
        ).heightIn(Spacing.Spacing0, InternalSizeValues.Size300),
        testTag = "LONG_TEXT",
        onFocusChanged = onFocusChanged,
    )
}

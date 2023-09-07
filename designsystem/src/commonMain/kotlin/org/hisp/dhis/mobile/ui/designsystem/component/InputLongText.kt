package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Input Long Text. Wraps DHIS · [InputShell].
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param supportingText is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param inputText manages the value of the text in the input field
 * @param isRequiredField manages whether the field is mandatory or not
 * @param onNextClicked gives access to the ImeAction event
 * @param onValueChanged gives access to the onValueChanged event
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
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
) {
    val inputValue by remember(inputText) { mutableStateOf(inputText ?: "") }
    var deleteButtonIsVisible by remember {
        mutableStateOf(!inputText.isNullOrEmpty() && state != InputShellState.DISABLED)
    }
    val focusManager = LocalFocusManager.current

    val keyboardOptions = KeyboardOptions(imeAction = imeAction)
    InputShell(
        modifier = modifier,
        isRequiredField = isRequiredField,
        title = title,
        primaryButton = {
            if (deleteButtonIsVisible) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_LONG_TEXT_RESET_BUTTON"),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        onValueChanged?.invoke("")
                        deleteButtonIsVisible = false
                    },
                    enabled = state != InputShellState.DISABLED,
                )
            }
        },
        state = state,
        legend = {
            legendData?.let {
                Legend(legendData, Modifier.testTag("INPUT_LONG_TEXT_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach {
                    label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = Modifier.testTag("INPUT_LONG_TEXT_SUPPORTING_TEXT"),
                )
            }
        },
        inputField = {
            BasicInput(
                modifier = Modifier.testTag("INPUT_LONG_TEXT_FIELD")
                    .scrollable(
                        orientation = Orientation.Vertical,
                        state = rememberScrollState(),
                    ).heightIn(Spacing.Spacing0, InternalSizeValues.Size300),
                isSingleLine = false,
                inputText = inputValue,
                onInputChanged = {
                    onValueChanged?.invoke(it)
                    deleteButtonIsVisible = it.isNotEmpty()
                },
                enabled = state != InputShellState.DISABLED,
                state = state,
                keyboardOptions = keyboardOptions,
                onNextClicked = {
                    if (onNextClicked != null) {
                        onNextClicked.invoke()
                    } else {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                },
            )
        },
    )
}
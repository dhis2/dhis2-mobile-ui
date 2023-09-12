package org.hisp.dhis.mobile.ui.designsystem.component

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
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations
import java.util.Locale

/**
 * DHIS2 Input positive Integer. Wraps DHIS · [InputShell].
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
 * @param helper manages the helper text to show
 * @param modifier allows a modifier to be passed externally
 */
@Composable
internal fun GenericInput(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputText: String? = null,
    isRequiredField: Boolean = false,
    onNextClicked: (() -> Unit)? = null,
    onValueChanged: ((String?) -> Unit)? = null,
    keyboardOptions: KeyboardOptions,
    allowedCharacters: Regex? = null,
    helper: String? = null,
    helperStyle: InputStyle = InputStyle.NONE,
    testTag: String = "",
    isSingleLine: Boolean = true,
    modifier: Modifier = Modifier,
) {
    val inputValue by remember(inputText) { mutableStateOf(inputText) }

    var deleteButtonIsVisible by remember { mutableStateOf(!inputText.isNullOrEmpty() && state != InputShellState.DISABLED) }
    val focusManager = LocalFocusManager.current
    InputShell(
        modifier = modifier.testTag("INPUT_$testTag"),
        isRequiredField = isRequiredField,
        title = title,
        primaryButton = {
            if (deleteButtonIsVisible) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_" + testTag + "_RESET_BUTTON"),
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
                Legend(legendData, Modifier.testTag("INPUT_" + testTag + "_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach {
                    label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = Modifier.testTag("INPUT_" + testTag + "_SUPPORTING_TEXT"),
                )
            }
        },
        inputField = {
            BasicInput(
                modifier = Modifier.testTag("INPUT_" + testTag + "_FIELD"),
                inputText = inputValue ?: "",
                helper = helper,
                isSingleLine = isSingleLine,
                helperStyle = helperStyle,
                onInputChanged = {
                    if (allowedCharacters != null) {
                        if (allowedCharacters == RegExValidations.SINGLE_LETTER.regex) {
                            if (it.uppercase(Locale.getDefault()).matches(allowedCharacters) || it.isEmpty()) {
                                onValueChanged?.invoke(it.uppercase(Locale.getDefault()))
                                deleteButtonIsVisible = it.isNotEmpty()
                            }
                        } else {
                            if (it.matches(allowedCharacters) || it.isEmpty()) {
                                onValueChanged?.invoke(it)
                                deleteButtonIsVisible = it.isNotEmpty()
                            }
                        }
                    } else {
                        onValueChanged?.invoke(it)
                        deleteButtonIsVisible = it.isNotEmpty()
                    }
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

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 Input dropdown. Wraps DHIS · [InputShell].
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param selectedItem manages the value of the selected item
 * @param supportingTextData is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param isRequiredField controls whether the field is mandatory or not
 * @param onFocusChanged gives access to the onFocusChanged returns true if
 * item is focused
 * @param modifier allows a modifier to be passed externally
 * @param onResetButtonClicked callback to when reset button is clicked
 * @param onArrowDropDownButtonClicked callback to when arrow drop down button is clicked
 */
@Composable
fun InputDropDown(
    title: String,
    state: InputShellState,
    selectedItem: String? = null,
    supportingTextData: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequiredField: Boolean = false,
    modifier: Modifier = Modifier,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    onResetButtonClicked: () -> Unit,
    onArrowDropDownButtonClicked: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    InputShell(
        modifier = modifier
            .testTag("INPUT_DROPDOWN")
            .focusRequester(focusRequester),
        title = title,
        state = state,
        isRequiredField = isRequiredField,
        onFocusChanged = onFocusChanged,
        supportingText = {
            supportingTextData?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = modifier.testTag("INPUT_DROPDOWN_SUPPORTING_TEXT"),
                )
            }
        },
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_DROPDOWN_LEGEND"))
            }
        },
        inputField = {
            Text(
                modifier = Modifier.testTag("INPUT_DROPDOWN_TEXT"),
                text = selectedItem ?: "",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = if (state != InputShellState.DISABLED) {
                        TextColor.OnSurface
                    } else {
                        TextColor.OnDisabledSurface
                    },
                ),
            )
        },
        primaryButton = {
            IconButton(
                modifier = Modifier.testTag("INPUT_DROPDOWN_ARROW_BUTTON").onFocusChanged {
                    onFocusChanged?.invoke(it.isFocused)
                },
                enabled = state != InputShellState.DISABLED,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowDropDown,
                        contentDescription = "Dropdown Button",
                    )
                },
                onClick = {
                    focusRequester.requestFocus()
                    onArrowDropDownButtonClicked.invoke()
                },
            )
        },
        secondaryButton =
        if (!selectedItem.isNullOrEmpty() && state != InputShellState.DISABLED) {
            {
                IconButton(
                    modifier = Modifier.testTag("INPUT_DROPDOWN_RESET_BUTTON"),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Reset Button",
                        )
                    },
                    onClick = {
                        focusRequester.requestFocus()
                        onResetButtonClicked.invoke()
                    },
                )
            }
        } else {
            null
        },
    )
}

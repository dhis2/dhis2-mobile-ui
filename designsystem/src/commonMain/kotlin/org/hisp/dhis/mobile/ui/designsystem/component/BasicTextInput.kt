package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import java.util.Locale

/**
 * DHIS2 BasicTextInput. Wraps DHIS · [InputShell].
 * Generic text input component that allows for all the different
 * implementations needed
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
 * @param actionButton controls action button composable, if null will show nothing
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BasicTextInput(
    title: String,
    state: InputShellState,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputText: String? = null,
    isRequiredField: Boolean = false,
    onNextClicked: (() -> Unit)? = null,
    onValueChanged: ((String?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    autoCompleteList: List<String>? = null,
    autoCompleteItemSelected: ((String?) -> Unit)? = null,
    keyboardOptions: KeyboardOptions,
    allowedCharacters: Regex? = null,
    helper: String? = null,
    helperStyle: InputStyle = InputStyle.NONE,
    testTag: String = "",
    isSingleLine: Boolean = true,
    actionButton: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val inputValue by remember(inputText) { mutableStateOf(inputText) }

    var deleteButtonIsVisible by remember(inputText) { mutableStateOf(!inputText.isNullOrEmpty() && state != InputShellState.DISABLED) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val filteredList = autoCompleteList?.filter { it.contains(inputValue ?: "") }
    var expanded by remember { mutableStateOf(false) }

    var deleteButton:
        @Composable()
        (() -> Unit)? = null
    if (deleteButtonIsVisible) {
        deleteButton = {
            IconButton(
                modifier = Modifier
                    .testTag("INPUT_" + testTag + "_RESET_BUTTON")
                    .padding(Spacing.Spacing0),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Icon Button",
                    )
                },
                onClick = {
                    focusRequester.requestFocus()
                    onValueChanged?.invoke("")
                    deleteButtonIsVisible = false
                },
                enabled = state != InputShellState.DISABLED,
            )
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { },
    ) {
        InputShell(
            modifier = modifier
                .testTag("INPUT_$testTag")
                .focusRequester(focusRequester)
                .menuAnchor(),
            isRequiredField = isRequiredField,
            title = title,
            primaryButton = deleteButton,
            secondaryButton = actionButton,
            state = state,
            legend = {
                legendData?.let {
                    Legend(legendData, Modifier.testTag("INPUT_" + testTag + "_LEGEND"))
                }
            },
            supportingText = {
                supportingText?.forEach { label ->
                    SupportingText(
                        label.text,
                        label.state,
                        modifier = Modifier.testTag("INPUT_" + testTag + "_SUPPORTING_TEXT"),
                    )
                }
            },
            inputField = {
                BasicTextField(
                    modifier = Modifier.testTag("INPUT_" + testTag + "_FIELD").fillMaxWidth(),
                    inputText = inputValue ?: "",
                    helper = helper,
                    isSingleLine = isSingleLine,
                    helperStyle = helperStyle,
                    onInputChanged = { newValue ->
                        if (allowedCharacters != null) {
                            if (allowedCharacters == RegExValidations.SINGLE_LETTER.regex) {
                                if (newValue.uppercase(Locale.getDefault())
                                        .matches(allowedCharacters) || newValue.isEmpty()
                                ) {
                                    onValueChanged?.invoke(newValue.uppercase(Locale.getDefault()))
                                    deleteButtonIsVisible = newValue.isNotEmpty()
                                }
                            } else {
                                if (newValue.matches(allowedCharacters) || newValue.isEmpty()) {
                                    onValueChanged?.invoke(newValue)
                                    deleteButtonIsVisible = newValue.isNotEmpty()
                                }
                            }
                        } else {
                            onValueChanged?.invoke(newValue)
                            deleteButtonIsVisible = newValue.isNotEmpty()
                        }
                        expanded = (!filteredList.isNullOrEmpty() && filteredList.any { it == newValue || it.contains(newValue) })
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
                if (expanded && !filteredList.isNullOrEmpty()) {
                    DropdownMenu(
                        modifier = Modifier.exposedDropdownSize(),
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        offset = DpOffset(x = -16.dp, y = Spacing.Spacing12),
                        properties = PopupProperties(focusable = false, dismissOnBackPress = true, dismissOnClickOutside = true, clippingEnabled = true),
                    ) {
                        filteredList.forEach {
                            if (filteredList.indexOf(it) <= 4) {
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    modifier = Modifier,
                                    onClick = {
                                        onValueChanged?.invoke(it)
                                        autoCompleteItemSelected?.invoke(it)
                                        expanded = false
                                    },
                                )
                            }
                        }
                    }
                }
            },
            onFocusChanged = onFocusChanged,
        )
    }
}

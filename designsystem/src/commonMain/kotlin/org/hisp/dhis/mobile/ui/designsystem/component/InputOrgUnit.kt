package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 Input org unit. Wraps DHIS · [BasicTextInput].
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param supportingText is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param inputText manages the value of the text in the input field
 * @param isRequiredField controls whether the field is mandatory or not
 * @param onValueChanged gives access to the onValueChanged event
 * @param onFocusChanged gives access to the onFocusChanged returns true if
 * item is focused
 * @param modifier allows a modifier to be passed externally
 * @param onOrgUnitActionCLicked callback to when org unit button is clicked
 */
@Composable
fun InputOrgUnit(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputText: String? = null,
    isRequiredField: Boolean = false,
    onValueChanged: ((String?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
    onOrgUnitActionCLicked: () -> Unit,
) {
    val inputValue by remember(inputText) { mutableStateOf(inputText) }

    var deleteButtonIsVisible by remember(inputText) { mutableStateOf(!inputText.isNullOrEmpty() && state != InputShellState.DISABLED) }
    val focusRequester = remember { FocusRequester() }

    val primaryButton:
        @Composable()
        (() -> Unit)?
    if (deleteButtonIsVisible) {
        primaryButton = {
            IconButton(
                modifier = Modifier
                    .testTag("INPUT_ORG_UNIT_RESET_BUTTON")
                    .padding(Spacing.Spacing0),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Reset Button",
                    )
                },
                onClick = {
                    onValueChanged?.invoke("")
                    deleteButtonIsVisible = false
                    focusRequester.requestFocus()
                },
                enabled = state != InputShellState.DISABLED,
            )
        }
    } else {
        primaryButton = {
            IconButton(
                modifier = Modifier
                    .testTag("INPUT_ORG_UNIT_DROPDOWN_BUTTON")
                    .padding(Spacing.Spacing0),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowDropDown,
                        contentDescription = "Dropdown Button",
                    )
                },
                onClick = {
                    onOrgUnitActionCLicked.invoke()
                    focusRequester.requestFocus()
                },
                enabled = state != InputShellState.DISABLED,
            )
        }
    }
    InputShell(
        modifier = modifier
            .testTag("INPUT_ORG_UNIT")
            .focusRequester(focusRequester),
        isRequiredField = isRequiredField,
        title = title,
        primaryButton = primaryButton,
        secondaryButton = {
            SquareIconButton(
                modifier = Modifier.testTag("ORG_UNIT_BUTTON"),
                enabled = state != InputShellState.DISABLED,
                icon = {
                    Icon(
                        painter = provideDHIS2Icon("org_unit"),
                        contentDescription = "org_unit_icon",
                    )
                },
                onClick = {
                    onOrgUnitActionCLicked.invoke()
                    focusRequester.requestFocus()
                },
            )
        },
        state = state,
        legend = {
            legendData?.let {
                Legend(legendData, Modifier.testTag("INPUT_ORG_UNIT_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = Modifier.testTag("INPUT_ORG_UNIT_SUPPORTING_TEXT"),
                )
            }
        },
        inputField = {
            Box {
                Text(
                    modifier = Modifier.testTag("INPUT_DROPDOWN_TEXT").fillMaxWidth(),
                    text = inputValue ?: "",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (state != InputShellState.DISABLED) {
                            TextColor.OnSurface
                        } else {
                            TextColor.OnDisabledSurface
                        },
                    ),
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .alpha(0f)
                        .clickable(onClick = {
                            onOrgUnitActionCLicked.invoke()
                            focusRequester.requestFocus()
                        }),
                )
            }
        },
        onFocusChanged = onFocusChanged,
    )
}

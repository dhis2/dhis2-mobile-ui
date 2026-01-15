package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalFloatValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing112
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing64
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.textFieldHoverPointerIcon

/**
 * DHIS2 Input org unit.
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param inputText: manages the value of the text in the input field.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused.
 * @param modifier: allows a modifier to be passed externally.
 * @param onOrgUnitActionCLicked: callback to when org unit button is clicked.
 */
@Composable
fun InputOrgUnit(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputText: String? = null,
    isRequiredField: Boolean = false,
    onValueChanged: ((String?) -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
    onOrgUnitActionCLicked: () -> Unit,
    showResetButton: Boolean = true,
) {
    var deleteButtonIsVisible by remember(inputText) { mutableStateOf(!inputText.isNullOrEmpty() && state != InputShellState.DISABLED) }
    val focusRequester = remember { FocusRequester() }

    val primaryButton:
        @Composable()
        (() -> Unit)?
    if (deleteButtonIsVisible && showResetButton) {
        primaryButton = {
            IconButton(
                modifier =
                    Modifier
                        .testTag("INPUT_ORG_UNIT_RESET_BUTTON")
                        .padding(Spacing.Spacing0),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Reset Button",
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
    } else {
        primaryButton = {
            IconButton(
                modifier =
                    Modifier
                        .testTag("INPUT_ORG_UNIT_DROPDOWN_BUTTON")
                        .padding(Spacing.Spacing0),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowDropDown,
                        contentDescription = "Dropdown Button",
                    )
                },
                onClick = {
                    focusRequester.requestFocus()
                    onOrgUnitActionCLicked.invoke()
                },
                enabled = state != InputShellState.DISABLED,
            )
        }
    }
    Box {
        InputShell(
            modifier =
                modifier
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
                        focusRequester.requestFocus()
                        onOrgUnitActionCLicked.invoke()
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
                val enabled = state != InputShellState.DISABLED
                androidx.compose.foundation.text.BasicTextField(
                    modifier =
                        modifier
                            .background(
                                Color.Transparent,
                            ).fillMaxWidth()
                            .textFieldHoverPointerIcon(enabled)
                            .testTag("INPUT_ORG_UNIT_TEXT"),
                    value = inputText ?: "",
                    onValueChange = { onValueChanged?.invoke(it) },
                    readOnly = true,
                    enabled = enabled,
                    textStyle =
                        MaterialTheme.typography.bodyLarge.copy(
                            color = if (enabled) TextColor.OnSurface else TextColor.OnDisabledSurface,
                        ),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(Modifier.weight(InternalFloatValues.One)) {
                                innerTextField()
                            }
                        }
                    },
                )
            },
            onFocusChanged = onFocusChanged,
            inputStyle = inputStyle,
        )
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(Spacing64)
                    .padding(end = Spacing112)
                    .alpha(0f)
                    .clickable(
                        enabled = state != InputShellState.DISABLED,
                        onClick = {
                            focusRequester.requestFocus()
                            onOrgUnitActionCLicked.invoke()
                        },
                    ),
        )
    }
}

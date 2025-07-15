package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Input Check Box. Wraps DHIS · [InputShell].
 * @param title: controls the text to be shown for the title.
 * @param checkBoxData: data class [CheckBoxData] used for checkboxes to be displayed.
 * @param modifier: allows a modifier to be passed externally.
 * @param orientation: Controls how the check boxes will be displayed, HORIZONTAL for rows or
 * VERTICAL for columns.
 * @param state: Manages the InputShell state.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param isRequired: controls whether the field is mandatory or not.
 * @param inputStyle: manages the InputShell style.
 * @param onItemChange: is a callback to notify which item has changed into the block.
 * @param onClearSelection: is a callback to notify all items has cleared into the block.
 */
@Composable
fun InputCheckBox(
    title: String,
    checkBoxData: List<CheckBoxData>,
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.VERTICAL,
    state: InputShellState,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    onItemChange: (CheckBoxData) -> Unit,
    onClearSelection: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    InputShell(
        modifier =
            modifier
                .focusRequester(focusRequester)
                .testTag("INPUT_CHECK_BOX"),
        isRequiredField = isRequired,
        title = title,
        state = state,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_CHECK_BOX_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = modifier.testTag("INPUT_CHECK_BOX_SUPPORTING_TEXT"),
                )
            }
        },
        inputField = {
            val updatedCheckBoxData = mutableListOf<CheckBoxData>()
            checkBoxData.forEach {
                updatedCheckBoxData.add(it.copy(enabled = state != InputShellState.DISABLED && it.enabled))
            }
            CheckBoxBlock(
                orientation = orientation,
                content = updatedCheckBoxData,
                modifier = Modifier.offset(x = -Spacing.Spacing8),
                onItemChange = {
                    focusRequester.requestFocus()
                    onItemChange.invoke(it)
                },
            )
        },
        primaryButton = {
            val isClearButtonVisible = checkBoxData.firstOrNull { it.checked } != null && state != InputShellState.DISABLED
            if (isClearButtonVisible) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_CHECK_BOX_CLEAR_BUTTON"),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        focusRequester.requestFocus()
                        onClearSelection.invoke()
                    },
                )
            } else {
                Spacer(modifier = Modifier.width(Spacing.Spacing48))
            }
        },
        inputStyle = inputStyle,
    )
}

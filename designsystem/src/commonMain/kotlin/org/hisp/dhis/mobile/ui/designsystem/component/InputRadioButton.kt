package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState.DISABLED
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation.VERTICAL
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Input Radio Button. Wraps DHIS · [RadioButton].
 * @param title: controls the text to be shown for the title
 * @param radioButtonData: a list of [RadioButtonData] with parameters for options
 * @param modifier: allows a modifier to be passed externally
 * @param orientation: Controls how the radio buttons will be displayed, HORIZONTAL for rows or
 * VERTICAL for columns.
 * @param state: Manages the InputShell state
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData: manages the legendComponent
 * @param isRequired: controls whether the field is mandatory or not
 * @param itemSelected: controls which item is selected.
 * @param onItemChange: is a callback to notify which item has changed into the block.
 */
@Composable
fun InputRadioButton(
    title: String,
    radioButtonData: List<RadioButtonData>,
    modifier: Modifier = Modifier,
    orientation: Orientation = VERTICAL,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    itemSelected: RadioButtonData? = null,
    onItemChange: (RadioButtonData?) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    InputShell(
        modifier =
            modifier
                .focusRequester(focusRequester)
                .testTag("RADIO_BUTTON_INPUT"),
        isRequiredField = isRequired,
        title = title,
        state = state,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("RADIO_BUTTON_INPUT_LEGEND"))
            }
        },
        supportingText = supportingText,
        supportingTextTestTag = "RADIO_BUTTON_INPUT_SUPPORTING_TEXT",
        inputField = {
            val updatedRadioButtonData = mutableListOf<RadioButtonData>()
            radioButtonData.forEach {
                updatedRadioButtonData.add(it.copy(enabled = state != DISABLED && it.enabled))
            }
            RadioButtonBlock(
                orientation = orientation,
                content = updatedRadioButtonData,
                itemSelected = itemSelected,
                modifier = Modifier.offset(x = -Spacing.Spacing8),
                onItemChange = {
                    focusRequester.requestFocus()
                    onItemChange.invoke(it)
                },
            )
        },
        primaryButton = {
            val isClearButtonVisible = itemSelected != null && state != DISABLED
            if (isClearButtonVisible) {
                IconButton(
                    modifier = Modifier.testTag("RADIO_BUTTON_INPUT_CLEAR_BUTTON"),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        focusRequester.requestFocus()
                        onItemChange.invoke(null)
                    },
                )
            } else {
                Spacer(modifier = Modifier.width(Spacing.Spacing48))
            }
        },
        inputStyle = inputStyle,
    )
}

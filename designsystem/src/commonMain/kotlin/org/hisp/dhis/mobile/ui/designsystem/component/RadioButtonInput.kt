package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState.DISABLED
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState.UNFOCUSED
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation.VERTICAL

@Composable
fun RadioButtonInput(
    title: String,
    radioButtonData: List<RadioButtonData>,
    modifier: Modifier = Modifier,
    orientation: Orientation = VERTICAL,
    state: InputShellState = UNFOCUSED,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    itemSelected: RadioButtonData? = null,
    onItemChange: ((RadioButtonData?) -> Unit)? = null,
) {
    InputShell(
        modifier = modifier.testTag("RADIO_BUTTON_INPUT"),
        isRequiredField = isRequired,
        title = title,
        state = state,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("RADIO_BUTTON_INPUT_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = modifier.testTag("RADIO_BUTTON_INPUT_SUPPORTING_TEXT"),
                )
            }
        },
        inputField = {
            val updatedRadioButtonData = mutableListOf<RadioButtonData>()
            radioButtonData.forEach {
                updatedRadioButtonData.add(it.copy(enabled = state != DISABLED && it.enabled))
            }
            RadioButtonBlock(
                orientation = orientation,
                content = updatedRadioButtonData,
                itemSelected = itemSelected,
                onItemChange = onItemChange,
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
                        onItemChange?.invoke(null)
                    },
                )
            }
        },
    )
}

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState.DISABLED
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState.UNFOCUSED
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation.HORIZONTAL
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
    onSelectionChanged: (uid: String) -> Unit,
    onClearSelection: (() -> Unit)? = null,
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
            when (orientation) {
                HORIZONTAL -> RadioButtonInputRow(
                    radioButtonData = radioButtonData,
                    state = state,
                    onSelectionChanged = onSelectionChanged,
                )

                VERTICAL -> RadioButtonInputColumn(
                    radioButtonData = radioButtonData,
                    state = state,
                    onSelectionChanged = onSelectionChanged,
                )
            }
        },
        primaryButton = {
            val isClearButtonVisible = radioButtonData.firstOrNull { it.selected } != null && state != DISABLED
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
                        onClearSelection?.invoke()
                    },
                )
            }
        },
    )
}

@Composable
private fun RadioButtonInputColumn(
    radioButtonData: List<RadioButtonData>,
    state: InputShellState,
    onSelectionChanged: (uid: String) -> Unit,
) {
    Column {
        radioButtonData.forEach { radioButtonData ->
            RadioButton(
                radioButtonData = radioButtonData,
                state = state,
                onSelectionChanged = onSelectionChanged,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun RadioButtonInputRow(
    radioButtonData: List<RadioButtonData>,
    state: InputShellState,
    onSelectionChanged: (uid: String) -> Unit,
) {
    FlowRow {
        radioButtonData.forEach { radioButtonData ->
            RadioButton(
                radioButtonData = radioButtonData,
                state = state,
                onSelectionChanged = onSelectionChanged,
            )
        }
    }
}

@Composable
private fun RadioButton(
    radioButtonData: RadioButtonData,
    state: InputShellState,
    onSelectionChanged: (uid: String) -> Unit,
) {
    RadioButton(
        radioButtonData = radioButtonData.copy(
            enabled = state != DISABLED,
        ),
        onClick = { _ ->
            onSelectionChanged(radioButtonData.uid)
        },
    )
}

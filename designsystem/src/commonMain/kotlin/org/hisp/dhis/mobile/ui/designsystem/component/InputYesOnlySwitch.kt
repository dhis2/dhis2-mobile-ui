package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun InputYesOnlySwitch(
    title: String,
    modifier: Modifier = Modifier,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    isChecked: Boolean,
    onClick: (Boolean) -> Unit,
) {
    val focusRequester  = remember { FocusRequester() }
    var indicatorColor by remember { mutableStateOf(InputShellState.UNFOCUSED.color) }
    InputShell(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                indicatorColor =
                    if (it.isFocused && state != InputShellState.ERROR && state != InputShellState.WARNING) InputShellState.FOCUSED.color else state.color
            }
            .testTag("INPUT_YES_ONLY_SWITCH"),
        isRequiredField = isRequired,
        title = "",
        state = state,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_YES_ONLY_SWITCH_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = modifier.testTag("INPUT_YES_ONLY_SWITCH_SUPPORTING_TEXT"),
                )
            }
        },
        inputField = {
            Row(
                modifier = Modifier
                    .focusable()
                    .fillMaxWidth()
                    .padding(end = Spacing.Spacing12, bottom = Spacing.Spacing4),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Title(
                    text = title,
                    textColor = indicatorColor,
                )
                Switch(
                    isChecked = isChecked,
                    enabled = state != InputShellState.DISABLED,
                ) {
                    focusRequester.requestFocus()
                    onClick.invoke(it)
                }
            }
        },
        inputStyle = inputStyle,
    )
}

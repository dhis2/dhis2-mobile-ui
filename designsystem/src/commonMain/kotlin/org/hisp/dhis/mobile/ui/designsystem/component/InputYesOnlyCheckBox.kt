package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun InputYesOnlyCheckBox(
    checkBoxData: CheckBoxData,
    modifier: Modifier = Modifier,
    state: InputShellState,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    onClick: (Boolean) -> Unit,
) {
    var indicatorColor by remember { mutableStateOf(InputShellState.UNFOCUSED.color) }
    InputShell(
        modifier = modifier
            .onFocusChanged {
                indicatorColor =
                    if (it.isFocused && state != InputShellState.ERROR && state != InputShellState.WARNING) InputShellState.FOCUSED.color else state.color
            }
            .testTag("INPUT_YES_ONLY_CHECKBOX"),
        isRequiredField = isRequired,
        title = "",
        state = state,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_YES_ONLY_CHECKBOX_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = modifier.testTag("INPUT_YES_ONLY_CHECKBOX_SUPPORTING_TEXT"),
                )
            }
        },
        inputField = {
            CheckBox(
                modifier = Modifier
                    .offset(x = -Spacing.Spacing8),
                checkBoxData = CheckBoxData(
                    uid = checkBoxData.uid,
                    checked = checkBoxData.checked,
                    enabled = if (state == InputShellState.DISABLED) false else checkBoxData.enabled,
                    textInput = checkBoxData.textInput,
                ),
            ) {
                onClick.invoke(it)
            }
        },
    )
}

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.offset
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
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Input Yes/No Field. Wraps DHIS · [RadioButton].
 * @param title: controls the text to be shown for the title.
 * @param modifier: allows a modifier to be passed externally.
 * @param state: Manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param isRequired: controls whether the field is mandatory or not.
 * @param itemSelected: controls which item is selected.
 * @param onItemChange: is a callback to notify which item has changed into the block.
 */
@Composable
fun InputYesNoField(
    title: String,
    modifier: Modifier = Modifier,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    itemSelected: InputYesNoFieldValues? = null,
    onItemChange: (InputYesNoFieldValues?) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    InputShell(
        modifier =
            modifier
                .focusRequester(focusRequester)
                .testTag("INPUT_YES_NO_FIELD"),
        isRequiredField = isRequired,
        title = title,
        state = state,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_YES_NO_FIELD_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = modifier.testTag("INPUT_YES_NO_FIELD_SUPPORTING_TEXT"),
                )
            }
        },
        inputField = {
            val options =
                InputYesNoFieldValues.entries.map {
                    RadioButtonData(
                        it.value,
                        itemSelected == it,
                        state != InputShellState.DISABLED,
                        provideStringResource(it.value.lowercase()),
                    )
                }
            RadioButtonBlock(
                Orientation.HORIZONTAL,
                options,
                options.find { it.selected },
                Modifier.offset(x = -Spacing.Spacing8),
            ) { radioButtonData ->
                focusRequester.requestFocus()
                onItemChange.invoke(
                    InputYesNoFieldValues.entries.firstOrNull { it.name.equals(radioButtonData.uid, true) },
                )
            }
        },
        primaryButton = {
            val isClearButtonVisible = itemSelected != null && state != InputShellState.DISABLED
            if (isClearButtonVisible) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_YES_NO_FIELD_CLEAR_BUTTON"),
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
            }
        },
        inputStyle = inputStyle,
    )
}

enum class InputYesNoFieldValues(
    val value: String,
) {
    YES("Yes"),
    NO("No"),
}

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState.DISABLED
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation.VERTICAL
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

private const val MAX_INLINE_OPTIONS_TO_DISPLAY = 7

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
    inputStyle: InputStyle = InputStyle.DarkInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    maxItemsToShow: Int = MAX_OPTIONS_ITEMS_TO_SHOW,
    noResultsFoundString: String = provideStringResource("no_results_found"),
    searchToFindMoreString: String = provideStringResource("search_to_see_more"),
    doneButtonText: String = provideStringResource("done"),
    itemSelected: RadioButtonData? = null,
    onItemChange: (RadioButtonData?) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var showOptionsBottomSheet by remember { mutableStateOf(false) }
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
            if (updatedRadioButtonData.size <= MAX_INLINE_OPTIONS_TO_DISPLAY) {
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
            } else {
                Box(
                    modifier =
                        Modifier
                            .testTag("INPUT_MULTI_SELECT_CLICKABLE")
                            .fillMaxSize()
                            .alpha(0f)
                            .clickable(
                                enabled = state != DISABLED,
                                onClick = {
                                    focusRequester.requestFocus()
                                    showOptionsBottomSheet = true
                                },
                            ),
                )
                Text(
                    modifier =
                        Modifier
                            .testTag("INPUT_RADIO_BUTTON_DROPDOWN_TEXT")
                            .focusable(true)
                            .fillMaxWidth(),
                    text = itemSelected?.textInput?.text.orEmpty(),
                    style =
                        MaterialTheme.typography.bodyLarge.copy(
                            color =
                                if (state != DISABLED) {
                                    TextColor.OnSurface
                                } else {
                                    TextColor.OnDisabledSurface
                                },
                        ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        },
        primaryButton = {
            if (radioButtonData.size <= MAX_INLINE_OPTIONS_TO_DISPLAY) {
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
            } else {
                IconButton(
                    modifier =
                        Modifier
                            .testTag("INPUT_DROP_DOWN_ICON_BUTTON")
                            .padding(Spacing.Spacing0),
                    enabled = state != DISABLED,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Show BottomSheet",
                        )
                    },
                    onClick = {
                        focusRequester.requestFocus()
                        showOptionsBottomSheet = true
                    },
                )
            }
        },
        secondaryButton =
            if (
                radioButtonData.size > MAX_INLINE_OPTIONS_TO_DISPLAY &&
                state != DISABLED &&
                itemSelected != null
            ) {
                {
                    IconButton(
                        modifier = Modifier.testTag("INPUT_DROPDOWN_RESET_BUTTON"),
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Cancel,
                                contentDescription = "Reset Button",
                            )
                        },
                        onClick = {
                            focusRequester.requestFocus()
                            onItemChange(null)
                        },
                    )
                }
            } else {
                null
            },
        inputStyle = inputStyle,
    )
    if (showOptionsBottomSheet) {
        OptionsBottomSheet(
            items = radioButtonData,
            title = title,
            maxItemsToShow = maxItemsToShow,
            noResultsFoundString = noResultsFoundString,
            searchToFindMoreString = searchToFindMoreString,
            doneButtonText = doneButtonText,
            content = { filteredItems ->
                RadioButtonBlock(
                    orientation = orientation,
                    content = filteredItems.filterIsInstance<RadioButtonData>(),
                    itemSelected = itemSelected,
                    modifier = Modifier.fillMaxWidth(),
                    onItemChange = {
                        focusRequester.requestFocus()
                        onItemChange.invoke(it)
                    },
                )
            },
            onDismiss = {
                showOptionsBottomSheet = false
            },
        )
    }
}

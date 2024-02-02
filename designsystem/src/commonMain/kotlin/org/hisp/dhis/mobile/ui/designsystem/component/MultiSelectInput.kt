package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

private const val INLINE_CHECKBOXES_MIN_REQ_ITEMS = 6

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiSelectInput(
    items: List<CheckBoxData>,
    title: String,
    state: InputShellState,
    modifier: Modifier = Modifier,
    onItemSelected: (CheckBoxData) -> Unit,
    onClearItemSelection: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    val clearSelectionButton: (@Composable () -> Unit)? = if (items.any { it.checked } && state != InputShellState.DISABLED) {
        {
            IconButton(
                modifier = Modifier.testTag("INPUT_MULTI_SELECT_CLEAR_ICON_BUTTON").padding(Spacing.Spacing0),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Clear Selection",
                    )
                },
                onClick = {
                    focusRequester.requestFocus()
                    onClearItemSelection()
                },
            )
        }
    } else {
        null
    }

    val bottomSheetDropdownButton: (@Composable () -> Unit) = {
        IconButton(
            modifier = Modifier.testTag("INPUT_MULTI_SELECT_DROP_DOWN_ICON_BUTTON").padding(Spacing.Spacing0),
            enabled = state != InputShellState.DISABLED,
            icon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Show MultiSelect BottomSheet",
                )
            },
            onClick = {
                focusRequester.requestFocus()
                // TODO: Show bottom sheet
            },
        )
    }

    InputShell(
        title = title,
        state = state,
        modifier = modifier.testTag("INPUT_MULTI_SELECT").focusRequester(focusRequester),
        inputField = {
            if (items.size < INLINE_CHECKBOXES_MIN_REQ_ITEMS) {
                Column(modifier = Modifier.testTag("INPUT_MULTI_SELECT_CHECKBOX_LIST")) {
                    items.forEachIndexed { index, item ->
                        CheckBox(
                            modifier = Modifier.testTag("INPUT_MULTI_SELECT_CHECKBOX_LIST_ITEM_$index").fillMaxWidth(),
                            checkBoxData = item.copy(
                                enabled = state != InputShellState.DISABLED,
                            ),
                            onCheckedChange = { checked ->
                                focusRequester.requestFocus()
                                onItemSelected(item.copy(checked = checked))
                            },
                        )
                    }
                }
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items.forEachIndexed { index, item ->
                        SelectedItemChip(
                            item = item,
                            index = index,
                            enabled = state != InputShellState.DISABLED,
                        ) { newItem ->
                            onItemSelected(newItem)
                        }
                    }
                }
            }
        },
        primaryButton = {
            Box(Modifier.testTag("INPUT_MULTI_SELECT_ACTION_ICON_BUTTON").requiredSize(48.dp)) {
                if (items.size < INLINE_CHECKBOXES_MIN_REQ_ITEMS) {
                    clearSelectionButton?.invoke()
                } else {
                    bottomSheetDropdownButton.invoke()
                }
            }
        },
    )
}

@Composable
private fun SelectedItemChip(
    item: CheckBoxData,
    index: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClearItemSelection: (CheckBoxData) -> Unit,
) {
    if (item.checked) {
        InputChip(
            modifier = modifier.testTag("INPUT_MULTI_SELECT_CHECKBOX_CHIP_ITEM_$index"),
            label = item.textInput!!,
            selected = false,
            enabled = enabled,
            withTrailingIcon = true,
            hasTransparentBackground = true,
            onIconSelected = {
                onClearItemSelection(item.copy(checked = false))
            },
        )
    }
}

@Composable
private fun MultiSelectBottomSheet() {
    // TODO
}

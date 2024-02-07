package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

private const val INLINE_CHECKBOXES_MIN_REQ_ITEMS = 6

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiSelectInput(
    items: List<CheckBoxData>,
    title: String,
    state: InputShellState,
    onItemsSelected: (List<CheckBoxData>) -> Unit,
    modifier: Modifier = Modifier,
    noResultsFoundString: String = provideStringResource("no_results_found"),
    doneButtonText: String = provideStringResource("done"),
    onClearItemSelection: () -> Unit,
) {
    var showMultiSelectBottomSheet by remember { mutableStateOf(false) }
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
                showMultiSelectBottomSheet = true
            },
        )
    }

    Box {
        InputShell(
            title = title,
            state = state,
            modifier = modifier.testTag("INPUT_MULTI_SELECT").focusRequester(focusRequester),
            inputField = {
                if (items.size <= INLINE_CHECKBOXES_MIN_REQ_ITEMS) {
                    Column(modifier = Modifier.testTag("INPUT_MULTI_SELECT_CHECKBOX_LIST")) {
                        items.forEachIndexed { index, item ->
                            CheckBox(
                                modifier = Modifier.testTag("INPUT_MULTI_SELECT_CHECKBOX_LIST_ITEM_$index")
                                    .fillMaxWidth(),
                                checkBoxData = item.copy(
                                    enabled = state != InputShellState.DISABLED,
                                ),
                                onCheckedChange = { checked ->
                                    focusRequester.requestFocus()
                                    onItemsSelected(listOf(item.copy(checked = checked)))
                                },
                            )
                        }
                    }
                } else {
                    Box(Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .testTag("INPUT_MULTI_SELECT_CLICKABLE")
                                .matchParentSize()
                                .requiredHeightIn(min = Spacing.Spacing48)
                                .alpha(0f)
                                .clickable(
                                    enabled = state != InputShellState.DISABLED &&
                                        items.size > INLINE_CHECKBOXES_MIN_REQ_ITEMS,
                                    onClick = {
                                        focusRequester.requestFocus()
                                        showMultiSelectBottomSheet = true
                                    },
                                ),
                        )

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items.forEachIndexed { index, item ->
                                SelectedItemChip(
                                    item = item,
                                    index = index,
                                    enabled = state != InputShellState.DISABLED,
                                ) { newItem ->
                                    onItemsSelected(listOf(newItem))
                                }
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

        if (showMultiSelectBottomSheet) {
            MultiSelectBottomSheet(
                items = items,
                title = title,
                noResultsFoundString = noResultsFoundString,
                doneButtonText = doneButtonText,
                onItemsSelected = {
                    onItemsSelected(it)
                    showMultiSelectBottomSheet = false
                },
                onDismiss = {
                    showMultiSelectBottomSheet = false
                },
            )
        }
    }
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
private fun MultiSelectBottomSheet(
    items: List<CheckBoxData>,
    title: String,
    noResultsFoundString: String,
    doneButtonText: String,
    onItemsSelected: (List<CheckBoxData>) -> Unit,
    onDismiss: () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredOptions = items
        .filter { it.textInput!!.contains(searchQuery, ignoreCase = true) }
        .toMutableStateList()

    val itemsModified = remember { mutableStateListOf<CheckBoxData>() }

    BottomSheetShell(
        modifier = Modifier.testTag("INPUT_MULTI_SELECT_BOTTOM_SHEET"),
        title = title,
        content = {
            Column(
                modifier = Modifier
                    .padding(top = Spacing.Spacing8),
            ) {
                if (filteredOptions.isNotEmpty()) {
                    filteredOptions.forEachIndexed { index, item ->
                        CheckBox(
                            checkBoxData = item,
                            onCheckedChange = {
                                filteredOptions[index] = item.copy(checked = it)
                                itemsModified.add(item.copy(checked = it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                } else {
                    Text(
                        text = noResultsFoundString,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                    )
                }
            }
        },
        onDismiss = onDismiss,
        searchQuery = searchQuery,
        onSearch = { searchQuery = it },
        onSearchQueryChanged = { searchQuery = it },
        buttonBlock = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onItemsSelected(itemsModified)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                    )
                },
                text = doneButtonText,
                style = ButtonStyle.FILLED,
            )
        },
    )
}

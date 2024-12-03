package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing0
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing16
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing56
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing64
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing8
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

private const val MAX_DROPDOWN_ITEMS = 6
private const val MAX_DROPDOWN_ITEMS_TO_SHOW = 50

/**
 * DHIS2 Input dropdown. Wraps DHIS · [DropdownInputField].
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param inputStyle: Manages the InputShell style.
 * @param itemCount: controls the number of items to be shown.
 * @param onSearchOption: callback to search for an specific option.
 * @param fetchItem: gets the item to display in the list.
 * @param selectedItem: manages the value of the selected item.
 * @param supportingTextData: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused.
 * @param modifier: allows a modifier to be passed externally.
 * @param onResetButtonClicked: callback to when reset button is clicked.
 * @param onItemSelected: callback to when a dropdown item is selected.
 * @param showSearchBar: config whether to show search bar in the bottom sheet.
 * @param expanded: config whether the dropdown should be initially displayed.
 * @param useDropDown: use dropdown if true. Bottomsheet with search capability otherwise.
 * @param onDismiss: gives access to the onDismiss event.
 * @param noResultsFoundString: text to be shown in pop up when no results are found.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDropDown(
    title: String,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    itemCount: Int,
    onSearchOption: (String) -> Unit,
    fetchItem: (index: Int) -> DropdownItem,
    selectedItem: DropdownItem? = null,
    supportingTextData: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequiredField: Boolean = false,
    modifier: Modifier = Modifier,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    onResetButtonClicked: () -> Unit,
    onItemSelected: (index: Int, item: DropdownItem) -> Unit,
    showSearchBar: Boolean = true,
    expanded: Boolean = false,
    useDropDown: Boolean = true,
    loadOptions: () -> Unit,
    onDismiss: () -> Unit = {},
    noResultsFoundString: String = provideStringResource("no_results_found"),
    searchToFindMoreString: String = provideStringResource("search_to_see_more"),
) {
    val focusRequester = remember { FocusRequester() }
    var showDropdown by remember { mutableStateOf(expanded) }
    var currentItem by remember(selectedItem) { mutableStateOf(selectedItem) }

    val inputField: @Composable (modifier: Modifier) -> Unit = { inputModifier ->
        DropdownInputField(
            modifier = inputModifier,
            focusRequester = focusRequester,
            title = title,
            state = state,
            inputStyle = inputStyle,
            isRequiredField = isRequiredField,
            expanded = showDropdown,
            onFocusChanged = onFocusChanged,
            supportingTextData = supportingTextData,
            legendData = legendData,
            selectedItem = currentItem,
            onResetButtonClicked = onResetButtonClicked,
            onDropdownIconClick = {
                showDropdown = !showDropdown
            },
        )
    }

    if (!useDropDown) {
        Box {
            inputField(modifier)

            if (showDropdown) {
                var searchQuery by remember { mutableStateOf("") }

                val scrollState = rememberLazyListState()
                BottomSheetShell(
                    modifier = Modifier.testTag("INPUT_DROPDOWN_BOTTOM_SHEET"),
                    title = title,
                    contentScrollState = scrollState,
                    content = {
                        LazyColumn(
                            modifier = Modifier
                                .testTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS")
                                .semantics {
                                    dropDownItemCount = itemCount
                                }
                                .padding(top = Spacing8),
                            state = scrollState,
                        ) {
                            when {
                                itemCount > 0 ->
                                    items(count = itemCount) { index ->
                                        with(fetchItem(index)) {
                                            DropdownItem(
                                                modifier = Modifier.testTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEM_$index"),
                                                item = this,
                                                selected = selectedItem == this,
                                                contentPadding = PaddingValues(Spacing8),
                                                onItemClick = {
                                                    currentItem = this
                                                    onItemSelected(index, this)
                                                    showDropdown = false
                                                    onDismiss()
                                                },
                                            )
                                        }
                                    }

                                searchQuery.isEmpty() -> {
                                    item {
                                        ProgressIndicator(type = ProgressIndicatorType.CIRCULAR_SMALL)
                                    }
                                    loadOptions()
                                }

                                else ->
                                    item {
                                        Text(
                                            text = noResultsFoundString,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(24.dp),
                                        )
                                    }
                            }
                        }
                    },
                    onDismiss = {
                        showDropdown = false
                        onDismiss()
                    },
                    searchQuery = if (showSearchBar) {
                        searchQuery
                    } else {
                        null
                    },
                    onSearch = {
                        searchQuery = it
                        onSearchOption(it)
                    },
                    onSearchQueryChanged = {
                        searchQuery = it
                        onSearchOption(it)
                    },
                )
            }
        }
    } else {
        ExposedDropdownMenuBox(
            expanded = showDropdown,
            onExpandedChange = { },
            modifier = modifier
                .background(
                    color = SurfaceColor.SurfaceBright,
                    shape = RoundedCornerShape(Spacing8),
                ),
        ) {
            inputField(Modifier.menuAnchor(MenuAnchorType.PrimaryEditable))

            MaterialTheme(
                shapes = Shapes(extraSmall = RoundedCornerShape(Spacing8)),
            ) {
                ExposedDropdownMenu(
                    expanded = showDropdown,
                    onDismissRequest = {
                        showDropdown = false
                        onDismiss()
                    },
                    modifier = Modifier.background(
                        color = SurfaceColor.SurfaceBright,
                        shape = RoundedCornerShape(Spacing8),
                    ).exposedDropdownSize().testTag("INPUT_DROPDOWN_MENU"),
                ) {
                    repeat(itemCount) { index ->
                        with(fetchItem(index)) {
                            DropdownItem(
                                modifier = Modifier.testTag("INPUT_DROPDOWN_MENU_ITEM_$index")
                                    .fillMaxWidth()
                                    .padding(start = dropdownStartPadding(inputStyle) + 8.dp),
                                item = this,
                                selected = selectedItem == this,
                                contentPadding = PaddingValues(
                                    horizontal = Spacing8,
                                    vertical = Spacing16,
                                ),
                                onItemClick = {
                                    currentItem = this
                                    onItemSelected(index, this)
                                    showDropdown = false
                                    onDismiss()
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun dropdownStartPadding(inputStyle: InputStyle): Dp {
    return if (inputStyle is InputStyle.ParameterInputStyle) {
        inputStyle.startIndent
    } else {
        Spacing0
    }
}

/**
 * DHIS2 DropDownInputField. Wraps DHIS · [InputShell].
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param inputStyle: Manages the InputShell style.
 * @param selectedItem: manages the value of the selected item.
 * @param supportingTextData: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param isRequiredField: controls whether the field is mandatory or not.
 * @param onFocusChanged: gives access to the onFocusChanged returns true if
 * item is focused.
 * @param modifier: allows a modifier to be passed externally.
 * @param onResetButtonClicked: callback to when reset button is clicked.
 * @param onDropdownIconClick: callback to when action button is clicked.
 * @param expanded: will control the action button.
 * @param focusRequester: [FocusRequester] to be used.
 */
// TODO make private when a period selector input is designed
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownInputField(
    title: String,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    isRequiredField: Boolean,
    expanded: Boolean,
    focusRequester: FocusRequester,
    onFocusChanged: ((Boolean) -> Unit)?,
    supportingTextData: List<SupportingTextData>?,
    legendData: LegendData?,
    selectedItem: DropdownItem?,
    onResetButtonClicked: () -> Unit,
    onDropdownIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        InputShell(
            modifier = modifier
                .testTag("INPUT_DROPDOWN")
                .focusRequester(focusRequester),
            title = title,
            state = state,
            isRequiredField = isRequiredField,
            onFocusChanged = onFocusChanged,
            supportingText = {
                supportingTextData?.forEach { label ->
                    SupportingText(
                        label.text,
                        label.state,
                        modifier = Modifier.testTag("INPUT_DROPDOWN_SUPPORTING_TEXT"),
                    )
                }
            },
            legend = {
                legendData?.let {
                    Legend(legendData, Modifier.testTag("INPUT_DROPDOWN_LEGEND"))
                }
            },
            inputField = {
                Text(
                    modifier = Modifier
                        .testTag("INPUT_DROPDOWN_TEXT")
                        .focusable(true)
                        .fillMaxWidth(),
                    text = selectedItem?.label.orEmpty(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (state != InputShellState.DISABLED) {
                            TextColor.OnSurface
                        } else {
                            TextColor.OnDisabledSurface
                        },
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            primaryButton = {
                IconButton(
                    modifier = Modifier.testTag("INPUT_DROPDOWN_ARROW_BUTTON").onFocusChanged {
                        onFocusChanged?.invoke(it.isFocused)
                    },
                    enabled = state != InputShellState.DISABLED,
                    icon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    onClick = {
                        focusRequester.requestFocus()
                        onDropdownIconClick()
                    },
                )
            },
            secondaryButton =
            if (selectedItem != null && state != InputShellState.DISABLED) {
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
                            onResetButtonClicked.invoke()
                        },
                    )
                }
            } else {
                null
            },
            inputStyle = inputStyle,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing64)
                .padding(end = Spacing56)
                .alpha(0f)
                .clickable(
                    enabled = state != InputShellState.DISABLED,
                    onClick = {
                        focusRequester.requestFocus()
                        onDropdownIconClick()
                    },
                ),
        )
    }
}

/**
 * DHIS2 composable  DropdownItem.
 * used internally for DHIS2 component [InputDropDown] with
 * @param item: [DropdownItem] with label to be used.
 * @param contentPadding: the padding to be used.
 * @param selected: whether item is selected or not.
 * @param onItemClick: call back for item selected.
 * @param modifier: optional modifier.
 */
@Composable
private fun DropdownItem(
    item: DropdownItem,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onItemClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Spacing8))
            .clickable(onClick = onItemClick)
            .background(
                color = if (selected) {
                    SurfaceColor.PrimaryContainer
                } else {
                    Color.Unspecified
                },
            )
            .padding(contentPadding),
    ) {
        Text(
            text = item.label,
            style = if (selected) {
                DHIS2SCustomTextStyles.bodyLargeBold
            } else {
                MaterialTheme.typography.bodyLarge
            },
            color = TextColor.OnSurface,
        )
    }
}

@Immutable
data class DropdownItem(val label: String)

val DropDownItemCount = SemanticsPropertyKey<Int>(
    name = "DropDownItemCount",
)
var SemanticsPropertyReceiver.dropDownItemCount by DropDownItemCount

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing0
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing16
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing8
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

private const val MAX_DROPDOWN_ITEMS = 6

/**
 * DHIS2 Input dropdown. Wraps DHIS · [InputShell].
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param selectedItem manages the value of the selected item
 * @param supportingTextData is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param isRequiredField controls whether the field is mandatory or not
 * @param onFocusChanged gives access to the onFocusChanged returns true if
 * item is focused
 * @param modifier allows a modifier to be passed externally
 * @param onResetButtonClicked callback to when reset button is clicked
 * @param showSearchBar config whether to show search bar in the bottom sheet
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDropDown(
    title: String,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    dropdownItems: List<DropdownItem>,
    selectedItem: DropdownItem? = null,
    supportingTextData: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequiredField: Boolean = false,
    modifier: Modifier = Modifier,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    onResetButtonClicked: () -> Unit,
    onItemSelected: (DropdownItem) -> Unit,
    showSearchBar: Boolean = true,
    noResultsFoundString: String = "No results found",
) {
    val focusRequester = remember { FocusRequester() }
    var showDropdown by remember { mutableStateOf(false) }

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
            selectedItem = selectedItem,
            onResetButtonClicked = onResetButtonClicked,
            onDropdownIconClick = {
                showDropdown = !showDropdown
            },
        )
    }

    if (dropdownItems.size > MAX_DROPDOWN_ITEMS) {
        Box {
            inputField(modifier)

            if (showDropdown) {
                var searchQuery by remember { mutableStateOf("") }

                val filteredOptions =
                    dropdownItems.filter { it.label.contains(searchQuery, ignoreCase = true) }

                BottomSheetShell(
                    modifier = Modifier.testTag("INPUT_DROPDOWN_BOTTOM_SHEET"),
                    title = title,
                    content = {
                        Column(
                            modifier = Modifier
                                .testTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS")
                                .padding(top = Spacing8),
                        ) {
                            if (filteredOptions.isNotEmpty()) {
                                filteredOptions.forEachIndexed { index, item ->
                                    DropdownItem(
                                        modifier = Modifier.testTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEM_$index"),
                                        item = item,
                                        selected = selectedItem == item,
                                        contentPadding = PaddingValues(Spacing8),
                                        onItemClick = {
                                            onItemSelected(item)
                                            showDropdown = false
                                        },
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
                    onDismiss = {
                        showDropdown = false
                    },
                    searchQuery = if (showSearchBar) {
                        searchQuery
                    } else {
                        null
                    },
                    onSearch = { searchQuery = it },
                    onSearchQueryChanged = { searchQuery = it },
                )
            }
        }
    } else {
        ExposedDropdownMenuBox(
            expanded = showDropdown,
            onExpandedChange = { },
            modifier = Modifier
                .background(
                    color = SurfaceColor.SurfaceBright,
                    shape = RoundedCornerShape(Spacing8),
                ),
        ) {
            inputField(modifier.menuAnchor())

            MaterialTheme(
                shapes = Shapes(extraSmall = RoundedCornerShape(Spacing8)),
            ) {
                ExposedDropdownMenu(
                    expanded = showDropdown,
                    onDismissRequest = { showDropdown = false },
                    modifier = Modifier.background(
                        color = SurfaceColor.SurfaceBright,
                        shape = RoundedCornerShape(Spacing8),
                    ).testTag("INPUT_DROPDOWN_MENU"),
                ) {
                    Column(modifier = Modifier.padding(horizontal = Spacing8)) {
                        dropdownItems.forEachIndexed { index, item ->
                            DropdownItem(
                                modifier = Modifier.testTag("INPUT_DROPDOWN_MENU_ITEM_$index")
                                    .padding(start = dropdownStartPadding(inputStyle)),
                                item = item,
                                selected = selectedItem == item,
                                contentPadding = PaddingValues(
                                    horizontal = Spacing8,
                                    vertical = Spacing16,
                                ),
                                onItemClick = {
                                    onItemSelected(item)
                                    showDropdown = false
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

fun dropdownStartPadding(inputStyle: InputStyle): Dp {
    return if (inputStyle is InputStyle.ParameterInputStyle) {
        inputStyle.startIndent
    } else {
        Spacing0
    }
}

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
                    modifier = modifier.testTag("INPUT_DROPDOWN_SUPPORTING_TEXT"),
                )
            }
        },
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_DROPDOWN_LEGEND"))
            }
        },
        inputField = {
            Box {
                Text(
                    modifier = Modifier
                        .testTag("INPUT_DROPDOWN_TEXT")
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
                Box(
                    modifier = Modifier
                        .matchParentSize()
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
                    onClick = onResetButtonClicked,
                )
            }
        } else {
            null
        },
        inputStyle = inputStyle,
    )
}

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

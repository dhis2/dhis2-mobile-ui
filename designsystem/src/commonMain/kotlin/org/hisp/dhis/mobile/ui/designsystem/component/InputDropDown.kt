package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles
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
 */
@Composable
fun InputDropDown(
    title: String,
    state: InputShellState,
    dropdownItems: List<DropdownItem>,
    selectedItem: DropdownItem? = null,
    supportingTextData: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequiredField: Boolean = false,
    modifier: Modifier = Modifier,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    onResetButtonClicked: () -> Unit,
    onItemSelected: (DropdownItem) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var showDropdown by remember { mutableStateOf(false) }

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
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .alpha(0f)
                            .clickable(
                                enabled = state != InputShellState.DISABLED,
                                onClick = {
                                    focusRequester.requestFocus()
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
                        Icon(
                            imageVector = Icons.Outlined.ArrowDropDown,
                            contentDescription = "Dropdown Button",
                        )
                    },
                    onClick = {
                        focusRequester.requestFocus()
                        showDropdown = true
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
        )

        if (showDropdown) {
            if (dropdownItems.size > MAX_DROPDOWN_ITEMS) {
                BottomSheetShell(
                    modifier = Modifier.testTag("INPUT_DROPDOWN_BOTTOM_SHEET"),
                    title = title,
                    content = {
                        Column(
                            modifier = Modifier.padding(top = Spacing8),
                        ) {
                            dropdownItems.forEachIndexed { index, item ->
                                BottomSheetItem(
                                    modifier = Modifier.testTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEM_$index"),
                                    item = item,
                                    selected = selectedItem == item,
                                    onItemClick = {
                                        onItemSelected(item)
                                        showDropdown = false
                                    },
                                )
                            }
                        }
                    },
                    onDismiss = {
                        showDropdown = false
                    },
                )
            } else {
                DropdownMenu(
                    modifier = Modifier.testTag("INPUT_DROPDOWN_MENU"),
                    expanded = true,
                    onDismissRequest = { showDropdown = false },
                ) {
                    dropdownItems.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            modifier = Modifier.testTag("INPUT_DROPDOWN_MENU_ITEM_$index"),
                            text = { Text(item.label) },
                            onClick = {
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

@Composable
private fun BottomSheetItem(
    item: DropdownItem,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onItemClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .background(
                color = if (selected) {
                    SurfaceColor.PrimaryContainer
                } else {
                    Color.Unspecified
                },
                shape = RoundedCornerShape(Spacing8),
            )
            .padding(Spacing8),
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

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 [OrgBottomSheet] component designed to be used
 * with Input Org Unit, wraps DHIS2 [BottomSheetShell].
 * @param orgTreeItems list of [OrgTreeItem] with Org tree information
 * @param title: Header.
 * @param subtitle: optional subtitle.
 * @param description: optional description.
 * @param clearAllButtonText: text for clear all button.
 * @param doneButtonText: text for accept button.
 * @param doneButtonIcon: icon for accept button.
 * @param noResultsFoundText: text for no results found.
 * @param headerTextAlignment [Alignment] for header text.
 * @param icon: optional icon to be shown above the header .
 * @param onSearch: access to the on search event.
 * @param onDismiss: access to the on dismiss event.
 * @param onItemSelected: access to the on item selected event.
 * @param onItemClick: access to onItemClick event.
 * @param onClearAll: access to the on clear all event.
 * @param onDone: access to the on done event.
 * @param modifier width and size of the barcode.
 */
@Composable
fun OrgBottomSheet(
    orgTreeItems: List<OrgTreeItem>,
    modifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    description: String? = null,
    clearAllButtonText: String = provideStringResource("clear_all"),
    doneButtonText: String? = null,
    doneButtonIcon: ImageVector = Icons.Filled.Check,
    noResultsFoundText: String = provideStringResource("no_results_found"),
    headerTextAlignment: TextAlign = TextAlign.Center,
    icon: @Composable (() -> Unit)? = null,
    onSearch: ((String) -> Unit)? = null,
    onDismiss: () -> Unit,
    onItemClick: (uid: String) -> Unit,
    onItemSelected: (uid: String, checked: Boolean) -> Unit,
    onClearAll: (() -> Unit)? = null,
    onDone: () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }
    var orgTreeHeight by remember { mutableStateOf(0) }
    val orgTreeHeightInDp = with(LocalDensity.current) { orgTreeHeight.toDp() }

    BottomSheetShell(
        modifier = modifier,
        title = title,
        subtitle = subtitle,
        description = description,
        headerTextAlignment = headerTextAlignment,
        icon = icon,
        searchQuery = searchQuery,
        onSearchQueryChanged = { query ->
            searchQuery = query
            onSearch?.invoke(searchQuery)
        },
        onSearch = onSearch,
        scrollableContainerMinHeight = InternalSizeValues.Size386,
        scrollableContainerMaxHeight = maxOf(orgTreeHeightInDp, InternalSizeValues.Size386),
        content = {
            OrgTreeList(
                orgTreeItems = orgTreeItems,
                searchQuery = searchQuery,
                noResultsFoundText = noResultsFoundText,
                onItemClick = onItemClick,
                onItemSelected = onItemSelected,
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        val treeHeight = coordinates.size.height
                        if (treeHeight > orgTreeHeight) {
                            orgTreeHeight = treeHeight
                        }
                    },
            )
        },
        buttonBlock = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (onClearAll != null) {
                    Button(
                        modifier = Modifier.weight(1f)
                            .testTag("CLEAR_ALL_BUTTON"),
                        onClick = onClearAll,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.ClearAll,
                                contentDescription = null,
                            )
                        },
                        text = clearAllButtonText,
                        enabled = orgTreeItems.any { it.selected },
                    )

                    Spacer(Modifier.requiredWidth(Spacing.Spacing16))
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onDone,
                    icon = {
                        Icon(
                            imageVector = doneButtonIcon,
                            contentDescription = null,
                        )
                    },
                    enabled = orgTreeItems.any { it.selected },
                    text = doneButtonText ?: provideStringResource("done"),
                    style = ButtonStyle.FILLED,
                )
            }
        },
        onDismiss = onDismiss,
    )
}

@Composable
private fun OrgTreeList(
    orgTreeItems: List<OrgTreeItem>,
    searchQuery: String,
    noResultsFoundText: String,
    modifier: Modifier = Modifier,
    onItemClick: (orgUnitUid: String) -> Unit,
    onItemSelected: (orgUnitUid: String, checked: Boolean) -> Unit,
) {
    val scrollState = rememberScrollState()
    val hasSearchQuery by derivedStateOf { searchQuery.isNotBlank() }
    if (orgTreeItems.isEmpty() && hasSearchQuery) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.Spacing24)
                .padding(vertical = Spacing.Spacing24)
                .testTag("ORG_TREE_NO_RESULTS_FOUND"),
            textAlign = TextAlign.Center,
            text = noResultsFoundText,
            color = TextColor.OnSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )
    } else {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .testTag("ORG_TREE_LIST")
                .horizontalScroll(scrollState),
            horizontalAlignment = Alignment.Start,
        ) {
            orgTreeItems.forEach { item ->
                key(item.uid) {
                    OrgUnitSelectorItem(
                        orgTreeItem = item,
                        higherLevel = orgTreeItems.minBy { it.level }.level,
                        searchQuery = searchQuery,
                        onItemClick = onItemClick,
                        onItemSelected = onItemSelected,
                    )
                }
            }
        }
    }
}

@Composable
fun OrgUnitSelectorItem(
    orgTreeItem: OrgTreeItem,
    higherLevel: Int,
    searchQuery: String,
    modifier: Modifier = Modifier,
    onItemClick: (uid: String) -> Unit,
    onItemSelected: (uid: String, checked: Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .testTag("$ITEM_TEST_TAG${orgTreeItem.label}")
            .fillMaxWidth()
            .background(Color.White)
            .clickable(
                enabled = orgTreeItem.hasChildren,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = rememberRipple(bounded = true),
            ) {
                onItemClick(orgTreeItem.uid)
            }
            .padding(start = ((orgTreeItem.level - higherLevel) * 16).dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OrgTreeItemIcon(
            modifier = Modifier.padding(Spacing.Spacing8),
            orgTreeItem = orgTreeItem,
        )

        val clickableModifier = if (orgTreeItem.canBeSelected) {
            Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onItemSelected(orgTreeItem.uid, !orgTreeItem.selected)
                    },
                )
        } else {
            Modifier
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = clickableModifier,
        ) {
            if (orgTreeItem.canBeSelected) {
                val checkBoxData = CheckBoxData(uid = orgTreeItem.uid, enabled = true, checked = orgTreeItem.selected, textInput = null)
                CheckBox(
                    modifier = Modifier.testTag("$ITEM_CHECK_TEST_TAG${orgTreeItem.label}"),
                    checkBoxData = checkBoxData,
                    onCheckedChange = { isChecked ->
                        onItemSelected(orgTreeItem.uid, isChecked)
                    },
                )
            } else {
                Spacer(modifier = Modifier.size(Spacing.Spacing16))
            }

            Text(
                text = orgTreeItemLabel(
                    orgTreeItem = orgTreeItem,
                    searchQuery = searchQuery,
                ),
                maxLines = 1,
                style = DHIS2SCustomTextStyles.bodyLargeBold.copy(
                    fontWeight = if (orgTreeItem.selectedChildrenCount > 0 || orgTreeItem.selected) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                ),
            )
        }
    }
}

@Composable
private fun OrgTreeItemIcon(
    modifier: Modifier = Modifier,
    orgTreeItem: OrgTreeItem,
) {
    if (!orgTreeItem.hasChildren) {
        Icon(
            modifier = modifier,
            painter = provideDHIS2Icon("material_circle_outline"),
            contentDescription = null,
            tint = TextColor.OnDisabledSurface,
        )
    } else if (orgTreeItem.isOpen) {
        Icon(
            modifier = modifier,
            painter = rememberVectorPainter(Icons.Filled.KeyboardArrowDown),
            contentDescription = null,
            tint = TextColor.OnDisabledSurface,
        )
    } else {
        Icon(
            modifier = modifier,
            painter = rememberVectorPainter(Icons.AutoMirrored.Filled.KeyboardArrowRight),
            contentDescription = null,
            tint = TextColor.OnSurfaceVariant,
        )
    }
}

private fun orgTreeItemLabel(
    orgTreeItem: OrgTreeItem,
    searchQuery: String,
): AnnotatedString {
    val label = buildAnnotatedString {
        val highlightIndexStart = orgTreeItem.label.indexOf(searchQuery, ignoreCase = true)
        val highlightIndexEnd = highlightIndexStart + searchQuery.length

        if (highlightIndexStart >= 0) {
            appendHighlightedString(
                orgTreeItem = orgTreeItem,
                highlightIndexStart = highlightIndexStart,
                highlightIndexEnd = highlightIndexEnd,
            )
        } else {
            append(orgTreeItem.label)
        }

        if (orgTreeItem.selectedChildrenCount > 0 && orgTreeItem.hasChildren) {
            append(" (${orgTreeItem.selectedChildrenCount})")
        }
    }

    return label
}

private fun AnnotatedString.Builder.appendHighlightedString(
    orgTreeItem: OrgTreeItem,
    highlightIndexStart: Int,
    highlightIndexEnd: Int,
) {
    append(orgTreeItem.label.substring(0, highlightIndexStart))

    withStyle(
        SpanStyle(background = SurfaceColor.Primary.copy(alpha = 0.1f)),
    ) {
        append(
            orgTreeItem.label.substring(
                startIndex = highlightIndexStart,
                endIndex = highlightIndexEnd,
            ),
        )
    }

    append(
        orgTreeItem.label.substring(
            startIndex = highlightIndexEnd,
            endIndex = orgTreeItem.label.length,
        ),
    )
}

/**
 * Data class used for [OrgBottomSheet]
 * used to hold information on Organisation Units.
 * @param uid: the item uid.
 * @param label: item label.
 * @param isOpen: whether the org unit is open or not.
 * @param hasChildren: whether the org unit has children or not.
 * @param selected: whether the org unit is selected or not.
 * @param level: the hierarchy level of the item.
 * @param selectedChildrenCount: number of selected children.
 * @param canBeSelected: whether the item is selectable or not.
 */
data class OrgTreeItem(
    val uid: String,
    val label: String,
    var isOpen: Boolean = true,
    val hasChildren: Boolean = false,
    val selected: Boolean = false,
    val level: Int = 0,
    val selectedChildrenCount: Int = 0,
    val canBeSelected: Boolean = true,
)

const val ITEM_TEST_TAG = "ORG_TREE_ITEM_"
const val ITEM_CHECK_TEST_TAG = "ORG_TREE_ITEM_CHECKBOX_"

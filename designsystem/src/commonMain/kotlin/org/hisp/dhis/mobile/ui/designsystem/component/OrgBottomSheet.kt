package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun OrgBottomSheet(
    orgTreeItems: List<OrgTreeItem>,
    modifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    description: String? = null,
    clearAllButtonText: String = provideStringResource("clear_all"),
    doneButtonText: String = provideStringResource("done"),
    noResultsFoundText: String = provideStringResource("org_tree_no_results_found"),
    icon: @Composable (() -> Unit)? = null,
    onSearch: ((String) -> Unit)? = null,
    onDismiss: () -> Unit,
    onItemClick: (uid: String) -> Unit,
    onItemSelected: (uid: String, checked: Boolean) -> Unit,
    onClearAll: () -> Unit,
    onDone: () -> Unit,
) {
    val listState = rememberLazyListState()
    var searchQuery by remember { mutableStateOf("") }
    var orgTreeHeight by remember { mutableStateOf(0) }
    val orgTreeHeightInDp = with(LocalDensity.current) { orgTreeHeight.toDp() }

    BottomSheetShell(
        modifier = modifier,
        title = title,
        subtitle = subtitle,
        description = description,
        icon = icon,
        searchQuery = searchQuery,
        onSearchQueryChanged = { query ->
            searchQuery = query
            onSearch?.invoke(searchQuery)
        },
        onSearch = onSearch,
        contentScrollState = listState,
        content = {
            OrgTreeList(
                state = listState,
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
                    }
                    .requiredHeightIn(min = orgTreeHeightInDp),
            )
        },
        buttonBlock = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    modifier = Modifier.weight(1f),
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

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onDone,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                        )
                    },
                    text = doneButtonText,
                    style = ButtonStyle.FILLED,
                )
            }
        },
        onDismiss = onDismiss,
    )
}

@Composable
private fun OrgTreeList(
    state: LazyListState,
    orgTreeItems: List<OrgTreeItem>,
    searchQuery: String,
    noResultsFoundText: String,
    modifier: Modifier = Modifier,
    onItemClick: (orgUnitUid: String) -> Unit,
    onItemSelected: (orgUnitUid: String, checked: Boolean) -> Unit,
) {
    val hasSearchQuery by derivedStateOf { searchQuery.isNotBlank() }
    if (orgTreeItems.isEmpty() && hasSearchQuery) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = Spacing.Spacing24, bottom = Spacing.Spacing96)
                .padding(horizontal = Spacing.Spacing16)
                .testTag("ORG_TREE_NO_RESULTS_FOUND"),
            textAlign = TextAlign.Center,
            text = noResultsFoundText,
            color = TextColor.OnSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )
    } else {
        LazyColumn(
            modifier = modifier
                .testTag("ORG_TREE_LIST"),
            state = state,
            horizontalAlignment = Alignment.Start,
        ) {
            items(orgTreeItems) { item ->
                OrgUnitSelectorItem(
                    orgTreeItem = item,
                    searchQuery = searchQuery,
                    onItemClick = onItemClick,
                    onItemSelected = onItemSelected,
                )
            }
        }
    }
}

@Composable
fun OrgUnitSelectorItem(
    orgTreeItem: OrgTreeItem,
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
            .padding(start = ((orgTreeItem.level * 2) * 16).dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val icon = orgTreeItemIcon(orgTreeItem)
        val iconTint = if (orgTreeItem.isOpen && orgTreeItem.hasChildren) {
            TextColor.OnDisabledSurface
        } else if (!orgTreeItem.isOpen && !orgTreeItem.hasChildren) {
            TextColor.OnDisabledSurface
        } else {
            TextColor.OnSurfaceVariant
        }

        Icon(
            modifier = Modifier.padding(Spacing.Spacing8),
            painter = icon,
            tint = iconTint,
            contentDescription = "",
        )

        Text(
            modifier = Modifier.weight(1f),
            text = orgTreeItemLabel(
                orgTreeItem = orgTreeItem,
                searchQuery = searchQuery,
            ),
            style = DHIS2SCustomTextStyles.bodyLargeBold.copy(
                fontWeight = if (orgTreeItem.selectedChildrenCount > 0 || orgTreeItem.selected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                },
            ),
        )

        if (orgTreeItem.canBeSelected) {
            Checkbox(
                modifier = Modifier.testTag("$ITEM_CHECK_TEST_TAG${orgTreeItem.label}"),
                checked = orgTreeItem.selected,
                onCheckedChange = { isChecked ->
                    onItemSelected(orgTreeItem.uid, isChecked)
                },
            )
        }
    }
}

@Composable
private fun orgTreeItemIcon(orgTreeItem: OrgTreeItem): Painter {
    if (!orgTreeItem.hasChildren) return provideDHIS2Icon("material_circle_outline")

    return if (orgTreeItem.isOpen) {
        rememberVectorPainter(Icons.Filled.KeyboardArrowDown)
    } else {
        rememberVectorPainter(Icons.Filled.KeyboardArrowRight)
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

package org.hisp.dhis.common.screens.bottomSheets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.hisp.dhis.common.screens.previews.lorem
import org.hisp.dhis.common.screens.previews.lorem_medium
import org.hisp.dhis.common.screens.previews.lorem_short
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.OrgBottomSheet
import org.hisp.dhis.mobile.ui.designsystem.component.OrgTreeItem

@Composable
fun OrgTreeBottomSheetScreen() {
    var showOneOrgTreeBottomSheet by rememberSaveable { mutableStateOf(false) }
    var showTwoOrgTreeBottomSheet by rememberSaveable { mutableStateOf(false) }
    var showMediumOrgTreeBottomSheet by rememberSaveable { mutableStateOf(false) }
    var showLargeOrgTreeBottomSheet by rememberSaveable { mutableStateOf(false) }

    if (showOneOrgTreeBottomSheet) {
        val orgTreeItemsRepo = remember { OrgTreeItemsFakeRepo() }
        val oneOrgTreeItem by orgTreeItemsRepo.state.collectAsState(emptyList())

        OrgBottomSheet(
            orgTreeItems = oneOrgTreeItem.take(1),
            onDismiss = {
                showOneOrgTreeBottomSheet = false
            },
            onSearch = orgTreeItemsRepo::search,
            onItemClick = orgTreeItemsRepo::toggleItemExpansion,
            onItemSelected = { uid, checked ->
                orgTreeItemsRepo.toggleItemSelection(uid, checked)
            },
            onClearAll = { orgTreeItemsRepo.clearItemSelections() },
            onDone = {
                // no-op
            },
        )
    }

    if (showTwoOrgTreeBottomSheet) {
        val orgTreeItemsRepo = remember { OrgTreeItemsFakeRepo() }
        val oneOrgTreeItem by orgTreeItemsRepo.state.collectAsState(emptyList())

        OrgBottomSheet(
            orgTreeItems = oneOrgTreeItem.take(4),
            onDismiss = {
                showTwoOrgTreeBottomSheet = false
            },
            onSearch = orgTreeItemsRepo::search,
            onItemClick = orgTreeItemsRepo::toggleItemExpansion,
            onItemSelected = { uid, checked ->
                orgTreeItemsRepo.toggleItemSelection(uid, checked)
            },
            onClearAll = { orgTreeItemsRepo.clearItemSelections() },
            onDone = {
                // no-op
            },
        )
    }

    if (showMediumOrgTreeBottomSheet) {
        val orgTreeItemsRepo = remember { OrgTreeItemsFakeRepo() }
        val oneOrgTreeItem by orgTreeItemsRepo.state.collectAsState(emptyList())

        OrgBottomSheet(
            orgTreeItems = oneOrgTreeItem.take(8),
            onDismiss = {
                showMediumOrgTreeBottomSheet = false
            },
            onSearch = orgTreeItemsRepo::search,
            onItemClick = orgTreeItemsRepo::toggleItemExpansion,
            onItemSelected = { uid, checked ->
                orgTreeItemsRepo.toggleItemSelection(uid, checked)
            },
            onClearAll = { orgTreeItemsRepo.clearItemSelections() },
            onDone = {
                // no-op
            },
        )
    }

    if (showLargeOrgTreeBottomSheet) {
        val orgTreeItemsRepo = remember { OrgTreeItemsFakeRepo() }
        val oneOrgTreeItem by orgTreeItemsRepo.state.collectAsState(emptyList())

        OrgBottomSheet(
            orgTreeItems = oneOrgTreeItem,
            onDismiss = {
                showLargeOrgTreeBottomSheet = false
            },
            onSearch = orgTreeItemsRepo::search,
            onItemClick = orgTreeItemsRepo::toggleItemExpansion,
            onItemSelected = { uid, checked ->
                orgTreeItemsRepo.toggleItemSelection(uid, checked)
            },
            onClearAll = { orgTreeItemsRepo.clearItemSelections() },
            onDone = {
                // no-op
            },
        )
    }

    ColumnComponentContainer(title = BottomSheets.ORG_TREE_BOTTOM_SHEET.label) {
        ColumnComponentItemContainer("Org Tree Bottom Sheet with single item") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show One Org Tree Bottom Sheet",
            ) {
                showOneOrgTreeBottomSheet = !showOneOrgTreeBottomSheet
            }
        }

        ColumnComponentItemContainer("Org Tree Bottom Sheet with multiple items") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Two Org Tree Bottom Sheet",
            ) {
                showTwoOrgTreeBottomSheet = !showTwoOrgTreeBottomSheet
            }
        }

        ColumnComponentItemContainer("Org Tree Bottom Sheet with medium items") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Org Tree Bottom Sheet",
            ) {
                showMediumOrgTreeBottomSheet = !showMediumOrgTreeBottomSheet
            }
        }

        ColumnComponentItemContainer("Org Tree Bottom Sheet with large items") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Large Org Tree Bottom Sheet",
            ) {
                showLargeOrgTreeBottomSheet = !showLargeOrgTreeBottomSheet
            }
        }
    }
}

private class OrgTreeItemsFakeRepo {
    private val originalOrgTreeItems = listOf(
        OrgTreeItem(
            uid = "12",
            label = "Krishna",
            isOpen = true,
            hasChildren = true,
        ),
        OrgTreeItem(
            uid = "21",
            label = "Guntur",
            isOpen = false,
            hasChildren = false,
        ),
        OrgTreeItem(
            uid = "31",
            label = lorem_medium,
            isOpen = false,
            hasChildren = false,
        ),
        OrgTreeItem(
            uid = "41",
            label = lorem,
            isOpen = false,
            hasChildren = false,
        ),
        OrgTreeItem(
            uid = "51",
            label = "UHC Alphabet",
            isOpen = false,
            hasChildren = false,
        ),
        OrgTreeItem(
            uid = "61",
            label = lorem_short,
            isOpen = false,
            hasChildren = false,
        ),
        OrgTreeItem(
            uid = "71",
            label = "UHC TEST 1",
            isOpen = false,
            hasChildren = false,
        ),
        OrgTreeItem(
            uid = "81",
            label = "UHC TEST 2",
            isOpen = false,
            hasChildren = false,
        ),
    )

    private val childrenOrgItems = listOf(
        OrgTreeItem(
            uid = "12-1",
            label = "Vijayawada-$lorem",
            isOpen = false,
            level = 1,
            hasChildren = false,
        ),
        OrgTreeItem(
            uid = "12-2",
            label = "Gudivada",
            isOpen = false,
            level = 1,
            hasChildren = false,
        ),
    )

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val _state = MutableStateFlow(createList(originalOrgTreeItems, childrenOrgItems))
    val state: StateFlow<List<OrgTreeItem>> =
        _state.stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = createList(originalOrgTreeItems, childrenOrgItems),
        )

    fun search(query: String) {
        coroutineScope.launch {
            if (query.isNotBlank()) {
                val filteredList = originalOrgTreeItems.filter { it.label.contains(query, ignoreCase = true) }
                _state.emit(filteredList)
            } else {
                _state.emit(originalOrgTreeItems)
            }
        }
    }

    fun toggleItemExpansion(uid: String) {
        coroutineScope.launch {
            val updatedList = _state.value
                .map {
                    if (it.hasChildren && it.uid == uid) {
                        it.copy(isOpen = !it.isOpen)
                    } else {
                        it
                    }
                }
            val parentItem = updatedList.first { it.uid == uid }

            val newList = if (parentItem.isOpen) {
                createList(updatedList, childrenOrgItems)
            } else {
                updatedList.filterNot { it.uid.contains(uid) && it.level > 0 }
            }

            _state.emit(newList)
        }
    }

    fun toggleItemSelection(uid: String, selected: Boolean) {
        coroutineScope.launch {
            val selectionToggledList = _state.value.map {
                if (it.uid.contains(uid, ignoreCase = true)) {
                    val selectedChildrenCount =
                        _state.value.count { it.uid.contains("12") && it.level > 0 && it.selected }

                    it.copy(selected = selected, selectedChildrenCount = selectedChildrenCount)
                } else {
                    it
                }
            }

            val newList = selectionToggledList.map {
                if (!uid.contains("12")) {
                    return@map it
                }

                when (it.uid) {
                    "12" -> {
                        val selectedChildrenCount = getSelectedChildrenCount(selectionToggledList, it)
                        it.copy(selectedChildrenCount = selectedChildrenCount)
                    }

                    else -> {
                        it.copy(selectedChildrenCount = 0)
                    }
                }
            }

            _state.emit(newList)
        }
    }

    private fun getSelectedChildrenCount(
        selectionToggledList: List<OrgTreeItem>,
        it: OrgTreeItem,
    ): Int {
        val hasChildrenItems = selectionToggledList.any { it.uid == "12-1" || it.uid == "12-2" }

        return if (hasChildrenItems) {
            selectionToggledList.count { it.uid.contains("12") && it.level > 0 && it.selected }
        } else {
            if (it.selected) 2 else 0
        }
    }

    fun clearItemSelections() {
        coroutineScope.launch {
            _state.emit(_state.value.map { it.copy(selected = false) })
        }
    }

    private fun createList(parentItems: List<OrgTreeItem>, childrenItems: List<OrgTreeItem>): List<OrgTreeItem> {
        val updatedChildrenItems = childrenItems.map {
            if (parentItems.first { it.uid == "12" }.selected) {
                it.copy(selected = true)
            } else {
                it
            }
        }

        return (parentItems + updatedChildrenItems).sortedBy { it.uid }
    }
}

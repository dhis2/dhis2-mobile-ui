package org.hisp.dhis.common.screens

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
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.OrgBottomSheet
import org.hisp.dhis.mobile.ui.designsystem.component.OrgTreeItem
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle

@Composable
fun OrgTreeBottomSheetScreen() {
    var showOrgTreeBottomSheet by rememberSaveable { mutableStateOf(false) }

    if (showOrgTreeBottomSheet) {
        val orgTreeItemsRepo = remember { OrgTreeItemsFakeRepo() }
        val orgTreeItems by orgTreeItemsRepo.state.collectAsState(emptyList())

        OrgBottomSheet(
            orgTreeItems = orgTreeItems,
            onDismiss = {
                showOrgTreeBottomSheet = false
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

    ColumnComponentContainer {
        SubTitle("Org Tree Bottom Sheet")
        Button(
            enabled = true,
            ButtonStyle.FILLED,
            text = "Show Org Tree Bottom Sheet",
        ) {
            showOrgTreeBottomSheet = !showOrgTreeBottomSheet
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
    )

    private val childrenOrgItems = listOf(
        OrgTreeItem(
            uid = "12-1",
            label = "Vijayawada",
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

package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.OrgBottomSheet
import org.hisp.dhis.mobile.ui.designsystem.component.OrgTreeItem
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle

@Composable
fun OrgTreeBottomSheetScreen() {
    var showOrgTreeBottomSheet by rememberSaveable { mutableStateOf(false) }
    var orgTreeItems by remember { mutableStateOf(originalOrgTreeItems) }

    if (showOrgTreeBottomSheet) {
        OrgBottomSheet(
            orgTreeItems = orgTreeItems,
            onDismiss = {
                showOrgTreeBottomSheet = false
            },
            onSearch = { query ->
                orgTreeItems = originalOrgTreeItems
                    .filter { it.label.contains(query, ignoreCase = true) }
            },
            onItemClick = { uid ->
                orgTreeItems = openChildren(uid = uid)
            },
            onItemSelected = { uid, checked ->
                orgTreeItems = toggleOrgItemSelection(
                    uid = uid,
                    isSelected = checked,
                    currentList = orgTreeItems,
                )
            },
            onClearAll = {
                orgTreeItems = originalOrgTreeItems
            },
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

private val originalOrgTreeItems = listOf(
    OrgTreeItem(
        uid = "uid-1",
        label = "Krishna",
        isOpen = true,
        hasChildren = true,
    ),
    OrgTreeItem(
        uid = "uid-1-1",
        label = "Vijayawada",
        isOpen = false,
        level = 1,
        hasChildren = false,
    ),
    OrgTreeItem(
        uid = "uid-1-2",
        label = "Gudivada",
        isOpen = false,
        level = 1,
        hasChildren = false,
    ),
    OrgTreeItem(
        uid = "uid-2",
        label = "Guntur",
        isOpen = false,
        hasChildren = false,
    ),
)

private fun openChildren(uid: String): List<OrgTreeItem> {
    return when (uid) {
        "uid-1" -> {
            val mutableList = originalOrgTreeItems.toMutableList()

            val parentIndex = originalOrgTreeItems.indexOfFirst { it.uid == "uid-1" }
            mutableList[parentIndex].isOpen = !mutableList[parentIndex].isOpen

            if (mutableList[parentIndex].isOpen) {
                mutableList
            } else {
                mutableList.filterNot { it.uid == "uid-1-1" || it.uid == "uid-1-2" }
            }
        }
        "uid-2" -> originalOrgTreeItems
        else -> originalOrgTreeItems
    }
}

fun toggleOrgItemSelection(
    uid: String,
    isSelected: Boolean,
    currentList: List<OrgTreeItem>,
): List<OrgTreeItem> {
    val mutableList = currentList.toMutableList()
    val index = currentList.indexOfFirst { it.uid == uid }
    val parentIndex = if (uid.contains("uid-1")) {
        currentList.indexOfFirst { it.uid == "uid-1" }
    } else {
        currentList.indexOfFirst { it.uid == "uid-2" }
    }

    mutableList[index] = mutableList[index].copy(selected = isSelected)

    if (uid == "uid-1") {
        val index1 = mutableList.indexOfFirst { it.uid == "uid-1-1" }
        val index2 = mutableList.indexOfFirst { it.uid == "uid-1-2" }

        mutableList[index1] = mutableList[index1].copy(selected = isSelected)
        mutableList[index2] = mutableList[index2].copy(selected = isSelected)
    }

    if (uid.contains("uid-1")) {
        val selectedChildrenCount = mutableList.count {
            if (it.uid == "uid-1-1" || it.uid == "uid-1-2") {
                it.selected
            } else {
                false
            }
        }

        mutableList[parentIndex] = mutableList[parentIndex].copy(
            selectedChildrenCount = selectedChildrenCount,
        )
    }

    return mutableList
}

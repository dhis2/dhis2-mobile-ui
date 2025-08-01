package org.hisp.dhis.showcaseapp.screens.bottomSheets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.showcaseapp.screens.NoComponentSelectedScreen
import org.hisp.dhis.showcaseapp.screens.components.GroupComponentDropDown

@Composable
fun BottomSheetsScreen() {
    val currentScreen = remember { mutableStateOf(BottomSheets.NO_COMPONENT_SELECTED) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    BottomSheets.entries.forEach {
        if (it != BottomSheets.NO_COMPONENT_SELECTED) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    GroupComponentDropDown(
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = BottomSheets.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
    )
    when (currentScreen.value) {
        BottomSheets.BOTTOM_SHEET -> BottomSheetScreen()
        BottomSheets.BOTTOM_SHEET_HEADER -> BottomSheetHeaderScreen()
        BottomSheets.ORG_TREE_BOTTOM_SHEET -> OrgTreeBottomSheetScreen()
        BottomSheets.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
    }
}

enum class BottomSheets(val label: String) {
    BOTTOM_SHEET("Bottom Sheet component"),
    BOTTOM_SHEET_HEADER("Bottom Sheet Header component"),
    ORG_TREE_BOTTOM_SHEET("Org Tree Bottom Sheet component"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): BottomSheets {
    return BottomSheets.entries.firstOrNull { it.label == label } ?: BottomSheets.BOTTOM_SHEET
}

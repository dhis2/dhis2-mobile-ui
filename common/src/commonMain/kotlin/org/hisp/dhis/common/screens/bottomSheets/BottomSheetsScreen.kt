package org.hisp.dhis.common.screens.bottomSheets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.common.screens.NoComponentSelectedScreen
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState

@Composable
fun BottomSheetsScreen() {
    val currentScreen = remember { mutableStateOf(BottomSheets.BOTTOM_SHEET) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    BottomSheets.entries.forEach {
        if (it != BottomSheets.BOTTOM_SHEET) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    InputDropDown(
        "Display",
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = BottomSheets.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
        state = InputShellState.UNFOCUSED,
    )
    when (currentScreen.value) {
        BottomSheets.BOTTOM_SHEET -> BottomSheetScreen()
        BottomSheets.BOTTOM_SHEET_HEADER -> BottomSheetHeaderScreen()
        BottomSheets.ORG_TREE_BOTTOM_SHEET -> OrgTreeBottomSheetScreen()
        BottomSheets.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
    }
}

enum class BottomSheets(val label: String) {
    BOTTOM_SHEET("Bottom Sheet "),
    BOTTOM_SHEET_HEADER("Bottom Sheet Header"),
    ORG_TREE_BOTTOM_SHEET("Org Tree Bottom Sheet"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): BottomSheets {
    return BottomSheets.entries.firstOrNull { it.label == label } ?: BottomSheets.BOTTOM_SHEET
}

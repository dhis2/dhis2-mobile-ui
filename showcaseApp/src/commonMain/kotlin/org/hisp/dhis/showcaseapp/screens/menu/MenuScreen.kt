package org.hisp.dhis.showcaseapp.screens.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.showcaseapp.screens.NoComponentSelectedScreen
import org.hisp.dhis.showcaseapp.screens.components.GroupComponentDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem

@Composable
fun MenuScreen() {
    val currentScreen = remember { mutableStateOf(MENU.NO_COMPONENT_SELECTED) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    MENU.entries.forEach {
        if (it != MENU.NO_COMPONENT_SELECTED) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    GroupComponentDropDown(
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = MENU.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
    )
    when (currentScreen.value) {
        MENU.DROPDOWN_MENU -> DropDownMenuScreen()
        MENU.MENU_ITEM -> MenuItemScreen()
        MENU.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
    }
}

enum class MENU(val label: String) {
    DROPDOWN_MENU("Drop down menu"),
    MENU_ITEM("Menu item"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): MENU {
    return MENU.entries.firstOrNull { it.label == label } ?: MENU.DROPDOWN_MENU
}

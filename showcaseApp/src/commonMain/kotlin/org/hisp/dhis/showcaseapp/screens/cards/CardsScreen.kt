package org.hisp.dhis.showcaseapp.screens.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.showcaseapp.screens.NoComponentSelectedScreen
import org.hisp.dhis.showcaseapp.screens.components.GroupComponentDropDown

@Composable
fun CardsScreen() {
    val currentScreen = remember { mutableStateOf(Cards.NO_COMPONENT_SELECTED) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    Cards.entries.forEach {
        if (it != Cards.NO_COMPONENT_SELECTED) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    GroupComponentDropDown(
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = Cards.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
    )
    when (currentScreen.value) {
        Cards.CARD_DETAIL -> CardDetailScreen()
        Cards.LIST_CARD -> ListCardScreen(false)
        Cards.LIST_CARD_HORIZONTAL -> ListCardScreen(true)
        Cards.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
        Cards.EXPANDABLE_LIST_CARD -> ExpandableListCardScreen()
    }
}

enum class Cards(val label: String) {
    CARD_DETAIL("Card Detail"),
    LIST_CARD("Vertical List Card"),
    LIST_CARD_HORIZONTAL("Horizontal List Cards"),
    EXPANDABLE_LIST_CARD("Expandable list card"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): Cards {
    return Cards.entries.firstOrNull { it.label == label } ?: Cards.CARD_DETAIL
}

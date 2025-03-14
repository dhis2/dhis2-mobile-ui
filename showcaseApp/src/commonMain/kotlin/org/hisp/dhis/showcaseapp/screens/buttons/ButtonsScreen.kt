package org.hisp.dhis.showcaseapp.screens.buttons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.showcaseapp.screens.NoComponentSelectedScreen
import org.hisp.dhis.showcaseapp.screens.components.GroupComponentDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem

@Composable
fun ButtonsScreen() {
    val currentScreen = remember { mutableStateOf(ButtonScreens.NO_COMPONENT_SELECTED) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    ButtonScreens.entries.forEach {
        if (it != ButtonScreens.NO_COMPONENT_SELECTED) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    GroupComponentDropDown(
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = ButtonScreens.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
    )
    when (currentScreen.value) {
        ButtonScreens.BUTTON -> ButtonScreen()
        ButtonScreens.BUTTON_BLOCK -> ButtonBlockScreen()
        ButtonScreens.CAROUSEL_BUTTONS -> ButtonCarouselScreen()
        ButtonScreens.CHECK_BOX -> CheckboxScreen()
        ButtonScreens.ICON_BUTTON -> IconButtonScreen()
        ButtonScreens.RADIO -> RadioButtonScreen()
        ButtonScreens.SWITCH -> SwitchScreen()
        ButtonScreens.FAB -> FABScreen()
        ButtonScreens.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
    }
}

enum class ButtonScreens(val label: String) {
    BUTTON("Button component"),
    BUTTON_BLOCK("Button block component"),
    CAROUSEL_BUTTONS("Carousel button component"),
    CHECK_BOX("Check Box component"),
    FAB("FAB component"),
    ICON_BUTTON("Icon Button component"),
    RADIO("Radio component"),
    SWITCH("Switch component"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): ButtonScreens {
    return ButtonScreens.entries.firstOrNull { it.label == label } ?: ButtonScreens.BUTTON
}

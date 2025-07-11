package org.hisp.dhis.showcaseapp.screens.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.model.TabColorStyle
import org.hisp.dhis.mobile.ui.designsystem.component.model.TabStyle
import org.hisp.dhis.showcaseapp.screens.NoComponentSelectedScreen
import org.hisp.dhis.showcaseapp.screens.components.GroupComponentDropDown

@Composable
fun TabsScreen() {
    val currentScreen = remember { mutableStateOf(Tabs.NO_COMPONENT_SELECTED) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    Tabs.entries.forEach {
        if (it != Tabs.NO_COMPONENT_SELECTED) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    GroupComponentDropDown(
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = Tabs.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
    )

    when (currentScreen.value) {
        Tabs.VERTICAL_TONAL_TABS_LABEL_ONLY -> VerticalTabsScreen(TabStyle.LabelOnly, TabColorStyle.Tonal)
        Tabs.VERTICAL_TONAL_TABS_ICON_ONLY -> VerticalTabsScreen(TabStyle.IconOnly, TabColorStyle.Tonal)
        Tabs.VERTICAL_TONAL_TABS_ICON_LABEL -> VerticalTabsScreen(TabStyle.IconAndLabel, TabColorStyle.Tonal)
        Tabs.VERTICAL_PRIMARY_TABS_LABEL_ONLY -> VerticalTabsScreen(TabStyle.LabelOnly, TabColorStyle.Primary)
        Tabs.VERTICAL_PRIMARY_TABS_ICON_ONLY -> VerticalTabsScreen(TabStyle.IconOnly, TabColorStyle.Primary)
        Tabs.VERTICAL_PRIMARY_TABS_ICON_LABEL -> VerticalTabsScreen(TabStyle.IconAndLabel, TabColorStyle.Primary)
        Tabs.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
    }
}

enum class Tabs(
    val label: String,
) {
    VERTICAL_TONAL_TABS_LABEL_ONLY("Vertical tabs: Tonal label only"),
    VERTICAL_TONAL_TABS_ICON_ONLY("Vertical tabs: Tonal icon only"),
    VERTICAL_TONAL_TABS_ICON_LABEL("Vertical tabs: Tonal icon and label"),
    VERTICAL_PRIMARY_TABS_LABEL_ONLY("Vertical tabs: Primary label only"),
    VERTICAL_PRIMARY_TABS_ICON_ONLY("Vertical tabs: Primary icon only"),
    VERTICAL_PRIMARY_TABS_ICON_LABEL("Vertical tabs: Primary icon and label"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): Tabs = Tabs.entries.firstOrNull { it.label == label } ?: Tabs.NO_COMPONENT_SELECTED

package org.hisp.dhis.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import org.hisp.dhis.common.screens.Components
import org.hisp.dhis.common.screens.NoComponentSelectedScreen
import org.hisp.dhis.common.screens.actionInputs.ActionInputsScreen
import org.hisp.dhis.common.screens.basicTextInputs.BasicTextInputsScreen
import org.hisp.dhis.common.screens.bottomSheets.BottomSheetsScreen
import org.hisp.dhis.common.screens.buttons.ButtonsScreen
import org.hisp.dhis.common.screens.cards.CardsScreen
import org.hisp.dhis.common.screens.others.BadgesScreen
import org.hisp.dhis.common.screens.others.ChipsScreen
import org.hisp.dhis.common.screens.others.IndicatorScreen
import org.hisp.dhis.common.screens.others.LegendScreen
import org.hisp.dhis.common.screens.others.MetadataAvatarScreen
import org.hisp.dhis.common.screens.others.ProgressScreen
import org.hisp.dhis.common.screens.others.SearchBarScreen
import org.hisp.dhis.common.screens.others.SectionScreen
import org.hisp.dhis.common.screens.others.TagsScreen
import org.hisp.dhis.common.screens.parameter.ParameterSelectorScreen
import org.hisp.dhis.common.screens.toggleableInputs.ToggleableInputsScreen
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun App(imageBitmapLoader: (() -> ImageBitmap)? = null) {
    DHIS2Theme {
        Main(imageBitmapLoader)
    }
}

@Composable
fun Main(
    imageBitmapLoader: (() -> ImageBitmap)?,
) {
    val currentScreen = remember { mutableStateOf(Components.ACTION_INPUTS) }

    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
        modifier = Modifier
            .background(Color.White)
            .padding(Spacing.Spacing16),
    ) {
        val screenDropdownItemList = mutableListOf<DropdownItem>()
        Components.entries.forEach {
            screenDropdownItemList.add(DropdownItem(it.label))
        }

        InputDropDown(
            "Components",
            dropdownItems = screenDropdownItemList.toList(),
            onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
            onResetButtonClicked = { currentScreen.value = Components.NO_COMPONENT_SELECTED },
            state = InputShellState.UNFOCUSED,
            selectedItem = DropdownItem(currentScreen.value.label),
        )

        when (currentScreen.value) {
            Components.ACTION_INPUTS -> ActionInputsScreen()
            Components.BADGES -> BadgesScreen()
            Components.BASIC_TEXT_INPUTS -> BasicTextInputsScreen()
            Components.BOTTOM_SHEETS -> BottomSheetsScreen()
            Components.BUTTONS -> ButtonsScreen()
            Components.CARDS -> CardsScreen()
            Components.CHIPS -> ChipsScreen()
            Components.INDICATOR -> IndicatorScreen()
            Components.LEGEND -> LegendScreen()
            Components.METADATA_AVATAR -> MetadataAvatarScreen()
            Components.PROGRESS_INDICATOR -> ProgressScreen()
            Components.PARAMETER_SELECTOR -> ParameterSelectorScreen()
            Components.SECTIONS -> SectionScreen()
            Components.TOGGLEABLE_INPUTS -> ToggleableInputsScreen(imageBitmapLoader)
            Components.TAGS -> TagsScreen()
            Components.SEARCH_BAR -> SearchBarScreen()
            Components.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
        }
    }
}

fun getCurrentScreen(label: String): Components {
    return Components.entries.firstOrNull { it.label == label } ?: Components.ACTION_INPUTS
}

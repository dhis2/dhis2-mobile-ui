package org.hisp.dhis.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import org.hisp.dhis.common.screens.Groups
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
import org.hisp.dhis.common.screens.others.NavigationBarScreen
import org.hisp.dhis.common.screens.others.ProgressScreen
import org.hisp.dhis.common.screens.others.SearchBarScreen
import org.hisp.dhis.common.screens.others.SectionScreen
import org.hisp.dhis.common.screens.others.TagsScreen
import org.hisp.dhis.common.screens.parameter.ParameterSelectorScreen
import org.hisp.dhis.common.screens.toggleableInputs.ToggleableInputsScreen
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatarSize
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

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
    val currentScreen = remember { mutableStateOf(Groups.NO_GROUP_SELECTED) }
    var isComponentSelected by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
        modifier = Modifier
            .background(SurfaceColor.Container),
    ) {
        val screenDropdownItemList = mutableListOf<DropdownItem>()
        Groups.entries.forEach {
            screenDropdownItemList.add(DropdownItem(it.label))
        }

        if (isComponentSelected) {
            InputDropDown(
                modifier = Modifier.padding(
                    start = Spacing.Spacing16,
                    end = Spacing.Spacing16,
                    top = Spacing.Spacing16,
                ),
                title = "Group",
                dropdownItems = screenDropdownItemList.toList(),
                onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
                onResetButtonClicked = { currentScreen.value = Groups.NO_GROUP_SELECTED },
                state = InputShellState.UNFOCUSED,
                expanded = true,
                selectedItem = DropdownItem(currentScreen.value.label),
                inputStyle = InputStyle.DataInputStyle().apply { backGroundColor = SurfaceColor.SurfaceBright },
            )

            when (currentScreen.value) {
                Groups.ACTION_INPUTS -> ActionInputsScreen()
                Groups.BADGES -> BadgesScreen()
                Groups.BASIC_TEXT_INPUTS -> BasicTextInputsScreen()
                Groups.BOTTOM_SHEETS -> BottomSheetsScreen()
                Groups.BUTTONS -> ButtonsScreen()
                Groups.CARDS -> CardsScreen()
                Groups.CHIPS -> ChipsScreen()
                Groups.INDICATOR -> IndicatorScreen()
                Groups.LEGEND -> LegendScreen()
                Groups.METADATA_AVATAR -> MetadataAvatarScreen()
                Groups.PROGRESS_INDICATOR -> ProgressScreen()
                Groups.PARAMETER_SELECTOR -> ParameterSelectorScreen()
                Groups.SECTIONS -> SectionScreen()
                Groups.TOGGLEABLE_INPUTS -> ToggleableInputsScreen(imageBitmapLoader)
                Groups.TAGS -> TagsScreen()
                Groups.SEARCH_BAR -> SearchBarScreen()
                Groups.NAVIGATION_BAR -> NavigationBarScreen()
                Groups.NO_GROUP_SELECTED -> NoComponentSelectedScreen()
            }
        } else {
            NoComponentSelectedScreen(
                modifier = Modifier
                    .background(Color.White, Shape.NoRounding),
            ) {
                isComponentSelected = !isComponentSelected
            }
        }
    }
}

fun getCurrentScreen(label: String): Groups {
    return Groups.entries.firstOrNull { it.label == label } ?: Groups.ACTION_INPUTS
}

fun getAvailableMetadataAvatarSizes() = listOf(
    MetadataAvatarSize.XS(),
    MetadataAvatarSize.S(),
    MetadataAvatarSize.M(),
    MetadataAvatarSize.L(),
    MetadataAvatarSize.XL(),
)

package org.hisp.dhis.common.screens.toggleableInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import org.hisp.dhis.common.screens.NoComponentSelectedScreen
import org.hisp.dhis.common.screens.components.GroupComponentDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem

@Composable
fun ToggleableInputsScreen(imageBitmapLoader: (() -> ImageBitmap)?) {
    val currentScreen = remember { mutableStateOf(ToggleableInputs.NO_COMPONENT_SELECTED) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    ToggleableInputs.entries.forEach {
        if (it != ToggleableInputs.NO_COMPONENT_SELECTED) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    GroupComponentDropDown(
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = ToggleableInputs.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
    )
    when (currentScreen.value) {
        ToggleableInputs.INPUT_RADIO_BUTTON -> InputRadioButtonScreen()
        ToggleableInputs.INPUT_MATRIX -> InputMatrixScreen(imageBitmapLoader)
        ToggleableInputs.INPUT_SEQUENTIAL -> InputSequentialScreen(imageBitmapLoader)
        ToggleableInputs.INPUT_CHECK_BOX -> InputCheckBoxScreen()
        ToggleableInputs.INPUT_YES_ONLY_SWITCH -> InputYesOnlySwitchScreen()
        ToggleableInputs.INPUT_YES_ONLY_CHECKBOX -> InputYesOnlyCheckBoxScreen()
        ToggleableInputs.INPUT_YES_NO_FIELD -> InputYesNoFieldScreen()
        ToggleableInputs.INPUT_DROPDOWN -> InputDropDownScreen()

        ToggleableInputs.MULTI_SELECT -> MultiSelectInputScreen()
        ToggleableInputs.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
    }
}

enum class ToggleableInputs(val label: String) {
    INPUT_RADIO_BUTTON("Input Radio Button component"),
    INPUT_MATRIX("Input Matrix component"),
    INPUT_SEQUENTIAL("Input Sequential component"),
    INPUT_CHECK_BOX("Input Check Box component"),
    INPUT_YES_ONLY_SWITCH("Input yes only switch component"),
    INPUT_YES_ONLY_CHECKBOX("Input yes only checkbox component"),
    INPUT_YES_NO_FIELD("Input Yes/No field component"),
    INPUT_DROPDOWN("Input Dropdown component"),
    MULTI_SELECT("Multi Select Input component"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): ToggleableInputs {
    return ToggleableInputs.entries.firstOrNull { it.label == label } ?: ToggleableInputs.INPUT_RADIO_BUTTON
}

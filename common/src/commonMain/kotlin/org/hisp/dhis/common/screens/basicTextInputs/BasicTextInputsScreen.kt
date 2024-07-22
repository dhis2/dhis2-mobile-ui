package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.common.screens.NoComponentSelectedScreen
import org.hisp.dhis.common.screens.components.GroupComponentDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem

@Composable
fun BasicTextInputsScreen() {
    val currentScreen = remember { mutableStateOf(BasicTextInputs.NO_COMPONENT_SELECTED) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    BasicTextInputs.entries.forEach {
        if (it != BasicTextInputs.NO_COMPONENT_SELECTED) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    GroupComponentDropDown(
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = BasicTextInputs.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
    )
    when (currentScreen.value) {
        BasicTextInputs.FORM_SHELLS -> FormShellsScreen()
        BasicTextInputs.INPUT_INTEGER -> InputIntegerScreen()
        BasicTextInputs.INPUT_LETTER -> InputLetterScreen()
        BasicTextInputs.INPUT_LONG_TEXT -> InputLongTextScreen()
        BasicTextInputs.INPUT_NEGATIVE_INTEGER -> InputNegativeIntegerScreen()
        BasicTextInputs.INPUT_NOT_SUPPORTED -> InputNotSupportedScreen()
        BasicTextInputs.INPUT_NUMBER -> InputNumberScreen()
        BasicTextInputs.INPUT_PERCENTAGE -> InputPercentageScreen()
        BasicTextInputs.INPUT_POSITIVE_INTEGER -> InputPositiveIntegerScreen()
        BasicTextInputs.INPUT_POSITIVE_INTEGER_OR_ZERO -> InputPositiveIntegerOrZeroScreen()
        BasicTextInputs.INPUT_TEXT -> InputTextScreen()
        BasicTextInputs.INPUT_UNIT_INTERVAL -> InputUnitIntervalScreen()
        BasicTextInputs.SUPPORTING_TEXT -> SupportingTextScreen()
        BasicTextInputs.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
    }
}

enum class BasicTextInputs(val label: String) {
    FORM_SHELLS("Form Shells component"),
    INPUT_INTEGER("Input Integer component"),
    INPUT_LETTER("Input Letter component"),
    INPUT_LONG_TEXT("Input Long Text component"),
    INPUT_NEGATIVE_INTEGER("Input Negative Integer component"),
    INPUT_NOT_SUPPORTED("Input Not Supported component"),
    INPUT_NUMBER("Input Number component"),
    INPUT_PERCENTAGE("Input Percentage component"),
    INPUT_POSITIVE_INTEGER("Input Positive Integer component"),
    INPUT_POSITIVE_INTEGER_OR_ZERO("Input Positive or Zero Integer component"),
    INPUT_TEXT("Input Text component"),
    INPUT_UNIT_INTERVAL("Input Unit Interval component"),
    SUPPORTING_TEXT("Supporting Text component"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): BasicTextInputs {
    return BasicTextInputs.entries.firstOrNull { it.label == label } ?: BasicTextInputs.INPUT_TEXT
}

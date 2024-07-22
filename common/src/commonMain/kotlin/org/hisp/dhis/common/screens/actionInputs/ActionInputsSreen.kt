package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.NoComponentSelectedScreen
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun ActionInputsScreen() {
    val currentScreen = remember { mutableStateOf(ActionInputs.NO_COMPONENT_SELECTED) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    ActionInputs.entries.forEach {
        if (it != ActionInputs.NO_COMPONENT_SELECTED) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    InputDropDown(
        modifier = Modifier.padding(horizontal = Spacing.Spacing16),
        title = "Component",
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = ActionInputs.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
        state = InputShellState.UNFOCUSED,
    )
    when (currentScreen.value) {
        ActionInputs.INPUT_QR_CODE -> InputQRCodeScreen()
        ActionInputs.INPUT_BARCODE -> InputBarCodeScreen()
        ActionInputs.INPUT_POLYGON -> InputPolygonScreen()
        ActionInputs.INPUT_ORG_UNIT -> InputOrgUnitScreen()
        ActionInputs.INPUT_FILE_RESOURCE -> InputFileResourceScreen()
        ActionInputs.INPUT_DATE_TIME -> InputDateTimeScreen()
        ActionInputs.INPUT_COORDINATE -> InputCoordinateScreen()
        ActionInputs.INPUT_SIGNATURE -> InputSignatureScreen()
        ActionInputs.INPUT_IMAGE -> InputImageScreen()
        ActionInputs.INPUT_AGE -> InputAgeScreen()
        ActionInputs.INPUT_PHONE_NUMBER -> InputPhoneNumberScreen()
        ActionInputs.INPUT_LINK -> InputLinkScreen()
        ActionInputs.INPUT_EMAIL -> InputEmailScreen()
        ActionInputs.LOGIN -> LoginScreen()
        ActionInputs.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
    }
}

enum class ActionInputs(val label: String) {
    LOGIN("Login"),
    INPUT_AGE("Input Age"),
    INPUT_BARCODE("Input Barcode"),
    INPUT_COORDINATE("Input Coordinate"),
    INPUT_DATE_TIME("Input Date Time"),
    INPUT_EMAIL("Input Email"),
    INPUT_FILE_RESOURCE("Input File Resource"),
    INPUT_IMAGE("Input Image"),
    INPUT_LINK("Input Link"),
    INPUT_ORG_UNIT("Input Org. Unit"),
    INPUT_PHONE_NUMBER("Input Phone Number"),
    INPUT_POLYGON("Input Polygon"),
    INPUT_QR_CODE("Input QR code"),
    INPUT_SIGNATURE("Input Signature"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): ActionInputs {
    return ActionInputs.entries.firstOrNull { it.label == label } ?: ActionInputs.INPUT_BARCODE
}

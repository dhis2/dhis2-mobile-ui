package org.hisp.dhis.showcaseapp.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.showcaseapp.screens.NoComponentSelectedScreen
import org.hisp.dhis.showcaseapp.screens.components.GroupComponentDropDown

@Composable
fun ActionInputsScreen() {
    val currentScreen = remember { mutableStateOf(ActionInputs.NO_COMPONENT_SELECTED) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    ActionInputs.entries.forEach {
        if (it != ActionInputs.NO_COMPONENT_SELECTED) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    GroupComponentDropDown(
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = ActionInputs.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
    )
    when (currentScreen.value) {
        ActionInputs.INPUT_QR_CODE -> InputQRCodeScreen()
        ActionInputs.INPUT_BARCODE -> InputBarCodeScreen()
        ActionInputs.INPUT_POLYGON -> InputPolygonScreen()
        ActionInputs.INPUT_ORG_UNIT -> InputOrgUnitScreen()
        ActionInputs.INPUT_FILE_RESOURCE -> InputFileResourceScreen()
        ActionInputs.INPUT_DATE_TIME -> InputDateTimeScreen()
        ActionInputs.PICKERS -> PickersScreen()
        ActionInputs.INPUT_COORDINATE -> InputCoordinateScreen()
        ActionInputs.INPUT_SIGNATURE -> InputSignatureScreen()
        ActionInputs.INPUT_CUSTOM_INTENT -> InputCustomIntentScreen()
        ActionInputs.INPUT_IMAGE -> InputImageScreen()
        ActionInputs.INPUT_AGE -> InputAgeScreen()
        ActionInputs.INPUT_PHONE_NUMBER -> InputPhoneNumberScreen()
        ActionInputs.INPUT_LINK -> InputLinkScreen()
        ActionInputs.INPUT_EMAIL -> InputEmailScreen()
        ActionInputs.LOGIN -> LoginScreen()
        ActionInputs.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
    }
}

enum class ActionInputs(
    val label: String,
) {
    LOGIN("Login component"),
    INPUT_AGE("Input Age component"),
    INPUT_BARCODE("Input Barcode component"),
    INPUT_COORDINATE("Input Coordinate component"),
    INPUT_DATE_TIME("Input Date Time component"),
    PICKERS("Pickers"),
    INPUT_EMAIL("Input Email component"),
    INPUT_FILE_RESOURCE("Input File Resource component"),
    INPUT_IMAGE("Input Image component"),
    INPUT_LINK("Input Link component"),
    INPUT_ORG_UNIT("Input Org. Unit component"),
    INPUT_PHONE_NUMBER("Input Phone Number component"),
    INPUT_POLYGON("Input Polygon component"),
    INPUT_QR_CODE("Input QR code component"),
    INPUT_SIGNATURE("Input Signature component"),
    INPUT_CUSTOM_INTENT("Input Custom Intent component"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): ActionInputs = ActionInputs.entries.firstOrNull { it.label == label } ?: ActionInputs.INPUT_BARCODE

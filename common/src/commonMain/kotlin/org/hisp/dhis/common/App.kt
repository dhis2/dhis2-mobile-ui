package org.hisp.dhis.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import org.hisp.dhis.common.screens.BadgesScreen
import org.hisp.dhis.common.screens.BarcodeBlockScreen
import org.hisp.dhis.common.screens.BottomSheetHeaderScreen
import org.hisp.dhis.common.screens.BottomSheetScreen
import org.hisp.dhis.common.screens.ButtonBlockScreen
import org.hisp.dhis.common.screens.ButtonCarouselScreen
import org.hisp.dhis.common.screens.ButtonScreen
import org.hisp.dhis.common.screens.CardDetailScreen
import org.hisp.dhis.common.screens.CheckboxScreen
import org.hisp.dhis.common.screens.ChipsScreen
import org.hisp.dhis.common.screens.Components
import org.hisp.dhis.common.screens.FABScreen
import org.hisp.dhis.common.screens.FormShellsScreen
import org.hisp.dhis.common.screens.FullScreenImageScreen
import org.hisp.dhis.common.screens.IconButtonScreen
import org.hisp.dhis.common.screens.ImageBlockScreen
import org.hisp.dhis.common.screens.IndicatorScreen
import org.hisp.dhis.common.screens.InputAgeScreen
import org.hisp.dhis.common.screens.InputBarCodeScreen
import org.hisp.dhis.common.screens.InputCheckBoxScreen
import org.hisp.dhis.common.screens.InputCoordinateScreen
import org.hisp.dhis.common.screens.InputDateTimeScreen
import org.hisp.dhis.common.screens.InputDropDownScreen
import org.hisp.dhis.common.screens.InputEmailScreen
import org.hisp.dhis.common.screens.InputFileResourceScreen
import org.hisp.dhis.common.screens.InputImageScreen
import org.hisp.dhis.common.screens.InputIntegerScreen
import org.hisp.dhis.common.screens.InputLetterScreen
import org.hisp.dhis.common.screens.InputLinkScreen
import org.hisp.dhis.common.screens.InputLongTextScreen
import org.hisp.dhis.common.screens.InputMatrixScreen
import org.hisp.dhis.common.screens.InputNegativeIntegerScreen
import org.hisp.dhis.common.screens.InputNotSupportedScreen
import org.hisp.dhis.common.screens.InputNumberScreen
import org.hisp.dhis.common.screens.InputOrgUnitScreen
import org.hisp.dhis.common.screens.InputPercentageScreen
import org.hisp.dhis.common.screens.InputPhoneNumberScreen
import org.hisp.dhis.common.screens.InputPolygonScreen
import org.hisp.dhis.common.screens.InputPositiveIntegerOrZeroScreen
import org.hisp.dhis.common.screens.InputPositiveIntegerScreen
import org.hisp.dhis.common.screens.InputQRCodeScreen
import org.hisp.dhis.common.screens.InputRadioButtonScreen
import org.hisp.dhis.common.screens.InputSequentialScreen
import org.hisp.dhis.common.screens.InputSignatureScreen
import org.hisp.dhis.common.screens.InputTextScreen
import org.hisp.dhis.common.screens.InputUnitIntervalScreen
import org.hisp.dhis.common.screens.InputYesNoFieldScreen
import org.hisp.dhis.common.screens.InputYesOnlyCheckBoxScreen
import org.hisp.dhis.common.screens.InputYesOnlySwitchScreen
import org.hisp.dhis.common.screens.LegendDescriptionScreen
import org.hisp.dhis.common.screens.LegendScreen
import org.hisp.dhis.common.screens.ListCardScreen
import org.hisp.dhis.common.screens.LoginScreen
import org.hisp.dhis.common.screens.MetadataAvatarScreen
import org.hisp.dhis.common.screens.MultiSelectInputScreen
import org.hisp.dhis.common.screens.OrgTreeBottomSheetScreen
import org.hisp.dhis.common.screens.ProgressScreen
import org.hisp.dhis.common.screens.RadioButtonScreen
import org.hisp.dhis.common.screens.SearchBarScreen
import org.hisp.dhis.common.screens.SectionScreen
import org.hisp.dhis.common.screens.SupportingTextScreen
import org.hisp.dhis.common.screens.SwitchScreen
import org.hisp.dhis.common.screens.TagsScreen
import org.hisp.dhis.common.screens.parameter.ParameterSelectorScreen
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
    val currentScreen = remember { mutableStateOf(Components.INPUT_DROPDOWN) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(Spacing.Spacing16),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            TextField(
                readOnly = true,
                value = currentScreen.value.label,
                onValueChange = {},
                label = { Text("Components") },
                leadingIcon = {
                    val icon = if (expanded) {
                        Icons.Filled.ArrowDropUp
                    } else {
                        Icons.Filled.ArrowDropDown
                    }
                    Icon(
                        icon,
                        "contentDescription",
                        Modifier.clickable { expanded = !expanded },
                    )
                },
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                Components.entries.forEach {
                    DropdownMenuItem(
                        text = { Text(it.label) },
                        onClick = {
                            currentScreen.value = it
                            expanded = false
                        },
                    )
                }
            }
        }

        when (currentScreen.value) {
            // BUTTONS
            Components.BUTTON -> ButtonScreen()
            Components.ICON_BUTTON -> IconButtonScreen()
            Components.BUTTON_BLOCK -> ButtonBlockScreen()
            Components.CAROUSEL_BUTTONS -> ButtonCarouselScreen()
            Components.FAB -> FABScreen()
            Components.SWITCH -> SwitchScreen()
            Components.RADIO -> RadioButtonScreen()
            Components.CHECK_BOX -> CheckboxScreen()

            // INPUT SHELL COMPONENTS
            Components.LEGEND -> LegendScreen()
            Components.LEGEND_DESCRIPTION -> LegendDescriptionScreen()
            Components.FORM_SHELLS -> FormShellsScreen()
            Components.PARAMETER_SELECTOR -> ParameterSelectorScreen()
            Components.SUPPORTING_TEXT -> SupportingTextScreen()

            // INPUTS
            Components.INPUT_TEXT -> InputTextScreen()
            Components.INPUT_LONG_TEXT -> InputLongTextScreen()
            Components.INPUT_POSITIVE_INTEGER_OR_ZERO -> InputPositiveIntegerOrZeroScreen()
            Components.INPUT_POSITIVE_INTEGER -> InputPositiveIntegerScreen()
            Components.INPUT_PERCENTAGE -> InputPercentageScreen()
            Components.INPUT_NEGATIVE_INTEGER -> InputNegativeIntegerScreen()
            Components.INPUT_INTEGER -> InputIntegerScreen()
            Components.INPUT_NUMBER -> InputNumberScreen()
            Components.INPUT_LETTER -> InputLetterScreen()
            Components.INPUT_UNIT_INTERVAL -> InputUnitIntervalScreen()
            Components.INPUT_RADIO_BUTTON -> InputRadioButtonScreen()
            Components.INPUT_CHECK_BOX -> InputCheckBoxScreen()
            Components.INPUT_MATRIX -> InputMatrixScreen(imageBitmapLoader)
            Components.INPUT_SEQUENTIAL -> InputSequentialScreen(imageBitmapLoader)
            Components.INPUT_YES_ONLY_SWITCH -> InputYesOnlySwitchScreen()
            Components.INPUT_YES_ONLY_CHECKBOX -> InputYesOnlyCheckBoxScreen()
            Components.INPUT_YES_NO_FIELD -> InputYesNoFieldScreen()
            Components.INPUT_DROPDOWN -> InputDropDownScreen()
            Components.INPUT_PHONE_NUMBER -> InputPhoneNumberScreen()
            Components.INPUT_AGE -> InputAgeScreen()
            Components.INPUT_LINK -> InputLinkScreen()
            Components.INPUT_EMAIL -> InputEmailScreen()
            Components.INPUT_BARCODE -> InputBarCodeScreen()
            Components.INPUT_POLYGON -> InputPolygonScreen()
            Components.INPUT_ORG_UNIT -> InputOrgUnitScreen()
            Components.INPUT_FILE_RESOURCE -> InputFileResourceScreen()
            Components.INPUT_DATE_TIME -> InputDateTimeScreen()
            Components.INPUT_COORDINATE -> InputCoordinateScreen()
            Components.INPUT_SIGNATURE -> InputSignatureScreen()
            Components.INPUT_IMAGE -> InputImageScreen()
            Components.LOGIN -> LoginScreen()
            Components.FULL_SCREEN_IMAGE -> FullScreenImageScreen()
            Components.INPUT_QR_CODE -> InputQRCodeScreen()
            Components.MULTI_SELECT -> MultiSelectInputScreen()
            Components.INPUT_NOT_SUPPORTED -> InputNotSupportedScreen()

            // OTHERS
            Components.LIST_CARD -> ListCardScreen()
            Components.CARD_DETAIL -> CardDetailScreen()
            Components.SECTIONS -> SectionScreen()
            Components.METADATA_AVATAR -> MetadataAvatarScreen()
            Components.BOTTOM_SHEET -> BottomSheetScreen()
            Components.BOTTOM_SHEET_HEADER -> BottomSheetHeaderScreen()
            Components.BARCODE_BLOCK -> BarcodeBlockScreen()
            Components.IMAGE_BLOCK -> ImageBlockScreen()
            Components.ORG_TREE_BOTTOM_SHEET -> OrgTreeBottomSheetScreen()
            Components.TAGS -> TagsScreen()
            Components.CHIPS -> ChipsScreen()
            Components.BADGES -> BadgesScreen()
            Components.SEARCH_BAR -> SearchBarScreen()
            Components.INDICATOR -> IndicatorScreen()
            Components.PROGRESS -> ProgressScreen()
        }
    }
}

package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.common.screens.previews.threeButtonCarousel
import org.hisp.dhis.mobile.ui.designsystem.component.BarcodeBlock
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetShell
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonCarousel
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputBarCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun InputBarCodeScreen() {
    ColumnComponentContainer(title = ActionInputs.INPUT_BARCODE.label) {
        ColumnComponentItemContainer("Default Input Barcode") {
            var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("889026a1-d01e-4d34-8209-81e8ed5c614b")) }
            var showEnabledBarCodeBottomSheet by rememberSaveable { mutableStateOf(false) }

            if (showEnabledBarCodeBottomSheet) {
                BottomSheetShell(
                    modifier = Modifier.testTag("LEGEND_BOTTOM_SHEET"),
                    title = provideStringResource("qr_code"),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Button",
                            tint = SurfaceColor.Primary,
                        )
                    },
                    content = {
                        Row(horizontalArrangement = Arrangement.Center) {
                            BarcodeBlock(data = inputValue1.text)
                        }
                    },
                    buttonBlock = {
                        ButtonCarousel(
                            carouselButtonList = threeButtonCarousel,
                        )
                    },
                ) {
                    showEnabledBarCodeBottomSheet = false
                }
            }

            InputBarCode(
                "label",
                state = InputShellState.UNFOCUSED,
                onActionButtonClicked = {
                    showEnabledBarCodeBottomSheet = !showEnabledBarCodeBottomSheet
                },
                inputTextFieldValue = inputValue1,
                onValueChanged = {
                    inputValue1 = it ?: TextFieldValue()
                },
            )
        }

        ColumnComponentItemContainer("Required field Input Barcode") {
            var inputValue2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
            InputBarCode(
                "label",
                state = InputShellState.ERROR,
                onActionButtonClicked = {
                },
                inputTextFieldValue = inputValue2,
                onValueChanged = {
                    inputValue2 = it ?: TextFieldValue()
                },
                isRequiredField = true,
                supportingText = listOf(SupportingTextData("Required", SupportingTextState.ERROR)),
            )
        }

        ColumnComponentItemContainer("Disabled Input Barcode") {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
            InputBarCode(
                "label",
                state = InputShellState.DISABLED,
                onActionButtonClicked = {
                },
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    inputValue = it ?: TextFieldValue()
                },
            )
        }

        ColumnComponentItemContainer("Disabled Input Barcode with content") {
            var inputValue3 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("889026a1-d01e-4d34-8209-81e8ed5c614b")) }
            InputBarCode(
                "label",
                state = InputShellState.DISABLED,
                onActionButtonClicked = {
                },
                inputTextFieldValue = inputValue3,
                onValueChanged = {
                    inputValue3 = it ?: TextFieldValue()
                },
            )
        }

        SubTitle("Barcode Block")
        BarcodeBlock(data = "Barcode value")
        HorizontalDivider()
        BarcodeBlock(data = "889026a1-d01e-4d34-8209-81e8ed5c614b")
        HorizontalDivider()
        BarcodeBlock(data = "l;kw1jheoi1u23iop1")
        HorizontalDivider()
        RowComponentContainer {
            BarcodeBlock(data = "563ce8df-8e0b-420c-a63c-fe000b1d1f11")
            BarcodeBlock(data = "378c472d-bb05-4174-9fe5-f6dbf8f5de36")
        }
    }
}

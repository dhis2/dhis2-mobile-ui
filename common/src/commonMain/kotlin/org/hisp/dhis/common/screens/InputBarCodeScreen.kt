package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
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
import org.hisp.dhis.mobile.ui.designsystem.component.Description
import org.hisp.dhis.mobile.ui.designsystem.component.InputBarCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputBarCodeScreen() {
    ColumnComponentContainer {
        var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("889026a1-d01e-4d34-8209-81e8ed5c614b")) }
        var showEnabledBarCodeBottomSheet by rememberSaveable { mutableStateOf(false) }

        Description("Default Input Barcode", textColor = TextColor.OnSurfaceVariant)
        InputBarCode(
            "label",
            state = InputShellState.UNFOCUSED,
            onActionButtonClicked = {
                showEnabledBarCodeBottomSheet = !showEnabledBarCodeBottomSheet
            },
            inputTextFieldValue = inputValue1,
            onValueChanged = {
                inputValue1 = it
            },
        )

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
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
        Description("Required field Input Barcode", textColor = TextColor.OnSurfaceVariant)
        InputBarCode(
            "label",
            state = InputShellState.ERROR,
            onActionButtonClicked = {
            },
            inputTextFieldValue = inputValue2,
            onValueChanged = {
                inputValue2 = it
            },
            isRequiredField = true,
            supportingText = listOf(SupportingTextData("Required", SupportingTextState.ERROR)),
        )

        Spacer(Modifier.size(Spacing.Spacing18))
        var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
        Description("Disabled Input Barcode", textColor = TextColor.OnSurfaceVariant)
        InputBarCode(
            "label",
            state = InputShellState.DISABLED,
            onActionButtonClicked = {
            },
            inputTextFieldValue = inputValue,
            onValueChanged = {
                inputValue = it
            },
        )

        Spacer(Modifier.size(Spacing.Spacing18))
        var inputValue3 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("889026a1-d01e-4d34-8209-81e8ed5c614b")) }
        Description("Disabled Input Barcode with content", textColor = TextColor.OnSurfaceVariant)
        InputBarCode(
            "label",
            state = InputShellState.DISABLED,
            onActionButtonClicked = {
            },
            inputTextFieldValue = inputValue3,
            onValueChanged = {
                inputValue3 = it
            },
        )
    }
}

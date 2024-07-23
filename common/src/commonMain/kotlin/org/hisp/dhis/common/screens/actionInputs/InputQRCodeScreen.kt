package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetShell
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonCarousel
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputQRCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.QrCodeBlock
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun InputQRCodeScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_QR_CODE.label) {
        var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("889026a1-d01e-4d34-8209-81e8ed5c614b")) }
        var showEnabledQRBottomSheet by rememberSaveable { mutableStateOf(false) }

        if (showEnabledQRBottomSheet) {
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
                        QrCodeBlock(data = inputValue1.text)
                    }
                },
                buttonBlock = {
                    ButtonCarousel(
                        carouselButtonList = threeButtonCarousel,
                    )
                },
            ) {
                showEnabledQRBottomSheet = false
            }
        }

        ColumnComponentContainer("Default Input QR code") {
            InputQRCode(
                "label",
                state = InputShellState.UNFOCUSED,
                onQRButtonClicked = {
                    showEnabledQRBottomSheet = !showEnabledQRBottomSheet
                },
                inputTextFieldValue = inputValue1,
                onValueChanged = {
                    if (it != null) {
                        inputValue1 = it
                    }
                },
            )
        }

        var inputValue2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
        ColumnComponentContainer("Required field Input QR code") {
            InputQRCode(
                "label",
                state = InputShellState.ERROR,
                onQRButtonClicked = {
                },
                inputTextFieldValue = inputValue2,
                onValueChanged = {
                    if (it != null) {
                        inputValue2 = it
                    }
                },
                isRequiredField = true,
                supportingText = listOf(SupportingTextData("Required", SupportingTextState.ERROR)),
            )
        }

        var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
        ColumnComponentContainer("Disabled Input QR code") {
            InputQRCode(
                "label",
                state = InputShellState.DISABLED,
                onQRButtonClicked = {
                },
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
    }
}

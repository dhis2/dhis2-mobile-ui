package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetShell
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Description
import org.hisp.dhis.mobile.ui.designsystem.component.InputQRCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.QrCodeBlock
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun QrCodeBlockScreen() {
    ColumnComponentContainer {
        var inputValue1 by rememberSaveable { mutableStateOf("889026a1-d01e-4d34-8209-81e8ed5c614b") }
        var showBottomSheetShell by rememberSaveable { mutableStateOf(false) }

        Description("Default Input QR code", textColor = TextColor.OnSurfaceVariant)
        InputQRCode(
            "label",
            state = InputShellState.UNFOCUSED,
            onQRButtonClicked = {
                showBottomSheetShell = !showBottomSheetShell
            },
            inputText = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
            },

        )

        if (showBottomSheetShell) {
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
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        QrCodeBlock(data = inputValue1)
                    }
                },
                buttonBlock = {
                },
            ) {
                showBottomSheetShell = false
            }
        }

        var inputValue2 by rememberSaveable { mutableStateOf("") }
        Description("Required field Input QR code", textColor = TextColor.OnSurfaceVariant)
        InputQRCode(
            "label",
            state = InputShellState.ERROR,
            onQRButtonClicked = {

            },
            inputText = inputValue2,
            onValueChanged = {
                if (it != null) {
                    inputValue2 = it
                }
            },
            isRequiredField = true,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Spacer(Modifier.size(Spacing.Spacing18))
        var inputValue by rememberSaveable { mutableStateOf("") }
        Description("Disabled Input QR code", textColor = TextColor.OnSurfaceVariant)
        InputQRCode(
            "label",
            state = InputShellState.DISABLED,
            onQRButtonClicked = {
          
            },
            inputText = inputValue,
            onValueChanged = {
                if (it != null) {
                    inputValue = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        QrCodeBlock(data = "QR code value")
        Divider()
        QrCodeBlock(data = "889026a1-d01e-4d34-8209-81e8ed5c614b")
        Divider()
        QrCodeBlock(data = "l;kw1jheoi1u23iop1")
        Divider()
        RowComponentContainer {
            QrCodeBlock(data = "563ce8df-8e0b-420c-a63c-fe000b1d1f11")
            QrCodeBlock(data = "378c472d-bb05-4174-9fe5-f6dbf8f5de36")
        }
    }
}

package org.hisp.dhis.common.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.component.BarcodeBlock
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetShell
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Description
import org.hisp.dhis.mobile.ui.designsystem.component.InputBarCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputBarCodeScreen() {
    ColumnComponentContainer {
        var inputValue1 by rememberSaveable { mutableStateOf("889026a1-d01e-4d34-8209-81e8ed5c614b") }
        var showEnabledBarCodeBottomSheet by rememberSaveable { mutableStateOf(false) }

        Description("Default Input QR code", textColor = TextColor.OnSurfaceVariant)
        InputBarCode(
            "label",
            state = InputShellState.UNFOCUSED,
            onActionButtonClicked = {
                showEnabledBarCodeBottomSheet = !showEnabledBarCodeBottomSheet
            },
            inputText = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
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
                        BarcodeBlock(data = inputValue1)
                    }
                },
                buttonBlock = {
                    Row(
                        Modifier
                            .fillMaxSize()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .padding(top = Spacing.Spacing4)
                                .width(Spacing.Spacing80)
                                .weight(1f),
                            shape = Shape.Full,
                            enabled = true,
                            colors = ButtonDefaults.buttonColors(Color.Transparent, TextColor.OnSurfaceVariant, Color.Transparent, TextColor.OnDisabledSurface),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Share,
                                    contentDescription = "Carousel Button",
                                )

                                Spacer(Modifier.size(Spacing.Spacing8))
                                Text("Share", style = MaterialTheme.typography.labelSmall, textAlign = TextAlign.Center, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            }
                        }
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .padding(top = Spacing.Spacing4)
                                .width(Spacing.Spacing80)
                                .weight(1f),
                            shape = Shape.Full,
                            enabled = true,
                            colors = ButtonDefaults.buttonColors(Color.Transparent, TextColor.OnSurfaceVariant, Color.Transparent, TextColor.OnDisabledSurface),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.QrCodeScanner,
                                    contentDescription = "Carousel Button",
                                )

                                Spacer(Modifier.size(Spacing.Spacing8))
                                Text("Scan", style = MaterialTheme.typography.labelSmall, textAlign = TextAlign.Center, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            }
                        }

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .padding(top = Spacing.Spacing4)
                                .width(Spacing.Spacing80)
                                .weight(1f),
                            shape = Shape.Full,
                            enabled = true,
                            colors = ButtonDefaults.buttonColors(Color.Transparent, TextColor.OnSurfaceVariant, Color.Transparent, TextColor.OnDisabledSurface),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.FileDownload,
                                    contentDescription = "Carousel Button",
                                )

                                Spacer(Modifier.size(Spacing.Spacing8))
                                Text("Download", style = MaterialTheme.typography.labelSmall, textAlign = TextAlign.Center, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            }
                        }
                    }
                },
            ) {
                showEnabledBarCodeBottomSheet = false
            }
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue2 by rememberSaveable { mutableStateOf("") }
        Description("Required field Input Barcode", textColor = TextColor.OnSurfaceVariant)
        InputBarCode(
            "label",
            state = InputShellState.ERROR,
            onActionButtonClicked = {
            },
            inputText = inputValue2,
            onValueChanged = {
                if (it != null) {
                    inputValue2 = it
                }
            },
            isRequiredField = true,
            supportingText = listOf(SupportingTextData("Required", SupportingTextState.ERROR)),
        )

        Spacer(Modifier.size(Spacing.Spacing18))
        var inputValue by rememberSaveable { mutableStateOf("") }
        Description("Disabled Input Barcode", textColor = TextColor.OnSurfaceVariant)
        InputBarCode(
            "label",
            state = InputShellState.DISABLED,
            onActionButtonClicked = {
            },
            inputText = inputValue,
            onValueChanged = {
                if (it != null) {
                    inputValue = it
                }
            },
            enabled = false,
        )

        Spacer(Modifier.size(Spacing.Spacing18))
        var inputValue3 by rememberSaveable { mutableStateOf("889026a1-d01e-4d34-8209-81e8ed5c614b") }
        Description("Disabled Input Barcode with content", textColor = TextColor.OnSurfaceVariant)
        InputBarCode(
            "label",
            state = InputShellState.DISABLED,
            onActionButtonClicked = {
            },
            inputText = inputValue3,
            onValueChanged = {
                if (it != null) {
                    inputValue3 = it
                }
            },
            enabled = false,
        )
    }
}

package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputSignature
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun InputSignatureScreen() {
    ColumnComponentContainer {
        Title("Input Signature", textColor = TextColor.OnSurfaceVariant)

        SubTitle("Basic Input Signature ", textColor = TextColor.OnSurfaceVariant)
        var sampleSignature0 by rememberSaveable { mutableStateOf<ImageBitmap?>(null) }

        InputSignature(
            title = "Label",
            load = { sampleSignature0 },
            painterFor = sampleSignature0?.let { imageBitmap ->
                {
                    BitmapPainter(imageBitmap)
                }
            },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
            onResetButtonClicked = {
                sampleSignature0 = null
            },
            onSaveSignature = {
                sampleSignature0 = it
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Basic Input Signature ", textColor = TextColor.OnSurfaceVariant)
        val sampleSignature = provideSampleImage()

        InputSignature(
            title = "Label",
            load = { sampleSignature },
            painterFor = { remember { it } },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
            onResetButtonClicked = {
            },
            onSaveSignature = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Input Signature without data ", textColor = TextColor.OnSurfaceVariant)
        InputSignature(
            title = "Label",
            state = InputShellState.DISABLED,
            load = { },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
            onResetButtonClicked = {},
            onSaveSignature = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Input Signature with data ", textColor = TextColor.OnSurfaceVariant)
        val sampleSignature2 = provideSampleImage()
        InputSignature(
            title = "Label",
            state = InputShellState.DISABLED,
            load = { sampleSignature2 },
            painterFor = { it },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
            onResetButtonClicked = { },
            onSaveSignature = {},
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun provideSampleImage(): Painter =
    painterResource("drawable/sample_signature.jpeg")

package org.hisp.dhis.showcaseapp.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import mobile_ui.showcaseapp.generated.resources.Res
import mobile_ui.showcaseapp.generated.resources.sample_signature
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputSignature
import org.jetbrains.compose.resources.painterResource

@Composable
fun InputSignatureScreen() {
    ColumnScreenContainer(title = ActionInputs.INPUT_SIGNATURE.label) {
        ColumnComponentContainer("Basic Input Signature ") {
            var sampleSignature0 by rememberSaveable { mutableStateOf<ImageBitmap?>(null) }

            InputSignature(
                title = "Label",
                load = { sampleSignature0 },
                painterFor =
                    sampleSignature0?.let { imageBitmap ->
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
        }

        ColumnComponentContainer("Basic Input Signature ") {
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
        }

        ColumnComponentContainer("Disabled Input Signature without data ") {
            InputSignature(
                title = "Label",
                state = InputShellState.DISABLED,
                load = { },
                onDownloadButtonClick = {},
                onShareButtonClick = {},
                onResetButtonClicked = {},
                onSaveSignature = {},
            )
        }

        ColumnComponentContainer("Disabled Input Signature with data ") {
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
}

@Composable
private fun provideSampleImage(): Painter = painterResource(Res.drawable.sample_signature)

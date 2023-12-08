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
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputSignature
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.component.UploadState
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun InputSignatureScreen() {
    ColumnComponentContainer {
        Title("Input Signature", textColor = TextColor.OnSurfaceVariant)

        SubTitle("Basic Input Signature ", textColor = TextColor.OnSurfaceVariant)
        var uploadState by rememberSaveable { mutableStateOf(UploadState.ADD) }
        val sampleSignature = provideSampleImage()

        InputSignature(
            title = "Label",
            uploadState = uploadState,
            load = { sampleSignature },
            painterFor = { remember { it } },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
            onResetButtonClicked = {
                uploadState = UploadState.ADD
            },
            onAddButtonClicked = {
                uploadState = UploadState.UPLOADING
                Timer().schedule(1000) {
                    uploadState = UploadState.LOADED
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Input Signature without data ", textColor = TextColor.OnSurfaceVariant)
        val uploadState1 by rememberSaveable { mutableStateOf(UploadState.ADD) }
        InputSignature(
            title = "Label",
            state = InputShellState.DISABLED,
            uploadState = uploadState1,
            load = { },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
            onResetButtonClicked = {},
            onAddButtonClicked = {},
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Input Signature with data ", textColor = TextColor.OnSurfaceVariant)
        val uploadState2 by rememberSaveable { mutableStateOf(UploadState.LOADED) }
        val sampleSignature2 = provideSampleImage()
        InputSignature(
            title = "Label",
            state = InputShellState.DISABLED,
            uploadState = uploadState2,
            load = { sampleSignature2 },
            painterFor = { it },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
            onResetButtonClicked = { },
            onAddButtonClicked = {},
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun provideSampleImage(): Painter =
    painterResource("drawable/sample_signature.jpeg")

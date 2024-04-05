package org.hisp.dhis.common.screens.actionInputs

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
import org.hisp.dhis.mobile.ui.designsystem.component.ImageBlock
import org.hisp.dhis.mobile.ui.designsystem.component.InputImage
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
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
fun InputImageScreen() {
    ColumnComponentContainer {
        Title("Input Image", textColor = TextColor.OnSurfaceVariant)

        SubTitle("Basic Input Image ", textColor = TextColor.OnSurfaceVariant)
        var uploadState by rememberSaveable { mutableStateOf(UploadState.ADD) }
        val sampleImage = provideSampleImage()

        InputImage(
            title = "Label",
            uploadState = uploadState,
            load = { sampleImage },
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

        SubTitle("Disabled Input Image without data ", textColor = TextColor.OnSurfaceVariant)
        val uploadState1 by rememberSaveable { mutableStateOf(UploadState.ADD) }
        InputImage(
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

        SubTitle("Disabled Input Image with data ", textColor = TextColor.OnSurfaceVariant)
        val uploadState2 by rememberSaveable { mutableStateOf(UploadState.LOADED) }
        val sampleImage2 = provideSampleImage()
        InputImage(
            title = "Label",
            state = InputShellState.DISABLED,
            uploadState = uploadState2,
            load = { sampleImage2 },
            painterFor = { it },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
            onResetButtonClicked = { },
            onAddButtonClicked = {},
        )

        SubTitle("Image Block", textColor = TextColor.OnSurfaceVariant)

        val sampleImage3 = provideSampleImage()
        ImageBlock(
            title = "Label",
            load = { sampleImage3 },
            painterFor = { remember { it } },
            onDownloadButtonClick = {},
            onShareButtonClick = {},
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun provideSampleImage(): Painter =
    painterResource("drawable/sample.png")

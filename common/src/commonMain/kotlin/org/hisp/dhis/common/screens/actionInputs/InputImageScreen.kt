package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import mobile_ui.common.generated.resources.Res
import mobile_ui.common.generated.resources.sample
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ImageBlock
import org.hisp.dhis.mobile.ui.designsystem.component.InputImage
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.UploadState
import org.jetbrains.compose.resources.painterResource
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun InputImageScreen() {
    ColumnComponentContainer(title = ActionInputs.INPUT_IMAGE.label) {

        ColumnComponentItemContainer("Basic Input Image ") {

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
        }

        ColumnComponentItemContainer("Disabled Input Image without data ") {
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
        }

        ColumnComponentItemContainer("Disabled Input Image with data ") {

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
        }

        ColumnComponentItemContainer("Image Block") {
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
}

@Composable
private fun provideSampleImage(): Painter =
    painterResource(Res.drawable.sample)

package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputImage
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.internal.UploadState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun InputImageScreen() {
    ColumnComponentContainer(
        title = "Input Image Component",
    ) {

        val sampleImage = provideSampleImage()

        InputImage(
            title = "Label",
            buttonText = provideStringResource("add_file"),
            onSelectFile = {},
            onLoadImageFile = { sampleImage },
            onPainterFor = { remember {it}},
            onUploadImage = {},
        )
        InputImage(
            title = "Label",
            buttonText = provideStringResource("add_file"),
            state = UploadState.UPLOADING,
            inputShellState = InputShellState.FOCUSED,
            onSelectFile = {},
            onLoadImageFile = { sampleImage },
            onPainterFor = { remember {it}},
            onUploadImage = {},
        )
        InputImage(
            title = "Label",
            buttonText = provideStringResource("add_file"),
            state = UploadState.LOADED,
            onSelectFile = {},
            onLoadImageFile = { sampleImage },
            onPainterFor = { remember {it}},
            onUploadImage = {},
        )
        InputImage(
            title = "Label",
            buttonText = provideStringResource("add_file"),
            inputShellState = InputShellState.DISABLED,
            onSelectFile = {},
            onLoadImageFile = { sampleImage },
            onPainterFor = { remember {it}},
            onUploadImage = {},
        )
        InputImage(
            title = "Label",
            buttonText = provideStringResource("add_file"),
            inputShellState = InputShellState.DISABLED,
            state = UploadState.LOADED,
            onSelectFile = {},
            onLoadImageFile = { sampleImage },
            onPainterFor = { remember {it}},
            onUploadImage = {},
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun provideSampleImage(): Painter =
    painterResource("drawable/sample.png")
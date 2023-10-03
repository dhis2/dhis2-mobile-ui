package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.ImageBitmap
import java.io.File

@Composable
internal fun rememberImageProvider(file: File): State<ImageBitmap?> {
    val imageProvider = LocalImageProvider.current
    val result = remember(file) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(file) {
        result.value = imageProvider.provideImageFromFile(file)
    }

    return result
}

internal expect class ImageProvider() {
    fun provideImageFromFile(file: File): ImageBitmap?
}

internal val LocalImageProvider = staticCompositionLocalOf { ImageProvider() }

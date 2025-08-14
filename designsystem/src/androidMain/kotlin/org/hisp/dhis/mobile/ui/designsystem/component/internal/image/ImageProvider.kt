package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun provideImage(filePath: String): ImageBitmap? =
    try {
        BitmapFactory.decodeFile(filePath).asImageBitmap()
    } catch (_: Exception) {
        null
    }

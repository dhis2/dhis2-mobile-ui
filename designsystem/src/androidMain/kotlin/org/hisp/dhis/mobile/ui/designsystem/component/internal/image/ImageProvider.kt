package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File

actual fun provideImage(file: File): ImageBitmap? =
    try {
        BitmapFactory.decodeFile(file.absolutePath).asImageBitmap()
    } catch (ex: Exception) {
        null
    }

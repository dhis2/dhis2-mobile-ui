package org.hisp.dhis.mobile.ui.designsystem.component.internal.qr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.ImageBitmap

@Composable
internal fun rememberQrCodeGenerator(value: String): State<ImageBitmap?> {
    val qrCodeGenerator = LocalQrCodeGenerator.current
    val result = remember(value) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(value) {
        result.value = qrCodeGenerator.generate(value)
    }

    return result
}

internal expect class QrCodeGenerator() {
    fun generate(data: String): ImageBitmap?
}

internal const val QR_CODE_SIZE = 512 // px

internal val LocalQrCodeGenerator = staticCompositionLocalOf { QrCodeGenerator() }

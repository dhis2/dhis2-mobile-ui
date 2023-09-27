package org.hisp.dhis.mobile.ui.designsystem.component.internal.barcode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.ImageBitmap

@Composable
internal fun rememberBarcodeGenerator(value: String): State<ImageBitmap?> {
    val barcodeGenerator = LocalBarcodeGenerator.current
    val result = remember(value) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(value) {
        result.value = barcodeGenerator.generate(value)
    }

    return result
}

internal expect class BarcodeGenerator() {
    fun generate(data: String): ImageBitmap?
}

internal const val BARCODE_WIDTH = 512 // px
internal const val BARCODE_HEIGHT = 256 // px

internal val LocalBarcodeGenerator = staticCompositionLocalOf { BarcodeGenerator() }

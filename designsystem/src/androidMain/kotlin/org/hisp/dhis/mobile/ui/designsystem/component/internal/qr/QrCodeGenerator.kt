package org.hisp.dhis.mobile.ui.designsystem.component.internal.qr

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.datamatrix.DataMatrixWriter

internal actual class QrCodeGenerator {
    actual fun generate(
        data: String,
        isDataMatrix: Boolean,
    ): ImageBitmap? =
        try {
            val writer = if (!isDataMatrix) MultiFormatWriter() else DataMatrixWriter()
            val bitMatrix =
                writer.encode(
                    data,
                    if (isDataMatrix) BarcodeFormat.DATA_MATRIX else BarcodeFormat.QR_CODE,
                    QR_CODE_SIZE,
                    QR_CODE_SIZE,
                    mapOf(Pair(EncodeHintType.MARGIN, 1)),
                )
            val bitmap = createAndroidBitmap(bitMatrix)

            bitmap.asImageBitmap()
        } catch (e: WriterException) {
            null
        }

    private fun createAndroidBitmap(bitMatrix: BitMatrix): Bitmap {
        val width = bitMatrix.width
        val height = bitMatrix.height
        val pixels = IntArray(width * height)

        for (y in 0 until height) {
            for (x in 0 until width) {
                pixels[y * width + x] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
            }
        }

        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(pixels, 0, width, 0, 0, width, height)
        }
    }
}

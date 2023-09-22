package org.hisp.dhis.mobile.ui.designsystem.component.internal.qr

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import java.awt.image.BufferedImage

internal actual class QrCodeGenerator {

    private val colorBlack = 0xFF000000.toInt()
    private val colorWhite = 0xFFFFFFFF.toInt()

    actual fun generate(data: String): ImageBitmap? {
        return try {
            val writer = MultiFormatWriter()
            val bitMatrix = writer.encode(
                data,
                BarcodeFormat.QR_CODE,
                QR_CODE_SIZE,
                QR_CODE_SIZE,
                mapOf(Pair(EncodeHintType.MARGIN, 1)),
            )
            val image = createBufferedImage(bitMatrix)

            image.toComposeImageBitmap()
        } catch (e: WriterException) {
            null
        }
    }

    private fun createBufferedImage(bitMatrix: BitMatrix): BufferedImage {
        val width = bitMatrix.width
        val height = bitMatrix.height
        val pixels = IntArray(width * height)

        for (y in 0 until height) {
            for (x in 0 until width) {
                pixels[y * width + x] = if (bitMatrix[x, y]) colorBlack else colorWhite
            }
        }

        return BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).apply {
            setRGB(0, 0, width, height, pixels, 0, width)
        }
    }
}

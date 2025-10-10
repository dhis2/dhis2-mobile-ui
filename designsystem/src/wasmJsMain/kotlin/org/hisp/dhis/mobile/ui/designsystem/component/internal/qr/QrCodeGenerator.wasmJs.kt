package org.hisp.dhis.mobile.ui.designsystem.component.internal.qr

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.js.JsAny
import kotlin.js.JsName

// Define an external interface to describe the shape of the JavaScript qrcode object
private external interface QrCode {
    fun addData(data: String)
    fun make()
    fun createDataURL(cellSize: Int, margin: Int): String
}

// Map to the global JavaScript `qrcode` function using @JsName
@JsName("qrcode")
private external fun qrCodeFunction(typeNumber: Int, errorCorrectionLevel: String): QrCode

// A property to check if the qrcode function exists on the global scope (window)
@JsName("qrcode")
private external val isQrCodeJsLoaded: JsAny?

@OptIn(ExperimentalEncodingApi::class)
internal actual class QrCodeGenerator actual constructor() {
    actual fun generate(
        data: String,
        isDataMatrix: Boolean
    ): ImageBitmap? {
        if (isDataMatrix) {
            println("DataMatrix generation is not supported for wasm target")
            return null
        }

        // Defensive check: Verify the library is loaded before using it.
        if (isQrCodeJsLoaded == null) {
            println("ERROR: qrcode.js library not loaded. Check script tag in index.html and network connection.")
            return null
        }

        return try {
            // Use the typed, external functions for safe JS interop
            val qr = qrCodeFunction(0, "L")
            qr.addData(data)
            qr.make()
            val dataUrl = qr.createDataURL(4, 4)

            val base64Data = dataUrl.substringAfter("base64,")
            val decodedBytes = Base64.Default.decode(base64Data)

            Image.makeFromEncoded(decodedBytes).toComposeImageBitmap()
        } catch (e: Exception) { // Catch a general exception
            println("QR code generation failed. This might be due to invalid data. Error: $e")
            null
        }
    }
}

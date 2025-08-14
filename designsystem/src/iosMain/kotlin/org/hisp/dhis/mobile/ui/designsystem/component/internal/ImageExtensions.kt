package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import org.hisp.dhis.mobile.ui.designsystem.files.toComposeImageBitmap
import platform.CoreGraphics.CGAffineTransformMakeScale
import platform.CoreGraphics.CGImageRef
import platform.CoreGraphics.CGImageRelease
import platform.CoreGraphics.CGRect
import platform.CoreImage.CIContext
import platform.CoreImage.CIImage
import platform.CoreImage.createCGImage
import platform.UIKit.UIImage
import kotlin.math.min

@OptIn(ExperimentalForeignApi::class)
internal fun CIImage.toImageBitmap(
    targetWidth: Double? = null,
    targetHeight: Double? = null,
): ImageBitmap? {
    val context = CIContext.contextWithOptions(null)
    var imageToProcess = this

    val extentCValue = imageToProcess.extent() // extent() returns CGRect

    val (originalWidth, originalHeight) =
        extentCValue.useContents {
            // 'this' inside useContents refers to the actual CGRect struct
            this.size.width to this.size.height
        }

    if (originalWidth == 0.0 || originalHeight == 0.0) {
        return null // Invalid CIImage
    }

    val defaultTargetHeight = 100.0
    val effectiveTargetHeight = targetHeight ?: defaultTargetHeight
    var scale = effectiveTargetHeight / originalHeight

    if (targetWidth != null) {
        val scaleX = targetWidth / originalWidth
        scale = min(scale, scaleX)
    }

    if (scale < 0.1) scale = 0.1
    if (scale > 20.0) scale = 20.0

    if (scale != 1.0) {
        // Ensure scale values are CGFloat (Double in Kotlin)
        val transform = CGAffineTransformMakeScale(scale, scale)
        imageToProcess = imageToProcess.imageByApplyingTransform(transform)
    }

    // Get the extent of the (potentially transformed) image
    val finalExtentCValue: kotlinx.cinterop.CValue<CGRect> = imageToProcess.extent()
    val cgImage: CGImageRef? = context.createCGImage(imageToProcess, finalExtentCValue)

    if (cgImage == null) return null

    try {
        val uiImage = UIImage.imageWithCGImage(cgImage)
        return uiImage.toComposeImageBitmap()
    } finally {
        CGImageRelease(cgImage)
    }
}

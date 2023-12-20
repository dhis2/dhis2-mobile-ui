package org.hisp.dhis.mobile.ui.designsystem.resource

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap

@Composable
actual fun captureBitmap(
    content: @Composable () -> Unit,
): () -> ImageBitmap {
    val context = LocalContext.current

    val composeView = remember { ComposeView(context) }

    fun captureBitmap(): ImageBitmap {
        composeView.setBackgroundColor(Color.WHITE)
        val bitmap = composeView.drawToBitmap()
        return bitmap.asImageBitmap()
    }

    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        },
    )

    return ::captureBitmap
}


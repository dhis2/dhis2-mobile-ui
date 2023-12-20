package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
expect fun captureBitmap(
    content: @Composable () -> Unit,
): () -> ImageBitmap

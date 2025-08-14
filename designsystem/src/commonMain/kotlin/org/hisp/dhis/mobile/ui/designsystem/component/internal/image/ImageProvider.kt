package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import androidx.compose.ui.graphics.ImageBitmap

internal expect fun provideImage(filePath: String): ImageBitmap?

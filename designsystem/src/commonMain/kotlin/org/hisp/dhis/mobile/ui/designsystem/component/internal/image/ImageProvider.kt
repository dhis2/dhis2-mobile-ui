package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import androidx.compose.ui.graphics.ImageBitmap
import java.io.File

internal expect fun provideImage(file: File): ImageBitmap?

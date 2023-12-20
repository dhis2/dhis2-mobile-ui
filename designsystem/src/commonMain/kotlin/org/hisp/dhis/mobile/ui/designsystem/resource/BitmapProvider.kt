package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ImageBitmap

@Composable
expect fun Rect.createBitmap() : ImageBitmap

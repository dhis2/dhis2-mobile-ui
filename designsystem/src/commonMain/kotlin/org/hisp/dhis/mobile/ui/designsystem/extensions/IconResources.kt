package org.hisp.dhis.mobile.ui.designsystem.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun dhis2IconResource(resource: ImageResource): Painter = painterResource(resource)

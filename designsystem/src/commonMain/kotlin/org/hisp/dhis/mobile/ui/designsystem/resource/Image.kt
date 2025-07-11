package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.designsystem.generated.resources.Res
import org.hisp.dhis.mobile.designsystem.generated.resources.allDrawableResources
import org.hisp.dhis.mobile.designsystem.generated.resources.dhis2_default_outline
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/**
 * DHIS2 icons resources
 * @param resourceName the name of the icon
 */
@Composable
fun provideDHIS2Icon(resourceName: String): Painter =
    painterResource(
        drawableResource(resourceName) ?: Res.drawable.dhis2_default_outline,
    )

@OptIn(ExperimentalResourceApi::class)
internal fun drawableResource(resourceName: String) = Res.allDrawableResources[resourceName]

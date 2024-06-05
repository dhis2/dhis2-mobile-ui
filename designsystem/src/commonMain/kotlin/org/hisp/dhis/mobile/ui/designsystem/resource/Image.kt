package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.painterResource

/**
 * DHIS2 icons resources
 * @param resourceName the name of the icon
 */
@Composable
fun provideDHIS2Icon(resourceName: String): Painter {
    val iconName = if (!resourceExists(resourceName)) {
        "dhis2_default_outline"
    } else {
        resourceName
    }
    return painterResource(
        drawableResource(iconName, "xml"),
    )
}

@Composable
fun provideImage(resourceName: String): Painter =
    painterResource(
        drawableResource(resourceName, "jpg"),
    )

@OptIn(InternalResourceApi::class)
fun drawableResource(resourceName: String, fileExtension: String): DrawableResource =
    lazy {
        DrawableResource(
            resourceName,
            setOf(
                ResourceItem(
                    setOf(),
                    "drawable/$resourceName.$fileExtension",
                    -1,
                    -1,
                ),
            ),
        )
    }.value

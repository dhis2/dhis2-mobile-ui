package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/**
 * DHIS2 icons resources
 * @param resourceName the name of the icon
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun provideDHIS2Icon(resourceName: String): Painter {
    val iconName = if (!resourceExists(resourceName)) {
        "dhis2_default_outline"
    } else {
        resourceName
    }
    return painterResource("drawable/$iconName.xml")
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun provideImage(resourceName: String): Painter =
    painterResource("drawable/$resourceName.jpg")

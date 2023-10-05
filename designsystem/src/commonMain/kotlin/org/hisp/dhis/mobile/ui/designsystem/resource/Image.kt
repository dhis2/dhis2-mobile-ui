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
fun provideDHIS2Icon(resourceName: String): Painter =
    painterResource("drawable/$resourceName.xml")

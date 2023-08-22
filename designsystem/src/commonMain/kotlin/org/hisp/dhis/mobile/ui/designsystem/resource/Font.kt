package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily

@Composable
expect fun fontResources(font: String = "roboto_regular"): FontFamily

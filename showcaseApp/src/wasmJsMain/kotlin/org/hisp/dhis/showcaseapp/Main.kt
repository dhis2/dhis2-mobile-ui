package org.hisp.dhis.showcaseapp

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        DHIS2Theme {
            val size = calculateWindowSizeClass()
            App(sizeClass = size)
        }
    }
}
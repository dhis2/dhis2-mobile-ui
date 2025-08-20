package org.hisp.dhis.showcaseapp

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.ComposeUIViewController

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun MainViewController() = ComposeUIViewController (
    configure = {
        enforceStrictPlistSanityCheck = false
    }
){
    val size = calculateWindowSizeClass()
    App(sizeClass = size)
}

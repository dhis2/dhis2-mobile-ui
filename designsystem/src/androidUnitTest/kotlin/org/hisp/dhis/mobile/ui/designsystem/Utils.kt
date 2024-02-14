package org.hisp.dhis.mobile.ui.designsystem

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams

private const val THEME = "android:Theme.Material.Light.NoActionBar.Fullscreen"

fun paparazzi(): Paparazzi {
    return Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = THEME,
        renderingMode = SessionParams.RenderingMode.SHRINK,
    )
}

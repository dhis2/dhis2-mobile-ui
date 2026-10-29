package org.hisp.dhis.mobile.ui.designsystem

import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.RenderExtension
import com.android.ide.common.rendering.api.SessionParams
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.setResourceReaderAndroidContext

private const val THEME = "android:Theme.Material.Light.NoActionBar.Fullscreen"

fun paparazzi(): Paparazzi =
    Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = THEME,
        renderingMode = SessionParams.RenderingMode.SHRINK,
        renderExtensions = setOf(ComposeResourcesContextExtension),
    )

/**
 * Compose resources read from the Android assets through a context that is normally set by
 * a ContentProvider, which does not run under Paparazzi.
 */
private object ComposeResourcesContextExtension : RenderExtension {
    @OptIn(ExperimentalResourceApi::class)
    override fun renderView(contentView: View): View {
        setResourceReaderAndroidContext(contentView.context)
        return contentView
    }
}

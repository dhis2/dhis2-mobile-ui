package org.hisp.dhis.mobile.ui.designsystem

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.RenderExtension
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.setResourceReaderAndroidContext

private const val THEME = "android:Theme.Material.Light.NoActionBar.Fullscreen"
private val DEVICE = DeviceConfig.PIXEL_5

fun paparazzi(): Paparazzi =
    Paparazzi(
        deviceConfig = DEVICE,
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

/**
 * Takes two snapshots of [content] inside a [DHIS2Theme], on the theme background:
 * - light theme, under the test's usual golden name, so light changes show up as image diffs;
 * - dark theme, with a "dark" suffix and the device in night mode, so dialogs and menus drawn in their own
 *   windows are dark as well.
 */
fun Paparazzi.themedSnapshots(content: @Composable () -> Unit) {
    themedSnapshot(name = null, darkTheme = false, content = content)
    unsafeUpdateConfig(deviceConfig = DEVICE.copy(nightMode = NightMode.NIGHT))
    try {
        themedSnapshot(name = "dark", darkTheme = true, content = content)
    } finally {
        unsafeUpdateConfig(deviceConfig = DEVICE)
    }
}

private fun Paparazzi.themedSnapshot(
    name: String?,
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) = snapshot(name = name) {
    DHIS2Theme(isDarkModeEnabled = darkTheme) {
        Box(Modifier.background(DHIS2Theme.colorScheme.background)) {
            content()
        }
    }
}

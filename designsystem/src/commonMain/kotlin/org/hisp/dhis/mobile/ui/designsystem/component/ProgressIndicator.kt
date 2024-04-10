package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

enum class ProgressIndicatorType {
    CIRCULAR,
    LINEAR,
}

/**
 * DHIS2 Progress indicator
 * Progress indicators inform users about the status of ongoing processes,
 * such as loading an app, submitting a form, or saving updates.
 * They communicate an app’s state and indicate available actions,
 * such as whether users can navigate away from the current screen.
 * @param modifier: optional modifier.
 * @param type: [ProgressIndicatorType] can be either
 * [ProgressIndicatorType.CIRCULAR] or [ProgressIndicatorType.LINEAR].
 * @param progress: indicates the loading progress
 * @param hasError: manages whether to show error or not.
 */
@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    type: ProgressIndicatorType,
    progress: Float? = null,
    hasError: Boolean = false,
) {
    when (type) {
        ProgressIndicatorType.CIRCULAR -> CircularIndicator(modifier, progress, hasError)
        ProgressIndicatorType.LINEAR -> LinearIndicator(modifier, progress, hasError)
    }
}

@Composable
internal fun LinearIndicator(modifier: Modifier, progress: Float?, hasError: Boolean) {
    val color = if (hasError) SurfaceColor.Error else SurfaceColor.Primary
    val trackColor = if (hasError) SurfaceColor.ErrorContainer else SurfaceColor.ContainerHigh
    if (progress != null) {
        LinearProgressIndicator(
            progress = progress,
            modifier = modifier,
            color = color,
            trackColor = trackColor,
        )
    } else {
        LinearProgressIndicator(
            modifier = modifier,
            color = color,
            trackColor = trackColor,
        )
    }
}

@Composable
internal fun CircularIndicator(modifier: Modifier, progress: Float?, hasError: Boolean) {
    val color = if (hasError) SurfaceColor.Error else SurfaceColor.Primary
    if (progress != null) {
        CircularProgressIndicator(
            progress = progress,
            modifier = modifier,
            color = color,
        )
    } else {
        CircularProgressIndicator(
            modifier = modifier,
            color = color,
        )
    }
}

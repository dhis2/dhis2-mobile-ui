package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

enum class ProgressIndicatorType {
    CIRCULAR,
    CIRCULAR_SMALL,
    LINEAR,
}

/**
 * DHIS2 Progress indicator
 * Progress indicators inform users about the status of ongoing processes,
 * such as loading an app, submitting a form, or saving updates.
 * They communicate an app’s state and indicate available actions,
 * such as whether users can navigate away from the current screen.
 * @param modifier: optional modifier.
 * @param type: [ProgressIndicatorType] can be [ProgressIndicatorType.CIRCULAR]
 * [ProgressIndicatorType.CIRCULAR_SMALL] or [ProgressIndicatorType.LINEAR].
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
        ProgressIndicatorType.LINEAR -> LinearIndicator(modifier, progress, hasError)
        ProgressIndicatorType.CIRCULAR -> CircularIndicator(
            modifier = modifier,
            progress = progress,
            hasError = hasError,
        )
        ProgressIndicatorType.CIRCULAR_SMALL -> CircularIndicator(
            modifier = modifier,
            strokeWidth = Border.Regular,
            progress = progress,
            hasError = hasError,
        )
    }
}

@Composable
internal fun LinearIndicator(modifier: Modifier, progress: Float?, hasError: Boolean) {
    val color = if (hasError) SurfaceColor.Error else SurfaceColor.Primary
    val trackColor = if (hasError) SurfaceColor.ErrorContainer else SurfaceColor.Container
    if (progress != null) {
        LinearProgressIndicator(
            progress = { progress },
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
internal fun CircularIndicator(
    modifier: Modifier,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    progress: Float?,
    hasError: Boolean,
) {
    val color = if (hasError) SurfaceColor.Error else SurfaceColor.Primary
    val trackColor = if (hasError) SurfaceColor.ErrorContainer else SurfaceColor.Container
    if (progress != null) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = modifier,
            strokeWidth = strokeWidth,
            color = color,
            trackColor = trackColor,
        )
    } else {
        CircularProgressIndicator(
            modifier = modifier,
            strokeWidth = strokeWidth,
            color = color,
            trackColor = trackColor,
        )
    }
}

package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor

enum class ProgressIndicatorType {
    CIRCULAR,
    LINEAR
}

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    type: ProgressIndicatorType,
    progress: Float? = null,
    hasError: Boolean = false
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
            trackColor = trackColor
        )
    } else {
        LinearProgressIndicator(
            modifier = modifier,
            color = color,
            trackColor = trackColor
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
            color = color
        )
    } else {
        CircularProgressIndicator(
            modifier = modifier,
            color = color
        )
    }
}

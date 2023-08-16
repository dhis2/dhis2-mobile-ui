package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Radius
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor

/**
 * DHIS2 Text Input Field. Wraps Material· [BasicTextField].
 * @param enabled Controls the enabled state of the component. When `false`, this component will not be
 * clickable and will appear disabled to accessibility services.
 */
@Composable
fun SimpleTextInputField(
    enabled: Boolean = true
) {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        modifier = Modifier
            .background(
                SurfaceColor.Surface
            )
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
        },
        enabled = enabled,
        textStyle = MaterialTheme.typography.titleMedium,
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.weight(1f)) {
                    innerTextField()
                }
            }
        }
    )
}

/**
 * DHIS2 EmptyInput
 * empty input container with dashed border
 * used for internal test purposes
 */
@Composable
fun EmptyInput(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth().height(Spacing.Spacing24).background(SurfaceColor.SurfaceBright)) {
        val stroke = Stroke(
            width = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )
        Canvas(Modifier.fillMaxWidth().height(Spacing.Spacing24)) {
            drawRoundRect(color = Outline.Light, style = stroke, cornerRadius = CornerRadius(x = Radius.XS.toPx(), y = Radius.XS.toPx()))
        }
    }
}

package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Radius
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

@Composable
fun ColumnComponentContainer(
    title: String = "",
    content: @Composable (() -> Unit)
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
        modifier = Modifier.padding(Spacing.Spacing10).verticalScroll(rememberScrollState())
    ) {
        if (title.isNotEmpty()) Text(title, style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.size(Spacing.Spacing4))
        content()
    }
}

@Composable
fun RowComponentContainer(
    title: String = "",
    content: @Composable (() -> Unit)
) {
    if (title.isNotEmpty()) Text(title)

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        content()
    }
}

/**
 * DHIS2 Input Shell
 * @param title is the value of the text to be shown.
 * @param state Controls the  state of the component. Will be Unfocused by default
 * @param primaryIcon controls
 * @param showSeparator Controls separator visibility
 * @param showActionButton Controls action button visibility
 * @param showLegend Controls action button visibility
 * @param valueType Controls the input field valueType.
 * @param onClick Will be called when the user clicks the action button.
 */
@Composable
fun InputShell(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    showSeparator: Boolean = true,
    primaryButton: @Composable (() -> Unit)? = null,
    secondaryButton: @Composable (() -> Unit)? = null,
    inputField: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    legend: @Composable (() -> Unit)? = null,
    onClickPrimary: (() -> Unit)? = null,
    onClickSecondary: (() -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        var indicatorColor by remember { mutableStateOf(InputShellState.UNFOCUSED.color) }
        val backgroundColor = if (state != InputShellState.DISABLED) SurfaceColor.Surface else SurfaceColor.DisabledSurface
        InputShellRow(
            modifier = Modifier.onFocusChanged {
                indicatorColor = if (it.isFocused) InputShellState.FOCUSED.color else state.color
            },
            backgroundColor = backgroundColor
        ) {
            Column(
                Modifier
                    .weight(4f, false)
                    .padding(end = Spacing.Spacing4),
                verticalArrangement = Arrangement.Center
            ) {
                InputShellLabelText(title, textColor = indicatorColor)
                inputField?.invoke()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                primaryButton?.let {
                    if (onClickPrimary != null) {
                        it.invoke()
                    }
                }
                if (showSeparator) {
                    InputShellButtonSeparator()
                    Spacer(modifier = Modifier.width(4.dp))
                }
                secondaryButton?.let {
                    if (onClickSecondary != null) {
                        it.invoke()
                    }
                }
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
        InputShellIndicator(color = indicatorColor)
        legend?.invoke()
        if (state != InputShellState.DISABLED) supportingText?.invoke()
    }
}

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

@Composable
fun InputShellRow(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable (() -> Unit)
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = modifier.fillMaxWidth()
            .background(backgroundColor)
            .clip(shape = RoundedCornerShape(Radius.S, Radius.L))
            .padding(Spacing.Spacing16, Spacing.Spacing8, 0.dp, Spacing.Spacing4)
    ) {
        content()
    }
}

@Composable
fun InputShellButtonSeparator(
    modifier: Modifier = Modifier
) {
    Divider(
        color = Outline.Medium,
        thickness = Spacing.Spacing1,
        modifier = modifier
            .height(Spacing.Spacing40)
            .width(Spacing.Spacing1)
    )
}

@Composable
fun InputShellIndicator(
    color: Color,
    modifier: Modifier = Modifier
) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .padding(),
        thickness = Spacing.Spacing1,
        color = color
    )
}

enum class InputShellState(val color: Color) {
    FOCUSED(SurfaceColor.Primary),
    UNFOCUSED(TextColor.OnSurfaceVariant),
    ERROR(TextColor.OnError),
    DISABLED(TextColor.OnDisabledSurface)
}

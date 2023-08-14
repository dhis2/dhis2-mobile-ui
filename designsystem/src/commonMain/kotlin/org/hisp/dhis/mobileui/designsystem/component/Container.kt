package org.hisp.dhis.mobileui.designsystem.component

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.component.internal.ValueType
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Radius
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

@Composable
fun ColumnComponentContainer(
    title: String,
    content: @Composable (() -> Unit)
) {
    Column(modifier = Modifier.padding(20.dp).verticalScroll(rememberScrollState())) {
        Text(text = title, fontWeight = FontWeight.Bold)
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
 * DHIS2 Text Input Shell. Wraps DHIS· [BasicInput].
 * ValueType will allways be TEXT
 * @param title Controls the selected option state for multiple options.
 * @param enabled Controls the enabled state of the component. When `false`, this component will not be
 * clickable and will appear disabled to accessibility services.
 * @param showResetButton Controls reset button visibility
 * @param showSeparator Controls separator visibility
 * @param showActionButton Controls action button visibility
 * @param showLegend Controls action button visibility
 * @param valueType Controls the input field valueType.
 * @param onClick Will be called when the user clicks the action button.
 */
@Composable
fun OldInputShell(
    title: String,
    enabled: Boolean = true,
    valueType: ValueType = ValueType.TEXT,
    showResetButton: Boolean = true,
    showSeparator: Boolean = true,
    showActionButton: Boolean = true,
    showLegend: Boolean = false,
    primaryIcon: @Composable (() -> Unit)? = null,
    secondaryIcon: @Composable (() -> Unit)? = null,
    inputContent: @Composable (() -> Unit)? = null,

    onClickPrimary: (() -> Unit)? = null,
    onClickSecondary: (() -> Unit)? = null
) {
    TextInputField("Label", enabled) {}
}

@Composable
fun InputShell(
    title: String,
    enabled: Boolean = true,
    showError: Boolean = false,
    showSeparator: Boolean = true,
    primaryIcon: @Composable (() -> Unit)? = null,
    secondaryIcon: @Composable (() -> Unit)? = null,
    inputField: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    legend: @Composable (() -> Unit)? = null,
    onClickPrimary: (() -> Unit)? = null,
    onClickSecondary: (() -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        var indicatorColor by remember { mutableStateOf(InputShellState.UNFOCUSED.color) }
        var labelColor by remember { mutableStateOf(if (enabled) TextColor.OnDisabledSurface else TextColor.OnDisabledSurface) }
        var showError by remember { mutableStateOf(showError) }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .background(if (enabled) SurfaceColor.Surface else SurfaceColor.DisabledSurface)
                .clip(shape = RoundedCornerShape(Radius.S, Radius.L))
                .padding(Spacing.Spacing16, Spacing.Spacing8, 0.dp, 0.dp).onFocusChanged {
                    indicatorColor = if (!showError) {
                        if (it.isFocused && enabled) InputShellState.FOCUSED.color else InputShellState.UNFOCUSED.color
                    } else {
                        InputShellState.ERROR.color
                    }
                    labelColor = if (!showError) {
                        if (it.isFocused && enabled) InputShellState.FOCUSED.color else TextColor.OnSurface
                    } else {
                        InputShellState.ERROR.color
                    }
                }
        ) {
            Column(Modifier.weight(4f, false).padding(0.dp, 0.dp, 0.dp, Spacing.Spacing2), verticalArrangement = Arrangement.Center) {
                Text(title, modifier = Modifier, color = labelColor, style = MaterialTheme.typography.titleSmall)
                inputField?.invoke()
            }
            Row(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                primaryIcon?.let {
                    if (onClickPrimary != null) {
                        IconButton(
                            icon = { it.invoke() },
                            onClick = onClickPrimary,
                            modifier = Modifier.padding(Spacing.Spacing4, 0.dp),
                            enabled = enabled
                        )
                    }
                }
                Divider(color = Outline.Medium, thickness = 1.dp, modifier = Modifier.height(Spacing.Spacing40).width(Spacing.Spacing1).padding(0.dp, 0.dp, 0.dp, Spacing.Spacing4))
                if (secondaryIcon != null) {
                    if (onClickSecondary != null) {
                        SquareIconButton(
                            enabled,
                            icon = secondaryIcon,
                            onClick = onClickSecondary,
                            modifier = Modifier.padding(Spacing.Spacing4, 0.dp)
                        )
                    }
                }
            }
        }
        Divider(modifier = Modifier.fillMaxWidth(), thickness = Spacing.Spacing1, color = indicatorColor)
        if (enabled) supportingText?.invoke()
        legend?.invoke()
    }
}

@Composable
fun EmptyInput(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth().height(Spacing.Spacing24).background(SurfaceColor.SurfaceBright).clip(shape = RoundedCornerShape(Radius.S))) {
    }
}

enum class InputShellState(val color: Color) {
    FOCUSED(SurfaceColor.Primary),
    UNFOCUSED(Outline.Dark),
    ERROR(TextColor.OnError)
}

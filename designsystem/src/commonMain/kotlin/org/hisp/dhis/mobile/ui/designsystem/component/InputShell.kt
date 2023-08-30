package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 Input Shell
 * @param title is the value of the text to be shown.
 * @param state controls the  state of the component. Will be Unfocused by default
 * @param primaryButton controls the primary button composable if null will show nothing
 * @param secondaryButton controls  action button composable, if null will show nothing
 * @param legend controls the optional legend composable
 * @param inputField controls the input field composable .
 * @param supportingText controls the supporting text composable
 */
@Composable
fun InputShell(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    primaryButton: @Composable (() -> Unit)? = null,
    secondaryButton: @Composable (() -> Unit)? = null,
    inputField: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    legend: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().clip(shape = RoundedCornerShape(Radius.XS, Radius.XS))) {
        var indicatorColor by remember { mutableStateOf(InputShellState.UNFOCUSED.color) }
        val backgroundColor = if (state != InputShellState.DISABLED) SurfaceColor.Surface else SurfaceColor.DisabledSurface
        InputShellRow(
            modifier = Modifier.onFocusChanged {
                indicatorColor = if (it.isFocused) InputShellState.FOCUSED.color else state.color
            },
            backgroundColor = backgroundColor,
        ) {
            Column(
                Modifier
                    .weight(4f, false)
                    .padding(end = Spacing.Spacing4),
                verticalArrangement = Arrangement.Center,
            ) {
                InputShellLabelText(title, textColor = indicatorColor)
                inputField?.invoke()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                primaryButton?.let {
                    Box(Modifier.size(Spacing.Spacing48)) {
                        it.invoke()
                    }
                }
                if (primaryButton != null && secondaryButton != null) {
                    InputShellButtonSeparator()
                    Spacer(modifier = Modifier.width(Spacing.Spacing4))
                }
                secondaryButton?.let {
                    Box(Modifier.size(Spacing.Spacing48)) {
                        it.invoke()
                    }
                }
            }
        }
        InputShellIndicator(color = indicatorColor)
        legend?.invoke()
        if (state != InputShellState.DISABLED) supportingText?.invoke()
    }
}

/**
 * DHIS2 InputShellRow, wraps Compose [Row]
 * Row used in Input shell component for title, input field
 * and buttons.
 * @param backgroundColor controls the containers color
 * @param content controls the content to be shown
 */
@Composable
private fun InputShellRow(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable (() -> Unit),
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = modifier.fillMaxWidth()
            .background(backgroundColor)
            .padding(Spacing.Spacing16, Spacing.Spacing8, Spacing.Spacing0, Spacing.Spacing4),
    ) {
        content()
    }
}

/**
 * DHIS2 InputShellButtonSeparator, wraps Material 3 [Divider]
 * used in the [InputShell] component
 */
@Composable
private fun InputShellButtonSeparator(
    modifier: Modifier = Modifier,
) {
    Divider(
        color = Outline.Medium,
        thickness = Spacing.Spacing1,
        modifier = modifier
            .height(Spacing.Spacing40)
            .width(Spacing.Spacing1),
    )
}

/**
 * DHIS2 InputShellIndicator, wraps Material 3 [Divider]
 *  used in the [InputShell] component
 */
@Composable
private fun InputShellIndicator(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .padding(),
        thickness = Spacing.Spacing1,
        color = color,
    )
}

/**
 * DHIS2 InputShellState,
 *  enum class to control the state [InputShell] component
 */
enum class InputShellState(val color: Color) {
    FOCUSED(SurfaceColor.Primary),
    UNFOCUSED(TextColor.OnSurfaceVariant),
    ERROR(SurfaceColor.Error),
    DISABLED(TextColor.OnDisabledSurface),
}

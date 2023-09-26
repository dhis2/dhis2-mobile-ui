package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
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
 * @param isRequiredField controls whether the field is mandatory
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
    isRequiredField: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().clip(shape = RoundedCornerShape(Radius.XS, Radius.XS))) {
        var indicatorColor by remember { mutableStateOf(InputShellState.UNFOCUSED.color) }
        var indicatorThickness by remember { mutableStateOf(Border.Thin) }
        val backgroundColor = if (state != InputShellState.DISABLED) SurfaceColor.Surface else SurfaceColor.DisabledSurface
        InputShellRow(
            modifier = Modifier
                .onFocusChanged {
                    indicatorColor =
                        if (it.isFocused && state != InputShellState.ERROR && state != InputShellState.WARNING) InputShellState.FOCUSED.color else state.color
                    indicatorThickness = if (it.isFocused) Border.Regular else Border.Thin
                },
            backgroundColor = backgroundColor,
        ) {
            Column(
                Modifier
                    .weight(4f, false)
                    .padding(end = Spacing.Spacing4)
                    .fillMaxWidth(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                if (title.isNotEmpty()) {
                    val titleText = if (isRequiredField) "$title *" else title
                    InputShellLabelText(titleText, textColor = indicatorColor)
                }
                inputField?.invoke()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(Spacing.Spacing48),
            ) {
                primaryButton?.invoke()
                if (primaryButton != null && secondaryButton != null) {
                    InputShellButtonSeparator()
                    Spacer(modifier = Modifier.width(Spacing.Spacing4))
                }
                secondaryButton?.let {
                    Box(
                        Modifier
                            .padding(end = Spacing.Spacing4).size(Spacing.Spacing48),
                    ) {
                        it.invoke()
                    }
                }
            }
        }
        Box(Modifier.height(Spacing.Spacing2)) {
            InputShellIndicator(
                color = indicatorColor,
                thickness = indicatorThickness,
            )
        }
        legend?.invoke()
        if (state != InputShellState.DISABLED) supportingText?.invoke()
        if (isRequiredField && state == InputShellState.ERROR && supportingText == null) SupportingText("Required", state = SupportingTextState.ERROR)
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
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .background(backgroundColor)
            .padding(Spacing.Spacing16, Spacing.Spacing8, Spacing.Spacing0, Spacing.Spacing6),
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
        thickness = Border.Thin,
        modifier = modifier
            .height(Spacing.Spacing40)
            .width(Border.Thin),
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
    thickness: Dp = Border.Thin,
) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = Spacing.Spacing0,
            ).offset {
                IntOffset(
                    0,
                    if (thickness == Border.Thin) 0 else -2,
                )
            },
        thickness = thickness,
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
    WARNING(TextColor.OnWarning),
}

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
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
    state: InputShellState,
    primaryButton: @Composable (() -> Unit)? = null,
    secondaryButton: @Composable (() -> Unit)? = null,
    inputField: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    legend: @Composable (ColumnScope.() -> Unit)? = null,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    isRequiredField: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().clip(shape = RoundedCornerShape(Radius.XS, Radius.XS))) {
        var indicatorColor by remember(state) { mutableStateOf(state.color) }
        var indicatorThickness by remember { mutableStateOf(Border.Thin) }
        val backgroundColor = if (state != InputShellState.DISABLED) SurfaceColor.Surface else SurfaceColor.DisabledSurface
        val focusRequester = remember { FocusRequester() }

        Box(Modifier.fillMaxWidth()) {
            InputShellRow(
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .pointerInput(Unit) {
                        if (state != InputShellState.DISABLED) {
                            detectTapGestures(
                                onTap = { focusRequester.requestFocus() },
                            )
                        }
                    }
                    .onFocusChanged {
                        indicatorColor =
                            when {
                                state == InputShellState.DISABLED -> InputShellState.DISABLED.color
                                it.isFocused && state != InputShellState.ERROR && state != InputShellState.WARNING -> InputShellState.FOCUSED.color
                                else -> state.color
                            }
                        indicatorThickness = when {
                            state == InputShellState.DISABLED -> Border.Thin
                            it.isFocused -> Border.Regular
                            else -> Border.Thin
                        }
                        onFocusChanged?.invoke(it.isFocused)
                    },
                backgroundColor = backgroundColor,
            ) {
                Column(
                    Modifier
                        .weight(1f)
                        .padding(end = Spacing.Spacing4),
                ) {
                    if (title.isNotEmpty()) {
                        val titleText = if (isRequiredField) "$title *" else title
                        InputShellLabelText(titleText, textColor = indicatorColor)
                    }
                    inputField?.invoke()
                }
                if (primaryButton != null || secondaryButton != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(Spacing.Spacing48)
                            .align(Alignment.CenterVertically),
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
            }

            InputShellIndicator(
                modifier = Modifier.align(Alignment.BottomStart),
                color = indicatorColor,
                thickness = indicatorThickness,
            )
        }

        legend?.invoke(this)
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
    content: @Composable (RowScope.() -> Unit),
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
            .background(backgroundColor)
            .padding(
                start = Spacing.Spacing16,
                top = Spacing.Spacing8,
                end = Spacing.Spacing0,
                bottom = Spacing.Spacing8,
            ),
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
        modifier = modifier.fillMaxWidth(),
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
    WARNING(SurfaceColor.Warning),
}

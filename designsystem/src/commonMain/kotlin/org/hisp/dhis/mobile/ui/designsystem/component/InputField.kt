package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.PrefixTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.SuffixTransformer
import org.hisp.dhis.mobile.ui.designsystem.theme.Color.Blue300
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalFloatValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.textFieldHoverPointerIcon

/**
 * DHIS2 EmptyInput
 * empty input container with dashed border
 * used for internal test purposes
 */
@Composable
fun EmptyInput(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxWidth().height(Spacing.Spacing24).background(SurfaceColor.SurfaceBright)) {
        val stroke = Stroke(
            width = InternalFloatValues.Two,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(InternalFloatValues.Ten, InternalFloatValues.Ten), InternalFloatValues.Zero),
        )
        Canvas(Modifier.fillMaxWidth().height(Spacing.Spacing24)) {
            drawRoundRect(color = Outline.Light, style = stroke, cornerRadius = CornerRadius(x = Radius.XS.toPx(), y = Radius.XS.toPx()))
        }
    }
}

/**
 * DHIS2 Basic Input. Wraps Material· [BasicTextField].
 * @param helper Manages the helper text to be shown
 * @param enabled Controls the enabled state of the component. When `false`, this component will not be
 * clickable and will appear disabled to accessibility services.
 * @param helperStyle manages the helper text style, NONE by default
 * @param inputText manages the value of the input field text
 * @param onInputChanged gives access to the onTextChangedEvent
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BasicInput(
    helper: String? = null,
    enabled: Boolean = true,
    helperStyle: InputStyle = InputStyle.NONE,
    inputText: String = "",
    onInputChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    state: InputShellState = InputShellState.FOCUSED,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    onNextClicked: (() -> Unit)? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var visualTransformation = VisualTransformation.None

    if (helperStyle != InputStyle.NONE) {
        if (helperStyle == InputStyle.WITH_HELPER_BEFORE) {
            helper?.let { visualTransformation = PrefixTransformation(it) }
        } else {
            helper?.let {
                visualTransformation = SuffixTransformer(it)
            }
        }
    }

    val cursorColor by remember {
        if (state == InputShellState.UNFOCUSED || state == InputShellState.FOCUSED) {
            mutableStateOf(InputShellState.FOCUSED.color)
        } else {
            mutableStateOf(state.color)
        }
    }

    val customTextSelectionColors = TextSelectionColors(
        handleColor = cursorColor,
        backgroundColor = Blue300,
    )
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        BasicTextField(

            modifier = modifier
                .background(
                    Color.Transparent,
                )
                .fillMaxWidth()
                .textFieldHoverPointerIcon(enabled),
            value = inputText,
            onValueChange = onInputChanged,
            enabled = enabled,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = if (enabled) TextColor.OnSurface else TextColor.OnDisabledSurface),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(Modifier.weight(InternalFloatValues.One)) {
                        innerTextField()
                    }
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onNext = {
                    onNextClicked?.invoke()
                },
                onDone = {
                    keyboardController?.hide()
                },
            ),
            visualTransformation = visualTransformation,
            cursorBrush = SolidColor(cursorColor),
        )
    }
}

enum class InputStyle {
    WITH_HELPER_AFTER,
    WITH_HELPER_BEFORE,
    NONE,
}

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.PrefixTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.SuffixTransformer
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.theme.Color.Blue300
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalFloatValues
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.textFieldHoverPointerIcon

/**
 * DHIS2 Basic Input. Wraps Material 3· [BasicTextField].
 * @param helper: Manages the helper text to be shown.
 * @param enabled: Controls the enabled state of the component. When `false`, this component will not be
 * clickable and will appear disabled to accessibility services.
 * @param isSingleLine: manages the number of lines to be allowed in the input field.
 * @param helperStyle: manages the helper text style, NONE by default.
 * @param inputTextValue: manages the value of the input field text.
 * @param onInputChanged: gives access to the onTextChangedEvent.
 * @param modifier: to pass a modifier if necessary.
 * @param state: manages the color of cursor depending on the state of parent component.
 * @param keyboardOptions: manages the ImeAction to be shown on the keyboard.
 * @param visualTransformation: manages custom visual transformation. When null it
 * will use the visual transformation created based on helper style, when a visual transformation
 * is passed it will ignore the helper style.
 * @param onNextClicked: gives access to the ImeAction event.
 * @param onSearchClicked: gives access to the ImeAction Search event.
 */
@Composable
fun BasicTextField(
    helper: String? = null,
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    helperStyle: HelperStyle = HelperStyle.NONE,
    inputTextValue: TextFieldValue? = null,
    onInputChanged: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    state: InputShellState = InputShellState.FOCUSED,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    visualTransformation: VisualTransformation? = null,
    onNextClicked: (() -> Unit)? = null,
    onSearchClicked: (() -> Unit)? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var textFieldVisualTransformation = VisualTransformation.None

    if (helperStyle != HelperStyle.NONE) {
        when (helperStyle) {
            HelperStyle.WITH_HELPER_BEFORE -> {
                helper?.let { textFieldVisualTransformation = PrefixTransformation(it, enabled) }
            }
            HelperStyle.WITH_DATE_OF_BIRTH_HELPER -> {
                textFieldVisualTransformation = DateTransformation()
            }
            else -> {
                helper?.let {
                    textFieldVisualTransformation = SuffixTransformer(it)
                }
            }
        }
    }

    textFieldVisualTransformation = visualTransformation ?: textFieldVisualTransformation

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
            value = inputTextValue ?: TextFieldValue(),
            onValueChange = {
                onInputChanged.invoke(it)
            },
            enabled = enabled,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = if (enabled) TextColor.OnSurface else TextColor.OnDisabledSurface),
            singleLine = isSingleLine,
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
                onSearch = {
                    onSearchClicked?.invoke()
                },
                onDone = {
                    keyboardController?.hide()
                },
            ),
            visualTransformation = textFieldVisualTransformation,
            cursorBrush = SolidColor(cursorColor),
        )
    }
}

enum class HelperStyle {
    WITH_HELPER_AFTER,
    WITH_HELPER_BEFORE,
    WITH_DATE_OF_BIRTH_HELPER,
    NONE,
}

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.buttonShadow
import org.hisp.dhis.mobile.ui.designsystem.theme.customRippleConfiguration
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

/**
 * DHIS2 ButtonState
 * @param text The text to display within.
 * @param textColor The color for the text to be used
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param style Controls style of the button. Will be OUTLINED by default, but can be
 * FILLED, TEXT, TONAL, ELEVATED or KEYBOARDKEY.
 * @param colorStyle the button color style to be used,
 * can be [ColorStyle.DEFAULT] or [ColorStyle.ERROR].
 * */
data class ButtonState(
    val text: String,
    val textColor: Color = Color.Unspecified,
    val enabled: Boolean = true,
    val style: ButtonStyle = ButtonStyle.OUTLINED,
    val colorStyle: ColorStyle = ColorStyle.DEFAULT,
)

private val LocalTextColor = staticCompositionLocalOf { Color.Unspecified }

/**
 * DHIS2 button with generic icon slot.
 * wraps DHIS2 [SimpleButton] which in turn wraps material 3 [Button].
 * @param modifier: optional [Modifier].
 * @param state: Controls the state of the button.
 * @param icon: The button icon content.
 * @param paddingValues: optional [PaddingValues] to be overridden.
 * @param onClick :Will be called when the user clicks the button.
 */
@Composable
fun Button(
    modifier: Modifier = Modifier,
    state: ButtonState,
    icon: @Composable
    (() -> Unit)? = null,
    paddingValues: PaddingValues = getPaddingValues(icon != null),
    onClick: () -> Unit,
) {
    CompositionLocalProvider(LocalTextColor provides state.textColor) {
        ButtonContent(
            modifier = modifier,
            enabled = state.enabled,
            style = state.style,
            colorStyle = state.colorStyle,
            text = state.text,
            icon = icon,
            paddingValues = paddingValues,
            onClick = onClick,
        )
    }
}

/**
 * DHIS2 button with generic icon slot.
 * wraps DHIS2 [SimpleButton] which in turn wraps material 3 [Button].
 * @param enabled: Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param style: Controls style of the button. Will be OUTLINED by default, but can be
 * FILLED, TEXT, TONAL, ELEVATED or KEYBOARDKEY.
 * @param colorStyle: the button color style to be used,
 * can be [ColorStyle.DEFAULT] or [ColorStyle.ERROR].
 * @param text: The text to display within.
 * @param icon: The button icon content.
 * @param paddingValues: optional [PaddingValues] to be overridden.
 * @param modifier: optional [Modifier].
 * @param onClick :Will be called when the user clicks the button.
 */
@Deprecated(
    "Use constructor with ButtonState instead",
    ReplaceWith(
        "Button(modifier = modifier, state = ButtonState(text = text, enabled = enabled, style = style, " +
            "colorStyle = colorStyle), icon = icon, paddingValues = paddingValues, onClick = onClick)",
    ),
)
@Composable
fun Button(
    enabled: Boolean = true,
    style: ButtonStyle = ButtonStyle.OUTLINED,
    colorStyle: ColorStyle = ColorStyle.DEFAULT,
    text: String,
    icon: @Composable
    (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = getPaddingValues(icon != null),
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        state =
            ButtonState(
                text = text,
                enabled = enabled,
                style = style,
                colorStyle = colorStyle,
            ),
        icon = icon,
        paddingValues = paddingValues,
        onClick = onClick,
    )
}

@Composable
private fun ButtonContent(
    modifier: Modifier,
    enabled: Boolean,
    style: ButtonStyle,
    colorStyle: ColorStyle,
    text: String,
    icon: @Composable
    (() -> Unit)?,
    paddingValues: PaddingValues,
    onClick: () -> Unit,
) {
    when (style) {
        ButtonStyle.FILLED -> {
            val buttonColors =
                when (colorStyle) {
                    ColorStyle.DEFAULT -> getFilledButtonColors(enabled)
                    ColorStyle.ERROR -> getErrorFilledButtonColors(enabled)
                    ColorStyle.WARNING -> getWarningFilledButtonColors(enabled)
                }

            SimpleButton(
                modifier = modifier,
                onClick = { onClick() },
                enabled = enabled,
                buttonColors =
                    ButtonDefaults.filledTonalButtonColors(
                        buttonColors.containerColor,
                        buttonColors.contentColor,
                        DHIS2Theme.extendedColors.disabledSurface,
                        DHIS2Theme.extendedColors.onDisabledSurface,
                    ),
                text = text,
                textColor = buttonColors.textColor,
                icon = icon,
                paddingValues = paddingValues,
            )
        }

        ButtonStyle.TEXT, ButtonStyle.TEXT_LIGHT -> {
            val buttonColors =
                when (colorStyle) {
                    ColorStyle.DEFAULT -> getTextButtonColors(style, enabled, LocalTextColor.current)
                    ColorStyle.ERROR -> getErrorTextButtonColors(style, enabled)
                    ColorStyle.WARNING -> getWarningTextButtonColors(style, enabled)
                }
            val rippleConfiguration =
                when (colorStyle) {
                    ColorStyle.DEFAULT -> customRippleConfiguration()
                    ColorStyle.ERROR -> customRippleConfiguration(DHIS2Theme.colorScheme.error)
                    ColorStyle.WARNING -> customRippleConfiguration(DHIS2Theme.extendedColors.warning)
                }
            CompositionLocalProvider(LocalRippleConfiguration provides rippleConfiguration) {
                OutlinedButton(
                    modifier = modifier,
                    onClick = { onClick() },
                    enabled = enabled,
                    colors =
                        ButtonDefaults.filledTonalButtonColors(
                            buttonColors.containerColor,
                            buttonColors.contentColor,
                            DHIS2Theme.extendedColors.disabledSurface,
                            DHIS2Theme.extendedColors.onDisabledSurface,
                        ),
                    border = BorderStroke(Border.Thin, Color.Transparent),
                ) {
                    ButtonText(text, buttonColors.textColor, icon, enabled)
                }
            }
        }

        ButtonStyle.ELEVATED -> {
            val textColor =
                if (enabled) {
                    DHIS2Theme.colorScheme.primary
                } else {
                    DHIS2Theme.extendedColors.onDisabledSurface
                }
            ElevatedButton(
                modifier =
                    modifier
                        .hoverPointerIcon(enabled),
                onClick = { onClick() },
                elevation = ButtonDefaults.elevatedButtonElevation(),
                enabled = enabled,
                colors =
                    ButtonDefaults.filledTonalButtonColors(
                        DHIS2Theme.colorScheme.surfaceContainerLow,
                        DHIS2Theme.colorScheme.primary,
                        DHIS2Theme.extendedColors.disabledSurface,
                        DHIS2Theme.extendedColors.onDisabledSurface,
                    ),
                shape = ButtonDefaults.outlinedShape,
                contentPadding = paddingValues,
            ) {
                ButtonText(text, textColor, icon, enabled)
            }
        }

        ButtonStyle.TONAL -> {
            val textColor =
                if (enabled) {
                    DHIS2Theme.colorScheme.onPrimaryContainer
                } else {
                    DHIS2Theme.extendedColors.onDisabledSurface
                }
            CompositionLocalProvider(LocalRippleConfiguration provides customRippleConfiguration()) {
                SimpleButton(
                    modifier = modifier,
                    onClick = { onClick() },
                    enabled = enabled,
                    buttonColors =
                        ButtonDefaults.filledTonalButtonColors(
                            DHIS2Theme.colorScheme.primaryContainer,
                            DHIS2Theme.colorScheme.onPrimaryContainer,
                            DHIS2Theme.extendedColors.disabledSurface,
                            DHIS2Theme.extendedColors.onDisabledSurface,
                        ),
                    text = text,
                    textColor = textColor,
                    icon = icon,
                    paddingValues = paddingValues,
                )
            }
        }

        ButtonStyle.KEYBOARDKEY -> {
            val textColor =
                if (enabled) {
                    DHIS2Theme.colorScheme.primary
                } else {
                    DHIS2Theme.extendedColors.onDisabledSurface
                }
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            var topPadding = mutableStateOf(0)
            val shadowColor: MutableState<Color>
            if (enabled) {
                if (isPressed) {
                    shadowColor = mutableStateOf(Color.Transparent)
                    topPadding = mutableStateOf(2)
                } else {
                    shadowColor =
                        when (colorStyle) {
                            ColorStyle.DEFAULT -> mutableStateOf(DHIS2Theme.colorScheme.surfaceContainerHighest)
                            ColorStyle.ERROR -> mutableStateOf(DHIS2Theme.extendedColors.errorContainerHighest)
                            ColorStyle.WARNING -> mutableStateOf(DHIS2Theme.extendedColors.warningContainer)
                        }
                    topPadding = mutableStateOf(0)
                }
            } else {
                shadowColor = mutableStateOf(Color.Transparent)
            }
            ElevatedButton(
                elevation = ButtonDefaults.buttonElevation(0.dp),
                onClick = { onClick() },
                interactionSource = interactionSource,
                modifier =
                    modifier
                        .buttonShadow(shadowColor, Radius.Full)
                        .offset {
                            IntOffset(
                                0,
                                topPadding.value,
                            )
                        },
                enabled = enabled,
                colors =
                    ButtonDefaults.elevatedButtonColors(
                        disabledContainerColor = Color.Transparent,
                        containerColor = DHIS2Theme.colorScheme.surfaceContainer,
                        contentColor = DHIS2Theme.colorScheme.primary,
                        disabledContentColor = DHIS2Theme.extendedColors.onDisabledSurface,
                    ),
            ) {
                ButtonText(text, textColor, icon, enabled)
            }
        }

        ButtonStyle.OUTLINED -> {
            val buttonColors =
                when (colorStyle) {
                    ColorStyle.DEFAULT -> getOutlinedButtonColors(enabled)
                    ColorStyle.ERROR -> getErrorOutlinedButtonColors(enabled)
                    ColorStyle.WARNING -> getWarningOutlinedButtonColors(enabled)
                }

            val rippleConfiguration =
                when (colorStyle) {
                    ColorStyle.DEFAULT -> customRippleConfiguration()
                    ColorStyle.ERROR -> customRippleConfiguration(DHIS2Theme.colorScheme.error)
                    ColorStyle.WARNING -> customRippleConfiguration(DHIS2Theme.extendedColors.warning)
                }
            CompositionLocalProvider(LocalRippleConfiguration provides rippleConfiguration) {
                OutlinedButton(
                    modifier = modifier.hoverPointerIcon(enabled).height(Spacing.Spacing40),
                    onClick = { onClick() },
                    enabled = enabled,
                    shape = ButtonDefaults.outlinedShape,
                    colors =
                        ButtonDefaults.outlinedButtonColors(
                            buttonColors.containerColor,
                            buttonColors.contentColor,
                            Color.Transparent,
                            DHIS2Theme.extendedColors.onDisabledSurface,
                        ),
                    border =
                        BorderStroke(
                            Border.Thin,
                            if (enabled) DHIS2Theme.colorScheme.outline else DHIS2Theme.extendedColors.disabledSurface,
                        ),
                    contentPadding = paddingValues,
                ) {
                    ButtonText(text, buttonColors.textColor, icon, enabled)
                }
            }
        }
    }
}

/**
 * DHIS2 private [SimpleButton] with generic icon slot.
 * wraps material 3 [Button].
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param buttonColors [ButtonColors] to be used
 * @param text The text to display within.
 * @param textColor The color for the text to be used
 * @param icon The button icon content.
 * @param paddingValues optional [PaddingValues] to be overridden.
 * @param modifier optional [Modifier].
 * @param onClick Will be called when the user clicks the button.
 */
@Composable
private fun SimpleButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonColors: ButtonColors,
    text: String,
    textColor: Color,
    icon: @Composable
    (() -> Unit)? = null,
    onClick: () -> Unit,
    paddingValues: PaddingValues,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.hoverPointerIcon(enabled),
        enabled = enabled,
        colors = buttonColors,
        shape = Shape.Full,
        contentPadding = paddingValues,
    ) {
        ButtonText(text, textColor, icon, enabled)
    }
}

/**
 * DHIS2 [TextButtonSelector]
 * @param enabled Controls the enabled state of the component. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param firstOptionText text to be displayed for first option to be used
 * @param middleText The text to be displayed between options.
 * @param secondOptionText The text to be displayed for second option
 * @param onClickFirstOption Callback when first option is clicked.
 * @param onClickSecondOption Callback when second option is clicked.
 * @param modifier optional [Modifier].
 */
@Composable
fun TextButtonSelector(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    firstOptionText: String,
    middleText: String,
    secondOptionText: String,
    onClickFirstOption: () -> Unit,
    onClickSecondOption: () -> Unit,
) {
    val interactionSourceOption1 = remember { MutableInteractionSource() }
    val interactionSourceOption2 = remember { MutableInteractionSource() }

    val isPressed1 by interactionSourceOption1.collectIsPressedAsState()
    val isPressed2 by interactionSourceOption2.collectIsPressedAsState()

    val clickableText1Color: MutableState<Color> =
        if (enabled) {
            if (!isPressed1) mutableStateOf(DHIS2Theme.colorScheme.primary) else mutableStateOf(DHIS2Theme.colorScheme.onSurfaceVariant)
        } else {
            mutableStateOf(DHIS2Theme.extendedColors.onDisabledSurface)
        }
    val clickableText2Color: MutableState<Color> =
        if (enabled) {
            if (!isPressed2) mutableStateOf(DHIS2Theme.colorScheme.primary) else mutableStateOf(DHIS2Theme.colorScheme.onSurfaceVariant)
        } else {
            mutableStateOf(DHIS2Theme.extendedColors.onDisabledSurface)
        }

    Row(modifier = modifier) {
        Text(
            text = firstOptionText,
            color = clickableText1Color.value,
            style = MaterialTheme.typography.titleMedium,
            modifier =
                Modifier.clickable(
                    enabled = enabled,
                    interactionSource = interactionSourceOption1,
                    onClick = {
                        onClickFirstOption()
                    },
                    indication = null,
                ),
        )
        Text(
            text = middleText,
            modifier = Modifier.padding(start = Spacing.Spacing8, end = Spacing.Spacing8),
            style = MaterialTheme.typography.bodyLarge,
            color = if (enabled) DHIS2Theme.colorScheme.onSurfaceVariant else DHIS2Theme.extendedColors.onDisabledSurface,
        )
        Text(
            text = secondOptionText,
            color = clickableText2Color.value,
            style = MaterialTheme.typography.titleMedium,
            modifier =
                Modifier.clickable(
                    enabled = enabled,
                    interactionSource = interactionSourceOption2,
                    onClick = {
                        onClickSecondOption()
                    },
                    indication = null,
                ),
        )
    }
}

private fun getPaddingValues(hasIcon: Boolean): PaddingValues {
    val buttonWithIconPaddingValues =
        PaddingValues(
            Spacing.Spacing16,
            Spacing.Spacing10,
            Spacing.Spacing24,
            Spacing.Spacing10,
        )
    val buttonWithoutIconPaddingValues =
        PaddingValues(
            Spacing.Spacing24,
            Spacing.Spacing10,
            Spacing.Spacing24,
            Spacing.Spacing10,
        )

    return if (hasIcon) buttonWithIconPaddingValues else buttonWithoutIconPaddingValues
}

enum class ButtonStyle {
    FILLED,
    OUTLINED,
    TEXT,
    TEXT_LIGHT,
    ELEVATED,
    TONAL,
    KEYBOARDKEY,
}

enum class ColorStyle {
    DEFAULT,
    ERROR,
    WARNING,
}

/**
 * DHIS2 ButtonBlock with generic buttons slot.
 * @param primaryButton Controls first or primary button, if there is
 * only one it will be centered, otherwise spaced equally
 * @param secondaryButton Controls the second button to be shown
 * @param modifier allow a modifier to be passed to the composable.
 */
@Composable
fun ButtonBlock(
    modifier: Modifier = Modifier,
    primaryButton: @Composable (() -> Unit),
    secondaryButton: @Composable (() -> Unit)? = null,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        if (secondaryButton != null) {
            Box(modifier = Modifier.weight(0.5f)) {
                primaryButton.invoke()
            }
            Spacer(Modifier.size(Spacing.Spacing16))
            Box(modifier = Modifier.weight(0.5f)) {
                secondaryButton.invoke()
            }
        } else {
            primaryButton.invoke()
        }
    }
}

private data class CustomButtonColors(
    val textColor: Color,
    val containerColor: Color,
    val contentColor: Color,
)

@Composable
private fun getFilledButtonColors(isEnabled: Boolean): CustomButtonColors =
    CustomButtonColors(
        textColor = if (isEnabled) DHIS2Theme.colorScheme.onPrimary else DHIS2Theme.extendedColors.onDisabledSurface,
        containerColor = DHIS2Theme.colorScheme.primary,
        contentColor = DHIS2Theme.colorScheme.onPrimary,
    )

@Composable
private fun getErrorFilledButtonColors(isEnabled: Boolean): CustomButtonColors =
    CustomButtonColors(
        textColor = if (isEnabled) DHIS2Theme.colorScheme.onError else DHIS2Theme.extendedColors.onDisabledSurface,
        containerColor = DHIS2Theme.colorScheme.error,
        contentColor = DHIS2Theme.colorScheme.onError,
    )

@Composable
private fun getWarningFilledButtonColors(isEnabled: Boolean): CustomButtonColors =
    CustomButtonColors(
        textColor = if (isEnabled) DHIS2Theme.extendedColors.onWarning else DHIS2Theme.extendedColors.onDisabledSurface,
        containerColor = DHIS2Theme.extendedColors.warning,
        contentColor = DHIS2Theme.extendedColors.onWarning,
    )

@Composable
private fun getTextButtonColors(
    style: ButtonStyle,
    isEnabled: Boolean,
    currentTextColor: Color,
): CustomButtonColors {
    val textColor =
        when {
            !isEnabled ->
                currentTextColor.takeIf { !it.isUnspecified }?.copy(alpha = 0.7f)
                    ?: DHIS2Theme.extendedColors.onDisabledSurface

            style == ButtonStyle.TEXT_LIGHT ->
                currentTextColor.takeIf { !it.isUnspecified } ?: DHIS2Theme.colorScheme.onPrimary

            else ->
                currentTextColor.takeIf { !it.isUnspecified } ?: DHIS2Theme.colorScheme.primary
        }
    return CustomButtonColors(
        textColor = textColor,
        containerColor = Color.Transparent,
        contentColor = DHIS2Theme.colorScheme.primary,
    )
}

@Composable
private fun getErrorTextButtonColors(
    style: ButtonStyle,
    isEnabled: Boolean,
): CustomButtonColors {
    val textColor =
        when {
            !isEnabled -> DHIS2Theme.extendedColors.onDisabledSurface
            style == ButtonStyle.TEXT_LIGHT -> DHIS2Theme.colorScheme.onError
            else -> DHIS2Theme.colorScheme.error
        }
    return CustomButtonColors(
        textColor = textColor,
        containerColor = Color.Transparent,
        contentColor = DHIS2Theme.colorScheme.error,
    )
}

@Composable
private fun getWarningTextButtonColors(
    style: ButtonStyle,
    isEnabled: Boolean,
): CustomButtonColors {
    val textColor =
        when {
            !isEnabled -> DHIS2Theme.extendedColors.onDisabledSurface
            style == ButtonStyle.TEXT_LIGHT -> DHIS2Theme.colorScheme.onError
            else -> DHIS2Theme.extendedColors.warning
        }
    return CustomButtonColors(
        textColor = textColor,
        containerColor = Color.Transparent,
        contentColor = DHIS2Theme.extendedColors.warning,
    )
}

@Composable
private fun getOutlinedButtonColors(isEnabled: Boolean): CustomButtonColors =
    CustomButtonColors(
        textColor = if (isEnabled) DHIS2Theme.colorScheme.primary else DHIS2Theme.extendedColors.onDisabledSurface,
        containerColor = Color.Transparent,
        contentColor = DHIS2Theme.colorScheme.primary,
    )

@Composable
private fun getErrorOutlinedButtonColors(isEnabled: Boolean): CustomButtonColors =
    CustomButtonColors(
        textColor = if (isEnabled) DHIS2Theme.colorScheme.error else DHIS2Theme.extendedColors.onDisabledSurface,
        containerColor = Color.Transparent,
        contentColor = DHIS2Theme.colorScheme.error,
    )

@Composable
private fun getWarningOutlinedButtonColors(isEnabled: Boolean): CustomButtonColors =
    CustomButtonColors(
        textColor = if (isEnabled) DHIS2Theme.extendedColors.warning else DHIS2Theme.extendedColors.onDisabledSurface,
        containerColor = Color.Transparent,
        contentColor = DHIS2Theme.extendedColors.warning,
    )

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.buttonShadow
import java.util.Locale

/**
 * DHIS2 button with generic icon slot.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param style Controls style of the button. Will be OUTLINED by default, but can be
 * FILLED, TEXT, TONAL, ELEVATED or KEYBOARDKEY
 * @param text The text to display within.
 * @param icon The button icon content.
 * @param onClick Will be called when the user clicks the button.
 */
@Composable
fun Button(
    enabled: Boolean = true,
    style: ButtonStyle = ButtonStyle.OUTLINED,
    text: String,
    icon: @Composable
    (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val paddingValues = getPaddingValues(icon != null)

    when (style) {
        ButtonStyle.FILLED -> {
            val textColor = if (enabled) TextColor.OnPrimary else TextColor.OnDisabledSurface

            SimpleButton(
                onClick = { onClick() },
                enabled = enabled,
                buttonColors = ButtonDefaults.filledTonalButtonColors(
                    SurfaceColor.Primary,
                    TextColor.OnPrimary,
                    SurfaceColor.DisabledSurface,
                    TextColor.OnDisabledSurface,
                ),
                text = text,
                textColor = textColor,
                icon = icon,

            )
        }
        ButtonStyle.TEXT -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface
            SimpleButton(
                onClick = { onClick() },
                enabled = enabled,
                buttonColors = ButtonDefaults.filledTonalButtonColors(
                    Color.Transparent,
                    SurfaceColor.Primary,
                    Color.Transparent,
                    TextColor.OnDisabledSurface,
                ),
                text = text,
                textColor = textColor,
                icon = icon,
            )
        }
        ButtonStyle.ELEVATED -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface

            ElevatedButton(
                onClick = { onClick() },
                elevation = ButtonDefaults.elevatedButtonElevation(),
                enabled = enabled,
                colors = ButtonDefaults.filledTonalButtonColors(
                    SurfaceColor.ContainerLow,
                    SurfaceColor.Primary,
                    SurfaceColor.DisabledSurface,
                    TextColor.OnDisabledSurface,
                ),
                shape = ButtonDefaults.outlinedShape,
                contentPadding = paddingValues,
            ) {
                ButtonText(text, textColor, icon, enabled)
            }
        }
        ButtonStyle.TONAL -> {
            val textColor = if (enabled) TextColor.OnPrimaryContainer else TextColor.OnDisabledSurface
            CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
                SimpleButton(
                    onClick = { onClick() },
                    enabled = enabled,
                    buttonColors = ButtonDefaults.filledTonalButtonColors(
                        SurfaceColor.PrimaryContainer,
                        TextColor.OnPrimaryContainer,
                        SurfaceColor.DisabledSurface,
                        TextColor.OnDisabledSurface,
                    ),
                    text = text,
                    textColor = textColor,
                    icon = icon,
                )
            }
        }
        ButtonStyle.KEYBOARDKEY -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface

            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            var topPadding = mutableStateOf(0)
            val shadowColor: MutableState<Color>
            if (enabled) {
                if (isPressed) {
                    shadowColor = mutableStateOf(Color.Transparent)
                    topPadding = mutableStateOf(2)
                } else {
                    shadowColor = mutableStateOf(SurfaceColor.ContainerHighest)
                    topPadding = mutableStateOf(0)
                }
            } else {
                shadowColor = mutableStateOf(Color.Transparent)
            }
            ElevatedButton(
                elevation = ButtonDefaults.buttonElevation(0.dp),
                onClick = { onClick() },
                interactionSource = interactionSource,
                modifier = modifier
                    .buttonShadow(shadowColor, Radius.Full, icon != null).offset {
                        IntOffset(
                            0,
                            topPadding.value,
                        )
                    },
                enabled = enabled,
                colors = ButtonDefaults.elevatedButtonColors(
                    disabledContainerColor = Color.Transparent,
                    containerColor = SurfaceColor.Container,
                ),

            ) {
                ButtonText(text, textColor, icon, enabled)
            }
        }
        ButtonStyle.OUTLINED -> {
            val textColor = if (enabled) SurfaceColor.Primary else TextColor.OnDisabledSurface
            OutlinedButton(
                onClick = { onClick() },
                enabled = enabled,
                shape = ButtonDefaults.outlinedShape,
                colors = ButtonDefaults.outlinedButtonColors(
                    Color.Transparent,
                    SurfaceColor.Primary,
                    Color.Transparent,
                    TextColor.OnDisabledSurface,
                ),
                border = BorderStroke(Spacing.Spacing1, if (enabled) Outline.Dark else SurfaceColor.DisabledSurface),
                contentPadding = paddingValues,
                modifier = Modifier.height(Spacing.Spacing40),
            ) {
                ButtonText(text, textColor, icon, enabled)
            }
        }
    }
}

@Composable
private fun SimpleButton(
    enabled: Boolean = true,
    buttonColors: ButtonColors,
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    icon: @Composable
    (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    val paddingValues = getPaddingValues(icon != null)

    Button(
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled,
        colors = buttonColors,
        shape = Shape.Full,
        contentPadding = paddingValues,
    ) {
        ButtonText(text, textColor, icon, enabled)
    }
}

@Composable
fun TextButtonSelector(
    enabled: Boolean = true,
    firstOptionText: String,
    secondOptionText: String,
    firstOptionComposable: @Composable (() -> Unit),
    secondOptionComposable: @Composable (() -> Unit)
) {
    var isTextVisible by remember { mutableStateOf(true) }
    var isFirstComposableVisible by remember { mutableStateOf(false) }
    var isSecondComposableVisible by remember { mutableStateOf(false) }

    if (isTextVisible) {
        Row {
            Text(
                text = firstOptionText.uppercase(Locale.getDefault()),
                color = SurfaceColor.Primary,
                modifier = Modifier.clickable {
                    isFirstComposableVisible = true
                    isTextVisible = false
                    isSecondComposableVisible = false
                }
            )
            Text(text = " OR ")

            Text(
                text = secondOptionText.uppercase(Locale.getDefault()),
                color = SurfaceColor.Primary,
                modifier = Modifier.clickable {
                    isFirstComposableVisible = false
                    isTextVisible = false
                    isSecondComposableVisible = true
                }
            )
        }
    }
    if (isFirstComposableVisible) {
        firstOptionComposable.invoke()
    }
    if (isSecondComposableVisible) {
        secondOptionComposable.invoke()
    }
}

private fun getPaddingValues(hasIcon: Boolean): PaddingValues {
    val buttonWithIconPaddingValues = PaddingValues(
        Spacing.Spacing16,
        Spacing.Spacing10,
        Spacing.Spacing24,
        Spacing.Spacing10,
    )
    val buttonWithoutIconPaddingValues = PaddingValues(
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
    ELEVATED,
    TONAL,
    KEYBOARDKEY,
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
    primaryButton: @Composable (() -> Unit),
    secondaryButton: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(horizontalArrangement = Arrangement.Center, modifier = modifier.padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8)) {
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

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.component.internal.iconButton
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon
import org.hisp.dhis.mobile.ui.designsystem.theme.iconButtonshadow

/**
 * DHIS2 square icon button with generic icon slot. Wraps Material 3 [ElevatedButton].
 *
 * @param enabled: Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon: The button icon content.
 * @param onClick: Will be called when the user clicks the button.
 * @param modifier: optional modifier.
 */
@Composable
fun SquareIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var topPadding = mutableStateOf(0)
    val shadowColor: MutableState<Color>
    if (enabled) {
        if (isPressed) {
            shadowColor = mutableStateOf(Color.Transparent)
            topPadding = mutableStateOf(1)
        } else {
            shadowColor = mutableStateOf(SurfaceColor.ContainerHighest)
            topPadding = mutableStateOf(0)
        }
    } else {
        shadowColor = mutableStateOf(Color.Transparent)
    }

    ElevatedButton(
        interactionSource = interactionSource,
        onClick = onClick,
        elevation = ButtonDefaults.elevatedButtonElevation(0.dp),
        modifier = modifier
            .size(InternalSizeValues.Size48)
            .padding(Spacing.Spacing4)
            .iconButtonshadow(shadowColor, Radius.S)
            .offset {
                IntOffset(
                    0,
                    topPadding.value,
                )
            }
            .hoverPointerIcon(enabled),
        enabled = enabled,
        shape = Shape.Small,
        colors = ButtonDefaults.elevatedButtonColors(
            disabledContainerColor = Color.Transparent,
            containerColor = SurfaceColor.Container,
            contentColor = SurfaceColor.Primary,
            disabledContentColor = TextColor.OnDisabledSurface,
        ),
        contentPadding = PaddingValues(Spacing.Spacing8),
    ) {
        Box(Modifier.size(Spacing.Spacing24)) {
            icon()
        }
    }
}

/**
 * DHIS2 icon button with generic icon slot.
 * @param enabled: Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon: The button icon content.
 * @param onClick: Will be called when the user clicks the button.
 * @param style: controls the button style, will be Standard by default,
 * but can be Tonal, Filled, or Outlined too.
 * @param modifier: optional modifier.
 */
@Composable
fun IconButton(
    style: IconButtonStyle = IconButtonStyle.STANDARD,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val scope = rememberCoroutineScope()
    when (style) {
        IconButtonStyle.FILLED -> FilledIconButton(enabled, icon, modifier = modifier, onClick = onClick, interactionSource = interactionSource, scope = scope)
        IconButtonStyle.TONAL -> FilledTonalIconButton(enabled, icon, modifier, onClick = onClick, interactionSource = interactionSource, scope = scope)
        IconButtonStyle.OUTLINED -> OutlinedIconButton(enabled, icon, modifier = modifier, onClick = onClick, interactionSource = interactionSource, scope = scope)
        else -> StandardIconButton(enabled, icon, modifier = modifier, onClick = onClick, interactionSource = interactionSource, scope = scope)
    }
}

/**
 * DHIS2 icon button with generic icon slot.
 * used internally for DHIS2 component  [BottomSheetShell].
 * @param enabled: Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon: The button icon content.
 * @param onClick: Will be called when the user clicks the button.
 * @param modifier: optional modifier.
 */
@Composable
internal fun BottomSheetIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
            .size(InternalSizeValues.Size48)
            .padding(Spacing.Spacing4)
            .hoverPointerIcon(enabled),
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(SurfaceColor.Scrim.copy(alpha = 0.34f), SurfaceColor.SurfaceBright, SurfaceColor.DisabledSurface, TextColor.OnDisabledSurface),
    ) {
        Box(Modifier.size(Spacing.Spacing24)) {
            icon()
        }
    }
}

/**
 * DHIS2 generic icon button with  icon slot.
 * used internally for DHIS2 component [IconButton].
 * @param enabled: controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon: the button icon content.
 * @param onClick: will be called when the user clicks the button.
 * @param interactionSource: manages the button's Interaction source.
 * @param scope: coroutine scope to be used.
 * @param modifier: optional modifier.
 */
@Composable
private fun StandardIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    scope: CoroutineScope = rememberCoroutineScope(),
    onClick: () -> Unit,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme()) {
        Box(
            Modifier.size(InternalSizeValues.Size48).clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                onClick = {
                    onClick.invoke()
                    emitPressInteraction(scope, interactionSource)
                },
                indication = null,
            ),
        ) {
            FilledIconButton(
                interactionSource = interactionSource,
                onClick = onClick,
                modifier = modifier.iconButton(enabled),
                enabled = enabled,
                colors = IconButtonDefaults.iconButtonColors(Color.Transparent, TextColor.OnSurfaceVariant, Color.Transparent, TextColor.OnDisabledSurface),
            ) {
                Box(Modifier.size(Spacing.Spacing24)) {
                    icon()
                }
            }
        }
    }
}

/**
 * DHIS2  icon button with  icon slot.
 * used internally for DHIS2 component [IconButton] with
 * style [IconButtonStyle.FILLED].
 * @param enabled: controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon: the button icon content.
 * @param onClick: will be called when the user clicks the button.
 * @param interactionSource: manages the button's Interaction source.
 * @param scope: coroutine scope to be used.
 * @param modifier: optional modifier.
 */
@Composable
private fun FilledIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    scope: CoroutineScope = rememberCoroutineScope(),
    onClick: () -> Unit,
) {
    Box(
        Modifier.size(InternalSizeValues.Size48).clickable(
            enabled = enabled,
            interactionSource = interactionSource,
            onClick = {
                onClick.invoke()
                emitPressInteraction(scope, interactionSource)
            },
            indication = null,
        ),
    ) {
        FilledIconButton(
            interactionSource = interactionSource,
            onClick = onClick,
            modifier = modifier.iconButton(enabled),
            enabled = enabled,
            colors = IconButtonDefaults.iconButtonColors(
                SurfaceColor.Primary,
                TextColor.OnPrimary,
                SurfaceColor.DisabledSurface,
                TextColor.OnDisabledSurface,
            ),
        ) {
            Box(Modifier.size(Spacing.Spacing24)) {
                icon()
            }
        }
    }
}

/**
 * DHIS2  icon button with  icon slot.
 * used internally for DHIS2 component [IconButton] with
 * style [IconButtonStyle.TONAL].
 * @param enabled: Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon: The button icon content.
 * @param onClick: Will be called when the user clicks the button.
 * @param interactionSource: manages the button's Interaction source.
 * @param scope: Coroutine scope to be used.
 * @param modifier: optional modifier.
 */
@Composable
private fun FilledTonalIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    scope: CoroutineScope = rememberCoroutineScope(),
    onClick: () -> Unit,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme()) {
        Box(
            Modifier.size(InternalSizeValues.Size48).clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                onClick = {
                    onClick.invoke()
                    emitPressInteraction(scope, interactionSource)
                },
                indication = null,
            ),
        ) {
            FilledTonalIconButton(
                onClick = onClick,
                interactionSource = interactionSource,
                modifier = modifier.iconButton(enabled),
                enabled = enabled,
                shape = CircleShape,
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    SurfaceColor.PrimaryContainer,
                    TextColor.OnPrimaryContainer,
                    SurfaceColor.DisabledSurface,
                    TextColor.OnDisabledSurface,
                ),
            ) {
                Box(Modifier.size(Spacing.Spacing24)) {
                    icon()
                }
            }
        }
    }
}

/**
 * DHIS2  icon button with  icon slot.
 * used internally for DHIS2 component [IconButton] with
 * style [IconButtonStyle.OUTLINED].
 * @param enabled: controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon: the button icon content.
 * @param onClick: will be called when the user clicks the button.
 * @param interactionSource: manages the button's Interaction source.
 * @param scope: coroutine scope to be used.
 * @param modifier: optional modifier.
 */
@Composable
private fun OutlinedIconButton(
    enabled: Boolean = true,
    icon: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    scope: CoroutineScope = rememberCoroutineScope(),
    onClick: () -> Unit,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme()) {
        Box(
            Modifier.size(InternalSizeValues.Size48).clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                onClick = {
                    scope.launch {
                        onClick.invoke()
                        emitPressInteraction(scope, interactionSource)
                    }
                },
                indication = null,
            ),
        ) {
            OutlinedIconButton(
                onClick = onClick,
                interactionSource = interactionSource,
                modifier = modifier
                    .iconButton(enabled),
                enabled = enabled,
                shape = CircleShape,
                border = BorderStroke(
                    Border.Thin,
                    if (enabled) Outline.Dark else SurfaceColor.DisabledSurface,
                ),
                colors = IconButtonDefaults.outlinedIconButtonColors(
                    Color.Transparent,
                    TextColor.OnPrimaryContainer,
                ),
            ) {
                Box(Modifier.size(Spacing.Spacing24)) {
                    icon()
                }
            }
        }
    }
}

private fun emitPressInteraction(coroutineScope: CoroutineScope, interactionSource: MutableInteractionSource) {
    coroutineScope.launch {
        val pressInteraction = PressInteraction.Press(Offset.Zero)
        interactionSource.emit(pressInteraction)
        interactionSource.emit(PressInteraction.Release(pressInteraction))
    }
}

enum class IconButtonStyle {
    STANDARD, FILLED, TONAL, OUTLINED
}

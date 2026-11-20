package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color as ComposeColor

/**
 * DHIS2 colors that have no equivalent slot in Material3's [androidx.compose.material3.ColorScheme].
 * Read them through [DHIS2Theme.extendedColors] so that they follow the light/dark theme.
 */
@Immutable
data class DHIS2ExtendedColors(
    val warning: ComposeColor,
    val onWarning: ComposeColor,
    val warningContainer: ComposeColor,
    val onWarningContainer: ComposeColor,
    val errorContainerHighest: ComposeColor,
    val disabledSurface: ComposeColor,
    val disabledSurfaceBright: ComposeColor,
    val onDisabledSurface: ComposeColor,
    val onSurfaceLight: ComposeColor,
    val outlineMedium: ComposeColor,
    val customGreen: ComposeColor,
    val customPink: ComposeColor,
    val customYellow: ComposeColor,
    val customBrown: ComposeColor,
    val customOrange: ComposeColor,
    val customGray: ComposeColor,
    val customShadow: ComposeColor,
)

/** Light extended colors. Same values as the [SurfaceColor], [TextColor] and [Outline] constants. */
val DHIS2LightExtendedColors =
    DHIS2ExtendedColors(
        warning = SurfaceColor.Warning,
        onWarning = TextColor.OnWarning,
        warningContainer = SurfaceColor.WarningContainer,
        onWarningContainer = TextColor.OnWarningContainer,
        errorContainerHighest = SurfaceColor.ErrorContainerHighest,
        disabledSurface = SurfaceColor.DisabledSurface,
        disabledSurfaceBright = SurfaceColor.DisabledSurfaceBright,
        onDisabledSurface = TextColor.OnDisabledSurface,
        onSurfaceLight = TextColor.OnSurfaceLight,
        outlineMedium = Outline.Medium,
        customGreen = SurfaceColor.CustomGreen,
        customPink = SurfaceColor.CustomPink,
        customYellow = SurfaceColor.CustomYellow,
        customBrown = SurfaceColor.CustomBrown,
        customOrange = SurfaceColor.CustomOrange,
        customGray = SurfaceColor.CustomGray,
        customShadow = SurfaceColor.CustomShadow,
    )

/** Dark extended colors, to be used together with [DHIS2DarkColorScheme]. */
val DHIS2DarkExtendedColors =
    DHIS2ExtendedColors(
        warning = Color.Orange300,
        onWarning = Color.Orange1000,
        warningContainer = Color.Orange1000,
        onWarningContainer = Color.Orange100,
        errorContainerHighest = Color.Red700,
        disabledSurface = Color.Charcoal800,
        disabledSurfaceBright = Color.Charcoal900,
        onDisabledSurface = Color.Charcoal300,
        onSurfaceLight = Color.Charcoal200,
        outlineMedium = Color.Charcoal400,
        customGreen = Color.CustomGreen,
        customPink = Color.CustomDarkPink,
        customYellow = Color.CustomYellow,
        customBrown = Color.Red400,
        customOrange = Color.Orange600,
        customGray = Color.Charcoal600,
        customShadow = Color.BlackA60,
    )

internal val LocalDHIS2ExtendedColors = staticCompositionLocalOf { DHIS2LightExtendedColors }

/** True when a [DHIS2Theme] is set up above in the composition. */
internal val LocalDHIS2ThemeProvided = staticCompositionLocalOf { false }

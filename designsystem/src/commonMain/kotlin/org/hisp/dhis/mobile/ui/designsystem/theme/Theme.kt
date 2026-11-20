package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import org.hisp.dhis.mobile.designsystem.generated.resources.Res
import org.hisp.dhis.mobile.designsystem.generated.resources.roboto_medium
import org.hisp.dhis.mobile.designsystem.generated.resources.roboto_regular
import org.jetbrains.compose.resources.Font

internal val DHIS2LightColorScheme =
    lightColorScheme(
        primary = SurfaceColor.Primary,
        onPrimary = TextColor.OnPrimary,
        primaryContainer = SurfaceColor.PrimaryContainer,
        onPrimaryContainer = TextColor.OnPrimaryContainer,
        error = SurfaceColor.Error,
        onError = TextColor.OnError,
        errorContainer = SurfaceColor.ErrorContainer,
        onErrorContainer = TextColor.OnErrorContainer,
        background = SurfaceColor.SurfaceBright,
        onBackground = TextColor.OnSurface,
        surface = SurfaceColor.Surface,
        onSurface = TextColor.OnSurface,
        surfaceVariant = SurfaceColor.SurfaceDim,
        onSurfaceVariant = TextColor.OnSurfaceVariant,
        outline = Outline.Dark,
        outlineVariant = Outline.Light,
        scrim = SurfaceColor.Scrim,
        surfaceContainerLowest = SurfaceColor.ContainerLowest,
        surfaceContainer = SurfaceColor.Container,
        surfaceContainerLow = SurfaceColor.ContainerLow,
        surfaceContainerHigh = SurfaceColor.ContainerHigh,
        surfaceContainerHighest = SurfaceColor.ContainerHighest,
        surfaceDim = SurfaceColor.SurfaceDim,
        surfaceBright = SurfaceColor.SurfaceBright,
        inverseSurface = TextColor.OnSurface,
        inverseOnSurface = TextColor.OnPrimary,
    )

/**
 * Dark counterpart of [DHIS2LightColorScheme].
 * Accent colors move to lighter tones, containers to darker ones, and surfaces get lighter as their
 * elevation increases. Text/background pairs meet WCAG AA (4.5:1), except disabled content, which WCAG exempts.
 */
val DHIS2DarkColorScheme =
    darkColorScheme(
        primary = Color.Blue300,
        onPrimary = Color.Blue900,
        primaryContainer = Color.Blue800,
        onPrimaryContainer = Color.Blue150,
        inversePrimary = Color.Blue600,
        secondary = Color.Blue300,
        onSecondary = Color.Blue900,
        secondaryContainer = Color.Blue800,
        onSecondaryContainer = Color.Blue150,
        tertiary = Color.Blue300,
        onTertiary = Color.Blue900,
        error = Color.Red300,
        onError = Color.Red900,
        errorContainer = Color.Red800,
        onErrorContainer = Color.Red200,
        background = Color.Charcoal950,
        onBackground = Color.Ash300,
        // Same roles as the light scheme: surfaceBright is the page background, surface a slightly lighter fill.
        surface = Color.Charcoal900,
        onSurface = Color.Ash300,
        surfaceVariant = Color.Charcoal700,
        onSurfaceVariant = Color.Ash700,
        surfaceDim = Color.Charcoal800,
        surfaceBright = Color.Charcoal950,
        surfaceContainerLowest = Color.Charcoal1000,
        surfaceContainerLow = Color.Charcoal900,
        surfaceContainer = Color.Charcoal800,
        surfaceContainerHigh = Color.Charcoal700,
        surfaceContainerHighest = Color.Charcoal600,
        outline = Color.Charcoal300,
        outlineVariant = Color.Charcoal600,
        scrim = Color.BlackA60,
        inverseSurface = Color.Ash300,
        inverseOnSurface = Color.Charcoal800,
    )

/**
 * DHIS2 typography. It is only rebuilt when the font families change, not on every recomposition.
 */
@Composable
internal fun dhis2Typography(): Typography {
    val regular = FontFamily(Font(Res.font.roboto_regular))
    val medium = FontFamily(Font(Res.font.roboto_medium))
    return remember(regular, medium) {
        Typography(
            headlineLarge =
                TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = regular,
                    fontWeight = FontWeight.Normal,
                ),
            headlineMedium =
                TextStyle(
                    fontSize = 28.sp,
                    lineHeight = 36.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = regular,
                    fontWeight = FontWeight.Normal,
                ),
            headlineSmall =
                TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = regular,
                    fontWeight = FontWeight.Normal,
                ),
            titleLarge =
                TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = medium,
                    fontWeight = FontWeight.Medium,
                ),
            titleMedium =
                TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = medium,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.15.sp,
                ),
            titleSmall =
                TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = medium,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.1.sp,
                ),
            labelLarge =
                TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = medium,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.1.sp,
                ),
            labelMedium =
                TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = medium,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.5.sp,
                ),
            labelSmall =
                TextStyle(
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = medium,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.5.sp,
                ),
            bodyLarge =
                TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = regular,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.5.sp,
                ),
            bodyMedium =
                TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = regular,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.25.sp,
                ),
            bodySmall =
                TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                    fontFamily = regular,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.4.sp,
                ),
        )
    }
}

/**
 * DHIS2 theme for design system components.
 *
 * @param isDarkModeEnabled whether to use the dark colors. Dark mode is opt-in: pass `isSystemInDarkTheme()`
 * to follow the system setting once the app's own screens support it.
 * @param colorScheme Material3 color scheme. Defaults to the DHIS2 light or dark scheme.
 * @param extendedColors DHIS2 colors with no Material3 equivalent. Keep them consistent with [colorScheme].
 * @param typography Material3 typography. Defaults to the DHIS2 typography.
 * @param shapes Material3 shapes. Defaults to the DHIS2 shapes.
 */
@Composable
fun DHIS2Theme(
    isDarkModeEnabled: Boolean = false,
    colorScheme: ColorScheme = if (isDarkModeEnabled) DHIS2DarkColorScheme else DHIS2LightColorScheme,
    extendedColors: DHIS2ExtendedColors = if (isDarkModeEnabled) DHIS2DarkExtendedColors else DHIS2LightExtendedColors,
    typography: Typography = dhis2Typography(),
    shapes: Shapes = DHISShapes,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalDHIS2ThemeProvided provides true,
        LocalDHIS2ExtendedColors provides extendedColors,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = shapes,
            content = content,
        )
    }
}

/**
 * Access to the current DHIS2 theme colors from components.
 */
object DHIS2Theme {
    /**
     * The Material3 color scheme set by the closest [DHIS2Theme]. Outside a [DHIS2Theme] it falls back to
     * [DHIS2LightColorScheme] instead of Material's baseline colors, so components keep the DHIS2 look.
     */
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = if (LocalDHIS2ThemeProvided.current) MaterialTheme.colorScheme else DHIS2LightColorScheme

    /**
     * DHIS2 colors with no Material3 equivalent (warning, disabled, custom...).
     */
    val extendedColors: DHIS2ExtendedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDHIS2ExtendedColors.current
}

enum class DHIS2TextStyle {
    HEADLINE_LARGE,
    HEADLINE_MEDIUM,
    HEADLINE_SMALL,
    TITLE_LARGE,
    TITLE_MEDIUM,
    TITLE_SMALL,
    LABEL_LARGE,
    LABEL_MEDIUM,
    LABEL_SMALL,
    BODY_LARGE,
    BODY_MEDIUM,
    BODY_SMALL,
}

@Composable
fun getTextStyle(style: DHIS2TextStyle): TextStyle =
    when (style) {
        DHIS2TextStyle.HEADLINE_LARGE -> {
            MaterialTheme.typography.headlineLarge
        }

        DHIS2TextStyle.HEADLINE_MEDIUM -> {
            MaterialTheme.typography.headlineMedium
        }

        DHIS2TextStyle.HEADLINE_SMALL -> {
            MaterialTheme.typography.headlineSmall
        }

        DHIS2TextStyle.TITLE_LARGE -> {
            MaterialTheme.typography.titleLarge
        }

        DHIS2TextStyle.TITLE_MEDIUM -> {
            MaterialTheme.typography.titleMedium
        }

        DHIS2TextStyle.TITLE_SMALL -> {
            MaterialTheme.typography.titleSmall
        }

        DHIS2TextStyle.LABEL_LARGE -> {
            MaterialTheme.typography.labelLarge
        }

        DHIS2TextStyle.LABEL_MEDIUM -> {
            MaterialTheme.typography.labelMedium
        }

        DHIS2TextStyle.LABEL_SMALL -> {
            MaterialTheme.typography.labelSmall
        }

        DHIS2TextStyle.BODY_LARGE -> {
            MaterialTheme.typography.bodyLarge
        }

        DHIS2TextStyle.BODY_MEDIUM -> {
            MaterialTheme.typography.bodyMedium
        }

        DHIS2TextStyle.BODY_SMALL -> {
            MaterialTheme.typography.bodySmall
        }
    }

internal object DHIS2MarkdownTextStyles {
    val h1 =
        TextStyle(
            fontSize = 24.sp,
            lineHeight = 28.sp,
            lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
            fontWeight = FontWeight.Medium,
        )
    val h2 =
        TextStyle(
            fontSize = 22.sp,
            lineHeight = 26.sp,
            lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
            fontWeight = FontWeight.Medium,
        )
    val h3 =
        TextStyle(
            fontSize = 20.sp,
            lineHeight = 24.sp,
            lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
            fontWeight = FontWeight.Medium,
        )
    val h4 =
        TextStyle(
            fontSize = 18.sp,
            lineHeight = 22.sp,
            lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
            fontWeight = FontWeight.Medium,
        )
    val h5 =
        TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.sp,
            lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
            fontWeight = FontWeight.Medium,
        )
    val h6 =
        TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
            fontWeight = FontWeight.Medium,
        )
    val paragraph =
        TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
        )
    val paragraphLarge =
        TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
        )
}

internal object DHIS2SCustomTextStyles {
    val titleMediumBold =
        TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
            fontWeight = FontWeight.Bold,
            color = TextColor.OnSurface,
            letterSpacing = 0.15.sp,
        )

    val bodyLargeBold =
        TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
            fontWeight = FontWeight.Bold,
            color = TextColor.OnSurface,
            letterSpacing = 0.5.sp,
        )

    val clickableSupportingText =
        SpanStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp,
            textDecoration = TextDecoration.Underline,
        )

    val regularSupportingText =
        SpanStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.25.sp,
            textDecoration = TextDecoration.None,
        )

    val inputFieldHelper =
        SpanStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = TextColor.OnDisabledSurface,
        )

    val listCardKey =
        SpanStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = TextColor.OnSurfaceLight,
        )
    val listCardValue =
        SpanStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = TextColor.OnSurface,
        )
    val inputField =
        SpanStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        )
}

internal object LineHeightStyles {
    val CentreAlignNoTrim =
        LineHeightStyle(
            LineHeightStyle.Alignment.Center,
            LineHeightStyle.Trim.None,
        )
}

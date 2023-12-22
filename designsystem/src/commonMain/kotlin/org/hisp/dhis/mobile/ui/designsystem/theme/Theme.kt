package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import org.hisp.dhis.mobile.ui.designsystem.resource.provideFontResource

val DHIS2LightColorScheme = lightColorScheme(
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
)

@Composable
fun DHIS2Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DHIS2LightColorScheme,
        typography = Typography(
            headlineLarge = TextStyle(
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
            ),
            headlineMedium = TextStyle(
                fontSize = 28.sp,
                lineHeight = 36.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
            ),
            headlineSmall = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
            ),
            titleLarge = TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
            ),
            titleMedium = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.15.sp,
            ),

            titleSmall = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.1.sp,
            ),
            labelLarge = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.1.sp,
            ),
            labelMedium = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.5.sp,
            ),
            labelSmall = TextStyle(
                fontSize = 11.sp,
                lineHeight = 16.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.5.sp,
            ),
            bodyLarge = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
                letterSpacing = 0.5.sp,
            ),
            bodyMedium = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
                letterSpacing = 0.25.sp,
            ),
            bodySmall = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = provideFontResource(),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
                letterSpacing = 0.4.sp,
            ),
        ),
        shapes = DHISShapes,
        content = content,
    )
}

internal object DHIS2SCustomTextStyles {

    val titleMediumBold = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor.OnSurface,
        letterSpacing = 0.15.sp,
    )

    val bodyLargeBold = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor.OnSurface,
        letterSpacing = 0.5.sp,
    )

    val clickableSupportingText = SpanStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.1.sp,
        textDecoration = TextDecoration.Underline,
    )

    val regularSupportingText = SpanStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.25.sp,
        textDecoration = TextDecoration.None,
    )

    val inputFieldHelper = SpanStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = TextColor.OnDisabledSurface,
    )
    val inputField = SpanStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    )
}

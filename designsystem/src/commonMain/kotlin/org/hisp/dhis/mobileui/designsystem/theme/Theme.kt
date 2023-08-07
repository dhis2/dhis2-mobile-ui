package org.hisp.dhis.mobileui.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource
import org.hisp.dhis.mobileui.designsystem.library.MR

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
    scrim = SurfaceColor.Scrim
)

@Composable
fun DHIS2Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DHIS2LightColorScheme,
        typography = Typography(
            titleLarge = TextStyle(
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontFamily = fontFamilyResource(MR.fonts.cormorant.italic),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface
            ),
            titleMedium = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
//        fontFamily = FontFamily(MR..fonts.Roboto).asF,
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.15.sp
            ),
            titleSmall = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
//        fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.1.sp
            ),
            labelLarge = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
//        fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.1.sp
            ),
            labelMedium = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
//        fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.5.sp
            ),
            labelSmall = TextStyle(
                fontSize = 11.sp,
                lineHeight = 16.sp,
//        fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.5.sp
            ),
            bodyLarge = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
//        fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
                letterSpacing = 0.5.sp
            ),
            bodyMedium = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
//        fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
                letterSpacing = 0.25.sp
            ),
            bodySmall = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
//        fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
                letterSpacing = 0.4.sp
            )
        ),
        shapes = DHISShapes,
        content = content
    )
}

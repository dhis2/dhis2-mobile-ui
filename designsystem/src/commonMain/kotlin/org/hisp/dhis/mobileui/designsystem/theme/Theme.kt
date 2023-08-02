package org.hisp.dhis.mobileui.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

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
fun DHIS2Theme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    /*val colors =  if (useDarkTheme) DHIS2DarkColorScheme
    else DHIS2LightColorScheme*/
    val colors = DHIS2LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = DHISTypography,
        shapes = DHISShapes,
        content = content
    )
}

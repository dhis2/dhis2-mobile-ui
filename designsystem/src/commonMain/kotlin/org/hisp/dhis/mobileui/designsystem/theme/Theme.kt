package org.hisp.dhis.mobileui.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val DHIS2LightColorScheme = lightColorScheme(
    primary = Surface.Primary,
    onPrimary = Surface.OnPrimary,
    primaryContainer = Surface.PrimaryContainer,
    onPrimaryContainer = Surface.OnPrimaryContainer,
    /*secondary = Orange40,
    onSecondary = Color.White,
    secondaryContainer = Orange90,
    onSecondaryContainer = Orange10,
    tertiary = Blue40,
    onTertiary = Color.White,
    tertiaryContainer = Blue90,
    onTertiaryContainer = Blue10,*/
    error = Surface.Error,
    onError = Surface.OnError,
    errorContainer = Surface.ErrorContainer,
    onErrorContainer = Surface.OnErrorContainer,
//    background = DarkPurpleGray99,
//    onBackground = DarkPurpleGray10,
    surface = Surface.Surface,
    onSurface = Surface.OnSurface,
//    surfaceVariant = PurpleGray90,
    onSurfaceVariant = Surface.OnSurfaceVariant,
    inverseSurface = Surface.InverseSurface,
    inverseOnSurface = Surface.InverseOnSurface,
    outline = Surface.OutlineDark
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
        /*typography = typography,
        shapes = shapes,*/
        content = content
    )
}

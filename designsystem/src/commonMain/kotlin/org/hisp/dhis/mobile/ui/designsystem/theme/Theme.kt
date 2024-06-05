package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
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
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_regular"),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
            ),
            headlineMedium = TextStyle(
                fontSize = 28.sp,
                lineHeight = 36.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_regular"),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
            ),
            headlineSmall = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_regular"),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
            ),
            titleLarge = TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_medium"),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
            ),
            titleMedium = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_medium"),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.15.sp,
            ),

            titleSmall = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_medium"),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.1.sp,
            ),
            labelLarge = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_medium"),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.1.sp,
            ),
            labelMedium = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_medium"),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.5.sp,
            ),
            labelSmall = TextStyle(
                fontSize = 11.sp,
                lineHeight = 16.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_medium"),
                fontWeight = FontWeight.Medium,
                color = TextColor.OnSurface,
                letterSpacing = 0.5.sp,
            ),
            bodyLarge = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_regular"),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
                letterSpacing = 0.5.sp,
            ),
            bodyMedium = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_regular"),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
                letterSpacing = 0.25.sp,
            ),
            bodySmall = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
                fontFamily = provideFontResource("roboto_regular"),
                fontWeight = FontWeight.Normal,
                color = TextColor.OnSurface,
                letterSpacing = 0.4.sp,
            ),
        ),
        shapes = DHISShapes,
        content = content,
    )
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
fun getTextStyle(style: DHIS2TextStyle): TextStyle {
    return when (style) {
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
}

internal object DHIS2SCustomTextStyles {

    val titleMediumBold = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
        fontWeight = FontWeight.Bold,
        color = TextColor.OnSurface,
        letterSpacing = 0.15.sp,
    )

    val bodyLargeBold = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyles.CentreAlignNoTrim,
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

internal object LineHeightStyles {
    val CentreAlignNoTrim = LineHeightStyle(
        LineHeightStyle.Alignment.Center,
        LineHeightStyle.Trim.None,
    )
}

package org.hisp.dhis.mobileui.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val DHISTypography = Typography(
    titleLarge = TextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp,
//        fontFamily = FontFamily(Font(R.font.roboto)),
        fontWeight = FontWeight.Normal,
        color = TextColor.OnSurface
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
//        fontFamily = FontFamily(Font(R.font.roboto)),
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
)

package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes

internal val DHISShapes = Shapes(
    extraSmall = Shape.ExtraSmall,
    small = Shape.Small,
    medium = Shape.Medium,
    large = Shape.Large,
    extraLarge = Shape.ExtraLarge,
)

object Shape {
    val NoRounding = RoundedCornerShape(Radius.NoRounding)
    val ExtraSmall = RoundedCornerShape(Radius.XS)
    val ExtraSmallTop = RoundedCornerShape(
        topStart = CornerSize(Radius.XS),
        topEnd = CornerSize(Radius.XS),
        bottomEnd = CornerSize(Radius.NoRounding),
        bottomStart = CornerSize(Radius.NoRounding),
    )
    val Small = RoundedCornerShape(Radius.S)
    val SmallBottom = RoundedCornerShape(
        topStart = CornerSize(Radius.NoRounding),
        topEnd = CornerSize(Radius.NoRounding),
        bottomEnd = CornerSize(Radius.S),
        bottomStart = CornerSize(Radius.S),
    )
    val Medium = RoundedCornerShape(Radius.M)
    val Large = RoundedCornerShape(Radius.L)
    val LargeTop = RoundedCornerShape(
        topStart = CornerSize(Radius.L),
        topEnd = CornerSize(Radius.L),
        bottomEnd = CornerSize(Radius.NoRounding),
        bottomStart = CornerSize(Radius.NoRounding),
    )

    val ExtraLargeTop = RoundedCornerShape(
        topStart = CornerSize(Radius.XL),
        topEnd = CornerSize(Radius.XL),
        bottomEnd = CornerSize(Radius.NoRounding),
        bottomStart = CornerSize(Radius.NoRounding),
    )
    val ExtraLarge = RoundedCornerShape(Radius.XL)
    val Full = RoundedCornerShape(Radius.Full)
}

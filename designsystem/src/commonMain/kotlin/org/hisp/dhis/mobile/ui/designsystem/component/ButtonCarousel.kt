package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

@Composable
fun ButtonCarousel(
    items: List<CarouselButtonData>
) {

}

@Composable
fun CarouselButton(
    modifier: Modifier,
    onClick: () -> Unit,
    enabled: Boolean,
    textInput: String,
    icon: @Composable
    (() -> Unit)
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        FilledIconButton(
            onClick = onClick,
            modifier = modifier
                .size(InternalSizeValues.Size48)
                .padding(Spacing.Spacing4)
                .hoverPointerIcon(enabled),
            enabled = enabled,
            colors = IconButtonDefaults.iconButtonColors(Color.Transparent, TextColor.OnSurfaceVariant, Color.Transparent, TextColor.OnDisabledSurface),
        ) {
            Column(Modifier.size(Spacing.Spacing24)) {
                icon()
                ButtonText(
                    text = textInput,
                    textColor = TextColor.OnSurface
                )
            }
        }
    }
}

data class CarouselButtonData(val textInput: String, val iconResource: String)
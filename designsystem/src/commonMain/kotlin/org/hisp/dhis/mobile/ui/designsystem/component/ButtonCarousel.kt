package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign


@Composable
fun CarouselButton(
    modifier: Modifier = Modifier,
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
                .size(width = InternalSizeValues.Size64, height = InternalSizeValues.Size52)
                //.padding(Spacing.Spacing4)
                .hoverPointerIcon(enabled),
            enabled = enabled,
            colors = IconButtonDefaults.iconButtonColors(Color.Transparent, TextColor.OnSurfaceVariant, Color.Transparent, TextColor.OnDisabledSurface),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.size(Spacing.Spacing4))
                icon.invoke()
                Spacer(Modifier.size(Spacing.Spacing8))
                Text(textInput, textAlign = TextAlign.Center, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun ButtonCarousel(
    carouselButtonList: List<CarouselButtonData>,
    onClick: () -> Unit
) {
   FlowRowComponentsContainer(
       content = {
           carouselButtonList.map {
               CarouselButton(
                   enabled = it.enabled,
                   textInput = it.textInput,
                   icon = {
                       Icon(
                           modifier = Modifier.size(Spacing.Spacing24),
                           painter = provideDHIS2Icon(it.iconResource),
                           contentDescription = ""
                       )
                   },
                   onClick = onClick
               )
           }
       },
       spacing = Spacing.Spacing0
   )
}

data class CarouselButtonData(val enabled: Boolean, val textInput: String, val iconResource: String)
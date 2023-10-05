package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing16
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing24
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing64
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

@Composable
fun CarouselButton(
    buttonData: CarouselButtonData,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        Box(
            modifier = modifier
                .size(Spacing.Spacing80)
                .hoverPointerIcon(buttonData.enabled).clip(RoundedCornerShape(Radius.Full))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        buttonData.onClick.invoke()
                    },
                    indication = rememberRipple(
                        color = SurfaceColor.Primary,
                    ),
                    enabled = buttonData.enabled,
                ),
            contentAlignment = Alignment.Center,

        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.width(Spacing64),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,

                ) {
                    buttonData.icon.invoke()
                    Spacer(Modifier.size(Spacing.Spacing8))
                    CarouselButtonText(text = buttonData.text)
                }
            }
        }
    }
}

@Composable
fun ButtonCarousel(
    carouselButtonList: List<CarouselButtonData>,
) {
    Row(
        Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = Spacing24),
        horizontalArrangement = Arrangement.Center,
    ) {
        carouselButtonList.map {
                buttonData ->
            CarouselButton(buttonData)
            Spacer(Modifier.size(Spacing16))
        }
    }
}

data class CarouselButtonData(
    val onClick: () -> Unit,
    val enabled: Boolean,
    val text: String,
    val icon: @Composable
    (() -> Unit),
)

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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

/**
 * DHIS2 [CarouselButton].
 * @param buttonData: [CarouselButtonData] data model with all parameters  for component.
 * @param modifier: optional [Modifier].
 */
@Composable
fun CarouselButton(
    buttonData: CarouselButtonData,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme()) {
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

/**
 * Component used to display a horizontal list of [CarouselButton].
 * @param carouselButtonList: accepts a list of [CarouselButtonData].
 * to display all carousel buttons.
 * @param modifier: optional modifier.
 */
@Composable
fun ButtonCarousel(
    carouselButtonList: List<CarouselButtonData>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .fillMaxWidth()
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

/**
 * Data model used for DHIS2  [CarouselButton] component.
 * @param onClick: Will be called when the user clicks the button.
 * @param enabled: Controls the enabled state of the component. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param text: the text to be displayed.
 * @param icon: the icon to be used.
 */
data class CarouselButtonData(
    val onClick: () -> Unit,
    val enabled: Boolean,
    val text: String,
    val icon: @Composable
    (() -> Unit),
)

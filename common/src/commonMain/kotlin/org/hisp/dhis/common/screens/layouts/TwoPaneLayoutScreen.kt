package org.hisp.dhis.common.screens.layouts

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.layout.TwoPaneConfig
import org.hisp.dhis.mobile.ui.designsystem.component.layout.TwoPaneLayout

@Composable
fun TwoPaneLayoutScreen() {
    val infiniteTransition = rememberInfiniteTransition()

    val weight by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.9f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    TwoPaneLayout(
        modifier = Modifier.fillMaxSize(),
        paneConfig = TwoPaneConfig.Weight(weight),
        primaryPane = {
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Red),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Primary pane")
            }
        },
        secondaryPane = {
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Green),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Secondary pane")
            }
        },
    )
}

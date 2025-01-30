package org.hisp.dhis.mobile.ui.designsystem.component.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Two pane screen
 * Divides the screen in two parts for extended device screens
 * @param modifier: Modifier for styling
 * @param paneConfig: Pane configuration
 * @param primaryPane: Primary pane content
 * @param secondaryPane: Secondary pane content
 * */
@Composable
fun TwoPaneLayout(
    modifier: Modifier = Modifier,
    paneConfig: TwoPaneConfig,
    primaryPane: @Composable () -> Unit,
    secondaryPane: @Composable () -> Unit,
) {
    Row(modifier = modifier) {
        val (primaryPaneModifier, secondaryPaneModifier) = getPaneModifier(paneConfig)

        Box(
            modifier = primaryPaneModifier,
        ) {
            secondaryPane()
        }
        Box(
            modifier = secondaryPaneModifier,
        ) {
            primaryPane()
        }
    }
}

private fun RowScope.getPaneModifier(paneConfig: TwoPaneConfig): Pair<Modifier, Modifier> =
    when (paneConfig) {
        is TwoPaneConfig.PrimaryPaneFixedSize ->
            Pair(
                Modifier.fillMaxHeight().width(paneConfig.size),
                Modifier.fillMaxHeight().weight(1f),
            )

        is TwoPaneConfig.SecondaryPaneFixedSize ->
            Pair(
                Modifier.fillMaxHeight().weight(1f),
                Modifier.fillMaxHeight().width(paneConfig.size),
            )

        is TwoPaneConfig.Weight ->
            Pair(
                Modifier.fillMaxHeight().weight(paneConfig.primaryPaneWeight),
                Modifier.fillMaxHeight().weight(1f - paneConfig.primaryPaneWeight),
            )
    }

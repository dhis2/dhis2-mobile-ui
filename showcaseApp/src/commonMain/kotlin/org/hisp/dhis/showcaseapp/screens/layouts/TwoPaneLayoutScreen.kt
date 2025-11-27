package org.hisp.dhis.showcaseapp.screens.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.layout.TwoPaneConfig
import org.hisp.dhis.mobile.ui.designsystem.component.layout.TwoPaneLayout
import org.hisp.dhis.showcaseapp.screens.Groups

@Composable
fun TwoPaneLayoutScreen() {
    ColumnScreenContainer(Groups.TWO_PANE_LAYOUT.label) {
        ColumnComponentContainer(
            "Primary pane 70% of the screen",
        ) {
            TwoPaneLayout(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                paneConfig = TwoPaneConfig.Weight(0.7f),
                primaryPane = {
                    PrimaryPane()
                },
                secondaryPane = {
                    SecondaryPane()
                },
            )
        }
        ColumnComponentContainer(
            "Primary pane fixed 200 dp width",
        ) {
            TwoPaneLayout(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                paneConfig = TwoPaneConfig.PrimaryPaneFixedSize(300.dp),
                primaryPane = {
                    PrimaryPane()
                },
                secondaryPane = {
                    SecondaryPane()
                },
            )
        }
        ColumnComponentContainer(
            "Secondary pane fixed 100 dp width",
        ) {
            TwoPaneLayout(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                paneConfig = TwoPaneConfig.SecondaryPaneFixedSize(150.dp),
                primaryPane = {
                    PrimaryPane()
                },
                secondaryPane = {
                    SecondaryPane()
                },
            )
        }
    }
}

@Composable
private fun PrimaryPane() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerHighest),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Primary pane")
    }
}

@Composable
private fun SecondaryPane() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerHigh),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Secondary pane")
    }
}

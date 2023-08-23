package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.Legend
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun LegendScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
        modifier = Modifier.padding(Spacing.Spacing10)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Green Legend")
        Legend(SurfaceColor.CustomGreen, "Legend") {}

        Text("Orange Legend")
        Legend(SurfaceColor.CustomOrange, "Legend") {}

        Text("Pink Legend")
        Legend(
            SurfaceColor.CustomPink,
            "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus," +
                " aliquam. Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Maecenas dolor lacus,"
        ) {}
    }
}

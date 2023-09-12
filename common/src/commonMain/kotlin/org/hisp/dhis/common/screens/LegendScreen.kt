package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Legend
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun LegendScreen() {
    ColumnComponentContainer(title = "Legend") {
        SubTitle("Green Legend")
        Legend(LegendData(SurfaceColor.CustomGreen, "Legend"))
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Orange Legend")
        Legend(LegendData(TextColor.OnWarning, "Legend"))
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Pink Legend")
        Legend(
            LegendData(
                SurfaceColor.CustomPink,
                "Lorem ipsum dolor sit amet," +
                    " consectetur adipiscing elit. Maecenas dolor lacus," +
                    " aliquam. Lorem ipsum dolor sit amet," +
                    " consectetur adipiscing elit. Maecenas dolor lacus,",
            ),
        )
    }
}

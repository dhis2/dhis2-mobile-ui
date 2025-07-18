package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.cells

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.MANDATORY_ICON_TEST_TAG
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun MandatoryIcon(
    style: MandatoryIconStyle,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = Icons.Default.Emergency,
        contentDescription = "mandatory",
        modifier =
            modifier
                .testTag(MANDATORY_ICON_TEST_TAG)
                .padding(style.paddingValues)
                .size(style.size),
        tint = style.color,
    )
}

sealed class MandatoryIconStyle(
    val paddingValues: PaddingValues,
    val color: Color,
    val alignment: Alignment,
    val size: Dp,
) {
    data object FilledMandatoryStyle : MandatoryIconStyle(
        paddingValues = PaddingValues(Spacing.Spacing2),
        color = Outline.Light,
        alignment = Alignment.TopStart,
        size = Spacing.Spacing8,
    )

    data object DefaultMandatoryStyle : MandatoryIconStyle(
        paddingValues = PaddingValues(start = Spacing.Spacing8),
        color = SurfaceColor.Error,
        alignment = Alignment.CenterStart,
        size = Spacing.Spacing10,
    )

    data object ComponentMandatoryStyle : MandatoryIconStyle(
        paddingValues = PaddingValues(all = Spacing.Spacing2),
        color = SurfaceColor.Error,
        alignment = Alignment.TopStart,
        size = Spacing.Spacing8,
    )
}

package org.hisp.dhis.mobile.ui.designsystem.component.parameter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.FilledModel
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.PristineModel
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun ParameterSelectorItem(
    model: ParameterSelectorItemModel,
) {
    when (model) {
        is PristineModel -> {
            PristineParameterField(
                model = model,
            )
        }

        is FilledModel -> {
            model.inputField.invoke()
        }
    }
}

@Composable
private fun PristineParameterField(
    model: PristineModel,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.Spacing16),
    ) {
        IconButton(
            modifier = Modifier
                .padding(Spacing.Spacing0)
                .wrapContentWidth(),
            icon = {
                Icon(
                    imageVector = Icons.Outlined.AddCircleOutline,
                    contentDescription = "Icon Button",
                    tint = SurfaceColor.Primary
                )
            },
            onClick = model.onClick,
        )
        Text(
            modifier = Modifier
                .weight(1f),
            text = model.label,
            color = SurfaceColor.Primary,
        )

        Text(
            modifier = Modifier
                .wrapContentWidth(),
            text = model.helper,
            color = TextColor.OnDisabledSurface,
        )
    }
    Box(Modifier.height(Spacing.Spacing2)) {
        Divider(
            modifier = Modifier.align(Alignment.BottomStart),
            color = SurfaceColor.DisabledSurface,
            thickness = Border.Thin,
        )
    }
}

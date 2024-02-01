package org.hisp.dhis.mobile.ui.designsystem.component.parameter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.Status.CLOSED
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel.Status.OPENED
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon

@Composable
fun ParameterSelectorItem(
    model: ParameterSelectorItemModel,
) {
    var status by remember(model.status) { mutableStateOf(model.status) }

    AnimatedVisibility(
        visible = status == CLOSED,
        enter = fadeIn() + expandVertically(),
        exit = shrinkVertically() + fadeOut(),
    ) {
        EmptyParameterField(
            model = model,
        ) {
            status = OPENED
        }
    }

    AnimatedVisibility(
        visible = status == OPENED,
        enter = fadeIn() + expandVertically(),
        exit = shrinkVertically() + fadeOut(),
    ) {
        model.inputField.invoke()
    }
}

@Composable
private fun EmptyParameterField(
    model: ParameterSelectorItemModel,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .fillMaxWidth()
            .clickable(
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    color = SurfaceColor.Primary,
                ),
                onClick = onClick,
            )
            .hoverPointerIcon(true),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Spacing.Spacing8,
                    top = Spacing.Spacing8,
                    end = Spacing.Spacing16,
                    bottom = Spacing.Spacing8,
                ),
        ) {
            IconButton(
                modifier = Modifier
                    .padding(Spacing.Spacing8)
                    .wrapContentWidth(),
                icon = {
                    Icon(
                        imageVector = model.icon,
                        contentDescription = "Icon Button",
                        tint = SurfaceColor.Primary,
                    )
                },
                onClick = onClick,
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
}

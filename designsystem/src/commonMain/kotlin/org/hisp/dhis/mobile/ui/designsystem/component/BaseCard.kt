package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.internal.conditional
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing4
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.hoverPointerIcon
import org.hisp.dhis.mobile.ui.designsystem.theme.shadow

@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    showShadow: Boolean,
    onCardClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .conditional(showShadow, {
                shadow()
            })
            .background(color = TextColor.OnPrimary, shape = RoundedCornerShape(Radius.S))
            .clip(shape = RoundedCornerShape(Radius.S))
            .clickable(
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    color = SurfaceColor.Primary,
                ),
                onClick = onCardClick,
            )
            .hoverPointerIcon(true),
    ) {
        content()
    }
}

@Composable
fun ToggleInfoTextButton(
    sectionState: SectionState,
    shrinkLabelText: String,
    expandLabelText: String,
    onClick: (newSectionState: SectionState) -> Unit,
) {
    val expandText =
        mutableStateOf(if (sectionState == SectionState.OPEN) shrinkLabelText else expandLabelText)
    val interactionSource = remember { MutableInteractionSource() }

    val iconVector = getIconVector(sectionState)
    val expandTextColor = TextColor.OnSurfaceLight
    Row(
        Modifier
            .clip(RoundedCornerShape(Radius.M))
            .clickable(
                onClick = {
                    onClick(getSectionState(sectionState))
                },
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    color = SurfaceColor.Primary,
                ),
            )
            .padding(end = Spacing.Spacing2)
            .offset(x = (-3).dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = iconVector,
            contentDescription = "Button",
            tint = expandTextColor,
        )
        Text(
            text = expandText.value,
            color = expandTextColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = Spacing4),
        )
    }
}

private fun getSectionState(sectionState: SectionState): SectionState {
    return if (sectionState == SectionState.CLOSE) SectionState.OPEN else SectionState.CLOSE
}

private fun getIconVector(sectionState: SectionState): ImageVector {
    return if (sectionState == SectionState.CLOSE) {
        Icons.Filled.KeyboardArrowDown
    } else {
        Icons.Filled.KeyboardArrowUp
    }
}

@Composable
fun ColumnScope.ExpandShrinkAnimatedVisibility(
    expanded: Boolean,
    content:
    @Composable()
    (AnimatedVisibilityScope.() -> Unit),
) {
    AnimatedVisibility(
        visible = expanded,
        enter = expandVertically(expandFrom = Alignment.CenterVertically),
        exit = shrinkVertically(shrinkTowards = Alignment.CenterVertically),
        content = content,
    )
}

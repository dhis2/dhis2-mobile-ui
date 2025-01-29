package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.model.Tab
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun VerticalTabs(
    modifier: Modifier = Modifier,
    tabs: List<Tab>,
    initialSelectedTabIndex: Int = 0,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    backgroundShape: Shape = MaterialTheme.shapes.large,
    defaultTabHeight: Dp = 48.dp,
    defaultIndicatorWidth: Dp = 6.dp,
    onSectionSelected: (String) -> Unit,
) {
    var selectedSection by remember { mutableStateOf(initialSelectedTabIndex) }
    val indicatorVerticalOffset by animateDpAsState(
        targetValue = defaultTabHeight * selectedSection,
        label = "",
    )

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = backgroundShape,
            )
            .clip(backgroundShape),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            itemsIndexed(tabs) { index, tab ->
                val interactionSource = remember { MutableInteractionSource() }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(defaultTabHeight)
                        .clickable(
                            onClick = {
                                selectedSection = index
                                onSectionSelected(tab.id)
                            },
                            role = Role.Tab,
                            interactionSource = interactionSource,
                            indication = ripple(
                                color = MaterialTheme.colorScheme.primary,
                            ),
                        ),
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterStart)
                            .padding(horizontal = Spacing.Spacing16),
                        text = tab.label,
                        color = if (selectedSection == index) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
        Spacer(
            Modifier
                .align(Alignment.TopEnd)
                .offset(x = defaultIndicatorWidth / 2, y = indicatorVerticalOffset)
                .requiredHeight(defaultTabHeight)
                .requiredWidth(defaultIndicatorWidth)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(defaultIndicatorWidth / 2),
                ),
        )
    }
}

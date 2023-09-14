package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState.UNFOCUSED
import org.hisp.dhis.mobile.ui.designsystem.component.internal.VerticalGrid
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.DHISShapes
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.bottomShadow

/**
 * DHIS2 icon card matrix input component
 * @param title: Label of the component
 * @param data: List of [IconCardData] to show
 * @param layoutType: [IconCardLayoutType] to render list in, Matrix or Sequential. Default is [IconCardLayoutType.Matrix]
 * @param selectedData: Selected [IconCardData], renders selected UI around that item
 * @param state: [InputShellState]
 * @param supportingText: List of [SupportingTextData] that manages all the messages to be shown
 * @param legendData: [LegendData] to be render below the input shell
 * @param isRequired: Mark this input as marked
 * @param onSelectionChanged: Callback to receive new selected item
 */
@Composable
fun InputMatrix(
    title: String,
    data: List<IconCardData>,
    itemCount: Int = 2,
    selectedData: IconCardData? = null,
    modifier: Modifier = Modifier,
    state: InputShellState = UNFOCUSED,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    testTag: String = "",
    onSelectionChanged: (IconCardData) -> Unit,
) {
    InputShell(
        modifier = modifier.testTag("ICON_CARDS_INPUT_$testTag"),
        title = title,
        state = state,
        primaryButton = null,
        secondaryButton = null,
        isRequiredField = isRequired,
        inputField = {
            VerticalGrid(
                columns = itemCount,
                itemCount = data.size,
                horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
                verticalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
                modifier = Modifier.padding(
                    PaddingValues(
                        start = Spacing.Spacing0,
                        top = Spacing.Spacing8,
                        end = Spacing.Spacing12,
                        bottom = Spacing.Spacing8,
                    ),
                ).testTag("ICON_CARD_INPUT_MATRIX_$testTag"),
            ) { index ->
                val iconCardData = data[index]
                MatrixIconCard(
                    data = iconCardData,
                    onClick = {
                        onSelectionChanged(iconCardData)
                    },
                    selected = iconCardData == selectedData,
                    enabled = state != InputShellState.DISABLED,
                    modifier = Modifier.testTag("MATRIX_ICON_CARD_$testTag"),
                )
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = Modifier.testTag("ICON_CARDS_INPUT_" + testTag + "_SUPPORTING_TEXT"),
                )
            }
        },
        legend = {
            legendData?.let {
                Legend(legendData, Modifier.testTag("ICON_CARDS_INPUT_" + testTag + "_LEGEND"))
            }
        },
    )
}

/**
 * DHIS2 icon card sequential input component
 * @param title: Label of the component
 * @param data: List of [IconCardData] to show
 * @param layoutType: [IconCardLayoutType] to render list in, Matrix or Sequential. Default is [IconCardLayoutType.Matrix]
 * @param selectedData: Selected [IconCardData], renders selected UI around that item
 * @param state: [InputShellState]
 * @param supportingText: List of [SupportingTextData] that manages all the messages to be shown
 * @param legendData: [LegendData] to be render below the input shell
 * @param isRequired: Mark this input as marked
 * @param onSelectionChanged: Callback to receive new selected item
 */
@Composable
fun InputSequential(
    title: String,
    data: List<IconCardData>,
    selectedData: IconCardData? = null,
    modifier: Modifier = Modifier,
    state: InputShellState = UNFOCUSED,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    testTag: String = "",
    onSelectionChanged: (IconCardData) -> Unit,
) {
    InputShell(
        modifier = modifier.testTag("ICON_CARDS_INPUT_$testTag"),
        title = title,
        state = state,
        primaryButton = null,
        secondaryButton = null,
        isRequiredField = isRequired,
        inputField = {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
                modifier = Modifier.padding(
                    PaddingValues(
                        start = Spacing.Spacing0,
                        top = Spacing.Spacing8,
                        end = Spacing.Spacing12,
                        bottom = Spacing.Spacing8,
                    ),
                ).testTag("ICON_CARD_INPUT_SEQUENTIAL_$testTag"),
            ) {
                data.forEach { iconCardData ->
                    SequentialIconCard(
                        data = iconCardData,
                        onClick = {
                            onSelectionChanged(iconCardData)
                        },
                        selected = iconCardData == selectedData,
                        enabled = state != InputShellState.DISABLED,
                        modifier = Modifier.testTag("SEQUENTIAL_ICON_CARD_$testTag"),
                    )
                }
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = Modifier.testTag("ICON_CARDS_INPUT_" + testTag + "_SUPPORTING_TEXT"),
                )
            }
        },
        legend = {
            legendData?.let {
                Legend(legendData, Modifier.testTag("ICON_CARDS_INPUT_" + testTag + "_LEGEND"))
            }
        },
    )
}

@Composable
private fun MatrixIconCard(
    data: IconCardData,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    onClick: () -> Unit,
) {
    IconCard(
        enabled = enabled,
        selected = selected,
        modifier = modifier.fillMaxSize(),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .padding(Spacing.Spacing8)
                .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
        ) {
            val iconTint = iconTint(enabled, data)
            val iconBackgroundColor = iconBackgroundColor(enabled, selected)

            MetadataAvatar(
                icon = {
                    Icon(
                        painter = provideDHIS2Icon(data.iconRes),
                        contentDescription = null,
                    )
                },
                size = AvatarSize.Large,
                iconTint = iconTint,
                backgroundColor = iconBackgroundColor,
            )

            Text(
                data.label,
                style = iconCardTextStyle(selected),
                color = iconCardTextColor(enabled),
            )
        }
    }
}

@Composable
private fun SequentialIconCard(
    data: IconCardData,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    onClick: () -> Unit,
) {
    IconCard(
        enabled = enabled,
        selected = selected,
        modifier = modifier,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .padding(Spacing.Spacing8)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
        ) {
            val iconTint = iconTint(enabled, data)
            val iconBackgroundColor = iconBackgroundColor(enabled, selected)

            MetadataAvatar(
                icon = {
                    Icon(
                        painter = provideDHIS2Icon(data.iconRes),
                        contentDescription = null,
                    )
                },
                size = AvatarSize.Large,
                iconTint = iconTint,
                backgroundColor = iconBackgroundColor,
            )

            Text(
                data.label,
                style = iconCardTextStyle(selected),
                color = iconCardTextColor(enabled),
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun IconCard(
    enabled: Boolean = true,
    selected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val cardShadowModifier = if (!selected && enabled) {
        Modifier.clip(DHISShapes.medium)
            .bottomShadow(color = mutableStateOf(Outline.Light))
            .offset(y = (-2).dp)
    } else {
        Modifier
    }

    Card(
        modifier = modifier.then(cardShadowModifier),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) SurfaceColor.Container else SurfaceColor.SurfaceBright,
            disabledContainerColor = if (selected) SurfaceColor.DisabledSurface else SurfaceColor.DisabledSurfaceBright,
        ),
        border = BorderStroke(
            width = if (selected) Spacing.Spacing1 else Spacing.Spacing0,
            color = if (enabled) {
                if (selected) SurfaceColor.Primary else Color.Unspecified
            } else {
                if (selected) Outline.Medium else Color.Unspecified
            },
        ),
        enabled = enabled,
        onClick = onClick,
    ) {
        content()
    }
}

@ReadOnlyComposable
private fun iconBackgroundColor(
    enabled: Boolean,
    selected: Boolean,
) = if (enabled) {
    Color.Unspecified
} else {
    if (selected) SurfaceColor.SurfaceBright else SurfaceColor.DisabledSurface
}

@ReadOnlyComposable
private fun iconTint(
    enabled: Boolean,
    data: IconCardData,
) = if (enabled) {
    data.iconTint
} else {
    TextColor.OnDisabledSurface
}

@ReadOnlyComposable
private fun iconCardTextColor(enabled: Boolean) =
    if (enabled) TextColor.OnSurface else TextColor.OnDisabledSurface

@Composable
private fun iconCardTextStyle(selected: Boolean) =
    if (selected) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge

data class IconCardData(
    val uid: String,
    val label: String,
    val iconRes: String,
    val iconTint: Color,
)

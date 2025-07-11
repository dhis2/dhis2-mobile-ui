package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.internal.VerticalGrid
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 icon card matrix input component
 * @param title: Label of the component.
 * @param data: List of [ImageCardData] to show.
 * @param itemCount: number of items.
 * @param selectedData: Selected [ImageCardData], renders selected UI around that item.
 * @param state: [InputShellState].
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: List of [SupportingTextData] that manages all the messages to be shown.
 * @param legendData: [LegendData] to be render below the input shell.
 * @param isRequired: Mark this input as marked.
 * @param testTag: optional tag for ui testing purposes.
 * @param onSelectionChanged: Callback to receive new selected item.
 * @param painterFor: is a composable function which controls how to paint the load param.
 * @param modifier: optional modifier.
 */
@Composable
fun InputMatrix(
    title: String,
    data: List<ImageCardData>,
    itemCount: Int = 2,
    selectedData: ImageCardData? = null,
    modifier: Modifier = Modifier,
    state: InputShellState,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    testTag: String = "",
    onSelectionChanged: (ImageCardData) -> Unit,
    painterFor: Map<String, Painter>? = null,
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
                modifier =
                    Modifier
                        .padding(
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
                    painterFor = painterFor,
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
        inputStyle = inputStyle,
    )
}

@Composable
private fun MatrixIconCard(
    data: ImageCardData,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    onClick: () -> Unit,
    painterFor: Map<String, Painter>?,
) {
    IconCard(
        enabled = enabled,
        selected = selected,
        modifier = modifier.fillMaxSize(),
        onClick = onClick,
    ) {
        Column(
            modifier =
                Modifier
                    .padding(Spacing.Spacing8)
                    .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
        ) {
            val iconTint = iconTint(enabled, data)
            val iconBackgroundColor = iconBackgroundColor(enabled, selected)

            MetadataAvatar(
                icon = {
                    MetadataIcon(
                        imageCardData = data,
                        painter = painterFor?.get(data.uid),
                    )
                },
                size = MetadataAvatarSize.M(),
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
    data: ImageCardData,
) = when {
    !enabled -> TextColor.OnDisabledSurface
    data is ImageCardData.IconCardData && enabled -> data.iconTint
    else -> Color.Unspecified
}

@ReadOnlyComposable
private fun iconCardTextColor(enabled: Boolean) = if (enabled) TextColor.OnSurface else TextColor.OnDisabledSurface

@Composable
private fun iconCardTextStyle(selected: Boolean) =
    if (selected) DHIS2SCustomTextStyles.bodyLargeBold else MaterialTheme.typography.bodyLarge

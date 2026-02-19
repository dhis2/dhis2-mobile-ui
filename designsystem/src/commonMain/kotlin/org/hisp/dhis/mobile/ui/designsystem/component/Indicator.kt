package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.mikepenz.markdown.annotator.AnnotatorSettings
import com.mikepenz.markdown.annotator.annotatorSettings
import com.mikepenz.markdown.coil3.Coil3ImageTransformerImpl
import com.mikepenz.markdown.compose.LocalMarkdownColors
import com.mikepenz.markdown.compose.LocalMarkdownDimens
import com.mikepenz.markdown.compose.components.markdownComponents
import com.mikepenz.markdown.compose.elements.MarkdownDivider
import com.mikepenz.markdown.compose.elements.MarkdownTableHeader
import com.mikepenz.markdown.compose.elements.MarkdownTableRow
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.m3.markdownTypography
import com.mikepenz.markdown.model.markdownDimens
import com.mikepenz.markdown.model.markdownPadding
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2MarkdownTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.findChildOfType
import org.intellij.markdown.flavours.gfm.GFMElementTypes.HEADER
import org.intellij.markdown.flavours.gfm.GFMElementTypes.ROW
import org.intellij.markdown.flavours.gfm.GFMTokenTypes.CELL
import org.intellij.markdown.flavours.gfm.GFMTokenTypes.TABLE_SEPARATOR

/**
 * DHIS2 Indicator. Wraps compose [Row].
 * A component designed to display indicators, each featuring a key and a value,
 * is complemented by a color badge for visual distinction.
 * This configuration allows for clear and efficient presentation of important data points.
 * @param title: the header to be displayed.
 * @param content: description to be displayed.
 * @param modifier: optional modifier.
 * @param indicatorColor: indicator main color.
 * @param useMarkdown: enables title and content to be rendered as Markdown
 */
@Composable
fun Indicator(
    title: String,
    content: String? = null,
    modifier: Modifier = Modifier,
    indicatorColor: Color = SurfaceColor.ContainerHigh,
    useMarkdown: Boolean = false,
) {
    val backgroundColor =
        indicatorColor.copy(
            alpha = if (indicatorColor == SurfaceColor.ContainerHigh) 0.2f else 0.1f,
        )

    val markdownDimensions =
        markdownDimens(
            tableCellPadding = 8.dp,
        )

    val padding =
        markdownPadding(
            listItemTop = 0.dp,
            listItemBottom = 0.dp,
        )

    val components =
        markdownComponents(
            table = {
                CustomMarkdownTable(
                    it.content,
                    it.node,
                    it.typography.table,
                )
            },
        )

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Spacing.Spacing8))
                .background(Color.White),
    ) {
        Row(
            modifier =
                modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Spacing.Spacing8))
                    .background(backgroundColor)
                    .drawBehind {
                        drawRect(
                            color = indicatorColor.copy(),
                            topLeft = Offset(size.width - Spacing.Spacing16.toPx(), 0f),
                            size = Size(Spacing.Spacing16.toPx(), size.height),
                        )
                    },
        ) {
            BoxWithConstraints(
                modifier = Modifier.fillMaxWidth().padding(end = Spacing.Spacing16),
            ) {
                val halfWidth = (maxWidth - Spacing.Spacing16) / 2

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Spacing.Spacing16,
                                vertical = Spacing.Spacing8,
                            ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        if (useMarkdown) {
                            Markdown(
                                content = title,
                                imageTransformer = Coil3ImageTransformerImpl,
                                typography =
                                    markdownTypography(
                                        h1 = DHIS2MarkdownTextStyles.h1,
                                        h2 = DHIS2MarkdownTextStyles.h2,
                                        h3 = DHIS2MarkdownTextStyles.h3,
                                        h4 = DHIS2MarkdownTextStyles.h4,
                                        h5 = DHIS2MarkdownTextStyles.h5,
                                        h6 = DHIS2MarkdownTextStyles.h6,
                                        paragraph = DHIS2MarkdownTextStyles.paragraph,
                                    ),
                                dimens = markdownDimensions,
                                padding = padding,
                                modifier = Modifier.padding(0.dp),
                                components = components,
                            )
                        } else {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    if (content.isNullOrEmpty().not()) {
                        Spacer(Modifier.width(8.dp))
                        Box(
                            modifier =
                                Modifier
                                    .widthIn(max = halfWidth)
                                    .wrapContentWidth(Alignment.End),
                        ) {
                            if (useMarkdown) {
                                Markdown(
                                    content,
                                    imageTransformer = Coil3ImageTransformerImpl,
                                    typography =
                                        markdownTypography(
                                            h1 = DHIS2MarkdownTextStyles.h1,
                                            h2 = DHIS2MarkdownTextStyles.h2,
                                            h3 = DHIS2MarkdownTextStyles.h3,
                                            h4 = DHIS2MarkdownTextStyles.h4,
                                            h5 = DHIS2MarkdownTextStyles.h5,
                                            h6 = DHIS2MarkdownTextStyles.h6,
                                            paragraph = DHIS2MarkdownTextStyles.paragraphLarge,
                                        ),
                                    dimens = markdownDimensions,
                                    padding = padding,
                                    modifier = Modifier.padding(0.dp),
                                    components = components,
                                )
                            } else {
                                Text(
                                    text = content,
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomMarkdownTable(
    content: String,
    node: ASTNode,
    style: TextStyle,
    annotatorSettings: AnnotatorSettings = annotatorSettings(),
    headerBlock: @Composable (String, ASTNode, Dp, TextStyle) -> Unit = { content, header, tableWidth, style ->
        MarkdownTableHeader(
            content = content,
            header = header,
            tableWidth = tableWidth,
            style = style,
            annotatorSettings = annotatorSettings,
        )
    },
    rowBlock: @Composable (String, ASTNode, Dp, TextStyle) -> Unit = { content, header, tableWidth, style ->
        MarkdownTableRow(
            content = content,
            header = header,
            tableWidth = tableWidth,
            style = style,
            annotatorSettings = annotatorSettings,
        )
    },
) {
    val tableMaxWidth = LocalMarkdownDimens.current.tableMaxWidth
    val tableCellWidth = LocalMarkdownDimens.current.tableCellWidth
    val tableCornerSize = LocalMarkdownDimens.current.tableCornerSize

    val columnsCount = remember(node) { node.findChildOfType(HEADER)?.children?.count { it.type == CELL } ?: 0 }
    val tableWidth = columnsCount * tableCellWidth

    val backgroundCodeColor = LocalMarkdownColors.current.tableBackground
    val scrollState = rememberScrollState()
    BoxWithConstraints(
        modifier =
            Modifier
                .background(backgroundCodeColor, RoundedCornerShape(tableCornerSize))
                .widthIn(max = tableMaxWidth),
    ) {
        val scrollable = maxWidth <= tableWidth

        Column(
            modifier =
                if (scrollable) {
                    Modifier
                        .horizontalScroll(scrollState)
                        .requiredWidth(tableWidth)
                } else {
                    Modifier.fillMaxWidth()
                },
        ) {
            node.children.forEach {
                when (it.type) {
                    HEADER -> headerBlock(content, it, tableWidth, style)
                    ROW -> rowBlock(content, it, tableWidth, style)
                    TABLE_SEPARATOR -> MarkdownDivider()
                }
            }
            if (scrollable) {
                Spacer(Modifier.height(2.dp))
            }
        }
        if (scrollable) {
            HorizontalScrollBar(
                barWidth = maxWidth,
                barHeight = 2.dp,
                scrollState = scrollState,
                barModifier = Modifier.align(Alignment.BottomStart),
            )
        }
    }
}

@Composable
fun HorizontalScrollBar(
    barWidth: Dp,
    barHeight: Dp,
    barColor: Color = Color.LightGray.copy(alpha = 0.2f),
    thumbWidth: Dp = barWidth / 3,
    thumbColor: Color = Color.LightGray,
    scrollState: ScrollState,
    barModifier: Modifier = Modifier,
) {
    val density = LocalDensity.current

    val barWidthPx = with(density) { barWidth.toPx() }
    val thumbWidthPx = with(density) { thumbWidth.toPx() }

    val progress =
        if (scrollState.maxValue > 0) {
            scrollState.value / scrollState.maxValue.toFloat()
        } else {
            0f
        }

    val maxOffsetPx = barWidthPx - thumbWidthPx

    val offsetPx = maxOffsetPx * progress
    val offsetDp = with(density) { offsetPx.toDp() }

    Box(
        modifier =
            barModifier
                .width(barWidth)
                .height(barHeight)
                .background(barColor, shape = RoundedCornerShape(percent = 50)),
    ) {
        Box(
            modifier =
                Modifier
                    .offset(x = offsetDp)
                    .width(thumbWidth)
                    .fillMaxHeight()
                    .background(thumbColor, shape = RoundedCornerShape(percent = 50)),
        )
    }
}

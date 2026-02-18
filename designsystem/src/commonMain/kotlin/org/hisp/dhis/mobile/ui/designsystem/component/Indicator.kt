package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mikepenz.markdown.coil3.Coil3ImageTransformerImpl
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.m3.markdownTypography
import com.mikepenz.markdown.model.markdownDimens
import com.mikepenz.markdown.model.markdownPadding
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2MarkdownTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

/**
 * DHIS2 Indicator. Wraps compose [Row].
 * A component designed to display indicators, each featuring a key and a value,
 * is complemented by a color badge for visual distinction.
 * This configuration allows for clear and efficient presentation of important data points.
 * @param title: the header to be displayed.
 * @param content: description to be displayed.
 * @param modifier: optional modifier.
 * @param indicatorColor: indicator main color.
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

    DHIS2Theme {
        val markdownDimensions =
            markdownDimens(
                tableCellPadding = 8.dp,
            )

        val padding =
            markdownPadding(
                listItemTop = 0.dp,
                listItemBottom = 0.dp,
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
}

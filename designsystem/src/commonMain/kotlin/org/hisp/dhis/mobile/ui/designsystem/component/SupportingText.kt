package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.customRippleConfiguration

/**
 * DHIS2 SupportingText component, wraps Compose [ClickableText].
 * @param text Controls the text to be shown
 * @param state Controls state of the the component. WARNING and ERROR are available
 * apart from default
 * @param maxLines: max number of lines allowed.
 * @param showMoreText the text to show for expansion will be in English by default.
 * @param showLessText the text to be shown for shrinking, also English by default
 * @param modifier: optional modifier.
 * @param paddingValues: optional padding values to be used.
 * @param onNoInteraction: optional interaction source for expand text.
 * If the text to be shown has overflow the component will automatically add
 * the expand functionality.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportingText(
    text: String,
    state: SupportingTextState = SupportingTextState.DEFAULT,
    maxLines: Int = 1,
    showMoreText: String = provideStringResource("show_more"),
    showLessText: String = provideStringResource("show_less"),
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues =
        PaddingValues(
            start = Spacing.Spacing16,
            top = Spacing.Spacing4,
            end = Spacing.Spacing16,
            bottom = Spacing.Spacing4,
        ),
    onNoInteraction: (() -> Pair<MutableInteractionSource, () -> Unit>)? = null,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val nonClickableTextStyle =
        DHIS2SCustomTextStyles.regularSupportingText.copy(color = state.color)
    val clickableTextStyle =
        DHIS2SCustomTextStyles.clickableSupportingText.copy(color = state.color)
    var isClickable by remember { mutableStateOf(false) }
    var annotatedText by remember(text) {
        mutableStateOf(
            buildAnnotatedString {
                withStyle(style = nonClickableTextStyle) { append(text) }
            },
        )
    }
    val seeMoreTag = "SEE_MORE"
    val scope = rememberCoroutineScope()

    val textLayoutResult = textLayoutResultState.value
    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        val link =
            LinkAnnotation.Clickable(
                tag = seeMoreTag,
                styles =
                    TextLinkStyles(
                        clickableTextStyle.copy(fontWeight = FontWeight.SemiBold),
                    ),
            ) {
                val link = it as LinkAnnotation.Clickable
                if (link.tag == seeMoreTag) {
                    if (isClickable) {
                        isExpanded = !isExpanded
                    }
                } else {
                    onNoInteraction?.invoke()?.let { (interactionSource, action) ->
                        scope.launch {
                            action.invoke()
                            val pressInteraction = PressInteraction.Press(Offset.Zero)
                            interactionSource.emit(pressInteraction)
                            interactionSource.emit(PressInteraction.Release(pressInteraction))
                        }
                    }
                }
            }

        when {
            !isExpanded && textLayoutResult.hasVisualOverflow && textLayoutResult.lineCount >= maxLines -> {
                val lastCharIndex = textLayoutResult.getLineEnd(maxLines - 1)
                val adjustedText =
                    text
                        .substring(startIndex = 0, endIndex = lastCharIndex)
                        .dropLast(showLessText.length + 5)
                        .dropLastWhile { it == ' ' || it == '.' }

                val unexpandedText = "$adjustedText...   "

                annotatedText =
                    buildAnnotatedString {
                        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                            withStyle(style = nonClickableTextStyle) {
                                append(unexpandedText)
                            }
                            withLink(link) {
                                append(showMoreText)
                            }
                        }
                        addStringAnnotation(
                            tag = seeMoreTag,
                            annotation = "Show more tag",
                            start = unexpandedText.length,
                            end = unexpandedText.length + showMoreText.length,
                        )
                    }

                isClickable = true
            }

            isExpanded -> {
                annotatedText =
                    buildAnnotatedString {
                        val expandedText = "$text "
                        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                            withStyle(
                                style = nonClickableTextStyle,
                            ) {
                                append(expandedText)
                            }
                            withLink(link) {
                                append(showLessText)
                            }
                        }
                        addStringAnnotation(
                            tag = seeMoreTag,
                            annotation = "Show less tag",
                            start = expandedText.length,
                            end = expandedText.length + showLessText.length,
                        )
                    }
            }
        }
    }

    CompositionLocalProvider(LocalRippleConfiguration provides customRippleConfiguration()) {
        Text(
            text = annotatedText,
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            onTextLayout = { textLayoutResultState.value = it },
            modifier = modifier.padding(paddingValues).animateContentSize(),
        )
    }
}

enum class SupportingTextState(
    val color: Color,
    val backgroundColor: Color,
) {
    DEFAULT(TextColor.OnSurfaceVariant, SurfaceColor.Surface),
    WARNING(SurfaceColor.Warning, SurfaceColor.WarningContainer),
    ERROR(SurfaceColor.Error, SurfaceColor.ErrorContainer),
    INFO(TextColor.OnSurfaceVariant, SurfaceColor.Container),
}

data class SupportingTextData(
    val text: String,
    val state: SupportingTextState = SupportingTextState.DEFAULT,
)

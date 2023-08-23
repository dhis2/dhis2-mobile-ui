package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 SupportingText component, wraps Compose's [ClickableText].
 * @param text Controls the text to be shown
 * @param state Controls state of the the component. WARNING and ERROR are available
 * apart from default
 * @param showMoreText the text to show for expansion will be in English by default.
 * @param showLessText the text to be shown for shrinking, also English by default
 * If the text to be shown has overflow the component will automatically add
 * the expand functionality
 * Translations will be allowed and hardcoded strings will be removed in near future
 */
@Composable
fun SupportingText(
    text: String,
    state: SupportingTextState = SupportingTextState.DEFAULT,
    showMoreText: String = "Show more",
    showLessText: String = "Show less"
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val nonClickableTextStyle = getSpanSupportingTextStyle(state.color)
    val clickableTextStyle = getSpanSupportingTextStyle(state.color, true)
    var isClickable by remember { mutableStateOf(false) }
    var annotatedText by remember {
        mutableStateOf(
            buildAnnotatedString {
                withStyle(style = nonClickableTextStyle) { append(text) }
            }
        )
    }
    val seeMoreTag = "SEE_MORE"

    val textLayoutResult = textLayoutResultState.value
    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(0)
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showLessText.length + 5)
                    .dropLastWhile { it == ' ' || it == '.' }

                val unexpandedText = "$adjustedText...   "

                annotatedText = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                        withStyle(style = nonClickableTextStyle) {
                            append(unexpandedText)
                        }
                        withStyle(style = clickableTextStyle) {
                            append(showMoreText)
                        }
                    }
                    addStringAnnotation(
                        tag = seeMoreTag,
                        annotation = "Show more tag",
                        start = unexpandedText.length,
                        end = unexpandedText.length + showMoreText.length
                    )
                }

                isClickable = true
            }
            isExpanded -> {
                annotatedText = buildAnnotatedString {
                    val expandedText = "$text "
                    withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                        withStyle(
                            style = nonClickableTextStyle
                        ) {
                            append(expandedText)
                        }
                        withStyle(
                            style = clickableTextStyle
                        ) {
                            append(showLessText)
                        }
                    }
                    addStringAnnotation(
                        tag = seeMoreTag,
                        annotation = "Show less tag",
                        start = expandedText.length,
                        end = expandedText.length + showLessText.length
                    )
                }
            }
        }
    }

    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        ClickableText(
            text = annotatedText,
            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
            onTextLayout = { textLayoutResultState.value = it },
            onClick = {
                    position ->
                val annotations = annotatedText.getStringAnnotations(seeMoreTag, start = position, end = position)
                annotations.firstOrNull()?.let {
                    if (isClickable) {
                        isExpanded = !isExpanded
                    }
                }
            },
            modifier = Modifier.animateContentSize()
                .padding(start = Spacing.Spacing16, top = Spacing.Spacing4, end = Spacing.Spacing16)
        )
    }
}

enum class SupportingTextState(val color: Color) {
    DEFAULT(TextColor.OnSurfaceVariant),
    WARNING(TextColor.OnWarning),
    ERROR(SurfaceColor.Error)
}

package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

@Composable
fun SupportingText(text: String, state: SupportingTextState = SupportingTextState.DEFAULT) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    var finalText by remember { mutableStateOf(text) }
    var annotatedText by remember {
        mutableStateOf(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = state.color,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.25.sp
                    )
                ) {
                    append(text)
                }
            }
        )
    }

    val textLayoutResult = textLayoutResultState.value
    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                annotatedText = buildAnnotatedString {
                    finalText = "$text "
                    withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                        withStyle(
                            style = SpanStyle(
                                color = state.color,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                letterSpacing = 0.25.sp
                            )
                        ) {
                            append(finalText)
                        }
                        withStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                color = state.color,
                                letterSpacing = 0.1.sp
                            )
                        ) {
                            append("Show Less")
                        }
                    }
                }
            }
            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(0)
                val showMoreString = "Show More"
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length + 5)
                    .dropLastWhile { it == ' ' || it == '.' }

                finalText = "$adjustedText...   "

                annotatedText = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
                        withStyle(
                            style = SpanStyle(
                                color = state.color,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                letterSpacing = 0.25.sp
                            )
                        ) {
                            append(finalText)
                        }
                        withStyle(
                            SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                color = state.color,
                                letterSpacing = 0.1.sp
                            )
                        ) {
                            append("Show more")
                        }
                    }
                }

                isClickable = true
            }
        }
    }

    ClickableText(
        text = annotatedText,

        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
        onTextLayout = { textLayoutResultState.value = it },
        onClick = {
            if (isClickable) {
                isExpanded = !isExpanded
            }
        },
        modifier = Modifier.animateContentSize().padding(start = Spacing.Spacing16, top = Spacing.Spacing4, end = Spacing.Spacing16)
    )
}

enum class SupportingTextState(val color: Color) {
    DEFAULT(TextColor.OnSurfaceVariant),
    WARNING(TextColor.OnWarning),
    ERROR(SurfaceColor.Error)
}

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 Text with generic icon slot. Wraps Material 3 [Text]
 * used for the [Button] component
 * @param text The text to display within.
 * @param textColor The color of text to display within.
 * @param icon The icon content is optional.
 * Content will be centered and if there is an Icon
 * required Button component spacing will be applied
 */
@Composable
internal fun ButtonText(
    text: String,
    textColor: Color,
    icon: @Composable
    (() -> Unit)? = null
) {
    icon?.let {
        it.invoke()
        Spacer(Modifier.size(Spacing.Spacing8))
    }
    Text(text, color = textColor, textAlign = TextAlign.Center, style = MaterialTheme.typography.titleSmall)
}

internal fun getSpanSupportingTextStyle(color: Color = SupportingTextState.DEFAULT.color, isexpandText: Boolean = false): SpanStyle {
    return SpanStyle(
        color = color,
        fontSize = 14.sp,
        fontWeight = if (isexpandText) FontWeight.Medium else FontWeight.Normal,
        letterSpacing = if (isexpandText) 0.1.sp else 0.25.sp,
        textDecoration = if (isexpandText) TextDecoration.Underline else TextDecoration.None
    )
}

/**
 * DHIS2 Text with generic icon slot. Wraps Material 3 [Text]
 * used for the [InputShell] component
 * @param text The text to display within.
 * @param textColor The color of text to display within.
 * Content will be aligned to the left
 * required Button component spacing will be applied
 */
@Composable
internal fun InputShellLabelText(
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Text(text, modifier = modifier, color = textColor, style = MaterialTheme.typography.titleSmall, textAlign = TextAlign.Start)
}

class PrefixTransformation(val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return prefixFilter(text, prefix)
    }
}

class SuffixTransformer(val suffix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val result = text + AnnotatedString(
            " $suffix",
            spanStyle = SpanStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = TextColor.OnDisabledSurface
            )
        )

        val textWithSuffixMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset > text.length) return text.length
                return offset
            }
        }

        return TransformedText(result, textWithSuffixMapping)
    }
}

fun prefixFilter(number: AnnotatedString, prefix: String): TransformedText {
    val out = AnnotatedString(
        "$prefix ",
        spanStyle = SpanStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = TextColor.OnDisabledSurface
        )
    )
        .plus(
            AnnotatedString(
                number.text,
                spanStyle = SpanStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextColor.OnSurface
                )
            )
        )
    val prefixOffset = prefix.length + 1

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset + prefixOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset > number.length) return number.length
            return offset
        }
    }

    return TransformedText(out, numberOffsetTranslator)
}

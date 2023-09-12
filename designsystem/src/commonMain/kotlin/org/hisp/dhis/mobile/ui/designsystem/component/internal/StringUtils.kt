package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

internal class PrefixTransformation(private val prefix: String, val enabled: Boolean) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return prefixFilter(text, prefix, enabled = enabled)
    }
}

internal fun prefixFilter(text: AnnotatedString, prefix: String, enabled: Boolean = true): TransformedText {
    val out = AnnotatedString(
        "$prefix ",
        spanStyle = SpanStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = TextColor.OnDisabledSurface,
        ),
    )
        .plus(
            AnnotatedString(
                text.text,
                spanStyle = SpanStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = if (enabled) TextColor.OnSurface else TextColor.OnDisabledSurface,
                ),
            ),
        )
    val prefixOffset = prefix.length + 1

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset + prefixOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset > text.length) return text.length
            return offset
        }
    }

    return TransformedText(out, numberOffsetTranslator)
}

internal class SuffixTransformer(val suffix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val result = text + AnnotatedString(
            " $suffix",
            spanStyle = SpanStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = TextColor.OnDisabledSurface,
            ),
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

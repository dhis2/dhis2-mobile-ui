package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.theme.DHISTypographyTheme
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

internal class PrefixTransformation(private val prefix: String, val enabled: Boolean) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return prefixFilter(text, prefix, enabled = enabled)
    }
}

internal fun prefixFilter(text: AnnotatedString, prefix: String, enabled: Boolean = true): TransformedText {
    val out = AnnotatedString(
        "$prefix ",
        spanStyle = DHISTypographyTheme.inputFieldHelper,
    )
        .plus(
            AnnotatedString(
                text.text,
                spanStyle = DHISTypographyTheme.inputField.copy(color = if (enabled) TextColor.OnSurface else TextColor.OnDisabledSurface),
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
            spanStyle = DHISTypographyTheme.inputFieldHelper,
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

enum class RegExValidations(val regex: Regex) {
    BRITISH_DECIMAL_NOTATION("""^(?!\.)(?!.*-[^0-9])(?!(?:[^.]*\.){3})[-0-9]*(?:\.[0-9]*)?$""".toRegex()),
    EUROPEAN_DECIMAL_NOTATION("""^(?!.*,.+,|.*-.*-)[0-9,-]*$""".toRegex()),
    ONLY_INTEGERS("^-?(?!0)\\d*".toRegex()),
    SINGLE_LETTER("^[A-Z]\$".toRegex()),
    NEGATIVE_INTEGERS("^(?!0)\\d*".toRegex()),
    PERCENTAGE("^([1-9]|[1-9][0-9]|100)\$".toRegex()),
    POSITIVE_INTEGER("^(?!0)\\d*".toRegex()),
    POSITIVE_INTEGER_OR_ZERO("^(0|[1-9]\\d*)\$".toRegex()),
}

package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

internal class PrefixTransformation(private val prefix: String, val enabled: Boolean) :
    VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return prefixFilter(text, prefix, enabled = enabled)
    }
}

internal fun prefixFilter(
    text: AnnotatedString,
    prefix: String,
    enabled: Boolean = true,
): TransformedText {
    val out = AnnotatedString(
        "$prefix ",
        spanStyle = DHIS2SCustomTextStyles.inputFieldHelper,
    )
        .plus(
            AnnotatedString(
                text.text,
                spanStyle = DHIS2SCustomTextStyles.inputField.copy(color = if (enabled) TextColor.OnSurface else TextColor.OnDisabledSurface),
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
            spanStyle = DHIS2SCustomTextStyles.inputFieldHelper,
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

internal fun convertStringToTextFieldValue(inputDateString: String?): TextFieldValue {
    inputDateString?.let {
        return TextFieldValue(inputDateString, TextRange(inputDateString.length))
    }
    return TextFieldValue()
}

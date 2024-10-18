package org.hisp.dhis.mobile.ui.designsystem.component.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles

class TimeTransformation : DateTimeVisualTransformation {

    companion object {
        private const val SEPARATOR = ":"

        // Check the usages before modifying
        internal const val TIME_MASK = "HHMM"
    }

    override val maskLength: Int
        get() = TIME_MASK.length

    override fun filter(text: AnnotatedString): TransformedText {
        return timeFilter(text)
    }

    private fun timeFilter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length > TIME_MASK.length) text.text.substring(0..TIME_MASK.length) else text.text
        val output = buildAnnotatedString {
            for (i in TIME_MASK.indices) {
                val timeChar = trimmed.getOrNull(i)
                if (timeChar == null) {
                    append(AnnotatedString(TIME_MASK[i].toString(), DHIS2SCustomTextStyles.inputFieldHelper))
                } else {
                    append(trimmed[i])
                }

                if (i == 1) {
                    val separator = if (timeChar != null) {
                        SEPARATOR
                    } else {
                        AnnotatedString(SEPARATOR, DHIS2SCustomTextStyles.inputFieldHelper)
                    }
                    append(separator)
                }
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (trimmed.lastIndex >= 0) {
                    if (offset <= 1) return offset
                    return offset + 1
                } else {
                    return 0
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset > text.length) return text.length
                return offset
            }
        }

        return TransformedText(output, offsetMapping)
    }
}

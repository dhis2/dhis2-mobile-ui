package org.hisp.dhis.mobile.ui.designsystem.component.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles

class DateTransformation : DateTimeVisualTransformation {

    companion object {
        private const val SEPARATOR = "/"

        // Check the usages before modifying
        internal const val DATE_MASK = "DDMMYYYY"
    }

    override val maskLength: Int
        get() = DATE_MASK.length

    override fun filter(text: AnnotatedString): TransformedText {
        return dateFilter(text)
    }

    private fun dateFilter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length > DATE_MASK.length) text.text.substring(0..DATE_MASK.length) else text.text
        val output = buildAnnotatedString {
            for (i in DATE_MASK.indices) {
                val dateChar = trimmed.getOrNull(i)
                if (dateChar == null) {
                    append(AnnotatedString(DATE_MASK[i].toString(), DHIS2SCustomTextStyles.inputFieldHelper))
                } else {
                    append(trimmed[i])
                }

                if (i % 2 == 1 && i < 4) {
                    val separator = if (dateChar != null) {
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
                    if (offset <= 3) return offset + 1
                    if (offset <= 8) return offset + 2
                    return 10
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

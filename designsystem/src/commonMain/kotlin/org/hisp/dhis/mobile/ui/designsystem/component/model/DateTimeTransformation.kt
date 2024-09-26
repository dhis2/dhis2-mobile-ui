package org.hisp.dhis.mobile.ui.designsystem.component.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles

class DateTimeTransformation : DateTimeVisualTransformation {

    companion object {
        private const val SEPARATOR_DATE = "/"
        private const val SEPARATOR_TIME = ":"
        private const val SEPARATOR_DATE_TIME = " - "

        // Check the usages before modifying
        internal const val DATETIME_MASK = "DDMMYYYYhhmm"
    }

    override val maskLength: Int
        get() = DATETIME_MASK.length

    override fun filter(text: AnnotatedString): TransformedText {
        return dateTimeFilter(text)
    }

    private fun dateTimeFilter(text: AnnotatedString): TransformedText {
        val trimmed =
            if (text.text.length > DATETIME_MASK.length) text.text.substring(0..DATETIME_MASK.length) else text.text
        val output = buildAnnotatedString {
            for (i in DATETIME_MASK.indices) {
                val char = trimmed.getOrNull(i)
                if (char == null) {
                    append(
                        AnnotatedString(
                            DATETIME_MASK[i].toString(),
                            DHIS2SCustomTextStyles.inputFieldHelper,
                        ),
                    )
                } else {
                    append(trimmed[i])
                }

                when (i) {
                    1, 3 -> {
                        val separator = if (char == null) {
                            AnnotatedString(SEPARATOR_DATE, DHIS2SCustomTextStyles.inputFieldHelper)
                        } else {
                            SEPARATOR_DATE
                        }
                        append(separator)
                    }

                    7 -> {
                        val separator = if (char == null) {
                            AnnotatedString(
                                SEPARATOR_DATE_TIME,
                                DHIS2SCustomTextStyles.inputFieldHelper,
                            )
                        } else {
                            SEPARATOR_DATE_TIME
                        }
                        append(separator)
                    }

                    9 -> {
                        val separator = if (char == null) {
                            AnnotatedString(SEPARATOR_TIME, DHIS2SCustomTextStyles.inputFieldHelper)
                        } else {
                            SEPARATOR_TIME
                        }
                        append(separator)
                    }
                }
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (trimmed.lastIndex >= 0) {
                    if (offset <= 1) return offset
                    if (offset <= 3) return offset + 1
                    if (offset < 8) return offset + 2
                    if (offset == 8) return offset + 5
                    if (offset <= 11) return offset + 6
                    return 18
                } else {
                    0
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

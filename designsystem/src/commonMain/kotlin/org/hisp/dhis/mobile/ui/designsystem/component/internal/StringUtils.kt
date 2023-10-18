package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

internal class PrefixTransformation(private val prefix: String, val enabled: Boolean) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return prefixFilter(text, prefix, enabled = enabled)
    }
}

internal fun prefixFilter(text: AnnotatedString, prefix: String, enabled: Boolean = true): TransformedText {
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

interface DateTimeVisualTransformation : VisualTransformation {
    val maskLength: Int
}

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
        val trimmed = if (text.text.length > DATETIME_MASK.length) text.text.substring(0..DATETIME_MASK.length) else text.text
        val output = buildAnnotatedString {
            for (i in DATETIME_MASK.indices) {
                val char = trimmed.getOrNull(i)
                if (char == null) {
                    append(AnnotatedString(DATETIME_MASK[i].toString(), DHIS2SCustomTextStyles.inputFieldHelper))
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
                            AnnotatedString(SEPARATOR_DATE_TIME, DHIS2SCustomTextStyles.inputFieldHelper)
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

enum class RegExValidations(val regex: Regex) {
    BRITISH_DECIMAL_NOTATION("""^(?!\.)(?!.*-[^0-9])(?!(?:[^.]*\.){3})[-0-9]*(?:\.[0-9]*)?$""".toRegex()),
    EUROPEAN_DECIMAL_NOTATION("""^(?!.*,.+,|.*-.*-)[0-9,-]*$""".toRegex()),
    ONLY_INTEGERS("^-?(?!0)\\d*".toRegex()),
    SINGLE_LETTER("^[^\\d.,;!?\\s]$".toRegex()),
    NEGATIVE_INTEGERS("^(?!0)\\d*".toRegex()),
    PERCENTAGE("^([1-9]|[1-9][0-9]|100)\$".toRegex()),
    POSITIVE_INTEGER("^(?!0)\\d*".toRegex()),
    POSITIVE_INTEGER_OR_ZERO("^(0|[1-9]\\d*)\$".toRegex()),
    PHONE_NUMBER("^[+0-9-()]+$".toRegex()),
    LINK("((https?|ftp|smtp)://)?(www\\.)?[a-zA-Z0-9@:%._+~#=-]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_+.~#?&/=-]*)".toRegex()),
    EMAIL("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$".toRegex()),
    DATE_TIME("^[0-9]+$".toRegex()),
    UNIT_INTERVAL_BETWEEN_0_AND_1("^(0?(\\.\\d*?)?|1(\\.0+)?)\$".toRegex()),
}

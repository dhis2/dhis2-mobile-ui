package org.hisp.dhis.mobile.ui.designsystem.component.model

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

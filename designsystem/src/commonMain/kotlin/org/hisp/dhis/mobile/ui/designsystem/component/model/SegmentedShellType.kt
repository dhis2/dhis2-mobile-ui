package org.hisp.dhis.mobile.ui.designsystem.component.model

import androidx.compose.ui.text.input.KeyboardType

sealed class SegmentedShellType(
    open val obfuscated: Boolean = false,
) {
    data class Numeric(
        override val obfuscated: Boolean = false,
    ) : SegmentedShellType(obfuscated)

    data class Letters(
        override val obfuscated: Boolean = false,
    ) : SegmentedShellType(obfuscated)

    data class LettersAndNumbers(
        override val obfuscated: Boolean = false,
    ) : SegmentedShellType(obfuscated)

    fun isAllowed(chr: Char): Boolean =
        when (this) {
            is Letters -> chr.isLetter()
            is LettersAndNumbers -> chr.isLetterOrDigit()
            is Numeric -> chr.isDigit()
        }

    fun keyboardType() =
        when (this) {
            is Numeric -> KeyboardType.Number
            is Letters -> KeyboardType.Text
            is LettersAndNumbers -> KeyboardType.Text
        }
}

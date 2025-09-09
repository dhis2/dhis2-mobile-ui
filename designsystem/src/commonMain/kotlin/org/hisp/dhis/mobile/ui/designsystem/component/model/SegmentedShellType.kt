package org.hisp.dhis.mobile.ui.designsystem.component.model

import androidx.compose.ui.text.input.KeyboardType

sealed interface SegmentedShellType {
    data object Numeric : SegmentedShellType

    data object Letters : SegmentedShellType

    data object LettersAndNumbers : SegmentedShellType

    fun isAllowed(chr: Char): Boolean =
        when (this) {
            Letters -> chr.isLetter()
            LettersAndNumbers -> chr.isLetterOrDigit()
            Numeric -> chr.isDigit()
        }

    fun keyboardType() =
        when (this) {
            Numeric -> KeyboardType.Number
            Letters -> KeyboardType.Text
            LettersAndNumbers -> KeyboardType.Text
        }
}

package org.hisp.dhis.mobile.ui.designsystem.component.model

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
}

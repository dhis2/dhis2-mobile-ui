package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.text.AnnotatedString
import org.junit.Test

class DateTimeTransformationTest {

    private val transformation = DateTimeTransformation()

    @Test
    fun timeTransformationShouldWorkCorrectly() {
        val transformedText = transformation
            .filter(AnnotatedString("100219941240"))
            .text
            .toString()

        assert(transformedText == "10/02/1994 - 12:40")
    }

    @Test
    fun partialTimeTransformationShouldWorkCorrectly() {
        val transformedText = transformation
            .filter(AnnotatedString("100219"))
            .text
            .toString()

        assert(transformedText == "10/02/19YY - hh:mm")
    }

    @Test
    fun emptyTextShouldDisplayDateMask() {
        val transformedText = transformation
            .filter(AnnotatedString(""))
            .text
            .toString()

        assert(transformedText == "DD/MM/YYYY - hh:mm")
    }
}

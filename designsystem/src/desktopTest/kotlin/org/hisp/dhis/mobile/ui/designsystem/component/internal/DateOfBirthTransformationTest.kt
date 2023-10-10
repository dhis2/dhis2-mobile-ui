package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.text.AnnotatedString
import org.junit.Test

private const val DATE_OF_BIRTH_MASK = "DDMMYYYY"

class DateOfBirthTransformationTest {

    private val transformation = DateOfBirthTransformation(DATE_OF_BIRTH_MASK)

    @Test
    fun dateOfBirthTransformationShouldWorkCorrectly() {
        val transformedText = transformation
            .filter(AnnotatedString("10041985"))
            .text
            .toString()

        assert(transformedText == "10/04/1985")
    }

    @Test
    fun partialDateOfBirthTransformationShouldWorkCorrectly() {
        val transformedText = transformation
            .filter(AnnotatedString("100"))
            .text
            .toString()

        assert(transformedText == "10/0M/YYYY")
    }

    @Test
    fun emptyTextShouldDisplayDateOfBirthMask() {
        val transformedText = transformation
            .filter(AnnotatedString(""))
            .text
            .toString()

        assert(transformedText == "DD/MM/YYYY")
    }
}

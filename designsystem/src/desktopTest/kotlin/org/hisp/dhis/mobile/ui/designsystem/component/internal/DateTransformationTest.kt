package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.text.AnnotatedString
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTransformation
import org.junit.Test

class DateTransformationTest {
    private val transformation = DateTransformation()

    @Test
    fun dateTransformationShouldWorkCorrectly() {
        val transformedText =
            transformation
                .filter(AnnotatedString("10041985"))
                .text
                .toString()

        assert(transformedText == "10/04/1985")
    }

    @Test
    fun partialDateTransformationShouldWorkCorrectly() {
        val transformedText =
            transformation
                .filter(AnnotatedString("100"))
                .text
                .toString()

        assert(transformedText == "10/0M/YYYY")
    }

    @Test
    fun emptyTextShouldDisplayDateMask() {
        val transformedText =
            transformation
                .filter(AnnotatedString(""))
                .text
                .toString()

        assert(transformedText == "DD/MM/YYYY")
    }
}

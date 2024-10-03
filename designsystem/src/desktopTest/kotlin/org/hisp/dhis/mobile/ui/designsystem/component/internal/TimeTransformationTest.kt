package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.text.AnnotatedString
import org.hisp.dhis.mobile.ui.designsystem.component.model.TimeTransformation
import org.junit.Test

class TimeTransformationTest {

    private val transformation = TimeTransformation()

    @Test
    fun timeTransformationShouldWorkCorrectly() {
        val transformedText = transformation
            .filter(AnnotatedString("1045"))
            .text
            .toString()

        assert(transformedText == "10:45")
    }

    @Test
    fun partialTimeTransformationShouldWorkCorrectly() {
        val transformedText = transformation
            .filter(AnnotatedString("104"))
            .text
            .toString()

        assert(transformedText == "10:4M")
    }

    @Test
    fun emptyTextShouldDisplayDateMask() {
        val transformedText = transformation
            .filter(AnnotatedString(""))
            .text
            .toString()

        assert(transformedText == "HH:MM")
    }
}

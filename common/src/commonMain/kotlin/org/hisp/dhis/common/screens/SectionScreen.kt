package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.hisp.dhis.common.screens.previews.lorem
import org.hisp.dhis.common.screens.previews.lorem_medium
import org.hisp.dhis.common.screens.previews.lorem_short
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.Section
import org.hisp.dhis.mobile.ui.designsystem.component.SectionState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle

@Composable
fun SectionScreen() {
    ColumnComponentContainer(title = "Section Header") {
        SubTitle("Collapsible header")

        Column {
            Section(
                title = "Section title",
                description = null,
                completedFields = 2,
                totalFields = 3,
                state = SectionState.CLOSE,
                errorCount = 0,
                warningCount = 0,
                content = { TestingFields() },
                onNextSection = { },
            )
            Section(
                title = "Section title",
                description = lorem,
                completedFields = 2,
                totalFields = 3,
                state = SectionState.CLOSE,
                errorCount = 2,
                warningCount = 1,
                content = { TestingFields() },
                onNextSection = { },
            )
            Section(
                title = "Section title",
                description = lorem_short,
                completedFields = 2,
                totalFields = 3,
                state = SectionState.CLOSE,
                errorCount = 2,
                warningCount = 1,
                content = { TestingFields() },
                onNextSection = { },
            )
            Section(
                title = "Section title",
                description = lorem_medium,
                completedFields = 2,
                totalFields = 3,
                state = SectionState.CLOSE,
                errorCount = 0,
                warningCount = 0,
                content = { TestingFields() },
                onNextSection = { },
            )
            Section(
                title = "Section title Section title Section title Section title Section title",
                description = null,
                completedFields = 2,
                totalFields = 3,
                state = SectionState.CLOSE,
                errorCount = 0,
                warningCount = 0,
                content = { TestingFields() },
                onNextSection = { },
            )
        }

        SubTitle("Flat header")
        Section(
            title = "Section title",
            description = null,
            completedFields = 2,
            totalFields = 3,
            state = SectionState.FIXED,
            errorCount = 0,
            warningCount = 0,
            content = { TestingFields() },
            onNextSection = { },
        )
        Section(
            title = "Section title",
            description = lorem,
            completedFields = 2,
            totalFields = 3,
            state = SectionState.FIXED,
            errorCount = 2,
            warningCount = 1,
            content = { TestingFields() },
            onNextSection = { },
        )
        Section(
            title = "Section title",
            description = lorem_short,
            completedFields = 2,
            totalFields = 3,
            state = SectionState.FIXED,
            errorCount = 0,
            warningCount = 0,
            content = { TestingFields() },
            onNextSection = { },
        )
        Section(
            title = "Section title",
            description = lorem_medium,
            completedFields = 2,
            totalFields = 3,
            state = SectionState.FIXED,
            errorCount = 0,
            warningCount = 0,
            content = { TestingFields() },
            onNextSection = { },
        )
        Section(
            title = "Section title Section title Section title Section title Section title",
            description = lorem_medium,
            completedFields = 2,
            totalFields = 3,
            state = SectionState.FIXED,
            errorCount = 0,
            warningCount = 0,
            content = { TestingFields() },
            onNextSection = { },
        )
    }
}

@Composable
private fun TestingFields() {
    var inputValue1: String by rememberSaveable { mutableStateOf("Input") }
    var inputValue2: String by rememberSaveable { mutableStateOf("") }
    var inputValue3: String by rememberSaveable { mutableStateOf("") }
    InputText(
        title = "Label",
        inputText = inputValue1,
        onValueChanged = { inputValue1 = it ?: "" },
    )
    InputText(
        title = "Label",
        inputText = inputValue2,
        onValueChanged = { inputValue2 = it ?: "" },
    )
    InputText(
        title = "Label",
        inputText = inputValue3,
        onValueChanged = { inputValue3 = it ?: "" },
    )
}

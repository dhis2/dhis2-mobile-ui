package org.hisp.dhis.common.screens.others

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.common.screens.previews.lorem
import org.hisp.dhis.common.screens.previews.lorem_medium
import org.hisp.dhis.common.screens.previews.lorem_short
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.Section
import org.hisp.dhis.mobile.ui.designsystem.component.SectionState

@Composable
fun SectionScreen() {
    ColumnComponentContainer(title = "Section Header component") {
        ColumnComponentItemContainer("Collapsible header") {
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
                onSectionClick = { },
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
                onSectionClick = { },
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
                onSectionClick = { },
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
                onSectionClick = { },
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
                onSectionClick = { },
            )
        }

        ColumnComponentItemContainer("Flat header") {
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
                onSectionClick = { },
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
                onSectionClick = { },
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
                onSectionClick = { },
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
                onSectionClick = { },
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
                onSectionClick = { },
            )
        }
    }
}

@Composable
private fun TestingFields() {
    var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }
    var inputValue2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var inputValue3 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    InputText(
        title = "Label",
        inputTextFieldValue = inputValue1,
        onValueChanged = { inputValue1 = it ?: TextFieldValue() },
        state = InputShellState.UNFOCUSED,
    )
    InputText(
        title = "Label",
        inputTextFieldValue = inputValue2,
        onValueChanged = { inputValue2 = it ?: TextFieldValue() },
        state = InputShellState.UNFOCUSED,
    )
    InputText(
        title = "Label",
        inputTextFieldValue = inputValue3,
        onValueChanged = { inputValue3 = it ?: TextFieldValue() },
        state = InputShellState.UNFOCUSED,
    )
}

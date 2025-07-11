package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class SectionTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplaySectionInfoIfCollapsable() {
        rule.setContent {
            SectionHeader(
                title = "Section title",
                description = "description",
                completedFields = 0,
                totalFields = 1,
                sectionState = SectionState.CLOSE,
                errorCount = 1,
                warningCount = 1,
                onSectionClick = {
                },
            )
        }
        rule.onNodeWithTag(SectionTestTag.TITLE, useUnmergedTree = true).assertIsDisplayed()
        rule.onNodeWithTag(SectionTestTag.DESCRIPTION, useUnmergedTree = true).assertIsDisplayed()
        rule.onNodeWithTag(SectionTestTag.FIELD_PROGRESS, useUnmergedTree = true).assertIsDisplayed()
        rule.onNodeWithTag(SectionTestTag.STATE_LABEL, useUnmergedTree = true).assertIsDisplayed()
        rule.onNodeWithTag(SectionTestTag.ERROR_LABEL, useUnmergedTree = true).assertIsDisplayed()
        rule.onNodeWithTag(SectionTestTag.WARNING_LABEL, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun shouldNotDisplaySectionInfoIfCollapsable() {
        rule.setContent {
            SectionHeader(
                title = "Section title",
                description = null,
                completedFields = 0,
                totalFields = 1,
                sectionState = SectionState.CLOSE,
                errorCount = 0,
                warningCount = 0,
                onSectionClick = {
                },
            )
        }
        rule.onNodeWithTag(SectionTestTag.TITLE, useUnmergedTree = true).assertIsDisplayed()
        rule.onNodeWithTag(SectionTestTag.DESCRIPTION, useUnmergedTree = true).assertDoesNotExist()
        rule.onNodeWithTag(SectionTestTag.FIELD_PROGRESS, useUnmergedTree = true).assertIsDisplayed()
        rule.onNodeWithTag(SectionTestTag.STATE_LABEL, useUnmergedTree = true).assertIsDisplayed()
        rule.onNodeWithTag(SectionTestTag.ERROR_LABEL, useUnmergedTree = true).assertDoesNotExist()
        rule.onNodeWithTag(SectionTestTag.WARNING_LABEL, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun shouldNotDisplaySectionStateInfoIfFixed() {
        rule.setContent {
            SectionHeader(
                title = "Section title",
                description = null,
                completedFields = 0,
                totalFields = 1,
                sectionState = SectionState.FIXED,
                errorCount = 0,
                warningCount = 0,
                onSectionClick = {
                },
            )
        }
        rule.onNodeWithTag(SectionTestTag.STATE_LABEL, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun shouldChangeSectionStateAndShowContent() {
        rule.setContent {
            Section(
                title = "Section title",
                description = null,
                completedFields = 0,
                totalFields = 1,
                state = SectionState.CLOSE,
                errorCount = 0,
                warningCount = 0,
                onNextSection = {
                },
                onSectionClick = { },
                content = {
                },
            )
        }
        rule
            .onNode(
                hasTestTag(SectionTestTag.HEADER)
                    .and(SemanticsMatcher.expectValue(SectionSemantics.State, SectionState.CLOSE)),
            ).assertExists()
        rule.onNodeWithTag(SectionTestTag.CONTENT, useUnmergedTree = true).assertDoesNotExist()
        rule.onNodeWithTag(SectionTestTag.HEADER).performClick()
        rule
            .onNode(
                hasTestTag(SectionTestTag.HEADER)
                    .and(SemanticsMatcher.expectValue(SectionSemantics.State, SectionState.OPEN)),
            ).assertExists()
        rule.onNodeWithTag(SectionTestTag.CONTENT, useUnmergedTree = true).assertExists()
        rule.onNodeWithTag(SectionTestTag.HEADER).performClick()
        rule
            .onNode(
                hasTestTag(SectionTestTag.HEADER)
                    .and(SemanticsMatcher.expectValue(SectionSemantics.State, SectionState.CLOSE)),
            ).assertExists()
        rule.onNodeWithTag(SectionTestTag.CONTENT, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun shouldNotChangeStateIfFixed() {
        rule.setContent {
            Section(
                title = "Section title",
                description = null,
                completedFields = 0,
                totalFields = 1,
                state = SectionState.FIXED,
                errorCount = 0,
                warningCount = 0,
                onNextSection = {
                },
                onSectionClick = { },
                content = {
                },
            )
        }

        rule
            .onNode(
                hasTestTag(SectionTestTag.HEADER)
                    .and(SemanticsMatcher.expectValue(SectionSemantics.State, SectionState.FIXED)),
            ).assertExists()
        rule.onNodeWithTag(SectionTestTag.CONTENT, useUnmergedTree = true).assertExists()
        rule.onNodeWithTag(SectionTestTag.HEADER).performClick()
        rule
            .onNode(
                hasTestTag(SectionTestTag.HEADER)
                    .and(SemanticsMatcher.expectValue(SectionSemantics.State, SectionState.FIXED)),
            ).assertExists()
        rule.onNodeWithTag(SectionTestTag.CONTENT, useUnmergedTree = true).assertExists()
    }
}

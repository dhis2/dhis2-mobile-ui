package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class OrgBottomSheetTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun clearAllButtonShouldBeDisabledWhenThereAreNoSelectedItems() {
        rule.setContent {
            OrgBottomSheet(
                orgTreeItems =
                    listOf(
                        OrgTreeItem(
                            uid = "1",
                            label = "Item 1",
                            selected = false,
                        ),
                        OrgTreeItem(
                            uid = "2",
                            label = "Item 2",
                            selected = false,
                        ),
                    ),
                onDismiss = {
                    // no-op
                },
                onItemClick = {
                    // no-op
                },
                onItemSelected = { _, _ ->
                    // no-op
                },
                onClearAll = {
                    // no-op
                },
                onDone = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("CLEAR_ALL_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun clearAllButtonShouldBeEnabledWhenThereIsOneSelectedItems() {
        rule.setContent {
            OrgBottomSheet(
                orgTreeItems =
                    listOf(
                        OrgTreeItem(
                            uid = "1",
                            label = "Item 1",
                            selected = true,
                        ),
                        OrgTreeItem(
                            uid = "2",
                            label = "Item 2",
                            selected = false,
                        ),
                    ),
                onDismiss = {
                    // no-op
                },
                onItemClick = {
                    // no-op
                },
                onItemSelected = { _, _ ->
                    // no-op
                },
                onClearAll = {
                    // no-op
                },
                onDone = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("CLEAR_ALL_BUTTON").assertIsEnabled()
    }

    @Test
    fun showCheckBoxIfItemCanBeSelected() {
        rule.setContent {
            OrgBottomSheet(
                orgTreeItems =
                    listOf(
                        OrgTreeItem(
                            uid = "1",
                            label = "Item 1",
                            canBeSelected = true,
                        ),
                        OrgTreeItem(
                            uid = "2",
                            label = "Item 2",
                            canBeSelected = false,
                        ),
                    ),
                onDismiss = {
                    // no-op
                },
                onItemClick = {
                    // no-op
                },
                onItemSelected = { _, _ ->
                    // no-op
                },
                onClearAll = {
                    // no-op
                },
                onDone = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("ORG_TREE_ITEM_CHECKBOX_Item 1").assertExists()
        rule.onNodeWithTag("ORG_TREE_ITEM_CHECKBOX_Item 2").assertDoesNotExist()
    }

    @Test
    fun shouldHideClearButtonWhenOnClearAllMethodIsNotProvided() {
        rule.setContent {
            OrgBottomSheet(
                orgTreeItems =
                    listOf(
                        OrgTreeItem(
                            uid = "1",
                            label = "Item 1",
                            canBeSelected = true,
                        ),
                        OrgTreeItem(
                            uid = "2",
                            label = "Item 2",
                            canBeSelected = false,
                        ),
                    ),
                onDismiss = {
                    // no-op
                },
                onItemClick = {
                    // no-op
                },
                onItemSelected = { _, _ ->
                    // no-op
                },
                onDone = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("CLEAR_ALL_BUTTON").assertDoesNotExist()
    }
}

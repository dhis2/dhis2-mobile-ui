package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class AssistChipTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun assistChipShouldDisplayBadges() {
        rule.setContent {
            AssistChip(
                label = "Label",
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search icon",
                        modifier = Modifier
                            .size(AssistChipDefaults.IconSize),
                    )
                },
                onClick = {},
                badge = "2",
            )
        }

        rule.onNodeWithTag("ASSIST_CHIP_BADGE").assertExists()
    }
}

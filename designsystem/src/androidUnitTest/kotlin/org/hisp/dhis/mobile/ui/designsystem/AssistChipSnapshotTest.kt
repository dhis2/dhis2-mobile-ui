package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.AssistChip
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.junit.Rule
import org.junit.Test

class AssistChipSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchAssistChip() {
        paparazzi.snapshot {
            ColumnScreenContainer {
                AssistChip(
                    label = "Label",
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search icon",
                            modifier =
                                Modifier
                                    .size(AssistChipDefaults.IconSize),
                        )
                    },
                    onClick = {},
                    badge = "3",
                )
                AssistChip(
                    label = "Label",
                    onClick = {},
                    badge = "3",
                )
                AssistChip(
                    label = "Label",
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search icon",
                            modifier =
                                Modifier
                                    .size(AssistChipDefaults.IconSize),
                        )
                    },
                    onClick = {},
                )
                AssistChip(
                    label = "Label",
                    onClick = {},
                )
                AssistChip(
                    label = "Label",
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search icon",
                            modifier =
                                Modifier
                                    .size(AssistChipDefaults.IconSize),
                        )
                    },
                    enabled = false,
                    onClick = {},
                )
                AssistChip(
                    label = "Label",
                    onClick = {},
                    enabled = false,
                )
                AssistChip(
                    label = "Label",
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search icon",
                            modifier =
                                Modifier
                                    .size(AssistChipDefaults.IconSize),
                        )
                    },
                    enabled = false,
                    onClick = {},
                    badge = "3",
                )
                AssistChip(
                    label = "Label",
                    onClick = {},
                    enabled = false,
                    badge = "3",
                )
            }
        }
    }
}

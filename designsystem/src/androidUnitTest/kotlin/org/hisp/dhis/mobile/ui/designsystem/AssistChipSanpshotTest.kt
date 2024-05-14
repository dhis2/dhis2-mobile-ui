package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.AssistChip
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class AssistChipSanpshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchAssistChip() {
        paparazzi.snapshot {
            ColumnComponentContainer(modifier = Modifier.padding(Spacing.Spacing10)) {
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
                    badge = "3",
                )
                AssistChip(
                    label = "Label",
                    onClick = {},
                    badge = "3",
                )
            }
        }
    }
}

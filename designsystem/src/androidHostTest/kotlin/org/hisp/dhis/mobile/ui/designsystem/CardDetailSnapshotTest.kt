package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneEnabled
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.CardDetail
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.junit.Rule
import org.junit.Test

class CardDetailSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    val lorem =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dolor lacus, aliquam. Lorem ipsum dolor sit amet, consectetur adipiscing elit."

    @Composable
    private fun teiDetailList() =
        listOf(
            AdditionalInfoItem(key = "National ID:", value = "001-224-789"),
            AdditionalInfoItem(
                key = lorem,
                value = "+234 554",
                icon = {
                    Icon(
                        imageVector = Icons.Filled.PhoneEnabled,
                        contentDescription = "Icon Button",
                        tint = DHIS2Theme.colorScheme.primary,
                    )
                },
                color = DHIS2Theme.colorScheme.primary,
                action = {},
            ),
            AdditionalInfoItem(key = lorem, value = lorem),
            AdditionalInfoItem(
                key = "Enrolled in:",
                value = "PHC Blueberry",
                isConstantItem = true,
            ),
            AdditionalInfoItem(
                value = "PHC Blueberry",
                isConstantItem = true,
            ),
            AdditionalInfoItem(
                key = lorem,
                value =
                    "Tuberculosis, Nutrition, " +
                        "Assistance Program, Malaria Diagnosis",
                action = {},
                color = DHIS2Theme.colorScheme.primary,
                isConstantItem = true,
            ),
            AdditionalInfoItem(
                key = lorem,
                value =
                    "Tuberculosis, Nutrition, " +
                        "Assistance Program, Malaria Diagnosis",
                action = {},
                color = DHIS2Theme.colorScheme.primary,
            ),
        )

    @Test
    fun launchCardDetail() {
        paparazzi.themedSnapshots {
            ColumnScreenContainer(title = "Card Detail and Info Bar") {
                CardDetail(
                    avatar = null,
                    title = "Narayan Khanna, M, 32",
                    additionalInfoList = teiDetailList(),
                    expandLabelText = "Show more",
                    shrinkLabelText = "Show less",
                )
            }
        }
    }
}

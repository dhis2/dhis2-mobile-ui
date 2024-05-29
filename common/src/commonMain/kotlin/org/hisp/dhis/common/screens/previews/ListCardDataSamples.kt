package org.hisp.dhis.common.screens.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneEnabled
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.EventBusy
import androidx.compose.material.icons.outlined.SyncProblem
import androidx.compose.material3.Icon
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

val basicAdditionalItemList = listOf(
    AdditionalInfoItem(key = lorem, value = lorem),
    AdditionalInfoItem(key = "Date of birth:", value = "12/12/1933"),

)
val enrollmentCompletedList = listOf(
    AdditionalInfoItem(key = "Phone:", value = "+234 123 111 6785"),
    AdditionalInfoItem(key = "Date of birth:", value = "12/12/1945"),
    AdditionalInfoItem(key = lorem, value = lorem),
    AdditionalInfoItem(key = "Enrolled in:", value = "12/12/1945"),
    AdditionalInfoItem(
        key = lorem,
        value = "Tuberculosis, Nutrition \n" +
            "Assistance Program, Malaria Diagnosis",
    ),
    AdditionalInfoItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = "Icon Button",
                tint = AdditionalInfoItemColor.SUCCESS.color,
            )
        },
        value = "Enrollment completed",
        color = AdditionalInfoItemColor.SUCCESS.color,
        isConstantItem = true,
    ),

)

val teiDetailList = listOf(
    AdditionalInfoItem(key = "National ID:", value = "001-224-789"),
    AdditionalInfoItem(
        key = "Phone:",
        value = "+234 123 111 6785",
        icon = {
            Icon(
                imageVector = Icons.Filled.PhoneEnabled,
                contentDescription = "Icon Button",
                tint = SurfaceColor.Primary,
            )
        },
        color = SurfaceColor.Primary,
        action = {},
    ),
    AdditionalInfoItem(key = "Address:", value = "134 Main Road, Behind the temple, Citytown, Basil District, Granite State"),
    AdditionalInfoItem(key = "Enrolled in:", value = "PHC Blueberry"),
    AdditionalInfoItem(
        key = lorem,
        value = "Tuberculosis, Nutrition," +
            "Assistance Program, Malaria Diagnosis",
        action = {},
        color = SurfaceColor.Primary,
    ),
)
val fullItemList = listOf(
    AdditionalInfoItem(key = "Phone:", value = "+234 123 111 6785"),
    AdditionalInfoItem(key = "Date of birth:", value = "12/12/1945"),
    AdditionalInfoItem(key = "Enrolled in:", value = "12/12/1945"),
    AdditionalInfoItem(
        key = "Programs:",
        value = "Tuberculosis, Nutrition \n" +
            "Assistance Program, Malaria Diagnosis",
    ),
    AdditionalInfoItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "Icon Button",
                tint = TextColor.OnDisabledSurface,
            )
        },
        value = "Enrollment cancelled",
        color = TextColor.OnDisabledSurface,
        isConstantItem = true,
    ),
    AdditionalInfoItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.EventBusy,
                contentDescription = "Icon Button",
                tint = AdditionalInfoItemColor.ERROR.color,
            )
        },
        value = "Baby post natal overdue 6 days",
        color = AdditionalInfoItemColor.ERROR.color,
        isConstantItem = true,
    ),
    AdditionalInfoItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.SyncProblem,
                contentDescription = "Icon Button",
                tint = AdditionalInfoItemColor.WARNING.color,
            )
        },
        value = "Sync warning",
        color = AdditionalInfoItemColor.WARNING.color,
        isConstantItem = true,
    ),
)

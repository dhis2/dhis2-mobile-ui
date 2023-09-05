package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ProgressIndicator
import org.hisp.dhis.mobile.ui.designsystem.component.ProgressIndicatorType
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle

@Composable
internal fun ProgressScreen() {
    ColumnComponentContainer(title = "Progress Indicator") {
        SubTitle("Linear indicator")
        ProgressIndicator(
            progress = 0.25f,
            type = ProgressIndicatorType.LINEAR,
            hasError = false,
        )
        ProgressIndicator(
            progress = 0.50f,
            type = ProgressIndicatorType.LINEAR,
            hasError = false,
        )
        ProgressIndicator(
            progress = 0.75f,
            type = ProgressIndicatorType.LINEAR,
            hasError = false,
        )
        ProgressIndicator(
            progress = 0.45f,
            type = ProgressIndicatorType.LINEAR,
            hasError = false,
        )
        ProgressIndicator(
            type = ProgressIndicatorType.LINEAR,
            hasError = false,
        )
        SubTitle("Linear indicator - Error")
        ProgressIndicator(
            progress = 0.70f,
            type = ProgressIndicatorType.LINEAR,
            hasError = true,
        )
        ProgressIndicator(
            type = ProgressIndicatorType.LINEAR,
            hasError = true,
        )
        SubTitle("Circular indicator")
        RowComponentContainer {
            ProgressIndicator(
                progress = 0.25f,
                type = ProgressIndicatorType.CIRCULAR,
                hasError = false,
            )
            ProgressIndicator(
                progress = 0.50f,
                type = ProgressIndicatorType.CIRCULAR,
                hasError = false,
            )
            ProgressIndicator(
                progress = 0.75f,
                type = ProgressIndicatorType.CIRCULAR,
                hasError = false,
            )
            ProgressIndicator(
                progress = 1f,
                type = ProgressIndicatorType.CIRCULAR,
                hasError = false,
            )
            ProgressIndicator(
                progress = 0.6f,
                type = ProgressIndicatorType.CIRCULAR,
                hasError = false,
            )
        }
        ProgressIndicator(
            type = ProgressIndicatorType.CIRCULAR,
            hasError = false,
        )
        SubTitle("Circular indicator - Error")
        RowComponentContainer {
            ProgressIndicator(
                progress = 0.70f,
                type = ProgressIndicatorType.CIRCULAR,
                hasError = true,
            )
            ProgressIndicator(
                type = ProgressIndicatorType.CIRCULAR,
                hasError = true,
            )
        }
    }
}

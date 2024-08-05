package org.hisp.dhis.common.screens.others

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ProgressIndicator
import org.hisp.dhis.mobile.ui.designsystem.component.ProgressIndicatorType
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer

@Composable
internal fun ProgressScreen() {
    ColumnScreenContainer(title = "Progress Indicator component") {
        ColumnComponentContainer("Linear indicator") {
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
        }

        ColumnComponentContainer("Linear indicator - Error") {
            ProgressIndicator(
                progress = 0.70f,
                type = ProgressIndicatorType.LINEAR,
                hasError = true,
            )
            ProgressIndicator(
                type = ProgressIndicatorType.LINEAR,
                hasError = true,
            )
        }

        ColumnComponentContainer("Circular indicator") {
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
            RowComponentContainer {
            ProgressIndicator(
                modifier = Modifier.size(24.dp),
                progress = 0.25f,
                type = ProgressIndicatorType.CIRCULAR_SMALL,
                hasError = false,
            )
            ProgressIndicator(
                modifier = Modifier.size(24.dp),
                progress = 0.50f,
                type = ProgressIndicatorType.CIRCULAR_SMALL,
                hasError = false,
            )
            ProgressIndicator(
                modifier = Modifier.size(24.dp),
                progress = 0.75f,
                type = ProgressIndicatorType.CIRCULAR_SMALL,
                hasError = false,
            )
            ProgressIndicator(
                modifier = Modifier.size(24.dp),
                progress = 1f,
                type = ProgressIndicatorType.CIRCULAR_SMALL,
                hasError = false,
            )
            ProgressIndicator(
                modifier = Modifier.size(24.dp),
                progress = 0.6f,
                type = ProgressIndicatorType.CIRCULAR_SMALL,
                hasError = false,
            )
        }
        ProgressIndicator(
            type = ProgressIndicatorType.CIRCULAR,
            hasError = false,
        )
        }

        ColumnComponentContainer("Circular indicator - Error") {
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
        RowComponentContainer {
            ProgressIndicator(
                modifier = Modifier.size(24.dp),
                progress = 0.70f,
                type = ProgressIndicatorType.CIRCULAR_SMALL,
                hasError = true,
            )
            ProgressIndicator(
                modifier = Modifier.size(24.dp),
                type = ProgressIndicatorType.CIRCULAR_SMALL,
                hasError = true,
            )
        }
    }
}

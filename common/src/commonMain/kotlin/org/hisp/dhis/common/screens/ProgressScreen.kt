package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.component.ProgressIndicator
import org.hisp.dhis.mobileui.designsystem.component.ProgressIndicatorType

@Composable
internal fun ProgressScreen() {
    Column(modifier = Modifier.padding(10.dp).verticalScroll(rememberScrollState())) {
        ComponentContainer(
            title = "Linear indicator",
            content = {
                ProgressIndicator(
                    progress = 0.25f,
                    type = ProgressIndicatorType.LINEAR,
                    hasError = false
                )
                ProgressIndicator(
                    progress = 0.50f,
                    type = ProgressIndicatorType.LINEAR,
                    hasError = false
                )
                ProgressIndicator(
                    progress = 0.75f,
                    type = ProgressIndicatorType.LINEAR,
                    hasError = false
                )
                ProgressIndicator(
                    progress = 0.45f,
                    type = ProgressIndicatorType.LINEAR,
                    hasError = false
                )
                ProgressIndicator(
                    type = ProgressIndicatorType.LINEAR,
                    hasError = false
                )
            }
        )

        ComponentContainer(
            title = "Linear indicator - Error",
            content = {
                ProgressIndicator(
                    progress = 0.70f,
                    type = ProgressIndicatorType.LINEAR,
                    hasError = true
                )
                ProgressIndicator(
                    type = ProgressIndicatorType.LINEAR,
                    hasError = true
                )
            }
        )

        ComponentContainer(
            title = "Linear indicator",
            content = {
                Row(horizontalArrangement = spacedBy(16.dp)) {
                    ProgressIndicator(
                        progress = 0.25f,
                        type = ProgressIndicatorType.CIRCULAR,
                        hasError = false
                    )
                    ProgressIndicator(
                        progress = 0.50f,
                        type = ProgressIndicatorType.CIRCULAR,
                        hasError = false
                    )
                    ProgressIndicator(
                        progress = 0.75f,
                        type = ProgressIndicatorType.CIRCULAR,
                        hasError = false
                    )
                    ProgressIndicator(
                        progress = 1f,
                        type = ProgressIndicatorType.CIRCULAR,
                        hasError = false
                    )
                    ProgressIndicator(
                        progress = 0.6f,
                        type = ProgressIndicatorType.CIRCULAR,
                        hasError = false
                    )
                }

                ProgressIndicator(
                    type = ProgressIndicatorType.CIRCULAR,
                    hasError = false
                )
            }
        )

        ComponentContainer(
            title = "Linear indicator - Error",
            content = {
                Row(horizontalArrangement = spacedBy(16.dp)) {
                    ProgressIndicator(
                        progress = 0.70f,
                        type = ProgressIndicatorType.CIRCULAR,
                        hasError = true
                    )
                    ProgressIndicator(
                        type = ProgressIndicatorType.CIRCULAR,
                        hasError = true
                    )
                }
            }
        )
    }
}

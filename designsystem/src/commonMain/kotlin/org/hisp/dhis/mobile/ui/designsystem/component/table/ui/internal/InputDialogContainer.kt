package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

/* ## InputDialogContainer

The `InputDialogContainer` is a composable function designed to contain input dialogs with background styles that adhere to DHIS2 design guidelines. It provides a structured layout with a gradient background at the top and a consistent background color for the rest of the content.

### Usage

To use the `InputDialogContainer`, simply wrap your input dialog content within the `InputDialogContainer` composable. The container will automatically apply the necessary background styles.

### Example
*/

/**
 * The `InputDialogContainer` is a composable function designed to contain [InputDialog]
 * with background styles that adhere to DHIS2 design guidelines.
 * It provides a structured layout with a gradient background at the top
 * and a consistent background color for the rest of the content.
 */

@Composable
internal fun InputDialogContainer(
    content: @Composable () -> Unit,
    isExpanded: Boolean = false,
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier,
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Column(verticalArrangement = Arrangement.Bottom) {
                    Box(
                        modifier = Modifier
                            .height(Spacing.Spacing24)
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        SurfaceColor.Primary.copy(alpha = 0f),
                                        SurfaceColor.Primary.copy(alpha = 0.20f),
                                    ),
                                ),
                            ),

                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = SurfaceColor.Primary.copy(alpha = 0.20f),
                            ),
                    )
                }
            }
            Column(verticalArrangement = Arrangement.Bottom) {
                content.invoke()
                Spacer(Modifier.size(Spacing.Spacing10))
            }
            if (isExpanded) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .requiredHeight(Spacing.Spacing24)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    SurfaceColor.Primary.copy(alpha = 0.20f),
                                    SurfaceColor.Primary.copy(alpha = 0f),
                                ),
                            ),
                        ),
                )
            }
        }
    }
}

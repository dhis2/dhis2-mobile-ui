package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
internal fun InputDialogContainer(
    content: @Composable () -> Unit,
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
                                        Color.White,
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
        }
    }
}

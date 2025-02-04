package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.InputDialogContainer
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

/**
 * DHIS2 Expandable Input Shell is a component designed to contain an Input with additional details
 * that will be shown bellow the component.
 * To ensure that the keyboard does not cover the component in android devices [android:windowSoftInputMode="adjustResize"]
 * must be enabled at the activity level in the android manifest.
 * @param input: is a lambda that contains the input component.
 * @param details: is a lambda that contains the details component.
 * @param actionButton: is a lambda that contains the action button component.
 * @param onDismiss: is a lambda that contains the dismiss event.
 * @param modifier: allows a modifier to be passed externally.
 */
@Composable
fun InputDialog(
    input: @Composable () -> Unit,
    details: @Composable (ColumnScope.() -> Unit)? = null,
    actionButton: @Composable () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var detailShown by remember { mutableStateOf(false) }
    InputDialogContainer(
        content = {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = Spacing.Spacing10),
                verticalArrangement = Arrangement.Bottom,
                modifier = modifier,
            ) {
                item {
                    Spacer(Modifier.size(Spacing.Spacing24))
                    Box {
                        Card(
                            shape = Shape.Large,
                            modifier = Modifier.clip(Shape.Large),
                            content = {
                                Column(
                                    modifier = Modifier.background(color = SurfaceColor.SurfaceBright),
                                ) {
                                    input()
                                    if (detailShown) {
                                        Button(
                                            modifier = Modifier.padding(Spacing.Spacing16)
                                                .fillMaxWidth(),
                                            text = provideStringResource("hide_details"),
                                            style = ButtonStyle.TONAL,
                                            icon = {
                                                Icon(
                                                    imageVector = Icons.Filled.ArrowDropUp,
                                                    contentDescription = "Icon Button",
                                                )
                                            },
                                            onClick = {
                                                detailShown = !detailShown
                                            },
                                        )
                                    } else {
                                        Row(
                                            modifier = Modifier
                                                .padding(Spacing.Spacing16)
                                                .background(SurfaceColor.SurfaceBright),
                                        ) {
                                            IconButton(
                                                style = IconButtonStyle.TONAL,
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Outlined.KeyboardArrowDown,
                                                        contentDescription = "Icon Button",
                                                    )
                                                },
                                                onClick = {
                                                    onDismiss.invoke()
                                                },
                                            )
                                            Spacer(Modifier.size(Spacing.Spacing10))
                                            if (details != null) {
                                                IconButton(
                                                    style = IconButtonStyle.TONAL,
                                                    icon = {
                                                        Icon(
                                                            imageVector = Icons.Outlined.Info,
                                                            contentDescription = "Icon Button",
                                                        )
                                                    },
                                                    onClick = {
                                                        detailShown = !detailShown
                                                    },
                                                )
                                                Spacer(Modifier.size(Spacing.Spacing10))
                                            }
                                            actionButton()
                                        }
                                    }
                                }
                            },
                        )
                    }
                }
                if (detailShown) {
                    item {
                        AnimatedVisibility(
                            visible = detailShown,
                            enter = slideInVertically(
                                initialOffsetY = { it },
                                animationSpec = tween(durationMillis = 500),
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = { it },
                                animationSpec = tween(durationMillis = 500),
                            ),
                        ) {
                            details?.let {
                                Column {
                                    details()
                                    Spacer(Modifier.size(Spacing.Spacing10))
                                    Button(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        text = provideStringResource("hide_details"),
                                        style = ButtonStyle.TONAL,
                                        icon = {
                                            Icon(
                                                imageVector = Icons.Filled.ArrowDropUp,
                                                contentDescription = "Icon Button",
                                            )
                                        },
                                        onClick = {
                                            detailShown = !detailShown
                                        },
                                    )
                                    Spacer(Modifier.size(Spacing.Spacing10))
                                }
                            }
                        }
                    }
                }
            }
        },
    )
}

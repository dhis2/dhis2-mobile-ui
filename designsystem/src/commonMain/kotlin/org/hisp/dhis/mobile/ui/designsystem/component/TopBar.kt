package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    topBarData: TopBarData,
) {
    var showMenu by remember { mutableStateOf(false) }

    if (topBarData.type == TopBarType.DEFAULT) {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(text = topBarData.title)
            },
            navigationIcon = {
                topBarData.navigationIcon()
            },
            actions = {
                if (topBarData.primaryAction != null) {
                    IconButton(
                        onClick = { topBarData.primaryAction.onClick() },
                        icon = {
                            Icon(
                                imageVector = topBarData.primaryAction.icon,
                                contentDescription = "Primary Action",
                            )
                        },
                    )
                }
                if (topBarData.secondaryAction != null) {
                    IconButton(
                        onClick = { topBarData.secondaryAction.onClick() },
                        icon = {
                            Icon(
                                imageVector = topBarData.secondaryAction.icon,
                                contentDescription = "Primary Action",
                            )
                        },
                    )
                }

                if (topBarData.dropdownMenu != null) {
                    IconButton(
                        onClick = { showMenu = !showMenu },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More",
                            )
                        },
                    )
                    topBarData.dropdownMenu()
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = topBarData.color,
            ),
        )
    } else {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = topBarData.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            navigationIcon = {
                topBarData.navigationIcon()
            },
            actions = {
                if (topBarData.primaryAction != null) {
                    IconButton(
                        onClick = { topBarData.primaryAction.onClick() },
                        icon = {
                            Icon(
                                imageVector = topBarData.primaryAction.icon,
                                contentDescription = "Primary Action",
                            )
                        },
                    )
                }
                if (topBarData.secondaryAction != null) {
                    IconButton(
                        onClick = { topBarData.secondaryAction.onClick() },
                        icon = {
                            Icon(
                                imageVector = topBarData.secondaryAction.icon,
                                contentDescription = "Primary Action",
                            )
                        },
                    )
                }

                if (topBarData.dropdownMenu != null) {
                    IconButton(
                        onClick = { showMenu = !showMenu },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More",
                            )
                        },
                    )
                    topBarData.dropdownMenu()
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = topBarData.color,
            ),
        )
    }
}

@ExperimentalMaterial3Api
data class TopBarData(
    val type: TopBarType = TopBarType.DEFAULT,
    val title: String,
    val navigationIcon: @Composable () -> Unit,
    val primaryAction: TopBarAction? = null,
    val secondaryAction: TopBarAction? = null,
    val dropdownMenu: (@Composable () -> Unit)? = null,
    val color: Color,
)

data class TopBarAction(
    val icon: ImageVector,
    val onClick: () -> Unit,
)

enum class TopBarType {
    DEFAULT,
    CENTERED,
}

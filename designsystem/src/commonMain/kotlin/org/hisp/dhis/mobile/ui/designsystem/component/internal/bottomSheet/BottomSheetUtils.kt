package org.hisp.dhis.mobile.ui.designsystem.component.internal.bottomSheet

import androidx.compose.runtime.Composable

//TODO - hack to get navigation bar padding does not take into account IME padding (reflection)
//TODO - Should be remove when google publish https://issuetracker.google.com/issues/274872542

@Composable
expect fun rememberDimensionByName(name: String): Int
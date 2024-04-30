package org.hisp.dhis.mobile.ui.designsystem.component.internal.bottomSheet

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

// TODO - hack to get navigation bar padding does not take into account IME padding (reflection)
// TODO - Should be remove when google publish https://issuetracker.google.com/issues/274872542
@Composable
@SuppressLint("DiscouragedApi")
actual fun rememberDimensionByName(name: String): Int {
    val resources = LocalContext.current.resources
    return remember {
        val id = resources.getIdentifier(name, "dimen", "android")
        if (id == 0) 0 else resources.getDimensionPixelSize(id)
    }
}

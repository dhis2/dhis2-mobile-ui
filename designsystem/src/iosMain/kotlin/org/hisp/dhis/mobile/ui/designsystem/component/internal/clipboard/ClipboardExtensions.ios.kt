package org.hisp.dhis.mobile.ui.designsystem.component.internal.clipboard

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ClipEntry

@OptIn(ExperimentalComposeUiApi::class)
actual suspend fun ClipEntry.getText(): String? = getPlainText()

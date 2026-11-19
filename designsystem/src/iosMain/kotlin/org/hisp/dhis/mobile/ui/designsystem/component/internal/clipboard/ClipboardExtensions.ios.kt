package org.hisp.dhis.mobile.ui.designsystem.component.internal.clipboard

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ClipEntry

@OptIn(ExperimentalComposeUiApi::class)
internal actual suspend fun ClipEntry.getText(): String? = getPlainText()

@OptIn(ExperimentalComposeUiApi::class)
internal actual fun String.toClipEntry(): ClipEntry = ClipEntry.withPlainText(this)

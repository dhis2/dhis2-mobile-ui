package org.hisp.dhis.mobile.ui.designsystem.component.internal.clipboard

import androidx.compose.ui.platform.ClipEntry

internal expect suspend fun ClipEntry.getText(): String?

internal expect fun String.toClipEntry(): ClipEntry

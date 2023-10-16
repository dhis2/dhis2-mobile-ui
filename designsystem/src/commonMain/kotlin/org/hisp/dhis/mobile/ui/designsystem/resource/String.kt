package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable

@Composable
expect fun provideStringResource(id: String): String

@Composable
expect fun provideQuantityStringResource(id: String, quantity: Int): String

@Composable
expect fun resourceExists(resourceName: String, resourceType: String = "drawable"): Boolean

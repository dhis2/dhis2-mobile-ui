package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.DefaultValidator
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.Validator
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalTableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalValidator

@Composable
fun TableTheme(
    tableColors: TableColors?,
    tableDimensions: TableDimensions? = LocalTableDimensions.current,
    tableConfiguration: TableConfiguration? = LocalTableConfiguration.current,
    tableValidator: Validator? = null,
    tableResizeActions: TableResizeActions? = null,
    content: @Composable
    () -> Unit,
) {
    CompositionLocalProvider(
        LocalTableColors provides (tableColors ?: TableColors()),
        LocalTableDimensions provides (tableDimensions ?: TableDimensions()),
        LocalTableConfiguration provides (tableConfiguration ?: TableConfiguration()),
        LocalValidator provides (tableValidator ?: DefaultValidator()),
        LocalTableResizeActions provides (tableResizeActions ?: object : TableResizeActions {}),
    ) {
        MaterialTheme(
            content = content,
        )
    }
}

object TableTheme {
    val colors: TableColors
        @Composable
        get() = LocalTableColors.current
    val dimensions: TableDimensions
        @Composable
        get() = LocalTableDimensions.current
    val configuration: TableConfiguration
        @Composable
        get() = LocalTableConfiguration.current
    val tableSelection
        @Composable
        get() = LocalTableSelection.current
    val validator
        @Composable
        get() = LocalValidator.current
}

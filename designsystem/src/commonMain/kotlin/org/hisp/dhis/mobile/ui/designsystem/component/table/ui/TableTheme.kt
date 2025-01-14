package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.DefaultValidator
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.Validator
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalTableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalValidator

/**
 * Composable function to provide table-related theme settings to the content.
 *
 * @param tableColors The colors to be used in the table.
 * @param tableDimensions The dimensions to be used in the table.
 * @param tableConfiguration The configuration settings for the table.
 * @param tableValidator The validator to be used for table validation.
 * @param tableResizeActions The actions to be used for table resizing.
 * @param content The composable content to which the theme settings will be applied.
 */
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

/**
 * Object to access the current table theme settings.
 */
object TableTheme {

    /**
     * The current table colors.
     */
    val colors: TableColors
        @Composable
        get() = LocalTableColors.current

    /**
     * The current table dimensions.
     */
    val dimensions: TableDimensions
        @Composable
        get() = LocalTableDimensions.current

    /**
     * The current table configuration.
     */
    val configuration: TableConfiguration
        @Composable
        get() = LocalTableConfiguration.current

    /**
     * The current table selection.
     */
    val tableSelection
        @Composable
        get() = LocalTableSelection.current

    /**
     * The current table validator.
     */
    val validator
        @Composable
        get() = LocalValidator.current
}

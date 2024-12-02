package org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.TableDialogModel
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
//todo replace hardcoded dp values with spacing class
@Composable
fun TableDialog(
    dialogModel: TableDialogModel,
    onDismiss: () -> Unit,
    onPrimaryButtonClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(dialogModel.title)
        },
        text = {
            Text(dialogModel.message)
        },
        confirmButton = {
            Button(
                modifier = Modifier.padding(bottom = 16.dp, end = 16.dp),
                text = provideStringResource("accept"),
                onClick = onPrimaryButtonClick,
            )
        },
    )
}

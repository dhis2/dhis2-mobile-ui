package org.hisp.dhis.mobile.ui.designsystem.component.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardDescriptionModel
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel

@Stable
interface ListCardState {
    val title: ListCardTitleModel
    val description: ListCardDescriptionModel?
    val lastUpdated: String?
    val additionalInfoColumnState: AdditionalInfoColumnState
    val loading: Boolean
    val shadow: Boolean
    val expandable: Boolean
    val itemVerticalPadding: Dp?

    fun descriptionBasedOnLoading() = description?.takeIf { !loading }
    fun lastUpdateBasedOnLoading() = lastUpdated?.takeIf { !loading }
}

@Stable
internal class ListCardStateImpl(
    override val title: ListCardTitleModel,
    override val description: ListCardDescriptionModel?,
    override val lastUpdated: String?,
    override val additionalInfoColumnState: AdditionalInfoColumnState,
    override val loading: Boolean,
    override val shadow: Boolean,
    override val expandable: Boolean,
    override val itemVerticalPadding: Dp?,
) : ListCardState

@Composable
fun rememberListCardState(
    title: ListCardTitleModel,
    description: ListCardDescriptionModel? = null,
    lastUpdated: String? = null,
    additionalInfoColumnState: AdditionalInfoColumnState,
    loading: Boolean = false,
    shadow: Boolean = true,
    expandable: Boolean = false,
    itemVerticalPadding: Dp? = null,
): ListCardState = remember(
    description,
    itemVerticalPadding,
    loading,
    additionalInfoColumnState,
    lastUpdated,
) {
    ListCardStateImpl(
        title,
        description,
        lastUpdated,
        additionalInfoColumnState,
        loading,
        shadow,
        expandable,
        itemVerticalPadding,
    )
}

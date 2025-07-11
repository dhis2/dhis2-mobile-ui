package org.hisp.dhis.mobile.ui.designsystem.component.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.SectionState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource

@Stable
interface AdditionalInfoColumnState {
    val additionalInfoList: List<AdditionalInfoItem>
    val syncProgressItem: AdditionalInfoItem
    val expandLabelText: String
    val shrinkLabelText: String
    val minItemsToShow: Int
    val scrollableContent: Boolean

    fun expandableItemList(): List<AdditionalInfoItem>

    fun visibleExpandableItemList(): List<AdditionalInfoItem>

    fun hiddenExpandableItemList(): List<AdditionalInfoItem>

    fun constantItemList(): List<AdditionalInfoItem>

    fun showExpandableContent(): Boolean

    fun updateSectionState(sectionState: SectionState)

    fun isExpanded(): Boolean

    fun currentSectionState(): SectionState
}

@Stable
internal class AdditionalInfoColumnStateImpl(
    override val additionalInfoList: List<AdditionalInfoItem>,
    override val syncProgressItem: AdditionalInfoItem,
    override val expandLabelText: String,
    override val shrinkLabelText: String,
    override val minItemsToShow: Int,
    override val scrollableContent: Boolean,
) : AdditionalInfoColumnState {
    private var expandableItemList = mutableListOf<AdditionalInfoItem>()
    private var constantItemList = mutableListOf<AdditionalInfoItem>()
    private var sectionState by mutableStateOf(SectionState.CLOSE)

    init {
        additionalInfoList.forEach { item ->
            if (item.isConstantItem) {
                constantItemList.add(item)
            } else {
                expandableItemList.add(item)
            }
        }
    }

    override fun expandableItemList() = expandableItemList

    override fun visibleExpandableItemList() = expandableItemList.take(minItemsToShow)

    override fun hiddenExpandableItemList() = expandableItemList.drop(minItemsToShow)

    override fun constantItemList(): List<AdditionalInfoItem> = constantItemList

    override fun showExpandableContent(): Boolean = expandableItemList.size > minItemsToShow

    override fun updateSectionState(sectionState: SectionState) {
        this.sectionState = sectionState
    }

    override fun isExpanded() = this.sectionState != SectionState.CLOSE

    override fun currentSectionState() = sectionState
}

@Composable
fun rememberAdditionalInfoColumnState(
    additionalInfoList: List<AdditionalInfoItem>,
    syncProgressItem: AdditionalInfoItem,
    expandLabelText: String = provideStringResource("show_more"),
    shrinkLabelText: String = provideStringResource("show_less"),
    minItemsToShow: Int = 3,
    scrollableContent: Boolean = false,
): AdditionalInfoColumnState =
    remember(additionalInfoList, syncProgressItem, minItemsToShow, scrollableContent) {
        AdditionalInfoColumnStateImpl(
            additionalInfoList,
            syncProgressItem,
            expandLabelText,
            shrinkLabelText,
            minItemsToShow,
            scrollableContent,
        )
    }

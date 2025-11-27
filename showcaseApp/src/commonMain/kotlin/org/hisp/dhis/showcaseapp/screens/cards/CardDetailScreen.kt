package org.hisp.dhis.showcaseapp.screens.cards

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.CardDetail
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.showcaseapp.screens.previews.teiDetailList

@Composable
fun CardDetailScreen() {
    ColumnScreenContainer(title = Cards.CARD_DETAIL.label) {
        ColumnComponentContainer("Card Detail") {
            CardDetail(
                avatar = null,
                title = "Narayan Khanna, M, 32",
                additionalInfoList = teiDetailList,
                expandLabelText = "Show more",
                shrinkLabelText = "Show less",
            )
        }
    }
}

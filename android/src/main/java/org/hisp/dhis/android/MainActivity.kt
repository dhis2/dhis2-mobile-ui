package org.hisp.dhis.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.android.showkase.models.Showkase
import org.hisp.dhis.common.designsystem.theme.DHIS2Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DHIS2Theme {
                startActivity(Showkase.getBrowserIntent(this))
            }
        }
    }
}

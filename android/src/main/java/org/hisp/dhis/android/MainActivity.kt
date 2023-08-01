package org.hisp.dhis.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import org.hisp.dhis.common.App
import org.hisp.dhis.common.designsystem.theme.DHIS2Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DHIS2Theme {
                App()
            }
        }
    }
}

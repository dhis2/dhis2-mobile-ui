package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ExampleTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun myTest() {
        rule.setContent {
            var text by remember { mutableStateOf("Hello") }
            Text(
                text = text,
                modifier = Modifier.testTag("text"),
            )
            androidx.compose.material3.Button(
                onClick = { text = "Compose" },
                modifier = Modifier.testTag("button"),
            ) {
                Text("Click me")
            }
        }

        rule.onNodeWithTag("text").assertTextEquals("Hello")
        rule.onNodeWithTag("button").performClick()
        rule.onNodeWithTag("text").assertTextEquals("Compose")
    }
}

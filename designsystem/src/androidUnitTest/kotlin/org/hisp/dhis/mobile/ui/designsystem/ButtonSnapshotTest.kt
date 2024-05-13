package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColorStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class ButtonSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchButtonSnapshot() {
        paparazzi.snapshot {
            ColumnComponentContainer() {
                Title("Buttons")
                SubTitle("Filled")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.FILLED, onClick = {})
                    Button(text = "Label", style = ButtonStyle.FILLED, enabled = false, onClick = {})
                }
                RowComponentContainer {
                    Button(text = "Label", style = ButtonStyle.FILLED, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.FILLED, enabled = false, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }

                Spacer(Modifier.size(Spacing.Spacing18))
                SubTitle("Outlined")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.OUTLINED, onClick = {})
                    Button(text = "Label", style = ButtonStyle.OUTLINED, enabled = false, onClick = {})
                }
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.OUTLINED, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.OUTLINED, enabled = false, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }
                Spacer(Modifier.size(Spacing.Spacing18))

                SubTitle("Text")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = true, onClick = {})
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = false, onClick = {})
                }
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = true, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = false, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }
                Spacer(Modifier.size(Spacing.Spacing18))

                SubTitle("Elevated")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.ELEVATED, enabled = true, onClick = {})
                    Button(text = "Label", style = ButtonStyle.ELEVATED, enabled = false, onClick = {})
                }
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.ELEVATED, enabled = true, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.ELEVATED, enabled = false, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }
                Spacer(Modifier.size(Spacing.Spacing18))

                SubTitle("Tonal")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.TONAL, enabled = true, onClick = {})
                    Button(text = "Label", style = ButtonStyle.TONAL, enabled = false, onClick = {})
                }
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.TONAL, enabled = true, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.TONAL, enabled = false, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }
                Spacer(Modifier.size(Spacing.Spacing18))

                SubTitle("Keyboard")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.KEYBOARDKEY, onClick = {})
                    Button(text = "Label", style = ButtonStyle.KEYBOARDKEY, enabled = false, onClick = {})
                }
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.KEYBOARDKEY, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.KEYBOARDKEY, enabled = false, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }

                Title("Error Buttons")
                SubTitle("Filled")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.FILLED, enabled = true, colorStyle = ColorStyle.ERROR, onClick = {})
                    Button(text = "Label", style = ButtonStyle.FILLED, enabled = false, colorStyle = ColorStyle.ERROR, onClick = {})
                }
                RowComponentContainer {
                    Button(text = "Label", style = ButtonStyle.FILLED, enabled = true, colorStyle = ColorStyle.ERROR, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.FILLED, enabled = false, colorStyle = ColorStyle.ERROR, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }

                Spacer(Modifier.size(Spacing.Spacing18))
                SubTitle("Outlined")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.OUTLINED, enabled = true, colorStyle = ColorStyle.ERROR, onClick = {})
                    Button(text = "Label", style = ButtonStyle.OUTLINED, enabled = false, colorStyle = ColorStyle.ERROR, onClick = {})
                }
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.OUTLINED, enabled = true, colorStyle = ColorStyle.ERROR, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.OUTLINED, enabled = false, colorStyle = ColorStyle.ERROR, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }
                Spacer(Modifier.size(Spacing.Spacing18))

                SubTitle("Text")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = true, colorStyle = ColorStyle.ERROR, onClick = {})
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = false, colorStyle = ColorStyle.ERROR, onClick = {})
                }
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = true, colorStyle = ColorStyle.ERROR, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = false, colorStyle = ColorStyle.ERROR, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }
                Spacer(Modifier.size(Spacing.Spacing18))

                Title("Warning Buttons")
                SubTitle("Filled")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.FILLED, enabled = true, colorStyle = ColorStyle.WARNING, onClick = {})
                    Button(text = "Label", style = ButtonStyle.FILLED, enabled = false, colorStyle = ColorStyle.WARNING, onClick = {})
                }
                RowComponentContainer {
                    Button(text = "Label", style = ButtonStyle.FILLED, enabled = true, colorStyle = ColorStyle.WARNING, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.FILLED, enabled = false, colorStyle = ColorStyle.WARNING, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }

                Spacer(Modifier.size(Spacing.Spacing18))
                SubTitle("Outlined")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.OUTLINED, enabled = true, colorStyle = ColorStyle.WARNING, onClick = {})
                    Button(text = "Label", style = ButtonStyle.OUTLINED, enabled = false, colorStyle = ColorStyle.WARNING, onClick = {})
                }
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.OUTLINED, enabled = true, colorStyle = ColorStyle.WARNING, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.OUTLINED, enabled = false, colorStyle = ColorStyle.WARNING, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }
                Spacer(Modifier.size(Spacing.Spacing18))

                SubTitle("Text")
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = true, colorStyle = ColorStyle.WARNING, onClick = {})
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = false, colorStyle = ColorStyle.WARNING, onClick = {})
                }
                RowComponentContainer() {
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = true, colorStyle = ColorStyle.WARNING, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                    Button(text = "Label", style = ButtonStyle.TEXT, enabled = false, colorStyle = ColorStyle.WARNING, onClick = {}, icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Button",
                        )
                    })
                }
            }
        }
    }
}

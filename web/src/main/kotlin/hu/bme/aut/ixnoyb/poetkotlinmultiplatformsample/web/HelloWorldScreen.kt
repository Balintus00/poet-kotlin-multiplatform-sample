package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.web

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import dev.petuska.kmdc.core.KMDCInternalAPI
import dev.petuska.kmdc.core.rememberUniqueDomElementId
import dev.petuska.kmdc.top.app.bar.*
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.viewlogic.HelloWorldComponent
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.web.material3.components.FilledButton
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.web.material3.components.FilledTextField
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

@Composable
internal fun HelloWorldScreen(component: HelloWorldComponent) {
    MDCTopAppBar {
        TopAppBar {
            Row {
                Section(align = MDCTopAppBarSectionAlign.Start) {
                    Title("Hello World!")
                }
            }
        }
        Main(
            attrs = {
                style {
                    alignItems(AlignItems.Center)
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                    justifyContent(JustifyContent.Center)
                    width(100.percent)
                }
            }
        ) {
            val helloWorldState by component.helloWorldState.subscribeAsState()

            HelloWorldContent(
                isLoading = helloWorldState.isLoading,
                greetedName = helloWorldState.greetedName,
                greetNameAction = component::greetName,
            )
        }
    }
}

@Composable
@OptIn(KMDCInternalAPI::class)
private fun HelloWorldContent(
    isLoading: Boolean,
    greetedName: String,
    greetNameAction: (name: String) -> Unit,
) {
    Section(
        attrs = {
            style {
                alignItems(AlignItems.Center)
                display(DisplayStyle.Flex)
                justifyContent(JustifyContent.Center)
                marginBottom(16.px)
            }
        },
    ) {
        val helloWorldTextFieldId = rememberUniqueDomElementId()

        FilledTextField(
            attrs = {
                attr("id", helloWorldTextFieldId)

                if (isLoading) {
                    attr("disabled", "true")
                }

                style {
                    marginRight(8.px)
                }
            },
        )
        FilledButton(
            attrs = {
                attr("label", "Greet")

                if (isLoading) {
                    attr("disabled", "true")
                }

                onClick {
                    greetNameAction((js("document.getElementById(helloWorldTextFieldId).value") as? String) ?: "")
                }
            },
        )
    }
    Section(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                justifyContent(JustifyContent.Center)
            }
        },
    ) {
        Text("Greeted name: ${greetedName.ifEmpty { "Nobody has been greeted yet!" }}")
    }
}
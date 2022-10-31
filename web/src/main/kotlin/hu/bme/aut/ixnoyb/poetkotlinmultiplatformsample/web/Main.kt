package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.web

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import dev.petuska.kmdc.core.KMDCInternalAPI
import dev.petuska.kmdc.top.app.bar.*
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.viewlogic.HelloWorldComponent
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.Document

external fun require(module: String): dynamic

@OptIn(KMDCInternalAPI::class)
fun main() {
    val lifecycle = LifecycleRegistry()

    val helloWorldComponent = HelloWorldComponent(
        componentContext = DefaultComponentContext(lifecycle = lifecycle),
        storeFactory = DefaultStoreFactory(),
    )

    lifecycle.attachToDocument()

    requireMaterial3WebComponents()

    renderComposable(rootElementId = "root") {
        Article(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    height(100.vh)
                }
            },
        ) {
            HelloWorldScreen(component = helloWorldComponent)
        }
    }
}

private fun LifecycleRegistry.attachToDocument() {
    fun onVisibilityChanged() {
        if (document.visibilityState == "visible") {
            resume()
        } else {
            stop()
        }
    }

    onVisibilityChanged()

    document.addEventListener(type = "visibilitychange", callback = { onVisibilityChanged() })
}

private val Document.visibilityState: String get() = asDynamic().visibilityState.unsafeCast<String>()

private fun requireMaterial3WebComponents() {
    require("@material/web/button/filled-button.js")
    require("@material/web/textfield/filled-text-field.js")
}

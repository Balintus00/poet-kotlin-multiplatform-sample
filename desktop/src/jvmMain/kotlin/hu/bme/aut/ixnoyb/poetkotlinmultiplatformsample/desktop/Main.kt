package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.desktop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.compose.HelloWorldScreen
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.viewlogic.HelloWorldComponent

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    val lifeCycle = LifecycleRegistry()

    val helloWorldComponent = HelloWorldComponent(
        componentContext = DefaultComponentContext(lifeCycle),
        storeFactory = DefaultStoreFactory(),
    )

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycleRegistry = lifeCycle, windowState = windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Kotlin Multiplatform Hello World!",
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    HelloWorldScreen(component = helloWorldComponent)
                }
            }
        }
    }
}
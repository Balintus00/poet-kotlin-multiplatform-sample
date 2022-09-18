package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.android.ui.theme.PoetKotlinMultiplatformSampleTheme
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.compose.HelloWorldScreen
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.viewlogic.HelloWorldComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val helloWorldComponent = HelloWorldComponent(
            componentContext = defaultComponentContext(),
            storeFactory = DefaultStoreFactory(),
        )

        setContent {
            PoetKotlinMultiplatformSampleTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    HelloWorldScreen(component = helloWorldComponent)
                }
            }
        }
    }
}
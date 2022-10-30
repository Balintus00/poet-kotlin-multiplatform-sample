package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.viewlogic.HelloWorldComponent

@Composable
@Suppress("UnnecessaryOptInAnnotation")
@OptIn(ExperimentalMaterial3Api::class)
fun HelloWorldScreen(component: HelloWorldComponent) {
    val componentState by component.helloWorldState.subscribeAsState()

    Column {
        CenterAlignedTopAppBar(
            title = { Text(text = "Hello World Demo") },
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(weight = 1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextFieldFix(
                    enabled = componentState.isLoading.not(),
                    modifier = Modifier.width(256.dp).padding(end = 8.dp),
                    onValueChange = component::setName,
                    singleLine = true,
                    value = componentState.name,
                )
                Button(
                    content = {
                        Text(text = "Greet")
                    },
                    enabled = componentState.isLoading.not(),
                    onClick = component::greetName,
                )
            }
            Text("Greeted name: ${componentState.greetedName.ifEmpty { "Nobody has been greeted yet!" }}")
        }
    }
}
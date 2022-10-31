package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.compose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.viewlogic.HelloWorldComponent

@Composable
@Suppress("UnnecessaryOptInAnnotation")
fun HelloWorldScreen(component: HelloWorldComponent) {
    val componentState by component.helloWorldState.subscribeAsState()

    HelloWorldContent(
        isLoading = componentState.isLoading,
        greetedName = componentState.greetedName,
        greetNameAction = component::greetName,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("UnnecessaryOptInAnnotation")
fun HelloWorldContent(isLoading: Boolean, greetedName: String, greetNameAction: (name: String) -> Unit) {
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
                var name by rememberSaveable { mutableStateOf("") }
                TextFieldFix(
                    enabled = isLoading.not(),
                    modifier = Modifier.width(256.dp).padding(end = 8.dp),
                    onValueChange = { name = it },
                    singleLine = true,
                    value = name,
                )
                Button(
                    content = { Text(text = "Greet") },
                    enabled = isLoading.not(),
                    onClick = { greetNameAction(name) },
                )
            }
            Text("Greeted name: ${greetedName.ifEmpty { "Nobody has been greeted yet!" }}")
        }
    }
}

// region Previews

@Preview
@Composable
fun HelloWorldContentPreview() {
    HelloWorldContent(
        isLoading = false,
        greetedName = "",
        greetNameAction = {},
    )
}
// endregion
package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.web

import androidx.compose.runtime.*
import com.arkivanov.decompose.value.Value

@Composable
fun <T : Any> Value<T>.subscribeAsState(): State<T> {
    val state = remember(this) { mutableStateOf(value) }

    DisposableEffect(this) {
        val observer: (T) -> Unit = { state.value = it }

        subscribe(observer)

        onDispose {
            unsubscribe(observer)
        }
    }

    return state
}

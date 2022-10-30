package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.viewlogic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.network.HelloWorldGrpcClient
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.viewlogic.HelloWorldStore.*
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.viewlogic.HelloWorldStore.Intent.*
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.viewlogic.HelloWorldStoreFactory.Message.*
import kotlinx.coroutines.launch

class HelloWorldComponent(componentContext: ComponentContext, storeFactory: StoreFactory) : ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { HelloWorldStoreFactory(storeFactory).create() }

    val helloWorldState: Value<HelloWorldComponentState> = store.asValue().map {
        HelloWorldComponentState(greetedName = it.greetedName, isLoading = it.isLoading, name = it.name)
    }

    fun greetName() {
        store.accept(GreetName)
    }

    fun setName(name: String) {
        store.accept(SetName(name))
    }
}

data class HelloWorldComponentState(
    val greetedName: String = "",
    val isLoading: Boolean = false,
    val name: String = "",
)

internal interface HelloWorldStore : Store<Intent, State, Nothing> {

    sealed interface Intent {
        object GreetName : Intent
        data class SetName(val name: String) : Intent
    }

    data class State(
        val greetedName: String = "",
        val isLoading: Boolean = false,
        val name: String = "",
    )
}

internal class HelloWorldStoreFactory(private val storeFactory: StoreFactory) {

    fun create(): HelloWorldStore =
        object : HelloWorldStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "HelloWorldStore",
            initialState = State(),
            executorFactory = HelloWorldStoreFactory::HelloWorldExecutor,
            reducer = HelloWorldReducer,
        ) {}

    private sealed interface Message {
        data class GreetedName(val greetedName: String) : Message
        data class Name(val name: String) : Message
        object StartGreeting : Message
    }

    private object HelloWorldReducer : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State =
            when (msg) {
                is GreetedName -> copy(greetedName = msg.greetedName, isLoading = false)
                is Name -> copy(name = msg.name)
                is StartGreeting -> copy(isLoading = true)
            }
    }

    private class HelloWorldExecutor: CoroutineExecutor<Intent, Nothing, State, Message, Nothing>() {

        private val grpcClient: HelloWorldGrpcClient = HelloWorldGrpcClient()

        override fun executeIntent(intent: Intent, getState: () -> State) {
            val state = getState()
            when (intent) {
                is GreetName -> {
                    if (state.isLoading.not()) {
                        scope.launch {
                            dispatch(StartGreeting)
                            dispatch(GreetedName(greetedName = grpcClient.sayHello(name = state.name)))
                        }
                    }
                }
                is SetName -> {
                    if (state.isLoading.not()) {
                        dispatch(Name(intent.name))
                    }
                }
            }
        }
    }
}
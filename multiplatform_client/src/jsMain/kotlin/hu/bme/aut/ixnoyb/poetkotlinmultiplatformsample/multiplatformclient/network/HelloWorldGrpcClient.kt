package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.network

import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.network.kotlinwrappers.HelloWorldRequest
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.network.kotlinwrappers.HelloWorldServicePromiseClient
import kotlinx.coroutines.await

actual class HelloWorldGrpcClient {

    private val grpcClient: HelloWorldServicePromiseClient =
        HelloWorldServicePromiseClient("$GRPC_SERVICE_URL_PROTOCOL://$GRPC_SERVICE_BASE_URL:$GRPC_SERVICE_WITH_PROXY_PORT")

    actual suspend fun sayHello(name: String): String {
        return try {
            grpcClient.sayHello(
                request = HelloWorldRequest().apply { setName(name) },
            ).await().getHello()
        } catch (e: dynamic) {
            "EXCEPTION: ${if (e is Exception) e.message else JSON.stringify(e, null, 2)}"
        }
    }

    companion object {
        private const val GRPC_SERVICE_BASE_URL = "localhost"
        private const val GRPC_SERVICE_URL_PROTOCOL = "http"
        private const val GRPC_SERVICE_WITH_PROXY_PORT = "3000"
    }
}
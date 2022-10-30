package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.network

import com.squareup.wire.GrpcClient
import kotlinx.coroutines.CancellationException
import okhttp3.OkHttpClient
import okhttp3.Protocol
import poet.HelloWorldRequest
import poet.HelloWorldServiceClient
import java.io.IOException

actual class HelloWorldGrpcClient {

    private val _grpcClient = GrpcClient.Builder()
        .client(OkHttpClient.Builder().protocols(listOf(Protocol.H2_PRIOR_KNOWLEDGE)).build())
        .baseUrl("$GRPC_SERVICE_URL_PROTOCOL://$GRPC_SERVICE_BASE_URL:$GRPC_SERVICE_PORT")
        .build()
        .create(HelloWorldServiceClient::class)

    @Suppress("BlockingMethodInNonBlockingContext") // https://github.com/square/wire/issues/1935
    actual suspend fun sayHello(name: String): String {
        return try {
            _grpcClient.SayHello().execute(HelloWorldRequest(name = name)).hello
        } catch (ioException: IOException) {
            "IO EXCEPTION: ${ioException.message}"
        } catch (cancellationException: CancellationException) {
            "CANCELLATION EXCEPTION: ${cancellationException.message}"
        }
    }

    companion object {
        private const val GRPC_SERVICE_BASE_URL = "0.0.0.0"
        private const val GRPC_SERVICE_PORT = "3001"
        private const val GRPC_SERVICE_URL_PROTOCOL = "http"
    }
}
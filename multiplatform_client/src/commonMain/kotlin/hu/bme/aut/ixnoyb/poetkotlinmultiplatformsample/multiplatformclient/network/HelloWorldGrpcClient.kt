package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.network

expect class HelloWorldGrpcClient() {

    suspend fun sayHello(name: String): String
}
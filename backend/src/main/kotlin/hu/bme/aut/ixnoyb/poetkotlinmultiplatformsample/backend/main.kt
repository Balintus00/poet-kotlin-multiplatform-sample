package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.backend

import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.backend.kotlinWrappers.Server

external fun require(name: String): dynamic

@Suppress("ObjectPropertyName")
external val __dirname: dynamic

val grpc = require("@grpc/grpc-js")
val protoLoader = require("@grpc/proto-loader")

val PROTO_PATH = "$__dirname/../../../../../protos/src/main/proto/poet.proto"

fun main() {
    val protoLoaderOptions: dynamic = object{}
    protoLoaderOptions["keepCase"] = true
    val packageDefinition = protoLoader.loadSync(PROTO_PATH, protoLoaderOptions)
    val packageObject = grpc.loadPackageDefinition(packageDefinition).poet
    val server = Server()
    val serviceObject: dynamic = object{}
    serviceObject["sayHello"] = { call: dynamic, callback: dynamic ->
        println("sayHello is called")
        callback(null, greeter(call.request))
    }

    server.addService(packageObject.HelloWorldService.service, serviceObject)
    server.bindAsync("0.0.0.0:50051", grpc.ServerCredentials.createInsecure()) {
        println("Starting server...")
        server.start()
        println("Server started!")
    }
}

fun greeter(helloWorldRequest: dynamic): dynamic {
    val helloWorldResponse: dynamic = object{}
    helloWorldResponse["hello"] = "Hello ${helloWorldRequest["name"]}!"
    return helloWorldResponse
}
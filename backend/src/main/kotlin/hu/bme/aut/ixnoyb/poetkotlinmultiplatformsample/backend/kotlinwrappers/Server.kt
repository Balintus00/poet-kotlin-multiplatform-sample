@file:JsModule("@grpc/grpc-js")
@file:JsNonModule

package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.backend.kotlinwrappers

open external class Server(options: dynamic = definedExternally) : Any {

    open fun addService(service: dynamic, implementation:dynamic)

    open fun bindAsync(port: String, credentials: dynamic, callback: dynamic)

    open fun start()
}
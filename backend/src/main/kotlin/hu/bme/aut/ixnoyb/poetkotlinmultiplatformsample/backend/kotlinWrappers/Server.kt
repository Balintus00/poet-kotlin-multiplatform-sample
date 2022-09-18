@file:JsModule("@grpc/grpc-js")
@file:JsNonModule

package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.backend.kotlinWrappers

open external class Server(options: Any? = definedExternally) : Any {

    open fun addService(service: Any, implementation: Any)

    open fun bindAsync(port: String, credentials: Any, callback: Any)

    open fun start()
}
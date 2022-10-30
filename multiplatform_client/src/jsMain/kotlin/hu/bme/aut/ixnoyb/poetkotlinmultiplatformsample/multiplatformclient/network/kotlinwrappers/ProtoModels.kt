@file:JsModule("grpc_web_client_poet/poet_pb.js")
@file:JsNonModule

package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.network.kotlinwrappers

open external class HelloWorldRequest(options: Any? = definedExternally) : Any {

    open fun setName(name: String)
}

open external class HelloWorldResponse(options: Any? = definedExternally) : Any {

    open fun getHello(): String
}

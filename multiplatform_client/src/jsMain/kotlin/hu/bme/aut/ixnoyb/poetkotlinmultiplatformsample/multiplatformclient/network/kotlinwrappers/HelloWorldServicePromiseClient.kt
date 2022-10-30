@file:JsModule("grpc_web_client_poet/poet_grpc_web_pb.js")
@file:JsNonModule

package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.multiplatformclient.network.kotlinwrappers

import kotlin.js.Promise

open external class HelloWorldServicePromiseClient(options: Any? = definedExternally) : Any {

    fun sayHello(request: HelloWorldRequest, metadata: dynamic = definedExternally) : Promise<HelloWorldResponse>
}

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.wire)
}

wire {
    kotlin {
        rpcCallStyle = "suspending"
        rpcRole = "client"
        singleMethodServices = false
    }
}

dependencies {
    implementation(libs.wire.grpcClient)
}
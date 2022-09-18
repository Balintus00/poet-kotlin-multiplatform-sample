@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.js)
}

kotlin {
    js(IR) {
        nodejs {}
        binaries.executable()
    }
}

dependencies {
    implementation(npm("@grpc/grpc-js", "1.7.0"))
    implementation(npm("@grpc/proto-loader", "0.7.2"))
}
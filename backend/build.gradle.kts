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

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().download =
        (System.getenv("USE_PREINSTALLED_NODE_JS") ?: "") != "FALSE"
}
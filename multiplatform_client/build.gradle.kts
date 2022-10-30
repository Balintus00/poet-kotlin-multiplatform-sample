@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    android()
    jvm()
    js(IR) { browser() }

    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val commonMain by getting {
            dependencies {
                api(libs.decompose)
                api(libs.mviKotlin)
                implementation(libs.kotlinXCoroutines.core)
                implementation(libs.mviKotlin.coroutineExtension)
                implementation(libs.mviKotlin.rx)
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val androidMain by getting {
            dependencies {
                implementation(project(":protos"))
                implementation(libs.wire.grpcClient)
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val jsMain by getting {
            dependencies {
                val grpcWebClientPath = rootProject.projectDir.resolve("grpc_web_client_poet").canonicalPath
                implementation(npm("grpc_web_client_poet", "file:$grpcWebClientPath"))
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val jvmMain by getting {
            dependencies {
                implementation(project(":protos"))
                implementation(libs.wire.grpcClient)
            }
        }
    }
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
}
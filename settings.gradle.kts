pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "PoetKotlinMultiplatformSample"

include(":android")
include(":backend")
include(":compose")
include(":desktop")
include(":multiplatform_client")
include(":protos")
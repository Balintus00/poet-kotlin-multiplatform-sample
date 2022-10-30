@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.multiplatform) // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(project(":compose"))
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinXCoroutines.swing)
                implementation(libs.mviKotlin.main)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.desktop.MainKt"
    }
}
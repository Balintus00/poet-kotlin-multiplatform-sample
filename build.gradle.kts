// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        classpath(libs.kotlin.gradlePlugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")    // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.gradleDependencyUpdate)
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.js) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

tasks.register<Delete>("cleanBuildDir") {
    delete(rootProject.buildDir)
}
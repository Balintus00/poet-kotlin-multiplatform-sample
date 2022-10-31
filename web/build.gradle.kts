@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.js)
    alias(libs.plugins.jetbrains.compose)
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    js(IR) {
        browser {
            useCommonJs()
            binaries.executable()
        }
    }
}

dependencies {
    implementation(project(":multiplatform_client"))
    implementation(compose.web.core)
    implementation(compose.runtime)
    implementation(libs.mviKotlin.main)
    implementation(npm("@material/web", "0.1.0-alpha.0"))
    implementation(npm("@webcomponents/webcomponentsjs", "2.6.0"))
    implementation("dev.petuska:kmdc-top-app-bar:0.0.5")

    // SCSS dependencies
    implementation(devNpm("style-loader", "^3.3.1"))
    implementation(devNpm("css-loader", "6.7.1"))
    implementation(devNpm("extract-loader", "5.1.0"))
    implementation(devNpm("file-loader", "6.2.0"))
    implementation(devNpm("sass", "1.54.9"))
    implementation(devNpm("sass-loader", "13.0.2"))
}

// a temporary workaround for a bug in jsRun invocation - see https://youtrack.jetbrains.com/issue/KT-48273
afterEvaluate {
    rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
        versions.webpackDevServer.version = "4.11.0"
        versions.webpackCli.version = "4.10.0"
    }
}
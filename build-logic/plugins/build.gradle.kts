plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.kotlin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "aichallenge.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "aichallenge.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "aichallenge.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
    }
}

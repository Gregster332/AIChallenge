plugins {
    id("aichallenge.android.application")
    id("aichallenge.android.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.gregzenkov.aichallenge"

    defaultConfig {
        applicationId = "com.gregzenkov.aichallenge"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Decompose
    implementation(libs.decompose)
    implementation(libs.decompose.extensions.compose)

    // Убрать
    implementation(project(":features:home"))

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.tooling)
}
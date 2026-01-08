plugins {
    id("aichallenge.android.library")
    id("aichallenge.android.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.gregzenkov.aichallenge.features.home"
}

dependencies {
    implementation(libs.decompose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
}
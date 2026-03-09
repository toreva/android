plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.toreva.mobile"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.toreva.mobile"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "TOREVA_API_URL", "\"${project.findProperty("TOREVA_API_URL") ?: "http://10.0.2.2:8080/"}\"")
        buildConfigField("String", "SOLANA_CLUSTER", "\"${project.findProperty("TOREVA_SOLANA_CLUSTER") ?: "devnet"}\"")
        buildConfigField("String", "EXPLORER_URL", "\"${project.findProperty("TOREVA_EXPLORER_URL") ?: "https://solscan.io"}\"")
        buildConfigField("String", "RPC_URL", "\"${project.findProperty("TOREVA_RPC_URL") ?: "https://api.devnet.solana.com"}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation("com.google.android.material:material:1.12.0")
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.mwa.clientlib)
    implementation(libs.sol4k)
    implementation(libs.datastore)
    implementation(libs.security.crypto)
    implementation(libs.coroutines)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)

    debugImplementation(libs.compose.ui.tooling)
}

kapt {
    correctErrorTypes = true
}

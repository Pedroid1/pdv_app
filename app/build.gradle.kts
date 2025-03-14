plugins {
    alias(libs.plugins.android.application)
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.pedroid.pdv_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pedroid.pdv_app"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    //--------------Default-------------
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    //--------------Lifecycle-------------
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.runtime)
    annotationProcessor(libs.androidx.lifecycle.compiler)

    //--------------Hilt-------------
    implementation(libs.hilt.android)
    annotationProcessor(libs.hilt.android.compiler)

    //--------------Retrofit-------------
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation (libs.adapter.rxjava3)


    //--------------Navigation Component-------------
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    //--------------RxJava-------------
    implementation (libs.rxandroid)
    implementation (libs.rxjava)

    //--------------Testing-------------
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    testImplementation(libs.truth)
    testImplementation(libs.mockk)
    testImplementation(libs.androidx.core.testing)
}

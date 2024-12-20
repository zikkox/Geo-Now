plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    //id("com.google.devtools.ksp") version "2.1.0-1.0.29"

    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.geonow"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.geonow"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.github.bumptech.glide:glide:4.16.0")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.activity:activity-ktx:1.9.3")

    val navVersion = "2.8.4"
    implementation("androidx.navigation:navigation-fragment:$navVersion")
    implementation("androidx.navigation:navigation-ui:$navVersion")


    //implementation("com.google.dagger:dagger-compiler:2.51.1")
    //ksp("com.google.dagger:dagger-compiler:2.51.1")

    implementation("io.insert-koin:koin-android:4.0.0")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
}
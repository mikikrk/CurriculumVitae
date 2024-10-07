plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    defaultConfig {
        applicationId = "com.mnowak.curriculumvitae"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    namespace = "com.mnowak.curriculumvitae"
}

dependencies {

    testImplementation("junit:junit:4.13.2")

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("com.google.android.material:material:1.12.0")

    //Dagger
    implementation("com.google.dagger:dagger-android:2.16")
    implementation("com.google.dagger:dagger-android-support:2.16")
    kapt("com.google.dagger:dagger-android-processor:2.16")
    kapt("com.google.dagger:dagger-compiler:2.16")

    //ButterKnife
    implementation("com.jakewharton:butterknife:10.2.3")
    kapt("com.jakewharton:butterknife-compiler:10.2.3")

    //GSON
    implementation("com.google.code.gson:gson:2.10")

    //Bindable Circle Image View
    implementation("de.hdodenhof:circleimageview:2.1.0")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.8.0")
}

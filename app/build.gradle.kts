import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(28)
    buildToolsVersion = "28.0.3"

    defaultConfig {
        applicationId = "com.mnowak.curriculumvitae"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    androidExtensions {
        configure(delegateClosureOf<AndroidExtensionsExtension> {
            isExperimental = true
        })
    }

    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
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
    dataBinding {
        isEnabled = true
    }
}

dependencies {

    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")

    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")
    implementation("androidx.core:core-ktx:1.0.1")
    implementation("com.google.android.material:material:1.0.0")
    kapt("androidx.databinding:databinding-compiler:3.2.1")

    //Kotlin
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))

    //Dagger
    implementation("com.google.dagger:dagger-android:2.16")
    implementation("com.google.dagger:dagger-android-support:2.16")
    kapt("com.google.dagger:dagger-android-processor:2.16")
    kapt("com.google.dagger:dagger-compiler:2.16")

    //ButterKnife
    implementation("com.jakewharton:butterknife:9.0.0-rc3")
    kapt("com.jakewharton:butterknife-compiler:9.0.0-rc3")

    //GSON
    implementation("com.google.code.gson:gson:2.8.2")

    //Bindable Circle Image View
    implementation("de.hdodenhof:circleimageview:2.1.0")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.8.0")
}

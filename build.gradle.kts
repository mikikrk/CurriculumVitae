buildscript {
    val kotlinVersion: String by extra {
        "2.0.0"
    }

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.2.1")
        classpath(kotlin("gradle-plugin", kotlinVersion))

        classpath("com.jakewharton:butterknife-gradle-plugin:9.0.0-rc3")
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://maven.google.com")
    }
}

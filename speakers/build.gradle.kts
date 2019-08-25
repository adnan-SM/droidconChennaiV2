import de.mannodermaus.gradle.plugins.junit5.junitPlatform

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("de.mannodermaus.android-junit5")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)



    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    testOptions {
        junitPlatform {
            filters (config = {
                includeEngines("spek2")
            })
        }
    }

}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to "*.jar"))

    implementation(AppDependencies.appCompat)
    implementation(AppDependencies.kotlin)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.11.1")

    implementation(project(":base"))
    implementation(project(":database"))

    testImplementation(AppTestDependencies.jUnit)
    androidTestImplementation(AppTestDependencies.testRunner)
    androidTestImplementation(AppTestDependencies.espresso)
    // assertion
    testImplementation(AppTestDependencies.kotlinTest)

    // spek2
    testImplementation(AppTestDependencies.spek)
    testImplementation(AppTestDependencies.spekRunner)

    androidTestRuntimeOnly(AppTestDependencies.junit5TestRunner)
    testImplementation (AppTestDependencies.mockito)
    testImplementation(AppTestDependencies.mockitoKotlin)
    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testImplementation(AppTestDependencies.jupiterApi)
    testImplementation(AppTestDependencies.jupiterEngine)

    // (Optional) If you need "Parameterized Tests"
    testImplementation(AppTestDependencies.jupiterParams)

    testImplementation(AppTestDependencies.junitVintage)
}
repositories {
    mavenCentral()
}

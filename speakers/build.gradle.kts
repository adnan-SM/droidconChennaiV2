plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.jakewharton.butterknife")
}

kapt {
    correctErrorTypes = true
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

}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to "*.jar"))

    implementation(AppDependencies.appCompat)
    implementation(AppDependencies.legacySupport)
    testImplementation("junit:junit:4.12")
    androidTestImplementation(AppTestDependencies.testRunner)
    androidTestImplementation(AppTestDependencies.espresso)
    implementation(AppDependencies.kotlin)
    implementation(AppDependencies.epoxy)
    kapt(AppDependencies.epoxyProcessor)

    implementation(project(":base"))
}
repositories {
    mavenCentral()
}

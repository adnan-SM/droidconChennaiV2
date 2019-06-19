plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Versions.compileSdkVersion)
        targetSdkVersion(Versions.compileSdkVersion)
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
    testImplementation("junit:junit:4.12")
    androidTestImplementation(AppTestDependencies.testRunner)
    androidTestImplementation(AppTestDependencies.espresso)
    implementation(project(":base"))

}

repositories {
    mavenCentral()
}

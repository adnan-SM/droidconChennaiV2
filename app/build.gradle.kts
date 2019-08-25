plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        applicationId = "in.droidcon.chennai"
        minSdkVersion(Versions.compileSdkVersion)
        targetSdkVersion(Versions.compileSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(AppDependencies.kotlin)
    implementation(AppDependencies.appCompat)
    implementation(AppDependencies.ktx)
    implementation(AppDependencies.constraint)
    testImplementation("junit:junit:4.12")
    androidTestImplementation(AppTestDependencies.testRunner)
    androidTestImplementation(AppTestDependencies.espresso)
    implementation(project(":speakers"))
    implementation(project(":home"))
    implementation(project(":base"))
    implementation(project(":database"))
}

apply(plugin = "com.google.gms.google-services")

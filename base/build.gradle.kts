plugins {
    id("com.android.library")
    id("kotlin-android-extensions")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    //def globalConfig = rootProject . extensions . getByName ("ext")
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
    api(AppDependencies.kotlin)
    api(AppDependencies.appCompat)
    api(AppDependencies.material)
    api(AppDependencies.koin)
    api(AppDependencies.koinViewModel)
    api(AppDependencies.timber)
    api(AppDependencies.navFragment)
    api(AppDependencies.navUi)
    api(AppDependencies.fragment)
    api(AppDependencies.rxAndroid)
    api(AppDependencies.rxKotlin)
    api(AppDependencies.archRuntime)
    api(AppDependencies.archExtension)
    api(AppDependencies.constraint)
    api(AppDependencies.ktx)
    api(AppDependencies.glide)
    api(AppDependencies.epoxy)
    api(AppDependencies.work)
    api(AppDependencies.lottie)
    api(AppDependencies.skeleton)
    api(AppDependencies.shimmer)
    kapt(AppDependencies.glide)
    kapt(AppDependencies.archCompiler)
    api(FirebaseDependencies.firestore)

    implementation(fileTree("dir" to "libs", "include" to "*.jar"))

    implementation(AppDependencies.appCompat)
    testImplementation("junit:junit:4.12")
    androidTestImplementation(AppTestDependencies.testRunner)
    androidTestImplementation(AppTestDependencies.espresso)
    implementation(AppDependencies.kotlin)
}
repositories {
    mavenCentral()
}

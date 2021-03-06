import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension

plugins {
    id("com.android.library")
    id("kotlin-android-extensions")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.jakewharton.butterknife")
}

androidExtensions {
    isExperimental = true
    configure(delegateClosureOf<AndroidExtensionsExtension> {
        isExperimental = true
    })
}

kapt {
    correctErrorTypes = true
}

android {
    //def globalConfig = rootProject . extensions . getByName ("ext")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    api(AppDependencies.work)
    api(AppDependencies.lottie)
    api(AppDependencies.skeleton)
    api(AppDependencies.shimmer)
    kapt(AppDependencies.glideCompiler)
    kapt(AppDependencies.archCompiler)
    kapt(AppDependencies.epoxyProcessor)
    api(FirebaseDependencies.firestore)

    implementation(fileTree("dir" to "libs", "include" to "*.jar"))

    implementation(AppDependencies.appCompat)
    implementation(AppDependencies.epoxy)
    testImplementation("junit:junit:4.12")
    androidTestImplementation(AppTestDependencies.testRunner)
    androidTestImplementation(AppTestDependencies.espresso)
    implementation(AppDependencies.kotlin)
}
repositories {
    mavenCentral()
}

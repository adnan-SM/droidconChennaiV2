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
    api(appDependencies.kotlin)
    api(appDependencies.appCompat)
    api(appDependencies.material)
    api(appDependencies.koin)
    api(appDependencies.koinViewModel)
    api(appDependencies.timber)
    api(appDependencies.navFragment)
    api(appDependencies.navUi)
    api(appDependencies.fragment)
    api(appDependencies.rxAndroid)
    api(appDependencies.rxKotlin)
    api(appDependencies.archRuntime)
    api(appDependencies.archExtension)
    api(appDependencies.constraint)
    api(appDependencies.ktx)
    api(appDependencies.glide)
    api(appDependencies.epoxy)
    api(appDependencies.work)
    api(appDependencies.lottie)
    api(appDependencies.skeleton)
    api(appDependencies.shimmer)
    kapt(appDependencies.glide)
    kapt(appDependencies.archCompiler)
    api(firebaseDependencies.firestore)

    implementation(fileTree("dir" to "libs", "include" to "*.jar"))

    implementation("com.android.support:appcompat-v7:28.0.0-rc02")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}")
}
repositories {
    mavenCentral()
}

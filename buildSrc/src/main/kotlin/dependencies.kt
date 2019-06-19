object Versions {

    // framework config
    const val compileSdkVersion = 28
    const val minSdkVersion = 21
    const val targetSdkVersion = 28

    // language
    const val kotlinVersion = "1.3.21"

    // dependency injection
    const val koinVersion = "1.0.2"

    // logging
    const val timberVersion = "4.7.1"

    // ktx
    const val ktxVersion = "1.2.0-alpha02"

    // architecture components
    const val lifecycleVersion = "2.0.0"
    const val coreTestVersion = "1.0.0"

    // room
    const val roomVersion = "2.1.0-alpha03"

    // design
    const val appCompatVersion = "1.0.0"
    const val materialVersion = "1.1.0-alpha04"
    const val fragmentVersion = "1.0.0"
    const val constraintVersion = "2.0.0-beta2"
    const val skeletonVersion = "1.1.2"
    const val shimmerVersion = "2.1.0"

    // navigation component
    const val navigationVersion = "1.0.0-rc02"

    // work manager
    const val workVersion = "1.0.0"

    // reactive extensions
    const val rxJavaVersion = "2.2.7"
    const val rxKotlinVersion = "2.3.0"
    const val rxAndroidVersion = "2.1.1"

    // network image
    const val glideVersion = "4.9.0"

    // epoxy
    const val epoxyVersion = "3.1.0"

    // lottie
    const val lottieVersion = "2.8.0"

    // debug data base
    const val debugDbVersion = "1.0.4"

    // firebase
    const val firestoreVersion = "18.1.0"

    // test
    const val jUnitVersion = "4.12"
    const val testRunnerVersion = "1.2.0"
    const val espressoVersion = "3.2.0"
    const val mockitoVersion = "2.0.0-RC3"
    const val robolectricVersion = "4.1"

    const val gradlePlugin = "3.3.2"
    const val googleServices = "4.2.0"

    //remote
    const val gsonVersion = "2.8.1"
    const val okHttpVersion = "3.8.1"
    const val androidAnnotationsVersion = "21.0.3"
    const val retrofitVersion = "2.3.0"
}

object BuildPlugins {
    const val android = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
}

object AppDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val koin = "org.koin:koin-android:${Versions.koinVersion}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koinVersion}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    const val navFragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navUi = "android.arch.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    const val archRuntime = "androidx.arch.core:core-runtime:${Versions.lifecycleVersion}"
    const val archExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val archCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktxVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val epoxy = "com.airbnb.android:epoxy:${Versions.epoxyVersion}"
    const val epoxyProcessor = "com.airbnb.android:epoxy-processor:${Versions.epoxyVersion}"
    const val work = "android.arch.work:work-runtime:${Versions.workVersion}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
    const val debugDb = "com.amitshekhar.android:debug-db:${Versions.debugDbVersion}"
    const val skeleton = "com.ethanhua:skeleton:${Versions.skeletonVersion}"
    const val shimmer = "io.supercharge:shimmerlayout:${Versions.shimmerVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val androidAnnotations = "com.android.support:support-annotations:${Versions.androidAnnotationsVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"

}

object AppTestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
    const val workTest = "android.arch.work:work-testing:${Versions.workVersion}"
}

object PresentationDependencies {
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    const val archRuntime = "androidx.arch.core:core-runtime:${Versions.lifecycleVersion}"
    const val archExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val archCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
}

object PresentationTestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
    const val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    const val archTest = "androidx.arch.core:core-testing:${Versions.lifecycleVersion}"
}

object DomainDependencies {
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
}

object DomainTestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
}

object DataDependencies {
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
}

object DataTestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
    const val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
}

object CacheDependencies {
    const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomRx = "androidx.room:room-rxjava2:${Versions.roomVersion}"
    const val roomCoroutinesKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
}

object CacheTestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
    const val roomTest = "androidx.room:room-testing:${Versions.roomVersion}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
    const val coreTest = "androidx.test:core:${Versions.coreTestVersion}"
}

object FirebaseDependencies {
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    const val firestore = "com.google.firebase:firebase-firestore:${Versions.firestoreVersion}"
}

object FirebaseTestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
}


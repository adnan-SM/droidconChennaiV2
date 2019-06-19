object Versions {

    // framework config
    val compileSdkVersion = 28
    val minSdkVersion = 21
    val targetSdkVersion = 28

    // language
    val kotlinVersion = "1.3.21"

    // dependency injection
    val koinVersion = "1.0.2"

    // logging
    val timberVersion = "4.7.1"

    // ktx
    val ktxVersion = "1.1.0-alpha04"

    // architecture components
    val lifecycleVersion = "2.0.0"
    val coreTestVersion = "1.0.0"

    // room
    val roomVersion = "2.1.0-alpha03"

    // design
    val appCompatVersion = "1.1.0-alpha02"
    val materialVersion = "1.1.0-alpha04"
    val fragmentVersion = "1.0.0"
    val constraintVersion = "2.0.0-alpha2"
    val skeletonVersion = "1.1.2"
    val shimmerVersion = "2.1.0"

    // navigation component
    val navigationVersion = "1.0.0-rc02"

    // work manager
    val workVersion = "1.0.0"

    // reactive extensions
    val rxJavaVersion = "2.2.7"
    val rxKotlinVersion = "2.3.0"
    val rxAndroidVersion = "2.1.1"

    // network image
    val glideVersion = "4.9.0"

    // epoxy
    val epoxyVersion = "3.1.0"

    // lottie
    val lottieVersion = "2.8.0"

    // debug data base
    val debugDbVersion = "1.0.4"

    // firebase
    val firestoreVersion = "18.1.0"

    // test
    val jUnitVersion = "4.12"
    val testRunnerVersion = "1.1.2-alpha01"
    val espressoVersion = "3.1.0-beta02"
    val mockitoVersion = "2.0.0-RC3"
    val robolectricVersion = "4.1"
}


object appDependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    val material = "com.google.android.material:material:${Versions.materialVersion}"
    val koin = "org.koin:koin-android:${Versions.koinVersion}"
    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koinVersion}"
    val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    val navFragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    val navUi = "android.arch.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    val archRuntime = "androidx.arch.core:core-runtime:${Versions.lifecycleVersion}"
    val archExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    val archCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}"
    val ktx = "androidx.core:core-ktx:${Versions.ktxVersion}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    val epoxy = "com.airbnb.android:epoxy:${Versions.epoxyVersion}"
    val epoxyProcessor = "com.airbnb.android:epoxy-processor:${Versions.epoxyVersion}"
    val work = "android.arch.work:work-runtime:${Versions.workVersion}"
    val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
    val debugDb = "com.amitshekhar.android:debug-db:${Versions.debugDbVersion}"
    val skeleton = "com.ethanhua:skeleton:${Versions.skeletonVersion}"
    val shimmer = "io.supercharge:shimmerlayout:${Versions.shimmerVersion}"

}

object appTestDependencies {
    val jUnit = "junit:junit:${Versions.jUnitVersion}"
    val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
    val workTest = "android.arch.work:work-testing:${Versions.workVersion}"
}

object presentationDependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    val archRuntime = "androidx.arch.core:core-runtime:${Versions.lifecycleVersion}"
    val archExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    val archCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
}

object presentationTestDependencies {
    val jUnit = "junit:junit:${Versions.jUnitVersion}"
    val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
    val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    val archTest = "androidx.arch.core:core-testing:${Versions.lifecycleVersion}"
}

object domainDependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
}

object domainTestDependencies {
    val jUnit = "junit:junit:${Versions.jUnitVersion}"
    val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
}

object dataDependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
}

object dataTestDependencies {
    val jUnit = "junit:junit:${Versions.jUnitVersion}"
    val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
    val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
}

object cacheDependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    val roomRx = "androidx.room:room-rxjava2:${Versions.roomVersion}"
}

object cacheTestDependencies {
    val jUnit = "junit:junit:${Versions.jUnitVersion}"
    val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
    val roomTest = "androidx.room:room-testing:${Versions.roomVersion}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
    val coreTest = "androidx.test:core:${Versions.coreTestVersion}"
}

object firebaseDependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    val firestore = "com.google.firebase:firebase-firestore:${Versions.firestoreVersion}"
}

object firebaseTestDependencies {
    val jUnit = "junit:junit:${Versions.jUnitVersion}"
    val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoVersion}"
}

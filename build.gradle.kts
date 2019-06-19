// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    //ext.kotlin_version = "1.3.21"
    repositories {
        google()
        mavenCentral()
        jcenter()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
        classpath("com.google.gms:google-services:4.2.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

//apply(from = "dependencies.gradle")

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()

    }
}

tasks {
    register("clean", Delete::class) {
        delete(buildDir)
    }
}

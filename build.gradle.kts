import java.net.URI

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = Repositories.spekFramework)

    }
    dependencies {
        classpath(BuildPlugins.android)
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.googleServices)
        classpath(BuildPlugins.androidjunit5)
        classpath(BuildPlugins.kotlinSerialization)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

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

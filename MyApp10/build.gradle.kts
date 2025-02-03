// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        val nav_version = "2.8.6"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false
    //https://developer.android.com/build/migrate-to-ksp#add-ksp
    //ksp版本必須跟kotlin版本一致 例如專案kotlin版本南1.9.24，ksp就要1.9.24-x.x.xx
}
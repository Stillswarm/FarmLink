// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    kotlin("plugin.serialization") version "1.8.22" apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false
    id("io.realm.kotlin") version "2.0.0" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}


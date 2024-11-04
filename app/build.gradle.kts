plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "dam.pmdm.tarea2nz"
    compileSdk = 34

    defaultConfig {
        applicationId = "dam.pmdm.tarea2nz"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
        dataBinding= true
    }

}


dependencies {
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation ("androidx.navigation:navigation-fragment:2.8.3")
    implementation ("androidx.navigation:navigation-ui:2.8.3")
    implementation ("com.google.android.material:material:1.12.0")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation ("androidx.preference:preference:1.2.1")


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.preference)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}



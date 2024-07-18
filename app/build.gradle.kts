plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "com.demo.cloudnix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.demo.cloudnix"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags += "-std=c++17"
            }
        }
    }


    buildTypes {

        debug {
            buildConfigField("String", "BASE_URL", "\"https://s7om3fdgbt7lcvqdnxitjmtiim0uczux.lambda-url.us-east-2.on.aws\"")
        }

        release {
            buildConfigField("String", "BASE_URL", "\"https://s7om3fdgbt7lcvqdnxitjmtiim0uczux.lambda-url.us-east-2.on.aws\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.benchmark.macro)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.squareup.adapter.rxjava)
    implementation(libs.adapter.rxjava2)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    //implementation(libs.dagger)
    //implementation("com.google.dagger:dagger-android:2.24")
    implementation("com.google.dagger:dagger:2.44.2")
    kapt("com.google.dagger:dagger-compiler:2.44.2")

    implementation("com.google.dagger:dagger-android:2.44.2")
    implementation("com.google.dagger:dagger-android-support:2.44.2") // optional if you use AndroidX
    kapt("com.google.dagger:dagger-android-processor:2.44.2")
  //  implementation(libs.com.google.dagger.dagger.android)
  //  implementation(libs.google.dagger.android.support)

  //  implementation(libs.kotlin.stdlib)
  //  implementation(libs.dagger.v239)
  //  kapt(libs.dagger.compiler)

    // AndroidX Dependencies
    implementation(libs.core.ktx)
    implementation(libs.appcompat.v161)
    //implementation(libs.material.v180)
    implementation(libs.constraintlayout)

}
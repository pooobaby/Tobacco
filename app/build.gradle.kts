plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)          // 是以 Kotlin 优先的 kapt 替代方案，Kapt（Kotlin 注解处理工具）
    alias(libs.plugins.hilt.android)        // // 依赖注入的依赖
}

android {
    namespace = "com.example.tobacco"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tobacco"
        minSdk = 28
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Room组件的依赖
    implementation(libs.androidx.room.runtime)  // Room 的核心库，提供了 Room 的基本功能。
    implementation(libs.androidx.room.ktx)      // Room 对 Kotlin 协程的支持，方便在协程中进行数据库操作
    ksp(libs.androidx.room.compiler)            // Room 注解处理器

    // LiveData的依赖
    implementation(libs.androidx.lifecycle.livedata.ktx)    // 扩展 LiveData 的功能，使其更易于在 Kotlin 中使用
    implementation(libs.androidx.runtime.livedata)          // 用于将 LiveData 与 Compose UI 集成，从而实现数据驱动的 UI 更新。

    // 单元测试的依赖
    testImplementation(libs.junit)  // JUnit 测试框架，用于单元测试

    // ViewModel的依赖
    implementation(libs.viewmodel)  // 用于实现 MVVM 架构中的 ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)   // 扩展 ViewModel 的功能，使其更易于在 Kotlin 中使用

    // 依赖注入的依赖
    implementation(libs.hilt.android)   // Hilt 的核心库，提供了依赖注入的基本功能。
    ksp(libs.hilt.compiler)     // Hilt 的注解处理器，用于处理 Hilt 相关的注解（如 @Module 、 @Inject 等）。
    implementation(libs.androidx.hilt.navigation.compose)   // Hilt 与 Compose 导航集成的扩展库，简化 ViewModel 的使用。

    // 导航组件的依赖
    implementation(libs.androidx.navigation.compose)

    // 汉字转为拼音库的依赖
    implementation(libs.promeg.tinypinyin)

    // 在网络加载图片的Coil库依赖
    implementation(libs.coil.compose)

    // 展示图片翻页功能的依赖
    implementation(libs.accompanist.pager)

    // 扫描条码的Zxing库的依赖和相机依赖
    implementation(libs.com.journeyapps.zxing.android.embedded7)
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
}
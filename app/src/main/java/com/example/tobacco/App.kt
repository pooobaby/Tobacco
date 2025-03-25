package com.example.tobacco

import android.app.Application
import com.github.promeg.pinyinhelper.Pinyin
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        // 初始化 TinyPinyin
        Pinyin.init(Pinyin.newConfig());
    }
}

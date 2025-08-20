package com.example.appxx_appthemewallpaper.util

import com.example.appxx_appthemewallpaper.model.CreateApp

class CallBack {
    interface ICallBackProgress {
        fun onProgress(progress: Int)
    }
    interface CallBackLanguage {
        fun callBackLanguage(language: String, key: String, position: Int)
    }
    interface CallBackLaunchApp {
        fun callBackLaunchApp(createApp: CreateApp, position: Int)
    }
}
package com.example.appxx_appthemewallpaper.util

import com.example.appxx_appthemewallpaper.model.CreateApp
import com.example.appxx_appthemewallpaper.model.LaunchApp

class CallBack {
    interface ICallBackProgress {
        fun onProgress(progress: Int)
    }
    interface CallBackLanguage {
        fun callBackLanguage(language: String, key: String, position: Int)
    }
    interface CallBackLaunchApp {
        fun callBackCreateShortcut(createApp: CreateApp, position: Int)
        fun callBackImportApp(position: Int)
    }
    interface CallBackAllPackage {
        fun callBackAllPackage(launchApp: LaunchApp, position: Int)
    }
}
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
        fun callBackCreateShortcut(createApp: CreateApp, position: Int, nameCreate: String)
        fun callBackImportApp(position: Int)
    }
    interface CallBackAllPackage {
        fun callBackAllPackage(launchApp: LaunchApp, position: Int)
    }
    interface CallBackFont {
        fun callBackFont(font: String, position: Int)
    }
    interface CallBackColor {
        fun callBackColor(color: String, position: Int)
    }
}
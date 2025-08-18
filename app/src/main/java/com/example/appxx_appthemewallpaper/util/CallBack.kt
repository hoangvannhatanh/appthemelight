package com.example.appxx_appthemewallpaper.util

class CallBack {
    interface ICallBackProgress {
        fun onProgress(progress: Int)
    }
    interface CallBackLanguage {
        fun callBackLanguage(language: String, key: String, position: Int)
    }
}
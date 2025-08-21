package com.example.appxx_appthemewallpaper.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.model.ThemeApp

class Util {
    companion object {
        fun getTheme1(context: Context): MutableList<ThemeApp> = mutableListOf(
            ThemeApp("1", "Telegram", "org.thunderdog.challegram", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "Netflix", "com.netflix.mediaclient", ContextCompat.getDrawable(context, R.drawable.ic_netflix_adaptive_foreground)),
            ThemeApp("1", "Instagram", "com.instagram.android", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "TikTok", "com.zhiliaoapp.musically", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "X", "com.twitter.android", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "Youtube", "com.google.android.youtube", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "Zalo", "com.zing.zalo", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "Chrome", "com.android.chrome", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "Facebook", "com.facebook.katana", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
//            ThemeApp("1", "Facebook", "com.facebook.lite", ContextCompat.getDrawable(context, R.drawable.ic_facebook_adaptive_foreground)),
//            ThemeApp("1", "Facebook", "com.facebook.orca", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "Camera", "com.sec.android.app.camera", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "Message", "org.telegram.messenger", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "TikTok", "com.ss.android.ugc.trill", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            ThemeApp("1", "Clock", "com.sec.android.app.clockpackage", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
//            ThemeApp("1", "Clock", "com.sec.android.app.clockpackage", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_background)),
//            ThemeApp("1", "Clock", "com.sec.android.app.clockpackage", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_background)),
//            ThemeApp("1", "Clock", "com.sec.android.app.clockpackage", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_background)),
        )
    }
}
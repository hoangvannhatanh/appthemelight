package com.example.appxx_appthemewallpaper.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.model.LaunchApp

class Util {
    companion object {
        fun getPackageList(context: Context): MutableList<LaunchApp> = mutableListOf(
            LaunchApp("", "org.thunderdog.challegram", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.facebook.lite", ContextCompat.getDrawable(context, R.drawable.ic_facebook_adaptive_foreground)),
            LaunchApp("", "com.netflix.mediaclient", ContextCompat.getDrawable(context, R.drawable.ic_netflix_adaptive_foreground)),
            LaunchApp("", "com.instagram.android", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.zhiliaoapp.musically", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.twitter.android", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.google.android.youtube", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.zing.zalo", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.android.chrome", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.facebook.katana", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.facebook.orca", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.sec.android.app.camera", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "org.telegram.messenger", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.ss.android.ugc.trill", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
            LaunchApp("", "com.sec.android.app.clockpackage", ContextCompat.getDrawable(context, R.drawable.ic_instagram_adaptive_foreground)),
        )
    }
}
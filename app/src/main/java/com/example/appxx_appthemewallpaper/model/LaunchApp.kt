package com.example.appxx_appthemewallpaper.model

import android.graphics.drawable.Drawable

data class LaunchApp(
    val appLabel: String,
    val packageName: String,
    val icon: Drawable
)
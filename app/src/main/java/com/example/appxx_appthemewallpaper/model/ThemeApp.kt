package com.example.appxx_appthemewallpaper.model

import android.graphics.drawable.Drawable

data class ThemeApp(
    val idTheme: String,
    val appLabel: String,
    val packageName: String,
    val icon: Drawable
)
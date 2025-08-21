package com.example.appxx_appthemewallpaper.model

import android.graphics.drawable.Drawable

data class CreateApp(
    val idTheme: String,
    val titleName1: String,
    val titleName2: String,
    val packageName1: String,
    val packageName2: String,
    val icon1: Drawable,
    val icon2: Drawable,
    val isSelect: Boolean = false,
)
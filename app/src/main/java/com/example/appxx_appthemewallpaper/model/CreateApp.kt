package com.example.appxx_appthemewallpaper.model

import android.graphics.drawable.Drawable

data class CreateApp(
    val idTheme: String,
    var titleName1: String,
    var titleName2: String,
    var packageName1: String,
    var packageName2: String,
    var icon1: Drawable,
    var iconCreate: Int,
    var backgroundCreate: Int,
    var isSelect: Boolean = false,
)
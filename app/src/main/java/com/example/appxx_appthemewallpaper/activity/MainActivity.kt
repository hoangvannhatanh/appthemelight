package com.example.appxx_appthemewallpaper.activity

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.view.LayoutInflater
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ActivityMainBinding
import com.example.appxx_appthemewallpaper.extensions.showActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun setBinding(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)

    override fun bindComponent() {

    }

    override fun bindData() {

    }

    override fun bindEvent() {
        binding.btnCreateShortcut.setOnClickListener {
            showActivity(CreateShortcutActivity::class.java)
        }
        binding.btnEditShortcut.setOnClickListener {
            showActivity(EditShortcutActivity::class.java)
        }
        binding.btnCreateWidget.setOnClickListener {
            showActivity(CreateWidgetActivity::class.java)
        }
    }
}
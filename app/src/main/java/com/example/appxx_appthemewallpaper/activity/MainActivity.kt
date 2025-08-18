package com.example.appxx_appthemewallpaper.activity

import android.view.LayoutInflater
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun setBinding(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)

    override fun bindComponent() {

    }

    override fun bindData() {

    }

    override fun bindEvent() {
        binding.btnCreateTelegramShortcut.setOnClickListener {
            createTelegramPinnedShortcut()
        }
    }

    private fun createTelegramPinnedShortcut() {
        val telegramUri = Uri.parse("tg://resolve?domain=telegram")
        val telegramIntent = Intent(Intent.ACTION_VIEW, telegramUri).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        // Fallback: mở app Telegram nếu có
        val fallbackIntent = Intent().apply {
            setPackage("org.telegram.messenger")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val launchIntent = Intent.createChooser(telegramIntent, null).apply {
            putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(fallbackIntent))
        }

        val shortcutId = "shortcut_telegram"

        val shortcutCompat = ShortcutInfoCompat.Builder(this, shortcutId)
            .setShortLabel("Telegram")
            .setLongLabel("Mở Telegram")
            .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_telegram_shortcut))
            .setIntent(telegramIntent)
            .build()

        if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
            ShortcutManagerCompat.requestPinShortcut(this, shortcutCompat, null)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val shortcutManager = getSystemService(ShortcutManager::class.java)
            val shortcut = ShortcutInfo.Builder(this, shortcutId)
                .setShortLabel("Telegram")
                .setLongLabel("Mở Telegram")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_telegram_shortcut))
                .setIntent(telegramIntent)
                .build()
            shortcutManager?.dynamicShortcuts = listOf(shortcut)
        } else {
            startActivity(launchIntent)
        }
    }

}
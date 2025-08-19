package com.example.appxx_appthemewallpaper.activity

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
import com.example.appxx_appthemewallpaper.databinding.ActivityEditShortcutBinding

class EditShortcutActivity : BaseActivity<ActivityEditShortcutBinding>(R.layout.activity_edit_shortcut) {
    override fun setBinding(layoutInflater: LayoutInflater) = ActivityEditShortcutBinding.inflate(layoutInflater)

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
            .setIcon(buildTelegramAdaptiveIcon())
            .setIntent(telegramIntent)
            .build()

        if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
            ShortcutManagerCompat.requestPinShortcut(this, shortcutCompat, null)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val shortcutManager = getSystemService(ShortcutManager::class.java)
            val shortcut = ShortcutInfo.Builder(this, shortcutId)
                .setShortLabel("Telegram")
                .setLongLabel("Mở Telegram")
                .setIcon(buildTelegramIcon())
                .setIntent(telegramIntent)
                .build()
            shortcutManager?.dynamicShortcuts = listOf(shortcut)
        } else {
            startActivity(launchIntent)
        }
    }

    private fun buildTelegramAdaptiveIcon(): IconCompat {
        val size = resources.getDimensionPixelSize(android.R.dimen.app_icon_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(this, R.drawable.ic_telegram_adaptive_background)!!
        val wrappedDrawable = DrawableCompat.wrap(bg)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#000000")) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN)

        val fg = AppCompatResources.getDrawable(this, R.drawable.ic_telegram_adaptive_foreground)!!
        val wrappedDrawable2 = DrawableCompat.wrap(fg)
        DrawableCompat.setTint(wrappedDrawable2, Color.parseColor("#303030")) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable2, PorterDuff.Mode.SRC_IN)


        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return IconCompat.createWithAdaptiveBitmap(bitmap) // API 26+
    }

    private fun buildTelegramIcon(): Icon {
        val size = resources.getDimensionPixelSize(android.R.dimen.app_icon_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(this, R.drawable.ic_telegram_adaptive_background)!!
        val fg = AppCompatResources.getDrawable(this, R.drawable.ic_telegram_adaptive_foreground)!!
        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return Icon.createWithBitmap(bitmap) // API 26+
    }

}
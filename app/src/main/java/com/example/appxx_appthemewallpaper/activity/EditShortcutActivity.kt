package com.example.appxx_appthemewallpaper.activity

import android.app.PendingIntent
import android.view.LayoutInflater
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Icon
import android.os.Build
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.activity_launcher.TelegramLauncherActivity
import com.example.appxx_appthemewallpaper.databinding.ActivityEditShortcutBinding

class EditShortcutActivity : BaseActivity<ActivityEditShortcutBinding>(R.layout.activity_edit_shortcut) {
    override fun setBinding(layoutInflater: LayoutInflater) = ActivityEditShortcutBinding.inflate(layoutInflater)

    override fun bindComponent() {

    }

    override fun bindData() {

    }

    override fun bindEvent() {
        binding.btnCreateTelegramShortcut.setOnClickListener {
            createTelegramShortcut()
        }
    }

    private fun createTelegramShortcut() {
        val shortcutID = "shortcut_telegram ${System.currentTimeMillis()}"
        val telegramIntent = Intent(this, TelegramLauncherActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = getSystemService(ShortcutManager::class.java)
            if (shortcutManager.isRequestPinShortcutSupported) {
                val shortcutInfo = ShortcutInfo.Builder(this, shortcutID)
                    .setShortLabel("Telegram")
                    .setLongLabel("Mở Telegram")
                    .setIcon(buildTelegramIcon())
                    .setIntent(telegramIntent)
                    .build()

                shortcutManager?.dynamicShortcuts = listOf(shortcutInfo)

                val pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(shortcutInfo)

                val successCallback = PendingIntent.getBroadcast(
                    this, 0,
                    pinnedShortcutCallbackIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )

                shortcutManager.requestPinShortcut(shortcutInfo, successCallback.intentSender)
            } else {
                Toast.makeText(this, "SHORTCUT NOT SUPPORT", Toast.LENGTH_SHORT).show()
            }
        } else {
            if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
                val shortcutInfo = ShortcutInfoCompat.Builder(this, shortcutID)
                    .setShortLabel("Telegram")
                    .setLongLabel("Mở Telegram")
                    .setIcon(buildTelegramAdaptiveIcon())
                    .setIntent(telegramIntent)
                    .build()

                ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
                Toast.makeText(this, "SHORTCUT ADDED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "SHORTCUT NOT SUPPORT", Toast.LENGTH_SHORT).show()
            }
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


        val a = (0.5f.coerceIn(0f, 1f) * 255).toInt()
        bg.alpha = a
        fg.alpha = a


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
        val wrappedDrawable = DrawableCompat.wrap(bg)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#000000")) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN)

        val wrappedDrawable2 = DrawableCompat.wrap(fg)
        DrawableCompat.setTint(wrappedDrawable2, Color.parseColor("#303030")) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable2, PorterDuff.Mode.SRC_IN)

        val a = (0.5f.coerceIn(0f, 1f) * 255).toInt()
        bg.alpha = a
        fg.alpha = a

        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return Icon.createWithBitmap(bitmap) // API 26+
    }

}
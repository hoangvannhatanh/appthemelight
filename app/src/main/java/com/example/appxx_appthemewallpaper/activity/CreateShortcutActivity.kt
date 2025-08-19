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
import android.util.Log
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ActivityCreateShortcutBinding

class CreateShortcutActivity : BaseActivity<ActivityCreateShortcutBinding>(R.layout.activity_create_shortcut) {
    override fun setBinding(layoutInflater: LayoutInflater) = ActivityCreateShortcutBinding.inflate(layoutInflater)

    override fun bindComponent() {

    }

    override fun bindData() {

    }

    override fun bindEvent() {
        binding.btnCreateTelegramShortcut.setOnClickListener {
            createTelegramShortcut()
        }
        
        binding.btnCreateFacbookShortcut.setOnClickListener {
            createFacebookShortcut()
        }
    }

    private fun createTelegramShortcut() {
        val telegramIntent = Intent(this, TelegramLauncherActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val shortcutID = "shortcut_telegram"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = getSystemService(ShortcutManager::class.java)
            val existingShortcuts = shortcutManager?.dynamicShortcuts?.map { it.id } ?: emptyList()
            val pinnedShortcuts = shortcutManager?.pinnedShortcuts?.map { it.id } ?: emptyList()

            //BELOW ANDROID 26 chỉ có thể dùng sharepreference

            if (shortcutManager.isRequestPinShortcutSupported) {
                pinnedShortcuts.forEach {
                    if (it == shortcutID) {
                        Toast.makeText(this, "SHORTCUT ALREADY EXIST", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
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


        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return IconCompat.createWithAdaptiveBitmap(bitmap) // API 26+
    }
    private fun buildFacebookAdaptiveIcon(): IconCompat {
        val size = resources.getDimensionPixelSize(android.R.dimen.app_icon_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(this, R.drawable.ic_facebook_adaptive_background)!!
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
        val wrappedDrawable = DrawableCompat.wrap(bg)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#000000")) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN)

        val fg = AppCompatResources.getDrawable(this, R.drawable.ic_telegram_adaptive_foreground)!!
        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return Icon.createWithBitmap(bitmap) // API 26+
    }

    private fun buildFacebookIcon(): Icon {
        val size = resources.getDimensionPixelSize(android.R.dimen.app_icon_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(this, R.drawable.ic_facebook_adaptive_background)!!
        val wrappedDrawable = DrawableCompat.wrap(bg)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#000000")) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN)

        val fg = AppCompatResources.getDrawable(this, R.drawable.ic_facebook_adaptive_foreground)!!
        val wrappedDrawable2 = DrawableCompat.wrap(fg)
        DrawableCompat.setTint(wrappedDrawable2, Color.parseColor("#303030")) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable2, PorterDuff.Mode.SRC_IN)

        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return Icon.createWithBitmap(bitmap) // API 26+
    }

    private fun createFacebookShortcut() {
        val facebookIntent = Intent(this, FacebookLauncherActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val shortcutID = "shortcut_facebook"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = getSystemService(ShortcutManager::class.java)
            val existingShortcuts = shortcutManager?.dynamicShortcuts?.map { it.id } ?: emptyList()
            val pinnedShortcuts = shortcutManager?.pinnedShortcuts?.map { it.id } ?: emptyList()


            pinnedShortcuts.forEach {
                if (it == shortcutID) {
                    Toast.makeText(this, "SHORTCUT ALREADY EXIST", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            if (shortcutManager.isRequestPinShortcutSupported) {
                val shortcutInfo = ShortcutInfo.Builder(this, shortcutID)
                    .setShortLabel("Facebook")
                    .setLongLabel("Mở Facebook")
                    .setIcon(buildFacebookIcon())
                    .setIntent(facebookIntent)
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
                    .setShortLabel("Facebook")
                    .setLongLabel("Mở Facebook")
                    .setIcon(buildFacebookAdaptiveIcon())
                    .setIntent(facebookIntent)
                    .build()

                ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
                Toast.makeText(this, "SHORTCUT ADDED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "SHORTCUT NOT SUPPORT", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
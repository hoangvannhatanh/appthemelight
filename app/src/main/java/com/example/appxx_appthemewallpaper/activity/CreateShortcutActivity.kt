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
import com.example.appxx_appthemewallpaper.databinding.ActivityCreateShortcutBinding

class CreateShortcutActivity : BaseActivity<ActivityCreateShortcutBinding>(R.layout.activity_create_shortcut) {
    override fun setBinding(layoutInflater: LayoutInflater) = ActivityCreateShortcutBinding.inflate(layoutInflater)

    override fun bindComponent() {

    }

    override fun bindData() {

    }

    override fun bindEvent() {
        binding.btnCreateTelegramShortcut.setOnClickListener {
            val shortcutID = "shortcut_telegram"
            createShortcut(shortcutID, TelegramLauncherActivity::class.java, "Telegram", R.drawable.ic_telegram_adaptive_background, R.drawable.ic_telegram_adaptive_foreground)
        }
        
        binding.btnCreateFacbookShortcut.setOnClickListener {
            val shortcutID = "shortcut_facebook"
            createShortcut(shortcutID, FacebookLauncherActivity::class.java, "Facebook", R.drawable.ic_facebook_adaptive_background, R.drawable.ic_facebook_adaptive_foreground)
        }
        
        binding.btnCreateNetflixShortcut.setOnClickListener {
            val shortcutID = "shortcut_netflix"
            createShortcut(shortcutID, NetflixLauncherActivity::class.java, "Netflix", R.drawable.ic_netflix_adaptive_background, R.drawable.ic_netflix_adaptive_foreground)
        }
    }

    private fun createShortcut(shortcutID: String, activity: Class<*>, label: String, backGround: Int, icon: Int) {
        val telegramIntent = Intent(this, activity).apply {
            action = Intent.ACTION_VIEW
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

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
                    .setShortLabel(label)
                    .setLongLabel(label)
                    .setIcon(
                        buildIcon(
                            backGround = backGround, icon = icon
                        )
                    )
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
                    .setShortLabel(label)
                    .setLongLabel(label)
                    .setIcon(
                        buildAdaptiveIcon(
                            backGround = backGround, icon = icon
                        )
                    )
                    .setIntent(telegramIntent)
                    .build()

                ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
                Toast.makeText(this, "SHORTCUT ADDED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "SHORTCUT NOT SUPPORT", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buildAdaptiveIcon(backGround: Int, icon: Int): IconCompat {
        val size = resources.getDimensionPixelSize(android.R.dimen.app_icon_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(this, backGround)!!
        val wrappedDrawable = DrawableCompat.wrap(bg)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#000000")) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN)

        val fg = AppCompatResources.getDrawable(this, icon)!!
        val wrappedDrawable2 = DrawableCompat.wrap(fg)
        DrawableCompat.setTint(wrappedDrawable2, Color.parseColor("#303030")) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable2, PorterDuff.Mode.SRC_IN)


        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return IconCompat.createWithAdaptiveBitmap(bitmap) // API 26+
    }

    private fun buildIcon(backGround: Int, icon: Int): Icon {
        val size = resources.getDimensionPixelSize(android.R.dimen.app_icon_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(this, backGround)!!
        val wrappedDrawable = DrawableCompat.wrap(bg)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#000000")) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN)

        val fg = AppCompatResources.getDrawable(this, icon)!!
        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return Icon.createWithBitmap(bitmap) // API 26+
    }
}
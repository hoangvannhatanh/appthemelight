package com.example.appxx_appthemewallpaper.activity

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.view.LayoutInflater
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
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
//            createTelegramShortcut()
        }
        
        binding.btnCreateFacbookShortcut.setOnClickListener {
//            createFacebookShortcut()
        }
    }



//    private fun createTelegramShortcut() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            // For Android O and above
//            val shortcutManager = getSystemService(ShortcutManager::class.java)
//
//            if (shortcutManager != null) {
//                if (shortcutManager.isRequestPinShortcutSupported) {
//                    // Create intent that launches our TelegramLauncherActivity
//                    val telegramIntent = Intent(this, TelegramLauncherActivity::class.java).apply {
//                        action = Intent.ACTION_VIEW
//                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
//                    }
//
//                    val shortcutInfo = ShortcutInfo.Builder(this, TELEGRAM_SHORTCUT_ID)
//                        .setShortLabel(getString(R.string.shortcut_short_label))
//                        .setLongLabel(getString(R.string.shortcut_long_label))
//                        .setIcon(Icon.createWithResource(this, R.drawable.ic_shortcut_telegram))
//                        .setIntent(telegramIntent)
//                        .build()
//
//                    val pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(shortcutInfo)
//
//                    val successCallback = PendingIntent.getBroadcast(
//                        this, 0,
//                        pinnedShortcutCallbackIntent,
//                        PendingIntent.FLAG_IMMUTABLE
//                    )
//
//                    shortcutManager.requestPinShortcut(shortcutInfo, successCallback.intentSender)
//                    Toast.makeText(this, getString(R.string.shortcut_added), Toast.LENGTH_SHORT).show()
//                }
//            }
//        } else {
//            // For older Android versions, use ShortcutManagerCompat
//            val telegramIntent = Intent(this, TelegramLauncherActivity::class.java).apply {
//                action = Intent.ACTION_VIEW
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
//            }
//
//            val shortcutInfo = ShortcutInfoCompat.Builder(this, TELEGRAM_SHORTCUT_ID)
//                .setShortLabel("hehe")
//                .setLongLabel(getString(R.string.shortcut_long_label))
//                .setIcon(IconCompat.createWithResource(this, R.drawable.ic_shortcut_telegram))
//                .setIntent(telegramIntent)
//                .build()
//
//            if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
//                ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
//                Toast.makeText(this, getString(R.string.shortcut_added), Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun createFacebookShortcut() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            // For Android O and above
//            val shortcutManager = getSystemService(ShortcutManager::class.java)
//
//            if (shortcutManager != null) {
//                if (shortcutManager.isRequestPinShortcutSupported) {
//                    // Create intent that launches our FacebookLauncherActivity
//                    val facebookIntent = Intent(this, FacebookLauncherActivity::class.java).apply {
//                        action = Intent.ACTION_VIEW
//                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
//                    }
//
////                    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_facebook) //PNG
//                    val bitmap = getBitmapFromVectorDrawable(R.drawable.ic_shortcut_facebook) //XML
//                    val shortcutInfo = ShortcutInfo.Builder(this, FACEBOOK_SHORTCUT_ID)
//                        .setShortLabel(getString(R.string.facebook_shortcut_short_label))
//                        .setLongLabel(getString(R.string.facebook_shortcut_long_label))
//                        .setIcon(Icon.createWithResource(this, R.drawable.ic_facebook))
//                        .setIntent(facebookIntent)
//                        .build()
//
//                    val pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(shortcutInfo)
//
//                    val successCallback = PendingIntent.getBroadcast(
//                        this, 0,
//                        pinnedShortcutCallbackIntent,
//                        PendingIntent.FLAG_IMMUTABLE
//                    )
//
//                    shortcutManager.requestPinShortcut(shortcutInfo, successCallback.intentSender)
//                    Toast.makeText(this, getString(R.string.shortcut_added), Toast.LENGTH_SHORT).show()
//                }
//            }
//        } else {
//            // For older Android versions, use ShortcutManagerCompat
//            val facebookIntent = Intent(this, FacebookLauncherActivity::class.java).apply {
//                action = Intent.ACTION_VIEW
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
//            }
//            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_shortcut_facebook)
//            val shortcutInfo = ShortcutInfoCompat.Builder(this, FACEBOOK_SHORTCUT_ID)
//                .setShortLabel(getString(R.string.facebook_shortcut_short_label))
//                .setLongLabel(getString(R.string.facebook_shortcut_long_label))
//                .setIcon(IconCompat.createWithResource(this, R.drawable.ic_facebook))
//                .setIntent(facebookIntent)
//                .build()
//
//            if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
//                ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
//                Toast.makeText(this, getString(R.string.shortcut_added), Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private fun getBitmapFromVectorDrawable(drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(this, drawableId)!!
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

}
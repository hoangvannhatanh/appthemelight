package com.example.appxx_appthemewallpaper.activity_launcher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TikTokLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchTikTok()
        finish()
    }

    private fun launchTikTok() {
        try {
            // Try to launch TikTok app
            val intent1 = packageManager.getLaunchIntentForPackage("com.zhiliaoapp.musically")
            val intent2 = packageManager.getLaunchIntentForPackage("com.ss.android.ugc.trill")
            if (intent1 != null) {
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent1)
            } else if (intent2 != null) {
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent2)
            } else {
                // If TikTok app is not installed, open Play Store
                val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.zhiliaoapp.musically"))
                playStoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(playStoreIntent)
            }
        } catch (e: Exception) {
            // If Play Store is not available, open browser
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.zhiliaoapp.musically"))
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(browserIntent)
            } catch (ex: Exception) {
                Toast.makeText(this, "Không thể mở TikTok", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

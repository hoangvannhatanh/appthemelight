package com.example.appxx_appthemewallpaper.activity_launcher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class InstagramLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchInstagram()
        finish()
    }

    private fun launchInstagram() {
        try {
            // Try to launch Instagram app
            val intent = packageManager.getLaunchIntentForPackage("com.instagram.android")
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                // If Instagram app is not installed, open Play Store
                val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.instagram.android"))
                playStoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(playStoreIntent)
            }
        } catch (e: Exception) {
            // If Play Store is not available, open browser
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.instagram.android"))
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(browserIntent)
            } catch (ex: Exception) {
                Toast.makeText(this, "Không thể mở Instagram", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

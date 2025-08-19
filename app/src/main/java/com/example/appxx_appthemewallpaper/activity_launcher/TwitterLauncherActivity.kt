package com.example.appxx_appthemewallpaper.activity_launcher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TwitterLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchTwitter()
        finish()
    }

    private fun launchTwitter() {
        try {
            // Try to launch Twitter/X app
            val intent = packageManager.getLaunchIntentForPackage("com.twitter.android")
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                // If Twitter app is not installed, open Play Store
                val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.twitter.android"))
                playStoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(playStoreIntent)
            }
        } catch (e: Exception) {
            // If Play Store is not available, open browser
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.twitter.android"))
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(browserIntent)
            } catch (ex: Exception) {
                Toast.makeText(this, "Không thể mở Twitter", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

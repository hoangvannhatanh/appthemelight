package com.example.appxx_appthemewallpaper.activity_launcher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChromeLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchChrome()
        finish()
    }

    private fun launchChrome() {
        try {
            // Try to launch Chrome browser
            val intent = packageManager.getLaunchIntentForPackage("com.android.chrome")
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                // If Chrome is not installed, try to open default browser
                try {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(browserIntent)
                } catch (ex: Exception) {
                    // If no browser available, open Play Store to install Chrome
                    val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.android.chrome"))
                    playStoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(playStoreIntent)
                }
            }
        } catch (e: Exception) {
            // If Play Store is not available, open browser
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.android.chrome"))
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(browserIntent)
            } catch (ex: Exception) {
                Toast.makeText(this, "Không thể mở Chrome", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

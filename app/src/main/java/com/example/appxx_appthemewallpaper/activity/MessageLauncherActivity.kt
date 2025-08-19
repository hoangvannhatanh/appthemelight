package com.example.appxx_appthemewallpaper.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MessageLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchMessage()
        finish()
    }

    private fun launchMessage() {
        try {
            // Try to launch default SMS app
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_APP_MESSAGING)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: Exception) {
            // Fallback: try to open SMS app directly
            try {
                val smsIntent = Intent(Intent.ACTION_SENDTO)
                smsIntent.data = Uri.parse("smsto:")
                smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(smsIntent)
            } catch (ex: Exception) {
                Toast.makeText(this, "Không thể mở ứng dụng tin nhắn", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

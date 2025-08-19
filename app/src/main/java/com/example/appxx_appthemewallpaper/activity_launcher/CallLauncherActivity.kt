package com.example.appxx_appthemewallpaper.activity_launcher

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CallLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchCall()
        finish()
    }

    private fun launchCall() {
        try {
            // Try to launch phone dialer
            val intent = Intent(Intent.ACTION_DIAL)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: Exception) {
            // Fallback: try to open phone app directly
            try {
                val phoneIntent = Intent(Intent.ACTION_MAIN)
                phoneIntent.addCategory(Intent.CATEGORY_APP_CONTACTS)
                phoneIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(phoneIntent)
            } catch (ex: Exception) {
                Toast.makeText(this, "Không thể mở ứng dụng gọi điện", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

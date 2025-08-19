package com.example.appxx_appthemewallpaper.activity_launcher

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TelegramLauncherActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        openTelegram()
        finish()
    }
    
    private fun openTelegram() {
        val telegramPackage = "org.telegram.messenger"
        val telegramXPackage = "org.thunderdog.challegram"
        
        when {
            isPackageInstalled(telegramPackage) -> {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    component = ComponentName(telegramPackage, "$telegramPackage.DefaultIcon")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                startActivity(intent)
            }
            isPackageInstalled(telegramXPackage) -> {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    component = ComponentName(telegramXPackage, "$telegramXPackage.MainActivity")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                startActivity(intent)
            }
            else -> {
                Toast.makeText(this, "Telegram not installed", Toast.LENGTH_SHORT).show()
                // Open Play Store to install Telegram
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("market://details?id=$telegramPackage")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    // If Play Store is not available, open browser
                    val webIntent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://play.google.com/store/apps/details?id=$telegramPackage")
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(webIntent)
                }
            }
        }
    }
    
    private fun isPackageInstalled(packageName: String): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
} 
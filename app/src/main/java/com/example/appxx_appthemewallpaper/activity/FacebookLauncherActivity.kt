package com.example.appxx_appthemewallpaper.activity

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FacebookLauncherActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        openFacebook()
        finish()
    }
    
    private fun openFacebook() {
        val facebookPackage = "com.facebook.katana"
        val facebookLitePackage = "com.facebook.lite"
        
        when {
            isPackageInstalled(facebookPackage) -> {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    component = ComponentName(facebookPackage, "$facebookPackage.LoginActivity")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                startActivity(intent)
            }
            isPackageInstalled(facebookLitePackage) -> {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    component = ComponentName(facebookLitePackage, "$facebookLitePackage.MainActivity")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                startActivity(intent)
            }
            else -> {
                Toast.makeText(this, "Facebook not installed", Toast.LENGTH_SHORT).show()
                // Open Play Store to install Facebook
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("market://details?id=$facebookPackage")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    // If Play Store is not available, open browser
                    val webIntent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://play.google.com/store/apps/details?id=$facebookPackage")
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
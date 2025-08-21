package com.example.appxx_appthemewallpaper.activity_launcher

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AppLauncherActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		open()
	}

	private fun open() {
		val targetPackage = intent.getStringExtra(EXTRA_TARGET_PACKAGE)
		if (targetPackage.isNullOrEmpty()) {
			finish()
			return
		}
		
		when {
			isPackageInstalled(targetPackage) -> {
				val launchIntent = packageManager.getLaunchIntentForPackage(targetPackage)
				if (launchIntent != null) {
					launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
					startActivity(launchIntent)
				}
			}
			else -> {
				val intent = Intent(Intent.ACTION_VIEW).apply {
					data = Uri.parse("market://details?id=$targetPackage")
					flags = Intent.FLAG_ACTIVITY_NEW_TASK
				}
				try {
					startActivity(intent)
				} catch (e: Exception) {
					// If Play Store is not available, open browser
					val webIntent = Intent(Intent.ACTION_VIEW).apply {
						data = Uri.parse("https://play.google.com/store/apps/details?id=$targetPackage")
						flags = Intent.FLAG_ACTIVITY_NEW_TASK
					}
					startActivity(webIntent)
				}
			}
		}
		finish()
	}

	private fun isPackageInstalled(packageName: String): Boolean {
		return try {
			packageManager.getPackageInfo(packageName, 0)
			true
		} catch (e: PackageManager.NameNotFoundException) {
			false
		}
	}

	companion object {
		const val EXTRA_TARGET_PACKAGE = "extra_target_package"
	}
}



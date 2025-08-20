package com.example.appxx_appthemewallpaper.activity_launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AppLauncherActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val targetPackage = intent.getStringExtra(EXTRA_TARGET_PACKAGE)
		if (targetPackage.isNullOrEmpty()) {
			finish()
			return
		}

		val launchIntent = packageManager.getLaunchIntentForPackage(targetPackage)
		if (launchIntent != null) {
			launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
			startActivity(launchIntent)
		}

		finish()
	}

	companion object {
		const val EXTRA_TARGET_PACKAGE = "extra_target_package"
	}
}



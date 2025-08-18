package com.example.appxx_appthemewallpaper.activity

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ActivitySplashBinding
import com.example.appxx_appthemewallpaper.extensions.invisible
import com.example.appxx_appthemewallpaper.extensions.setPref
import com.example.appxx_appthemewallpaper.extensions.show
import com.example.appxx_appthemewallpaper.extensions.showActivity
import com.example.appxx_appthemewallpaper.util.CallBack
import com.example.appxx_appthemewallpaper.util.PREFERENCE_SELECTED_LANGUAGE

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun setBinding(layoutInflater: LayoutInflater) = ActivitySplashBinding.inflate(layoutInflater)

    override fun bindData() {}

    override fun bindEvent() {}

    override fun bindComponent() {
        setPref(this, PREFERENCE_SELECTED_LANGUAGE, "")

        Handler(Looper.getMainLooper()).postDelayed({
            openNextScreen()
        }, 3000)

        loadProgressBarAnimation()
        animateViews()
    }

    private fun openNextScreen() {
        showActivity(LanguageStartActivity::class.java)
        finishAffinity()
    }

    private fun animateViews() {
        val textViews = listOf(
            binding.cvLogo,
            binding.tvAppName,
            binding.loading,
            binding.tvLoading,
            binding.loadFile,
            binding.txtMessAdvertising
        )

        textViews.forEach {
            it.invisible()
            it.translationY = 100f
            it.alpha = 0f
        }

        val handler = Handler(Looper.getMainLooper())
        for (i in textViews.indices) {
            handler.postDelayed({
                textViews[i].show()
                textViews[i].animate()
                    .translationY(0f)
                    .alpha(1f)
                    .setDuration(600)
                    .start()
            }, i * 200L)
        }
    }

    private fun loadProgressBarAnimation() {
        binding.loadFile.onProgress = object : CallBack.ICallBackProgress {
            override fun onProgress(progress: Int) {
                binding.tvLoading.text = "Loading ($progress%)..."
            }
        }
    }
}
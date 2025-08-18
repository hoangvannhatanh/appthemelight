package com.example.appxx_appthemewallpaper.activity

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.UninstallTwoActivityBinding
import com.example.appxx_appthemewallpaper.extensions.setBackGroundDrawable
import com.example.appxx_appthemewallpaper.extensions.showActivity

class UninstallTwoActivity : BaseActivity<UninstallTwoActivityBinding>(R.layout.uninstall_two_activity) {

    override fun setBinding(layoutInflater: LayoutInflater) = UninstallTwoActivityBinding.inflate(layoutInflater)

    override fun bindComponent() {

    }

    override fun bindData() {

    }

    override fun bindEvent() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.tvCancel.setOnClickListener {
            showActivity(MainActivity::class.java)
            finishAffinity()
        }
        binding.tvUninstall.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
        }
        binding.cv1.setOnClickListener {
            binding.cv1.setBackGroundDrawable(R.drawable.background_button_gradient_10)
            binding.cv2.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv3.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv4.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv5.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv6.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.tv1.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv2.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv3.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv4.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv5.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv6.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
        binding.cv2.setOnClickListener {
            binding.cv1.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv2.setBackGroundDrawable(R.drawable.background_button_gradient_10)
            binding.cv3.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv4.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv5.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv6.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.tv1.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv2.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv3.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv4.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv5.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv6.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
        binding.cv3.setOnClickListener {
            binding.cv1.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv2.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv3.setBackGroundDrawable(R.drawable.background_button_gradient_10)
            binding.cv4.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv5.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv6.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.tv1.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv2.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv3.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv4.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv5.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv6.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
        binding.cv4.setOnClickListener {
            binding.cv1.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv2.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv3.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv4.setBackGroundDrawable(R.drawable.background_button_gradient_10)
            binding.cv5.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv6.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.tv1.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv2.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv3.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv4.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv5.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv6.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
        binding.cv5.setOnClickListener {
            binding.cv1.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv2.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv3.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv4.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv5.setBackGroundDrawable(R.drawable.background_button_gradient_10)
            binding.cv6.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.tv1.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv2.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv3.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv4.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv5.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv6.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
        binding.cv6.setOnClickListener {
            binding.cv1.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv2.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv3.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv4.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv5.setBackGroundDrawable(R.drawable.background_button_gray_10)
            binding.cv6.setBackGroundDrawable(R.drawable.background_button_gradient_10)
            binding.tv1.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv2.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv3.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv4.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv5.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.tv6.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }
}
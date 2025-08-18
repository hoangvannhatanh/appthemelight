package com.example.appxx_appthemewallpaper.activity

import android.view.LayoutInflater
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.UninstallActivityBinding
import com.example.appxx_appthemewallpaper.extensions.onClick
import com.example.appxx_appthemewallpaper.extensions.showActivity

class UninstallActivity : BaseActivity<UninstallActivityBinding>(R.layout.uninstall_activity) {

    override fun setBinding(layoutInflater: LayoutInflater) = UninstallActivityBinding.inflate(layoutInflater)

    override fun bindComponent() {

    }

    override fun bindData() {}

    override fun bindEvent() {
        binding.btnRestart.setOnClickListener {
            showActivity(MainActivity::class.java)
            finishAffinity()
        }
        binding.btnExplore.setOnClickListener {
            showActivity(MainActivity::class.java)
            finishAffinity()
        }
        binding.ivBack.setOnClickListener {
            showActivity(MainActivity::class.java)
            finishAffinity()
        }
        binding.tvCancel.onClick(1000) {
            showActivity(UninstallTwoActivity::class.java)
        }
        binding.tvUninstall.setOnClickListener {
            showActivity(MainActivity::class.java)
            finishAffinity()
        }
    }
}
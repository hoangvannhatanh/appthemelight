package com.example.appxx_appthemewallpaper.activity

import android.view.LayoutInflater
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ActivityMainBinding
import com.example.appxx_appthemewallpaper.extensions.showActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun setBinding(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)

    override fun bindComponent() {

    }

    override fun bindData() {

    }

    override fun bindEvent() {
        binding.btnCreateShortcut.setOnClickListener {
            showActivity(CreateShortcutActivity::class.java)
        }
        binding.btnEditShortcut.setOnClickListener {
            showActivity(EditShortcutActivity::class.java)
        }
        binding.btnCreateWidget.setOnClickListener {
            showActivity(CreateWidgetActivity::class.java)
        }
    }
}

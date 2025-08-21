package com.example.appxx_appthemewallpaper.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ActivitySelectTopicShortcutBinding
import com.example.appxx_appthemewallpaper.extensions.showActivity

class SelectTopicShortcutActivity : BaseActivity<ActivitySelectTopicShortcutBinding>(R.layout.activity_select_topic_shortcut) {

    override fun setBinding(layoutInflater: LayoutInflater) = ActivitySelectTopicShortcutBinding.inflate(layoutInflater)

    override fun bindComponent() {

    }

    override fun bindData() {

    }

    override fun bindEvent() {
        binding.btnTheme1.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("TOPIC_SHORTCUT", "1")
            showActivity(CreateShortcutActivity::class.java, bundle)
        }
        binding.btnTheme2.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("TOPIC_SHORTCUT", "2")
            showActivity(CreateShortcutActivity::class.java, bundle)
        }
    }
}

package com.example.appxx_appthemewallpaper.activity

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.adapter.FontAdapter
import com.example.appxx_appthemewallpaper.databinding.ActivityFontBinding
import com.example.appxx_appthemewallpaper.util.CallBack
import com.example.appxx_appthemewallpaper.util.getListFont

class FontActivity : BaseActivity<ActivityFontBinding>(R.layout.activity_font) {
    private var listFont: MutableList<String> = arrayListOf()
    private val fontAdapter by lazy { FontAdapter() }

    override fun setBinding(layoutInflater: LayoutInflater) = ActivityFontBinding.inflate(layoutInflater)

    override fun bindComponent() {
        listFont = getListFont()
        initRecyclerviewFont()
    }

    override fun bindData() {}

    override fun bindEvent() {
        fontAdapter.callBackFont(object : CallBack.CallBackFont {
            override fun callBackFont(font: String, position: Int) {
                val intent = Intent()
                intent.putExtra("KEY_POSITION", position)
                intent.putExtra("KEY_FONT", font)
                setResult(Activity.RESULT_OK, intent)
                onBackPressed()
            }
        })
    }

    private fun initRecyclerviewFont() {
        binding.recyclerViewFont.apply {
            layoutManager = LinearLayoutManager(this@FontActivity, LinearLayoutManager.VERTICAL, false)
            fontAdapter.addAll(listFont)
            adapter = fontAdapter
        }
    }
}
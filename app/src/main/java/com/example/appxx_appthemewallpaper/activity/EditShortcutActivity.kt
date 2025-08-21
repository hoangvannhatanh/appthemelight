package com.example.appxx_appthemewallpaper.activity

import android.app.PendingIntent
import android.view.LayoutInflater
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.activity_launcher.TelegramLauncherActivity
import com.example.appxx_appthemewallpaper.adapter.BackgroundColorAdapter
import com.example.appxx_appthemewallpaper.adapter.IconColorAdapter
import com.example.appxx_appthemewallpaper.adapter.FontHorizontalAdapter
import com.example.appxx_appthemewallpaper.databinding.ActivityEditShortcutBinding
import com.example.appxx_appthemewallpaper.extensions.showActivity
import com.example.appxx_appthemewallpaper.util.*

class EditShortcutActivity : BaseActivity<ActivityEditShortcutBinding>(R.layout.activity_edit_shortcut) {

    private var listFont: MutableList<String> = arrayListOf()
    private val fontAdapter by lazy { FontHorizontalAdapter() }
    private var listIconColor: MutableList<String> = arrayListOf()
    private var listBackgroundColor: MutableList<String> = arrayListOf()
    private val colorAdapter by lazy { IconColorAdapter() }
    private val backgroundAdapter by lazy { BackgroundColorAdapter() }
    private var strColor = ""
    private var strBackground = ""
    private var strFont = "Default"

    override fun setBinding(layoutInflater: LayoutInflater) = ActivityEditShortcutBinding.inflate(layoutInflater)

    override fun bindComponent() {
        listIconColor.apply {
            add("#ffffff")
            add("#000000")
            add("#27A7E7")
            add("#FDB5C0")
            add("#677892")
            add("#202020")
        }
        initRecyclerviewColor()

        listBackgroundColor.apply {
            add("#000000")
            add("#ffffff")
            add("#27A7E7")
            add("#FDB5C0")
            add("#677892")
            add("#202020")
        }
        initRecyclerviewBackground()

        listFont = getListFont()
        initRecyclerviewFont()
    }

    override fun bindData() {}

    override fun bindEvent() {
        fontAdapter.callBackFont(object : CallBack.CallBackFont {
            override fun callBackFont(font: String, position: Int) {
                fontAdapter.checkSelectView(position)

                val text = getString(R.string.app_name)
                when (font) {
                    "Default" -> binding.tvTitle.text = text
                    "Roboto" -> binding.tvTitle.text = toRoboto(text)
                    "General Sans" -> binding.tvTitle.text = toGeneralsans(text)
                    "Helvetica Neue" -> binding.tvTitle.text = toHelveticaNeue(text)
                    "Fraktur", "Gothic" -> binding.tvTitle.text = toFraktur(text)
                    "Kanit" -> binding.tvTitle.text = toKanit(text)
                    "Satoshi" -> binding.tvTitle.text = toSatoshi(text)
                    "Poppins" -> binding.tvTitle.text = toPoppins(text)
                    "Product Sans" -> binding.tvTitle.text = toProductSans(text)
                }

                strFont = font
            }
        })

        colorAdapter.callBackColor(object : CallBack.CallBackColor {
            override fun callBackColor(color: String, position: Int) {
                colorAdapter.checkSelectView(position)

                strColor = color
                binding.ivIcon.setColorFilter(Color.parseColor(strColor), PorterDuff.Mode.SRC_IN)
            }
        })

        backgroundAdapter.callBackColor(object : CallBack.CallBackColor {
            override fun callBackColor(color: String, position: Int) {
                backgroundAdapter.checkSelectView(position)

                strBackground = color
                binding.loBackground.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
            }
        })

        binding.tvNext.setOnClickListener {
            if (strFont.isEmpty()) {
                return@setOnClickListener
            }
            if (strColor.isEmpty()) {
                return@setOnClickListener
            }
            if (strBackground.isEmpty()) {
                return@setOnClickListener
            }
            val bundle = Bundle()
            bundle.putString("KEY_COLOR_ICON", strColor)
            bundle.putString("KEY_COLOR_BACKGROUND", strBackground)
            bundle.putString("KEY_FONT", strFont)
            showActivity(CreateEditShortcutActivity::class.java, bundle)
        }
    }

    private fun initRecyclerviewFont() {
        binding.recyclerViewFont.apply {
            layoutManager = LinearLayoutManager(this@EditShortcutActivity, LinearLayoutManager.HORIZONTAL, false)
            fontAdapter.addAll(listFont)
            adapter = fontAdapter
        }
    }

    private fun initRecyclerviewColor() {
        binding.recyclerViewColor.apply {
            layoutManager = LinearLayoutManager(this@EditShortcutActivity, LinearLayoutManager.HORIZONTAL, false)
            colorAdapter.addAll(listIconColor)
            adapter = colorAdapter
        }
    }

    private fun initRecyclerviewBackground() {
        binding.recyclerViewBackground.apply {
            layoutManager = LinearLayoutManager(this@EditShortcutActivity, LinearLayoutManager.HORIZONTAL, false)
            backgroundAdapter.addAll(listBackgroundColor)
            adapter = backgroundAdapter
        }
    }
}
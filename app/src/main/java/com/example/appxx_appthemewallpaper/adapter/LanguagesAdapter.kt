package com.example.appxx_appthemewallpaper.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ItemLanguageBinding
import com.example.appxx_appthemewallpaper.extensions.isSingleCLick
import com.example.appxx_appthemewallpaper.extensions.setBackGroundDrawable
import com.example.appxx_appthemewallpaper.model.Language
import com.example.appxx_appthemewallpaper.util.CallBack

class LanguagesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<Language> = arrayListOf()

    var currentPos: Int = -1
    var isHandLoading = true

    private var callBackLanguage: CallBack.CallBackLanguage? = null

    fun callBackLanguage(callBackLanguage: CallBack.CallBackLanguage) {
        this.callBackLanguage = callBackLanguage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(mData: MutableList<Language>, currentPos: Int) {
        this.data = mData
        this.currentPos = currentPos
    }

    fun checkSelectView(pos: Int) {
        val oldPos = currentPos
        currentPos = pos
        notifyItemChanged(pos)
        notifyItemChanged(oldPos)
    }

    fun hideHandLoading() {
        isHandLoading = false
        if (data.size >= 3) notifyItemChanged(3)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            binding.txtLanguageTitle.text = data[position].name
            binding.imLangLogo.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    data[position].img
                )
            )

            when (data[position].name) {
                "English" -> binding.txtContent.text = "English"
                "French" -> binding.txtContent.text = "Français"
                "Spanish" -> binding.txtContent.text = "Español"
                "German" -> binding.txtContent.text = "Deutsch"
                "Arabic" -> binding.txtContent.text = "عربي"
                "Indonesian" -> binding.txtContent.text = "Bahasa"
                "Malaysia" -> binding.txtContent.text = "Bahasa Melayu (Malaysia)"
                "Turkish" -> binding.txtContent.text = "Türkçe"
                "Korean" -> binding.txtContent.text = "日本語"
                "Japanese" -> binding.txtContent.text = "日本語"
                "Russian" -> binding.txtContent.text = "Русский"
                "Bengali" -> binding.txtContent.text = "বাংলা"
            }
            when (data[position].key) {
                "zh" -> binding.txtContent.text = "繁體中文"
                "zh-rTW" -> binding.txtContent.text = "簡體中文"
                "th" -> binding.txtContent.text = "ไทย"
                "hi" -> binding.txtContent.text = "हिंदी भाषा"
                "pt" -> {
                    binding.txtLanguageTitle.text = "Portuguese"
                    binding.txtContent.text = "Português"
                }
                "pt-rBR" -> {
                    binding.txtLanguageTitle.text = "Portuguese"
                    binding.txtContent.text = "(Português) Brasil"
                }
            }

            if (currentPos == position) {
                binding.loItem.setBackGroundDrawable(R.drawable.background_button_gradient_10)
                binding.txtLanguageTitle.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.black
                    )
                )
                binding.txtContent.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.color_677892
                    )
                )
            } else {
                binding.loItem.setBackGroundDrawable(R.drawable.background_button_white_10)
                binding.txtLanguageTitle.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.black
                    )
                )
                binding.txtContent.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.color_677892
                    )
                )
            }

            binding.root.setOnClickListener {
                if (!isSingleCLick()) {
                    return@setOnClickListener
                }
                callBackLanguage?.callBackLanguage(data[position].name, data[position].key, position)
            }
        }
    }
}
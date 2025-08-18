package com.example.appxx_appthemewallpaper.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.adapter.LanguagesSettingAdapter
import com.example.appxx_appthemewallpaper.databinding.*
import com.example.appxx_appthemewallpaper.extensions.*
import com.example.appxx_appthemewallpaper.model.*
import com.example.appxx_appthemewallpaper.util.*

class LanguageSettingActivity : BaseActivity<ActivityLanguageSettingBinding>(R.layout.activity_language_setting) {
    private var listLanguage: MutableList<Language> = ArrayList()
    private lateinit var stringLanguage: String
    private val languagesAdapter by lazy { LanguagesSettingAdapter() }

    override fun setBinding(layoutInflater: LayoutInflater) = ActivityLanguageSettingBinding.inflate(layoutInflater)

    override fun bindComponent() {
        stringLanguage = getPref(this, PREFERENCE_SELECTED_LANGUAGE, getString(R.string.english)).toString()

        textTranslateHeader(stringLanguage)

        populateLanguageList()

        selectLanguage()

        initRecyclerview()
    }

    override fun bindData() {

    }

    private fun selectLanguage() {
        stringLanguage = getPref(this@LanguageSettingActivity, PREFERENCE_SELECTED_LANGUAGE, "").toString()

        textTranslateHeader(stringLanguage)

        val selectedIndex = listLanguage.indexOfFirst { it.key == stringLanguage }

        if (selectedIndex >= 0) {
            val item = listLanguage.removeAt(selectedIndex)
            listLanguage.add(3, item)
        }
    }

    override fun bindEvent() {
        languagesAdapter.callBackLanguage(object : CallBack.CallBackLanguage {
            override fun callBackLanguage(language: String, key: String, position: Int) {
                stringLanguage = key
                languagesAdapter.checkSelectView(position)
                textTranslateHeader(stringLanguage)
            }
        })

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.ivTick.onClick(500) {
            setPref(this@LanguageSettingActivity, PREFERENCE_SELECTED_LANGUAGE, stringLanguage)
            showActivity(MainActivity::class.java)
            finishAffinity()
        }

        binding.txtLanguageTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                languagesAdapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun populateLanguageList() {
        listLanguage.apply {
            add(createLanguageItem(R.drawable.ic_english, R.string.english, "en"))
            add(createLanguageItem(R.drawable.ic_spanish, R.string.spanish, "es"))
            add(createLanguageItem(R.drawable.ic_france, R.string.french, "fr"))
            add(createLanguageItem(R.drawable.ic_indonesia, R.string.indonesian, "in"))
            add(createLanguageItem(R.drawable.ic_portugal, R.string.portuguese, "pt"))//
            add(createLanguageItem(R.drawable.ic_brazil, R.string.brazil, "pt-rBR"))//
            add(createLanguageItem(R.drawable.ic_lang_ja, R.string.japanese, "ja"))//
            add(createLanguageItem(R.drawable.ic_lang_ar, R.string.arabic, "ar"))//
            add(createLanguageItem(R.drawable.ic_lang_be, R.string.bengali, "bn"))//
            add(createLanguageItem(R.drawable.ic_lang_ru, R.string.russian, "ru"))//
            add(createLanguageItem(R.drawable.ic_turkey, R.string.turkish, "tr"))//
            add(createLanguageItem(R.drawable.ic_lang_ko, R.string.korean, "ko"))//
            add(createLanguageItem(R.drawable.ic_india, R.string.hindi, "hi"))
            add(createLanguageItem(R.drawable.ic_lang_de, R.string.german, "de"))
            add(createLanguageItem(R.drawable.ic_lang_chinese, R.string.chinese, "zh"))
            add(createLanguageItem(R.drawable.ic_lang_chinese, R.string.chinese, "zh-rTW"))
        }
    }

    private fun createLanguageItem(iconRes: Int, nameRes: Int, code: String) = Language(iconRes, getString(nameRes), code)

    private fun initRecyclerview() {
        try {
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(
                    this@LanguageSettingActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                languagesAdapter.addAll(listLanguage, 3)
                adapter = languagesAdapter
            }
        } catch (_: Exception) {
        }
    }

    private fun textTranslateHeader(stringLanguage: String) {
        when (stringLanguage) {
            "en" -> {
                binding.tvTitle.text = "Languages"
                binding.tvContent.text = "Please select language to continue"
                binding.txtLanguageTitle.hint = "Search languages"
            }

            "fr" -> {
                binding.tvTitle.text = "Langue"
                binding.tvContent.text = "Veuillez sélectionner la langue pour continuer"
                binding.txtLanguageTitle.hint = "Rechercher des langues"
            }

            "hi" -> {
                binding.tvTitle.text = "भाषा"
                binding.tvContent.text = "कृपया जारी रखने के लिए भाषा चुनें"
                binding.txtLanguageTitle.hint = "भाषाओं को ढूंढें"
            }

            "es" -> {
                binding.tvTitle.text = "Idioma"
                binding.tvContent.text = "Por favor seleccione el idioma para continuar"
                binding.txtLanguageTitle.hint = "Buscar idiomas"
            }

            "ms" -> {
                binding.tvTitle.text = "Bahasa"
                binding.tvContent.text = "Sila pilih bahasa untuk meneruskan"
                binding.txtLanguageTitle.hint = "Cari bahasa"
            }

            "de" -> {
                binding.tvTitle.text = "Sprache"
                binding.tvContent.text = "Bitte wählen Sie die Sprache aus, um fortzufahren"
                binding.txtLanguageTitle.hint = "Sprachen suchen"
            }

            "ar" -> {
                binding.tvTitle.text = "لغة"
                binding.tvContent.text = "الرجاء تحديد اللغة للمتابعة"
                binding.txtLanguageTitle.hint = "البحث عن اللغات"
            }

            "tr" -> {
                binding.tvTitle.text = "Dil"
                binding.tvContent.text = "Devam etmek için lütfen dili seçin"
                binding.txtLanguageTitle.hint = "Diller arayın"
            }

            "ko" -> {
                binding.tvTitle.text = "언어"
                binding.tvContent.text = "계속하려면 언어를 선택하세요."
                binding.txtLanguageTitle.hint = "언어 검색"
            }

            "ja" -> {
                binding.tvTitle.text = "言語"
                binding.tvContent.text = "続行するには言語を選択してください"
                binding.txtLanguageTitle.hint = "言語を検索"
            }

            "ru" -> {
                binding.tvTitle.text = "Язык"
                binding.tvContent.text = "Пожалуйста, выберите язык, чтобы продолжить"
                binding.txtLanguageTitle.hint = "Поиск языков"
            }

            "zh" -> {
                binding.tvTitle.text = "语言"
                binding.tvContent.text = "请选择语言以继续"
                binding.txtLanguageTitle.hint = "搜索语言"
            }

            "zh-rTW" -> {
                binding.tvTitle.text = "语言"
                binding.tvContent.text = "请选择语言以继续"
                binding.txtLanguageTitle.hint = "搜尋語言"
            }

            "bn" -> {
                binding.tvTitle.text = "ভাষা"
                binding.tvContent.text = "চালিয়ে যেতে ভাষা নির্বাচন করুন"
                binding.txtLanguageTitle.hint = "ভাষা খুঁজুন"
            }

            "pt" -> {
                binding.tvTitle.text = "Linguagem"
                binding.tvContent.text = "Selecione o idioma para continuar"
                binding.txtLanguageTitle.hint = "Procurar idiomas"
            }

            "pt-rBR" -> {
                binding.tvTitle.text = "Linguagem"
                binding.tvContent.text = "Selecione o idioma para continuar"
                binding.txtLanguageTitle.hint = "Procurar idiomas"
            }

            "in" -> {
                binding.tvTitle.text = "Bahasa"
                binding.tvContent.text = "Silakan pilih bahasa untuk melanjutkan"
                binding.txtLanguageTitle.hint = "Cari bahasa"
            }
        }
    }
}
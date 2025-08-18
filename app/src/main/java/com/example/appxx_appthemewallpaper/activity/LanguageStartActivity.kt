package com.example.appxx_appthemewallpaper.activity

import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.adapter.LanguagesAdapter
import com.example.appxx_appthemewallpaper.databinding.ActivityLanguageBinding
import com.example.appxx_appthemewallpaper.extensions.getPref
import com.example.appxx_appthemewallpaper.extensions.onClick
import com.example.appxx_appthemewallpaper.extensions.setPref
import com.example.appxx_appthemewallpaper.extensions.show
import com.example.appxx_appthemewallpaper.model.Language
import com.example.appxx_appthemewallpaper.util.CallBack
import com.example.appxx_appthemewallpaper.util.PREFERENCE_SELECTED_LANGUAGE

class LanguageStartActivity : BaseActivity<ActivityLanguageBinding>(R.layout.activity_language) {
    private var listLanguage: MutableList<Language> = ArrayList()
    private val languagesAdapter by lazy { LanguagesAdapter() }
    private var stringLanguage: String = ""
    private var languageDefaultDevice = ""
    private var isClickedItem = false

    override fun setBinding(layoutInflater: LayoutInflater) = ActivityLanguageBinding.inflate(layoutInflater)

    override fun bindComponent() {

        setupDynamicShortcuts()

        binding.ivTick.alpha = 0.2f
        binding.ivTick.isEnabled = false

        populateLanguageList()

        selectLanguage()

        initRecyclerview()
    }

    override fun bindData() {}

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


    private fun selectLanguage() {
        try {
            languageDefaultDevice = Resources.getSystem().configuration.locales.get(0).toString()
            languageDefaultDevice = when {
                languageDefaultDevice.lowercase().contains("in_") -> "in"
                languageDefaultDevice.lowercase().contains("es_") -> "es"
                languageDefaultDevice.lowercase().contains("fr_") -> "fr"
                languageDefaultDevice.lowercase().contains("pt_") -> "pt"
                languageDefaultDevice.lowercase().contains("pt_br") -> "pt-rBR"
                languageDefaultDevice.lowercase().contains("hi_") -> "hi"
                languageDefaultDevice.lowercase().contains("de_") -> "de"
                languageDefaultDevice.lowercase().contains("ms_") -> "ms"
                languageDefaultDevice.lowercase().contains("ar_") -> "ar"
                languageDefaultDevice.lowercase().contains("tr") -> "tr"
                languageDefaultDevice.lowercase().contains("ja") -> "ja"
                languageDefaultDevice.lowercase().contains("ko") -> "ko"
                languageDefaultDevice.lowercase().contains("bn") -> "bn"
                languageDefaultDevice.lowercase().contains("zh") -> "zh"
                languageDefaultDevice.lowercase().contains("ru") -> "ru"
                else -> {
                    "en"
                }
            }
        } catch (_: Exception) {
            languageDefaultDevice = "en"
        }

        if (getPref(this, PREFERENCE_SELECTED_LANGUAGE, "").toString().isEmpty()) {
            setPref(this, PREFERENCE_SELECTED_LANGUAGE, languageDefaultDevice)
        }

        stringLanguage = getPref(this, PREFERENCE_SELECTED_LANGUAGE, "").toString()

        textTranslateHeader(stringLanguage)

        val selectedIndex = listLanguage.indexOfFirst { it.key == stringLanguage }

        if (selectedIndex >= 0) {
            val item = listLanguage.removeAt(selectedIndex)
            listLanguage.add(3, item)
        }
    }

    override fun bindEvent() {
        binding.ivTick.onClick(500) {
            setPref(this@LanguageStartActivity, PREFERENCE_SELECTED_LANGUAGE, stringLanguage)
            startActivity(Intent(this@LanguageStartActivity, LanguageApplyActivity::class.java))
            finish()
        }

        languagesAdapter.callBackLanguage(object : CallBack.CallBackLanguage {
            override fun callBackLanguage(language: String, key: String, position: Int) {
                stringLanguage = key
                languagesAdapter.hideHandLoading()
                languagesAdapter.checkSelectView(position)

                addGiftTapToItem(position)
                binding.tapGif.show()

                binding.ivTick.alpha = 1f
                binding.ivTick.isEnabled = true

                textTranslateHeader(stringLanguage)

//                ViewMover
//                    .move(binding.tapGif)
//                    .to(binding.ivTick)
//                    .attachTo(ViewMover.Point.BOTTOM)
//                    .duration(1000)
//                    .start()
                isClickedItem = true
            }
        })
    }

    private fun initRecyclerview() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@LanguageStartActivity, LinearLayoutManager.VERTICAL, false)

            for (i in 0 until listLanguage.size) {
                if (listLanguage[i].key == stringLanguage) {
                    languagesAdapter.addAll(listLanguage, i)
                }
            }

            adapter = languagesAdapter
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (isClickedItem) return
                binding.viewOptionEnglish.translationY -= dy
                binding.tapGif.translationY -= dy
                anchorTapGifToItem3WithOffset(dy)
            }
        })

        binding.recyclerView.post {
//            binding.recyclerView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            addGiftTapToItem(3)
        }
    }

    private fun anchorTapGifToItem3WithOffset(dy: Int) {
        if (isClickedItem) return
        binding.viewOptionEnglish.translationY -= dy
        addGiftTapToItem(3)
        checkTapGifInForbiddenZone()
    }

    private fun addGiftTapToItem(position: Int) {
        if (isClickedItem) return
        val viewHolder = binding.recyclerView.findViewHolderForAdapterPosition(position)
        if (viewHolder != null) {
            val item3View = viewHolder.itemView
            val item3TopInRecycler = item3View.top
            val recyclerViewTop = binding.recyclerView.top
            val targetY = recyclerViewTop + item3TopInRecycler + resources.getDimension(com.intuit.sdp.R.dimen._20sdp)
            val currentTapGifTop = binding.tapGif.top
            binding.tapGif.translationY = (targetY - currentTapGifTop).toFloat()
        }
    }

    private fun checkTapGifInForbiddenZone() {
        // Tính vị trí hiện tại của tapGif
        val tapGifCurrentTop = binding.tapGif.top + binding.tapGif.translationY
        val tapGifCurrentBottom = tapGifCurrentTop + binding.tapGif.height

        // Lấy vị trí bottom của tvContent (đây là ranh giới dưới của vùng cấm)
        val tvContentBottom = binding.tvContent.bottom.toFloat()

        // Vùng cấm: từ đỉnh màn hình (0) đến bottom của tvContent
        val forbiddenZoneTop = 0f
        val forbiddenZoneBottom = tvContentBottom

        // Kiểm tra xem tapGif có nằm trong vùng cấm không
        val isInForbiddenZone = tapGifCurrentTop < forbiddenZoneBottom && tapGifCurrentBottom > forbiddenZoneTop

        // Ẩn nếu trong vùng cấm, hiện nếu ngoài vùng cấm
        binding.tapGif.visibility = if (isInForbiddenZone) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }


    private fun textTranslateHeader(stringLanguage: String) {
        when (stringLanguage) {
            "en" -> {
                binding.tvTitle.text = "Languages"
                binding.tvContent.text = "Please select language to continue"
            }

            "fr" -> {
                binding.tvTitle.text = "Langue"
                binding.tvContent.text = "Veuillez sélectionner la langue pour continuer"
            }

            "hi" -> {
                binding.tvTitle.text = "भाषा"
                binding.tvContent.text = "कृपया जारी रखने के लिए भाषा चुनें"
            }

            "es" -> {
                binding.tvTitle.text = "Idioma"
                binding.tvContent.text = "Por favor seleccione el idioma para continuar"
            }

            "ms" -> {
                binding.tvTitle.text = "Bahasa"
                binding.tvContent.text = "Sila pilih bahasa untuk meneruskan"
            }

            "de" -> {
                binding.tvTitle.text = "Sprache"
                binding.tvContent.text = "Bitte wählen Sie die Sprache aus, um fortzufahren"
            }

            "ar" -> {
                binding.tvTitle.text = "لغة"
                binding.tvContent.text = "الرجاء تحديد اللغة للمتابعة"
            }

            "tr" -> {
                binding.tvTitle.text = "Dil"
                binding.tvContent.text = "Devam etmek için lütfen dili seçin"
            }

            "ko" -> {
                binding.tvTitle.text = "언어"
                binding.tvContent.text = "계속하려면 언어를 선택하세요."
            }

            "ja" -> {
                binding.tvTitle.text = "言語"
                binding.tvContent.text = "続行するには言語を選択してください"
            }

            "ru" -> {
                binding.tvTitle.text = "Язык"
                binding.tvContent.text = "Пожалуйста, выберите язык, чтобы продолжить"
            }

            "zh" -> {
                binding.tvTitle.text = "语言"
                binding.tvContent.text = "请选择语言以继续"
            }

            "zh-rTW" -> {
                binding.tvTitle.text = "语言"
                binding.tvContent.text = "请选择语言以继续"
            }

            "bn" -> {
                binding.tvTitle.text = "ভাষা"
                binding.tvContent.text = "চালিয়ে যেতে ভাষা নির্বাচন করুন"
            }

            "pt" -> {
                binding.tvTitle.text = "Linguagem"
                binding.tvContent.text = "Selecione o idioma para continuar"
            }

            "pt-rBR" -> {
                binding.tvTitle.text = "Linguagem"
                binding.tvContent.text = "Selecione o idioma para continuar"
            }

            "in" -> {
                binding.tvTitle.text = "Bahasa"
                binding.tvContent.text = "Silakan pilih bahasa untuk melanjutkan"
            }
        }
    }
}
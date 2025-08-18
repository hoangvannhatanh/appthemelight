package com.example.appxx_appthemewallpaper.activity

import android.animation.Animator
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ActivityLanguageApplyBinding
import com.example.appxx_appthemewallpaper.extensions.getPref
import com.example.appxx_appthemewallpaper.extensions.showActivity
import com.example.appxx_appthemewallpaper.util.PREFERENCE_SELECTED_LANGUAGE


class LanguageApplyActivity : BaseActivity<ActivityLanguageApplyBinding>(R.layout.activity_language_apply) {

    override fun setBinding(layoutInflater: LayoutInflater) = ActivityLanguageApplyBinding.inflate(layoutInflater)

    override fun bindComponent() {
        textTranslateHeader()
        loadAnimate()
    }

    override fun bindData() {

    }

    override fun bindEvent() {

    }

    private fun loadAnimate() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.txtLoading.apply {
                animation =
                    AnimationUtils.loadAnimation(this@LanguageApplyActivity, R.anim.fade_out_250)
                text = context.getString(R.string.applied)
            }

            binding.loading.apply {
                setAnimation(R.raw.loading_language_apply)
                speed = 3.0f
                repeatCount = 0
                addAnimatorListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(p0: Animator) {}

                    override fun onAnimationEnd(p0: Animator) {
                        navigateToIntro()
                    }

                    override fun onAnimationCancel(p0: Animator) {}

                    override fun onAnimationRepeat(p0: Animator) {
                        navigateToIntro()
                    }
                })
            }
        }, 1000)
    }

    private fun navigateToIntro() {
//        showActivity(IntroActivity::class.java)
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
//        finish()
    }

    private fun textTranslateHeader() {
        val selectedLanguage = getPref(this, PREFERENCE_SELECTED_LANGUAGE, "").toString()

        val languageData = mapOf(
            "en" to LanguageInfo(
                "Languages", "Please select language to continue", R.drawable.ic_english, "English",
                titleOverride = getString(R.string.english)
            ),
            "fr" to LanguageInfo(
                "Langue",
                "Veuillez sélectionner la langue pour continuer",
                R.drawable.ic_france,
                "Français",
                titleOverride = getString(R.string.french)
            ),
            "hi" to LanguageInfo(
                "भाषा", "कृपया जारी रखने के लिए भाषा चुनें", R.drawable.ic_india, "हिंदी भाषा",
                titleOverride = getString(R.string.hindi)
            ),
            "pt-rBR" to LanguageInfo(
                "Linguagem",
                "Selecione o idioma para continuar",
                R.drawable.ic_brazil,
                "(Português) Brasil",
                titleOverride = getString(R.string.portuguese)
            ),
            "es" to LanguageInfo(
                "Idioma",
                "Por favor seleccione el idioma para continuar",
                R.drawable.ic_spanish,
                "Español",
                titleOverride = getString(R.string.spanish)
            ),
//            getString(R.string.malaysia) to LanguageInfo(
//                "Bahasa",
//                "Sila pilih bahasa untuk meneruskan",
//                R.drawable.ic_english,
//                "Bahasa Melayu (Malaysia)"
//            ),
            "de" to LanguageInfo(
                "Sprache",
                "Bitte wählen Sie die Sprache aus, um fortzufahren",
                R.drawable.ic_lang_de,
                "Deutsch",
                titleOverride = getString(R.string.german)
            ),
            "ar" to LanguageInfo(
                "لغة", "الرجاء تحديد اللغة للمتابعة", R.drawable.ic_lang_ar, "عربي",
                titleOverride = getString(R.string.arabic)
            ),
            "tr" to LanguageInfo(
                "Dil", "Devam etmek için lütfen dili seçin", R.drawable.ic_turkey, "Türkçe",
                titleOverride = getString(R.string.turkish)
            ),
            "ko" to LanguageInfo(
                "언어", "계속하려면 언어를 선택하세요.", R.drawable.ic_lang_ko, "日本語",
                titleOverride = getString(R.string.korean)
            ),
            "ja" to LanguageInfo(
                "言語", "続行するには言語を選択してください", R.drawable.ic_lang_ja, "日本語",
                titleOverride = getString(R.string.japanese)
            ),
            "ru" to LanguageInfo(
                "Язык",
                "Пожалуйста, выберите язык, чтобы продолжить",
                R.drawable.ic_lang_ru,
                "Русский",
                titleOverride = getString(R.string.russian)
            ),
            "zh" to LanguageInfo(
                "语言", "请选择语言以继续", R.drawable.ic_lang_chinese, "中文（简体)",
                titleOverride = getString(R.string.chinese)
            ),
            "zh-rTW" to LanguageInfo(
                "语言", "请选择语言以继续", R.drawable.ic_lang_chinese, "中文（简体)",
                titleOverride = getString(R.string.chinese)
            ),
            "bn" to LanguageInfo(
                "ভাষা", "চালিয়ে যেতে ভাষা নির্বাচন করুন", R.drawable.ic_lang_be, "বাংলা",
                titleOverride = getString(R.string.bengali)
            ),
            "pt" to LanguageInfo(
                "Linguagem",
                "Selecione o idioma para continuar",
                R.drawable.ic_portugal,
                "(Português) Portugal",
                titleOverride = getString(R.string.brazil)
            ),
            "in" to LanguageInfo(
                "Bahasa",
                "Silakan pilih bahasa untuk melanjutkan",
                R.drawable.ic_indonesia,
                "Bahasa",
                titleOverride = getString(R.string.indonesian)
            )
        )

        languageData[selectedLanguage]?.let { info ->
            binding.tvTitleStart.text = info.title
            binding.imLangLogo.setImageDrawable(ContextCompat.getDrawable(this, info.iconRes))
            binding.txtContent.text = info.displayText
            binding.tvContent.text = info.content

            binding.txtLanguageTitle.text = info.titleOverride ?: selectedLanguage
        } ?: run {
            binding.txtLanguageTitle.text = selectedLanguage
        }
    }

    data class LanguageInfo(
        val title: String,
        val content: String,
        val iconRes: Int,
        val displayText: String,
        val titleOverride: String? = null
    )
}

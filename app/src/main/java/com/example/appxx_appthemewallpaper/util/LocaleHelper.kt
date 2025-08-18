package com.example.appxx_appthemewallpaper.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.extensions.getPref
import java.util.Locale

object LocaleHelper {
    fun setLocale(c: Context): Context {
        return updateResources(c, getLanguageCode(c))
    }

    private fun getLanguageCode(context: Context): String {
        val languageMap = mapOf(
            "English" to "en",
            "Spanish" to "es",
            "French" to "fr",
            "Indonesian" to "in",
            "Portuguese" to "pt",
            "Brazil" to "pt",
            "Japanese" to "ja",
            context.getString(R.string.arabic) to "ar",
            context.getString(R.string.bengali) to "bn",
            context.getString(R.string.korean) to "ko",
            context.getString(R.string.turkish) to "tr",
            context.getString(R.string.russian) to "ru",
            context.getString(R.string.hindi) to "hi",
            context.getString(R.string.german) to "de",
            context.getString(R.string.switzerland) to "de",
            context.getString(R.string.vietnamese) to "vi",
            context.getString(R.string.eusaka) to "eu",
            context.getString(R.string.gujarati) to "gu",
            context.getString(R.string.hebrew) to "iw",
            context.getString(R.string.bahasa_indonesia) to "id",
            context.getString(R.string.kannada) to "kn",
            context.getString(R.string.malayalam) to "ml",
            context.getString(R.string.marathi) to "mr",
            context.getString(R.string.tamil) to "ta",
            context.getString(R.string.telugu) to "te",
            context.getString(R.string.ukraine) to "uk",
            context.getString(R.string.papua_new_guinea) to "en",
            context.getString(R.string.united_kingdom) to "en",
            "Andorra" to "ca", // Andorra
            "Anguilla" to "en", // Anguilla
            "Antigua and Barbuda" to "en", // Antigua and Barbuda
            "Argentina" to "es", // Argentina
            "Australia" to "en", // Australia
            "Austria" to "de", // Austria
            "Bahamas" to "en", // Bahamas
            "Bahrain" to "ar", // Bahrain
            "Barbados" to "en", // Barbados
            "Belgium" to "nl", // Belgium
            "Belize" to "en", // Belize
            "Benin" to "fr", // Benin
            "Bermuda" to "en", // Bermuda
            "Bolivia" to "es", // Bolivia
            "Botswana" to "en", // Botswana
            "British Virgin Islands" to "en", // British Virgin Islands
            "Bulgaria" to "bg", // Bulgaria
            "Burkina Faso" to "fr", // Burkina Faso
            "Burundi" to "fr", // Burundi
            "Cameroon" to "fr", // Cameroon
            "Canada" to "en", // Canada
            "Central African Republic" to "fr", // Central African Republic
            "Chad" to "fr", // Chad
            "Chile" to "es", // Chile
            "Colombia" to "es", // Colombia
            "Comoros" to "ar", // Comoros
            "Congo" to "fr", // Congo
            "Cook Islands" to "en", // Cook Islands
            "Costa Rica" to "es", // Costa Rica
            "Croatia" to "hr", // Croatia
            "Cuba" to "es", // Cuba
            "Curacao" to "nl", // Curaçao
            "Cyprus" to "el", // Cyprus
            "Czech Republic" to "cs", // Czech Republic
            "Denmark" to "da", // Denmark
            "Djibouti" to "fr", // Djibouti
            "Dominica" to "en", // Dominica
            "Dominican Republic" to "es", // Dominican Republic
            "Ecuador" to "es", // Ecuador
            "Egypt" to "ar", // Egypt
            "Estonia" to "et", // Estonia
            "Ethiopia" to "am", // Ethiopia
            "Finland" to "fi", // Finland
            "French Polynesia" to "fr", // French Polynesia
            "Gabon" to "fr", // Gabon
            "Gambia" to "en", // Gambia
            "Grenada" to "en", // Grenada
            "Guam" to "en", // Guam
            "Guinea" to "fr", // Guinea
            "Guyana" to "en", // Guyana
            "Haiti" to "fr", // Haiti
            "Hungary" to "hu", // Hungary
            "India" to "hi", // India
            "Iran" to "fa", // Iran
            "Iraq" to "ar", // Iraq
            "Italy" to "it", // Italy
            "Jamaica" to "en", // Jamaica
            "Jordan" to "ar", // Jordan
            "Kiribati" to "en", // Kiribati
            "Kuwait" to "ar", // Kuwait
            "Latvia" to "lv", // Latvia
            "Lebanon" to "ar", // Lebanon
            "Lesotho" to "en", // Lesotho
            "Liberia" to "en", // Liberia
            "Libya" to "ar", // Libya
            "Lithuania" to "lt", // Lithuania
            "Madagascar" to "fr", // Madagascar
            "Malawi" to "en", // Malawi
            "Malta" to "mt", // Malta
            "Moldova" to "ro", // Moldova
            "Monaco" to "fr", // Monaco
            "Montenegro" to "sr", // Montenegro
            "Morocco" to "ar", // Morocco
            "Netherlands" to "nl", // Netherlands
            "Nigeria" to "en", // Nigeria
            "North Korea" to "ko", // North Korea
            "Norway" to "no", // Norway
            "Oman" to "ar", // Oman
            "Palau" to "en", // Palau
            "Puerto Rico" to "es", // Puerto Rico
            "Qatar" to "ar", // Qatar
            "Russia" to "ru", // Russia
            "Rwanda" to "en", // Rwanda
            "Senegal" to "fr", // Senegal
            "Seychelles" to "fr", // Seychelles
            "Singapore" to "en", // Singapore
            "Slovakia" to "sk", // Slovakia
            "Slovenia" to "sl", // Slovenia
            "South Korea" to "ko", // South Korea
            "South Sudan" to "en", // South Sudan
            "Spain" to "es", // Spain
            "Syria" to "ar", // Syria
            "Togo" to "fr", // Togo
            "Tunisia" to "ar", // Tunisia
            "Vietnam" to "vi", // Vietnam
            "Yemen" to "ar", // Yemen
            "Zambia" to "en", // Zambia
            "Anguilla" to "en", // Anguilla
            "Antigua and Barbuda" to "en", // Antigua and Barbuda
            "Aruba" to "nl", // Aruba
            "Austria" to "de", // Austria
            "Bahamas" to "en", // Bahamas
            "Bahrain" to "ar", // Bahrain
            "Barbados" to "en", // Barbados
            "Belize" to "en", // Belize
            "Benin" to "fr", // Benin
            "Bermuda" to "en", // Bermuda
            "Botswana" to "en", // Botswana
            "British Virgin Islands" to "en", // British Virgin Islands
            "Brunei" to "ms", // Brunei
            "Burkina Faso" to "fr", // Burkina Faso
            "Burundi" to "fr", // Burundi
            "Cameroon" to "fr", // Cameroon
            "Canada" to "en", // Canada
            "Central African Republic" to "fr", // Central African Republic
            "Chad" to "fr", // Chad
            "Comoros" to "ar", // Comoros
            "Congo" to "fr", // Congo
            "Cook Islands" to "en", // Cook Islands
            "Croatia" to "hr", // Croatia
            "Curacao" to "nl", // Curaçao
            "Czech Republic" to "cs", // Czech Republic
            "Denmark" to "da", // Denmark
            "Djibouti" to "fr", // Djibouti
            "Dominica" to "en", // Dominica
            "Egypt" to "ar", // Egypt
            "El Salvador" to "es", // El Salvador
            "Equatorial Guinea" to "es", // Equatorial Guinea
            "Estonia" to "et", // Estonia
            "Ethiopia" to "am", // Ethiopia
            "Finland" to "fi", // Finland
            "French Polynesia" to "fr", // French Polynesia
            "Gabon" to "fr", // Gabon
            "Gambia" to "en", // Gambia
            "Ghana" to "en", // Ghana
            "Greece" to "el", // Greece
            "Grenada" to "en", // Grenada
            "Guam" to "en", // Guam
            "Guatemala" to "es", // Guatemala
            "Guinea" to "fr", // Guinea
            "Guyana" to "en", // Guyana
            "Haiti" to "fr", // Haiti
            "Honduras" to "es", // Honduras
            "Iceland" to "is", // Iceland
            "India" to "hi", // India
            "Iran" to "fa", // Iran
            "Iraq" to "ar", // Iraq
            "Ireland" to "en", // Ireland
            "Jamaica" to "en", // Jamaica
            "Jordan" to "ar", // Jordan
            "Kenya" to "sw", // Kenya
            "Kiribati" to "en", // Kiribati
            "Kuwait" to "ar", // Kuwait
            "Lebanon" to "ar", // Lebanon
            "Lesotho" to "en", // Lesotho
            "Liberia" to "en", // Liberia
            "Libya" to "ar", // Libya
            "Madagascar" to "fr", // Madagascar
            "Malawi" to "en", // Malawi
            "Malaysia" to "ms", // Malaysia
            "Malta" to "mt", // Malta
            "Mexico" to "es", // Mexico
            "Monaco" to "fr", // Monaco
            "Morocco" to "ar", // Morocco
            "New Zealand" to "en", // New Zealand
            "Nicaragua" to "es", // Nicaragua
            "North Korea" to "ko", // North Korea
            "Oman" to "ar", // Oman
            "Pakistan" to "ur", // Pakistan
            "Palau" to "en", // Palau
            "Panama" to "es", // Panama
            "Paraguay" to "es", // Paraguay
            "Peru" to "es", // Peru
            "Philippines" to "fil", // Philippines
            "Poland" to "pl", // Poland
            "Qatar" to "ar", // Qatar
            "Romania" to "ro", // Romania
            "Russia" to "ru", // Russia
            "Rwanda" to "en", // Rwanda
            "Senegal" to "fr", // Senegal
            "Serbia" to "sr", // Serbia
            "Seychelles" to "fr", // Seychelles
            "Singapore" to "en", // Singapore
            "South Korea" to "ko", // South Korea
            "South Sudan" to "en", // South Sudan
            "Spain" to "es", // Spain
            "Sweden" to "sv", // Sweden
            "Syria" to "ar", // Syria
            "Tanzania" to "sw", // Tanzania
            "Thailand" to "th", // Thailand
            "Togo" to "fr", // Togo
            "Tunisia" to "ar", // Tunisia
            "United States" to "en", // United States
            "Uruguay" to "es", // Uruguay
            "Venezuela" to "es", // Venezuela
            "Vietnam" to "vi", // Vietnam
            "Yemen" to "ar", // Yemen
            "Zambia" to "en", // Zambia
            "Zimbabwe" to "en",
            "Chinese" to "zh",
        )
        val selectedLanguage = getPref(
            context,
            PREFERENCE_SELECTED_LANGUAGE,
            context.getString(R.string.english)
        ).toString()
        return languageMap[selectedLanguage] ?: "en"
    }


    private fun updateResources(contextMain: Context, language: String): Context {
        var context: Context = contextMain
        val locale: Locale = when (language) {
            "zh-rTW" -> Locale("zh", "TW")
            "pt-rBR" -> Locale("pt", "BR")
            else -> Locale(language)
        }
        Locale.setDefault(locale)
        val res: Resources = context.resources
        val config = Configuration(res.configuration)
        config.setLocale(locale)
        context = context.createConfigurationContext(config)
        return context
    }

}
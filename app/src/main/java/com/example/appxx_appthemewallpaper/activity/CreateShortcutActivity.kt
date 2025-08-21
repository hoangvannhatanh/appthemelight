package com.example.appxx_appthemewallpaper.activity

import android.app.PendingIntent
import android.view.LayoutInflater
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Icon
import android.os.Build
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.activity_launcher.AppLauncherActivity
import com.example.appxx_appthemewallpaper.adapter.LaunchAppAdapter
import com.example.appxx_appthemewallpaper.databinding.ActivityCreateShortcutBinding
import com.example.appxx_appthemewallpaper.model.CreateApp
import com.example.appxx_appthemewallpaper.model.LaunchApp
import com.example.appxx_appthemewallpaper.model.ThemeApp
import com.example.appxx_appthemewallpaper.util.CallBack
import com.example.appxx_appthemewallpaper.util.Util.Companion.getTheme1
import com.example.appxx_appthemewallpaper.util.toFraktur

class CreateShortcutActivity : BaseActivity<ActivityCreateShortcutBinding>(R.layout.activity_create_shortcut) {

    private var listAppHaveOnDevice: List<LaunchApp> = arrayListOf()
    private var listThemeShortcut: List<ThemeApp> = arrayListOf()
    private var listSameApp: MutableList<CreateApp> = arrayListOf()
    private var listNoSameApp: MutableList<CreateApp> = arrayListOf()
    private var listShowOnRecyclerView: MutableList<CreateApp> = arrayListOf()
    private val launchAppAdapter by lazy { LaunchAppAdapter() }

    override fun setBinding(layoutInflater: LayoutInflater) = ActivityCreateShortcutBinding.inflate(layoutInflater)

    override fun bindComponent() {
        listAppHaveOnDevice = queryAllLaunchApps()
        listThemeShortcut = getTheme1(this)

        getListCreateApp()
    }

    private fun getListCreateApp() {
        listSameApp.clear()
        listNoSameApp.clear()
        listShowOnRecyclerView.clear()

        listAppHaveOnDevice.forEach { itemA ->
            listThemeShortcut.forEach { itemB ->
                val createApp = CreateApp(
                    idTheme = itemB.idTheme,
                    titleName1 = itemA.appLabel,
                    titleName2 = itemA.appLabel,
                    packageName1 = itemA.packageName,
                    packageName2 = itemA.packageName,
                    icon1 = itemA.icon,
                    icon2 = itemB.icon,
                )

                if (itemA.packageName == itemB.packageName) {
                    listSameApp.add(createApp)
                }
            }
        }

        listThemeShortcut.forEach { themeApp ->
            val hasMatchingApp = listAppHaveOnDevice.any { launchApp ->
                launchApp.packageName == themeApp.packageName
            }
            
            if (!hasMatchingApp) {
                val createApp = CreateApp(
                    idTheme = themeApp.idTheme,
                    titleName1 = themeApp.appLabel,
                    titleName2 = themeApp.appLabel,
                    packageName1 = themeApp.packageName,
                    packageName2 = themeApp.packageName,
                    icon1 = themeApp.icon,
                    icon2 = themeApp.icon,
                )
                listNoSameApp.add(createApp)
            }
        }

        listShowOnRecyclerView.addAll(listSameApp)
        listShowOnRecyclerView.addAll(listNoSameApp)

        initRecyclerview()
    }

    override fun bindData() {

    }

    override fun bindEvent() {
        launchAppAdapter.callBackLaunchApp(object : CallBack.CallBackLaunchApp {
            override fun callBackLaunchApp(createApp: CreateApp, position: Int) {
                createApp.let {
                    val shortcutID = "ID_${it.idTheme}_${it.titleName1}"
                    createShortcut(shortcutID, it.packageName1, toFraktur(it.titleName1), R.drawable.ic_telegram_adaptive_background, R.drawable.ic_telegram_adaptive_foreground)
                }
            }
        })
    }

    private fun queryAllLaunchApps(): List<LaunchApp> {
        val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfos = packageManager.queryIntentActivities(mainIntent, 0)

        val apps = resolveInfos
            .mapNotNull { ri ->
                val label = ri.loadLabel(packageManager)?.toString() ?: return@mapNotNull null
                val pkg = ri.activityInfo?.packageName ?: return@mapNotNull null
                val icon = ri.loadIcon(packageManager)
                LaunchApp(label, pkg, icon)
            }
            .distinctBy { it.packageName }
            .sortedBy { it.appLabel.lowercase() }

        apps.forEach {

        }

        val filteredList = apps.filter {
            it.packageName in listOf(
                "org.thunderdog.challegram",
                "com.facebook.lite",
                "com.netflix.mediaclient",
                "com.zhiliaoapp.musically",
                "com.twitter.android",
                "com.zing.zalo",
                "com.instagram.android",
                "com.google.android.youtube",
                "com.android.chrome",
                "com.facebook.katana",
//                "com.facebook.orca",
//                "com.sec.android.app.camera",
//                "org.telegram.messenger",
//                "com.ss.android.ugc.trill",
//                "com.sec.android.app.clockpackage"
            )
        }.toMutableList()
        return filteredList
    }

    private fun initRecyclerview() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CreateShortcutActivity, LinearLayoutManager.VERTICAL, false)
            launchAppAdapter.addAll(listShowOnRecyclerView)
            adapter = launchAppAdapter
        }
    }

    private fun createShortcut(shortcutID: String, packageName: String, label: String, backGround: Int, icon: Int) {
        val telegramIntent = Intent(this, AppLauncherActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            putExtra(AppLauncherActivity.EXTRA_TARGET_PACKAGE, packageName)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = getSystemService(ShortcutManager::class.java)
            val existingShortcuts = shortcutManager?.dynamicShortcuts?.map { it.id } ?: emptyList()
            val pinnedShortcuts = shortcutManager?.pinnedShortcuts?.map { it.id } ?: emptyList()

            //BELOW ANDROID 26 chỉ có thể dùng sharepreference

            if (shortcutManager.isRequestPinShortcutSupported) {
                pinnedShortcuts.forEach {
                    if (it == shortcutID) {
                        Toast.makeText(this, "SHORTCUT ALREADY EXIST", Toast.LENGTH_SHORT).show()
                        return
                    }
                }

                val shortcutInfo = ShortcutInfo.Builder(this, shortcutID)
                    .setShortLabel(label)
                    .setLongLabel(label)
                    .setIcon(
                        buildIcon(
                            backGround = backGround, icon = icon
                        )
                    )
                    .setIntent(telegramIntent)
                    .build()

                shortcutManager?.dynamicShortcuts = listOf(shortcutInfo)

                val pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(shortcutInfo)

                val successCallback = PendingIntent.getBroadcast(
                    this, 0,
                    pinnedShortcutCallbackIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )

                shortcutManager.requestPinShortcut(shortcutInfo, successCallback.intentSender)
            } else {
                Toast.makeText(this, "SHORTCUT NOT SUPPORT", Toast.LENGTH_SHORT).show()
            }
        } else {
            if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
                val shortcutInfo = ShortcutInfoCompat.Builder(this, shortcutID)
                    .setShortLabel(label)
                    .setLongLabel(label)
                    .setIcon(
                        buildAdaptiveIcon(
                            backGround = backGround, icon = icon
                        )
                    )
                    .setIntent(telegramIntent)
                    .build()

                ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
                Toast.makeText(this, "SHORTCUT ADDED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "SHORTCUT NOT SUPPORT", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buildAdaptiveIcon(backGround: Int, icon: Int): IconCompat {
        val size = resources.getDimensionPixelSize(android.R.dimen.app_icon_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(this, backGround)!!
//        val wrappedDrawable = DrawableCompat.wrap(bg)
//        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#000000")) // màu đen
//        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN)

        val fg = AppCompatResources.getDrawable(this, icon)!!
//        val wrappedDrawable2 = DrawableCompat.wrap(fg)
//        DrawableCompat.setTint(wrappedDrawable2, Color.parseColor("#303030")) // màu đen
//        DrawableCompat.setTintMode(wrappedDrawable2, PorterDuff.Mode.SRC_IN)

//        val a = (0.5f.coerceIn(0f, 1f) * 255).toInt()
//        bg.alpha = a
//        fg.alpha = a


        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return IconCompat.createWithAdaptiveBitmap(bitmap) // API 26+
    }

    private fun buildIcon(backGround: Int, icon: Int): Icon {
        val size = resources.getDimensionPixelSize(android.R.dimen.app_icon_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(this, backGround)!!
//        val wrappedDrawable = DrawableCompat.wrap(bg)
//        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#000000")) // màu đen
//        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN)

        val fg = AppCompatResources.getDrawable(this, icon)!!

//        val a = (0.5f.coerceIn(0f, 1f) * 255).toInt()
//        bg.alpha = a
//        fg.alpha = a


        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return Icon.createWithBitmap(bitmap) // API 26+
    }
}
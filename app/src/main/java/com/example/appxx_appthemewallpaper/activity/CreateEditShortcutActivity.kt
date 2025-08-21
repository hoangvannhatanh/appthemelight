package com.example.appxx_appthemewallpaper.activity

import android.app.PendingIntent
import android.view.LayoutInflater
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Icon
import android.os.Build
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.activity_launcher.AppLauncherActivity
import com.example.appxx_appthemewallpaper.adapter.CreateEditShortcutAdapter
import com.example.appxx_appthemewallpaper.databinding.ActivityCreateEditShortcutBinding
import com.example.appxx_appthemewallpaper.model.CreateApp
import com.example.appxx_appthemewallpaper.model.LaunchApp
import com.example.appxx_appthemewallpaper.model.ThemeApp
import com.example.appxx_appthemewallpaper.util.CallBack
import com.example.appxx_appthemewallpaper.util.Util.Companion.getTheme1

class CreateEditShortcutActivity : BaseActivity<ActivityCreateEditShortcutBinding>(R.layout.activity_create_shortcut) {

    private var listAppHaveOnDevice: List<LaunchApp> = arrayListOf()
    private var listThemeShortcut: List<ThemeApp> = arrayListOf()
    private var listCreateShortcut: MutableList<CreateApp> = arrayListOf()
    private val createEditShortcutAdapter by lazy { CreateEditShortcutAdapter() }
    private var strColor = ""
    private var strBackground = ""
    private var strFont = "Default"

    override fun setBinding(layoutInflater: LayoutInflater) = ActivityCreateEditShortcutBinding.inflate(layoutInflater)

    override fun bindComponent() {

        listAppHaveOnDevice = queryAllLaunchApps()

        getThemeShortcut()

        getListCreateApp()
    }

    private fun getThemeShortcut() {
        strColor = intent?.extras?.getString("KEY_COLOR_ICON") ?: "000000"
        strBackground = intent?.extras?.getString("KEY_COLOR_BACKGROUND") ?: "000000"
        strFont = intent?.extras?.getString("KEY_FONT") ?: "Default"

        listThemeShortcut = getTheme1()
    }

    private fun getListCreateApp() {
        listCreateShortcut.clear()

        listAppHaveOnDevice.forEach { itemA ->
            listThemeShortcut.forEach { itemB ->
                val createApp = CreateApp(
                    idTheme = itemB.idTheme,
                    titleName1 = itemA.appLabel,
                    titleName2 = itemA.appLabel,
                    packageName1 = itemA.packageName,
                    packageName2 = itemA.packageName,
                    icon1 = itemA.icon,
                    iconCreate = itemB.iconCreate,
                    backgroundCreate = itemB.backGroundCreate,
                )

                if (itemA.packageName == itemB.packageName) {
                    listCreateShortcut.add(createApp)
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
                    titleName1 = "",
                    titleName2 = "",
                    packageName1 = "",
                    packageName2 = themeApp.packageName,
                    icon1 = ContextCompat.getDrawable(this, themeApp.iconCreate),
                    iconCreate = themeApp.iconCreate,
                    backgroundCreate = themeApp.backGroundCreate,
                )
                listCreateShortcut.add(createApp)
            }
        }

        initRecyclerviewApp()
    }

    override fun bindData() {}

    override fun bindEvent() {
        createEditShortcutAdapter.callBackLaunchApp(object : CallBack.CallBackLaunchApp {
            override fun callBackCreateShortcut(createApp: CreateApp, position: Int, nameCreate: String) {
                createApp.let {
                    val shortcutID = "ID_${it.idTheme}_${it.titleName1}"
                    createShortcut(shortcutID, it.packageName1, nameCreate, it.backgroundCreate, it.iconCreate)
                }
            }

            override fun callBackImportApp(position: Int) {
                showPopupAllPackage(this@CreateEditShortcutActivity,
                    onClick = { launchApp, int ->
                        listCreateShortcut[position].packageName1 = launchApp.packageName
                        listCreateShortcut[position].packageName2 = launchApp.packageName
                        listCreateShortcut[position].icon1 = launchApp.icon
                        listCreateShortcut[position].titleName1 = launchApp.appLabel
                        listCreateShortcut[position].titleName2 = launchApp.appLabel
                        createEditShortcutAdapter.updateList(listCreateShortcut)
                    },
                    onShow = {},
                    onDismiss = {}
                )
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

    private fun initRecyclerviewApp() {
        binding.recyclerViewApp.apply {
            layoutManager = LinearLayoutManager(this@CreateEditShortcutActivity, LinearLayoutManager.VERTICAL, false)
            createEditShortcutAdapter.addAll(listCreateShortcut)
            adapter = createEditShortcutAdapter
            createEditShortcutAdapter.updateFont(strFont)
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
        val size = (48 * resources.displayMetrics.density).toInt()
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(this, backGround)!!
        val wrappedDrawable = DrawableCompat.wrap(bg)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(strBackground)) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN)

        val fg = AppCompatResources.getDrawable(this, icon)!!
        val wrappedDrawable2 = DrawableCompat.wrap(fg)
        DrawableCompat.setTint(wrappedDrawable2, Color.parseColor(strColor)) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable2, PorterDuff.Mode.SRC_IN)

//        val a = (0.5f.coerceIn(0f, 1f) * 255).toInt()
//        bg.alpha = a
//        fg.alpha = a


        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return IconCompat.createWithAdaptiveBitmap(bitmap) // API 26+
    }

    private fun buildIcon(backGround: Int, icon: Int): Icon {
        val size = (48 * resources.displayMetrics.density).toInt()
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(this, backGround)!!
        val wrappedDrawable = DrawableCompat.wrap(bg)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(strBackground)) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN)

        val fg = AppCompatResources.getDrawable(this, icon)!!
        val wrappedDrawable2 = DrawableCompat.wrap(fg)
        DrawableCompat.setTint(wrappedDrawable2, Color.parseColor(strColor)) // màu đen
        DrawableCompat.setTintMode(wrappedDrawable2, PorterDuff.Mode.SRC_IN)

//        val a = (0.5f.coerceIn(0f, 1f) * 255).toInt()
//        bg.alpha = a
//        fg.alpha = a


        bg.setBounds(0, 0, size, size); bg.draw(canvas)
        fg.setBounds(0, 0, size, size); fg.draw(canvas)

        return Icon.createWithAdaptiveBitmap(bitmap) // API 26+
    }
}
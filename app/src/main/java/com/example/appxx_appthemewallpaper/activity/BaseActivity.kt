package com.example.appxx_appthemewallpaper.activity

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.DialogExitAppBinding
import com.example.appxx_appthemewallpaper.dialog.AllPackageDialog
import com.example.appxx_appthemewallpaper.extensions.hideNavigation
import com.example.appxx_appthemewallpaper.extensions.tryOrCatch
import com.example.appxx_appthemewallpaper.model.LaunchApp
import com.example.appxx_appthemewallpaper.util.LocaleHelper

abstract class BaseActivity<V : ViewDataBinding> constructor(@LayoutRes val layoutResourceId: Int) : AppCompatActivity() {
    protected lateinit var binding: V
    private lateinit var builder: AlertDialog.Builder
    private lateinit var alert: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        initWindow()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        fullScreenCall()
        super.onCreate(savedInstanceState)
        setContentView(getInflatedLayout(layoutInflater))

        bindComponent()
        bindData()
        bindEvent()
    }

    abstract fun setBinding(layoutInflater: LayoutInflater): V

    private fun getInflatedLayout(inflater: LayoutInflater): View {
        binding = setBinding(inflater)
        return binding.root
    }

    abstract fun bindComponent()
    abstract fun bindData()
    abstract fun bindEvent()

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    open fun initWindow() {
        window?.apply {
            val background: Drawable = ColorDrawable(Color.parseColor("#FFFFFF"))
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = resources.getColor(android.R.color.black)
            setBackgroundDrawable(background)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    fun checkPermissionNotification(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissionCamera(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun showGoToSetting(onHideAds: (() -> Unit)? = null, onShowAds: (() -> Unit)? = null, onClick: (() -> Unit)? = null) {
        builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.go_to_setting)
        builder.setMessage(R.string.please_grant_all_permissions)
        builder.setCancelable(false)
        builder.setPositiveButton(R.string.go_to_setting) { _, _ ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
            onClick?.invoke()
        }
        alert = builder.create()
        alert.setOnDismissListener {
            Handler(Looper.getMainLooper()).postDelayed({
                onShowAds?.invoke()
            }, 30)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            onHideAds?.invoke()
        }, 30)
        alert.show()
    }

    fun showGoToSetting(onClick: (() -> Unit)? = null) {
        builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.go_to_setting)
        builder.setMessage(R.string.please_grant_all_permissions)
        builder.setCancelable(false)
        builder.setPositiveButton(R.string.go_to_setting) { _, _ ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
            onClick?.invoke()
        }
        alert = builder.create()
        alert.setOnDismissListener {

        }
        alert.show()
    }

    private fun fullScreenCall() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(base))
    }

    fun requestPermissionNotification() {
        val permission = arrayOf(Manifest.permission.POST_NOTIFICATIONS)
        requestPermissions(permission, 7777)
    }

    fun showPopupExitApp(context: Context, onExit: () -> Unit, onDismiss: () -> Unit) {
        val dialogExitApp = Dialog(context)
        hideNavigation()
        val dialogBinding = DialogExitAppBinding.inflate(LayoutInflater.from(context))
        dialogExitApp.setContentView(dialogBinding.root)
        val window = dialogExitApp.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window?.attributes
        windowAttributes?.gravity = Gravity.CENTER

        dialogBinding.btnExit.setOnClickListener {
            dialogExitApp.dismiss()
            hideNavigation()
            onExit.invoke()
        }
        dialogBinding.btnCancel.setOnClickListener {
            dialogExitApp.dismiss()
            hideNavigation()
        }

        dialogExitApp.setOnDismissListener {
            onDismiss.invoke()
        }
        dialogExitApp.show()
    }

    fun setupDynamicShortcuts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            tryOrCatch(
                blockTry = {
                    val shortcutManager = getSystemService(ShortcutManager::class.java)
                    val shortcut = ShortcutInfo.Builder(this, "ivBack")
                        .setShortLabel(getString(R.string._uninstall))
                        .setLongLabel(getString(R.string._uninstall))
                        .setIcon(Icon.createWithResource(this, R.drawable.ic_uninstall))
                        .setIntent(Intent(Intent.ACTION_VIEW).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            setClassName(
                                "com.example.appxx_appthemewallpaper",
                                "com.example.appxx_appthemewallpaper.activity.SplashUninstallActivity"
                            )
                        })
                        .build()

                    shortcutManager?.run {
                        dynamicShortcuts = listOf(shortcut)
                        addDynamicShortcuts(listOf(shortcut))
                    }
                },
                blockCatch = {}
            )
        }
    }

    fun showPopupAllPackage(context: Activity, onClick: (() -> Unit)? = null, onShow: (() -> Unit)? = null, onDismiss: (() -> Unit)? = null) {
        val selectStepActivateDialog = AllPackageDialog(context = context)
        selectStepActivateDialog.bindEvent(object : AllPackageDialog.OnPress {
            override fun onClose() {}

            override fun onProvidePermission(caunchApp: LaunchApp, position: Int) {
                selectStepActivateDialog.dismiss()
                onClick?.invoke()
            }
        })

        selectStepActivateDialog.setOnDismissListener {
            onDismiss?.invoke()
        }
        onShow?.invoke()
        selectStepActivateDialog.show()
    }
}

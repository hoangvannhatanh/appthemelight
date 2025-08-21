package com.example.appxx_appthemewallpaper.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.adapter.AllPackageAdapter
import com.example.appxx_appthemewallpaper.databinding.DialogAllPackageBinding
import com.example.appxx_appthemewallpaper.model.LaunchApp
import com.example.appxx_appthemewallpaper.util.CallBack
import com.google.android.material.bottomsheet.BottomSheetDialog

@SuppressLint("SetTextI18n")
class AllPackageDialog(private val context: Activity) : BottomSheetDialog(context, R.style.SheetDialogPermission) {
    private var binding = DialogAllPackageBinding.inflate(LayoutInflater.from(context))
    private var onPress: OnPress? = null

    private val allPackageAdapter by lazy { AllPackageAdapter() }

    init {
        setContentView(binding.root)
        window?.let {
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            it.setGravity(Gravity.CENTER)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            it.statusBarColor =
                ContextCompat.getColor(getContext(), android.R.color.transparent)
            it.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            it.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        initRecyclerview()

        setCancelable(true)

        hideNavigation(this)

        onClick()
    }

    interface OnPress {
        fun onClose()
        fun onProvidePermission(launchApp: LaunchApp, position: Int)
    }

    fun bindEvent(onPress: OnPress) {
        this.onPress = onPress
    }

    private fun onClick() {

        allPackageAdapter.callBackAllPackage(object : CallBack.CallBackAllPackage {
            override fun callBackAllPackage(launchApp: LaunchApp, position: Int) {
                onPress?.onProvidePermission(launchApp, position)
            }
        })
    }

    private fun hideNavigation(dialog: Dialog) {
        dialog.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    private fun queryAllLaunchApps(): List<LaunchApp> {
        val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfos = context.packageManager.queryIntentActivities(mainIntent, 0)

        val apps = resolveInfos
            .mapNotNull { ri ->
                val label = ri.loadLabel(context.packageManager)?.toString() ?: return@mapNotNull null
                val pkg = ri.activityInfo?.packageName ?: return@mapNotNull null
                val icon = ri.loadIcon(context.packageManager)
                LaunchApp(label, pkg, icon)
            }
            .distinctBy { it.packageName }
            .sortedBy { it.appLabel.lowercase() }

        apps.forEach {

        }
        return apps
    }

    private fun initRecyclerview() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            allPackageAdapter.addAll(queryAllLaunchApps() as MutableList<LaunchApp>)
            adapter = allPackageAdapter
        }
    }
}
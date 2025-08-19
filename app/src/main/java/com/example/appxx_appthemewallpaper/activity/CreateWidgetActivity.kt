package com.example.appxx_appthemewallpaper.activity

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.view.LayoutInflater
import android.content.Intent
import android.os.Build
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ActivityCreateWidgetBinding
import com.example.appxx_appthemewallpaper.widget.SystemInfoWidget

class CreateWidgetActivity : BaseActivity<ActivityCreateWidgetBinding>(R.layout.activity_create_widget) {
    override fun setBinding(layoutInflater: LayoutInflater) = ActivityCreateWidgetBinding.inflate(layoutInflater)

    override fun bindComponent() {

    }

    override fun bindData() {

    }

    override fun bindEvent() {
        binding.btnCreateWidget.setOnClickListener {
            createSystemInfoWidget()
        }
    }

    private fun createSystemInfoWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val componentName = ComponentName(this, SystemInfoWidget::class.java)
        
        // Kiểm tra xem widget có được hỗ trợ không
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && appWidgetManager.isRequestPinAppWidgetSupported) {
            // Tạo widget mới (Android 8.0+)
            val successCallback = PendingIntent.getBroadcast(
                this,
                0,
                Intent(this, com.example.appxx_appthemewallpaper.widget.SystemInfoWidget::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            
            appWidgetManager.requestPinAppWidget(componentName, null, successCallback)
        } else {
            // Fallback: Hiển thị hướng dẫn cho người dùng
            try {
                val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_PICK)
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
                startActivity(intent)
            } catch (e: Exception) {
                // Nếu không thể mở widget picker, hiển thị thông báo
                android.widget.Toast.makeText(
                    this,
                    "Vui lòng thêm widget thủ công từ màn hình chờ",
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}
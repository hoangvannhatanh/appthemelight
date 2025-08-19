package com.example.appxx_appthemewallpaper.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.widget.RemoteViews
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.activity.MainActivity

class SystemInfoWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
    
    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        // Bắt đầu service cập nhật khi widget được tạo
        val serviceIntent = Intent(context, com.example.appxx_appthemewallpaper.service.WidgetUpdateService::class.java)
        context.startService(serviceIntent)
    }
    
    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        // Dừng service khi widget bị xóa
        val serviceIntent = Intent(context, com.example.appxx_appthemewallpaper.service.WidgetUpdateService::class.java)
        context.stopService(serviceIntent)
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_system_info)
        
        // Lấy thông tin hệ thống
        val batteryInfo = getBatteryInfo(context)
        val romInfo = getRomInfo()
        val wifiInfo = getWifiInfo(context)
        
        // Cập nhật text
        views.setTextViewText(R.id.tvBatteryInfo, batteryInfo)
        views.setTextViewText(R.id.tvRomInfo, romInfo)
        views.setTextViewText(R.id.tvWifiInfo, wifiInfo)
        
        // Đặt hình ảnh mặc định
        views.setImageViewResource(R.id.ivWidgetImage, R.drawable.ic_widget_image)
        
        // Tạo intent để mở app khi nhấn vào widget
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        views.setOnClickPendingIntent(R.id.widgetContainer, pendingIntent)
        
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
    
    private fun getBatteryInfo(context: Context): String {
        try {
            val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as android.os.BatteryManager
            val batteryLevel = batteryManager.getIntProperty(android.os.BatteryManager.BATTERY_PROPERTY_CAPACITY)
            val batteryStatus = batteryManager.getIntProperty(android.os.BatteryManager.BATTERY_PROPERTY_STATUS)
            
            val isCharging = batteryStatus == android.os.BatteryManager.BATTERY_STATUS_CHARGING || 
                           batteryStatus == android.os.BatteryManager.BATTERY_STATUS_FULL
            
            return if (isCharging) {
                "🔋 Pin: $batteryLevel% (Sạc)"
            } else {
                "🔋 Pin: $batteryLevel%"
            }
        } catch (e: Exception) {
            return "🔋 Pin: N/A"
        }
    }
    
    private fun getRomInfo(): String {
        try {
            val totalSpace = android.os.Environment.getExternalStorageDirectory().totalSpace
            val freeSpace = android.os.Environment.getExternalStorageDirectory().freeSpace
            val usedSpace = totalSpace - freeSpace
            
            val totalGB = totalSpace / (1024.0 * 1024.0 * 1024.0)
            val usedGB = usedSpace / (1024.0 * 1024.0 * 1024.0)
            
            return "💾 ROM: ${String.format("%.1f", usedGB)}GB / ${String.format("%.1f", totalGB)}GB"
        } catch (e: Exception) {
            return "💾 ROM: N/A"
        }
    }
    
    private fun getWifiInfo(context: Context): String {
        try {
            val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            return if (wifiManager.isWifiEnabled) {
                val wifiInfo = wifiManager.connectionInfo
                if (wifiInfo != null && wifiInfo.networkId != -1) {
                    val ssid = wifiInfo.ssid?.removeSurrounding("\"") ?: "Unknown"
                    "📶 WiFi: $ssid"
                } else {
                    "📶 WiFi: Bật (Không kết nối)"
                }
            } else {
                "📶 WiFi: Tắt"
            }
        } catch (e: Exception) {
            return "📶 WiFi: N/A"
        }
    }
    
    companion object {
        fun updateWidgets(context: Context) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(
                ComponentName(context, SystemInfoWidget::class.java)
            )
            if (appWidgetIds.isNotEmpty()) {
                val intent = Intent(context, SystemInfoWidget::class.java)
                intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
                context.sendBroadcast(intent)
            }
        }
    }
}

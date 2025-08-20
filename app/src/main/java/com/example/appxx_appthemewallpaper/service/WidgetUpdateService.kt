package com.example.appxx_appthemewallpaper.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.widget.SystemInfoWidget

class WidgetUpdateService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    private var systemChangeReceiver: BroadcastReceiver? = null

    override fun onCreate() {
        super.onCreate()
        maybeStartForeground()
        registerSystemReceivers()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Cập nhật widget ngay khi service khởi chạy
        SystemInfoWidget.updateWidgets(this)
        return START_STICKY
    }

    override fun onDestroy() {
        unregisterSystemReceivers()
        super.onDestroy()
    }

    private fun maybeStartForeground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "widget_update_service"
            val channelName = "Widget Update Service"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (notificationManager.getNotificationChannel(channelId) == null) {
                val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_MIN)
                channel.setShowBadge(false)
                notificationManager.createNotificationChannel(channel)
            }

            val notification: Notification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_widget_image)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Đang cập nhật widget hệ thống")
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build()

            startForeground(1011, notification)
        }
    }

    private fun registerSystemReceivers() {
        if (systemChangeReceiver != null) return
        systemChangeReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                // Bất kỳ sự kiện liên quan, cập nhật widget
                SystemInfoWidget.updateWidgets(this@WidgetUpdateService)
            }
        }

        val filter = IntentFilter().apply {
            // Pin thay đổi
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            // WiFi / mạng thay đổi
            addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
            addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        }

        registerReceiver(systemChangeReceiver, filter)
    }

    private fun unregisterSystemReceivers() {
        try {
            if (systemChangeReceiver != null) {
                unregisterReceiver(systemChangeReceiver)
                systemChangeReceiver = null
            }
        } catch (_: Exception) {
        }
    }
}

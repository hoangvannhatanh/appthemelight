package com.example.appxx_appthemewallpaper.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.appxx_appthemewallpaper.widget.SystemInfoWidget

class WidgetUpdateService : Service() {
    
    override fun onBind(intent: Intent?): IBinder? = null
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Cập nhật widget
        SystemInfoWidget.updateWidgets(this)
        return START_NOT_STICKY
    }
}

package com.raqun.android.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.raqun.android.R
import com.raqun.android.ui.NavigationController
import com.raqun.android.ui.main.MainActivity

class MyFcmService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        RegisterTokenService.enqueue(this, refresh = true)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (getString(R.string.gcm_defaultSenderId) == remoteMessage.from) {
            remoteMessage.notification?.let {
                var intent = MainActivity.newIntent(this)
                if (remoteMessage.data.isNotEmpty()) {
                    val payload = remoteMessage.data
                    val intentFactory: NavigationController.IntentFactory
                            = NavigationController.DefaultIntentFactory(this)
                    intent = intentFactory.createNotificationIntent(payload["notification_type"]!!.toInt(),
                            payload["key"])
                }

                val title = it.title ?: getString(R.string.app_name)
                val message = it.body ?: getString(R.string.notification_default_message)

                showNotification(title, message, intent)
            }
        }
    }

    private fun showNotification(title: String?, message: String?, intent: Intent) {
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_ONE_SHOT
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, flags)
        val channelId = "raqun"
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, with(NotificationCompat.Builder(this, channelId)) {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setContentText(message)
            setAutoCancel(true)
            setSound(soundUri)
            setContentIntent(pendingIntent)
            build()
        })
    }
}

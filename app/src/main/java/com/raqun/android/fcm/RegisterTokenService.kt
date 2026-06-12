package com.raqun.android.fcm

import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging
import com.raqun.android.RaqunApp
import com.raqun.android.api.RaqunServices
import com.raqun.android.api.request.RegisterTokenRequest
import com.raqun.android.util.SharedPrefUtil
import java.util.concurrent.Executors

object RegisterTokenService {

    private val executor = Executors.newSingleThreadExecutor()

    fun enqueue(context: Context, refresh: Boolean = false) {
        executor.execute {
            doWork(context.applicationContext, refresh)
        }
    }

    private fun doWork(context: Context, forceRefresh: Boolean) {
        var token: String? = null

        if (!forceRefresh) {
            token = SharedPrefUtil.get(context,
                    RaqunApp.sessionManager.getSession(),
                    null)
        }

        if (token == null) {
            try {
                val tokenTask = FirebaseMessaging.getInstance().token
                val result = com.google.android.gms.tasks.Tasks.await(tokenTask)
                token = result
            } catch (e: Exception) {
                return
            }
        }
    }
}

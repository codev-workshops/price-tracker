package com.raqun.android.fcm

import com.google.firebase.messaging.FirebaseMessagingService

/**
 * Created by tyln on 24/10/2017.
 * Deprecated: Token refresh is now handled by MyFcmService.onNewToken()
 */
class MyFcmInstanceIdService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        startService(RegisterTokenService.newIntent(this, refresh = true))
    }
}

package com.raqun.android

import android.app.Application
import com.raqun.android.data.source.local.UserHelper
import com.raqun.android.session.SessionManager
import com.raqun.android.session.SessionProvider
import com.raqun.android.util.SharedPrefHelper
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class RaqunApp : Application() {

    override fun onCreate() {
        super.onCreate()

        RaqunApp.sessionManager = SessionManager(getOrCreateSessionKey()).also {
            it.updateSession(UserHelper.getUserCredentials(this))
        }
    }

    private fun getOrCreateSessionKey(): String {
        var session: String? = SharedPrefHelper.getSession(this, null)
        if (session == null) {
            session = UUID.randomUUID().toString()
            SharedPrefHelper.putSession(this, session)
        }
        return session
    }

    companion object {
        lateinit var sessionManager: SessionProvider
            private set
    }
}

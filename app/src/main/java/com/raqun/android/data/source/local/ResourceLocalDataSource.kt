package com.raqun.android.data.source.local

import android.content.Context
import com.raqun.android.Constants
import com.raqun.android.R
import com.raqun.android.data.source.ResourceDataSource
import com.raqun.android.model.Content
import javax.inject.Inject

class ResourceLocalDataSource @Inject constructor(private val context: Context) : ResourceDataSource {

    override suspend fun getContent(contentId: Int): Content {
        return when (contentId) {
            Constants.CONTENT_TYPE_ABOUT -> Content(Constants.CONTENT_TYPE_ABOUT, context.getString(R.string.content_title_about), context.getString(R.string.content_about))
            Constants.CONTENT_TYPE_USER_AGREEMENT -> Content(Constants.CONTENT_TYPE_USER_AGREEMENT, context.getString(R.string.content_title_user_agreement), context.getString(R.string.content_user_agreement))
            Constants.CONTENT_TYPE_CONTACT_PERMISSION -> Content(Constants.CONTENT_TYPE_CONTACT_PERMISSION, context.getString(R.string.content_contact_permissions), context.getString(R.string.content_contact_permissions))
            else -> Content(-1, context.getString(R.string.app_name), context.getString(R.string.content_empty))
        }
    }
}

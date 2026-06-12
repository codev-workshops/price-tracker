package com.raqun.android.data.source

import com.raqun.android.model.Content

interface ResourceDataSource {
    suspend fun getContent(contentId: Int): Content
}

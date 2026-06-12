package com.raqun.android.data.source

import com.raqun.android.data.source.local.ResourceLocalDataSource
import com.raqun.android.model.Content
import javax.inject.Inject

class ResourceRepository @Inject constructor(private val resourceLocalDataSource: ResourceLocalDataSource)
    : ResourceDataSource {

    override suspend fun getContent(contentId: Int): Content =
            resourceLocalDataSource.getContent(contentId)
}

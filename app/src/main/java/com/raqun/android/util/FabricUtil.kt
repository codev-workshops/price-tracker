package com.raqun.android.util

import com.raqun.android.data.DataState

/**
 * Created by tyln on 01/11/2017.
 */

object FabricUtil {
    const val EVENT_ADD_PRODUCT = "add_product"
    const val KEY_RESULT = "result"
    const val KEY_URL = "url"
}

object AnalyticsHelper {
    fun reportAddProduct(dataState: DataState, url: String) {
        // No-op: Fabric/Crashlytics removed; replace with your analytics provider
    }
}

package com.raqun.android.ui.product

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.util.SparseArray
import androidx.fragment.app.FragmentPagerAdapter
import com.raqun.android.model.Product
import com.raqun.android.ui.product.alarms.AlarmsFragment
import com.raqun.android.ui.product.detail.DetailFragment


/**
 * Created by tyln on 06/11/2017.
 */
internal class ProductTabsAdapter(
        private val titles: Array<String>,
        private val product: Product?,
        private val productId: String?,
        fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            TAB_DETAIL -> DetailFragment.newInstance(product, productId)
            TAB_ALARMS -> AlarmsFragment.newInstance(productId)
            else -> throw IllegalArgumentException("Unexpected tab id!!")
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    companion object {
        val TAB_DETAIL = 0
        val TAB_ALARMS = 1
    }
}
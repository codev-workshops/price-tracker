package com.raqun.android.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.raqun.android.api.ApiConstants

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    open fun getNavigationType(): NavigationController.NavigationType = NavigationController.NavigationType.BACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())

        when (getNavigationType()) {
            NavigationController.NavigationType.BACK -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            NavigationController.NavigationType.ROOT -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }

    open fun setScreenTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun initNavigation(navigationType: NavigationController.NavigationType) {
        when (navigationType) {
            NavigationController.NavigationType.BACK -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            NavigationController.NavigationType.ROOT -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

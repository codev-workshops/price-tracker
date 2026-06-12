package com.raqun.android.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.raqun.android.Constants
import com.raqun.android.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Created by tyln on 22/07/2017.
 */
abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @LayoutRes abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        findViewById<Toolbar>(R.id.toolbar)?.let {
            setToolbar(it)
        }
        initNavigation(getNavigationType())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (getMenuRes() != Constants.NO_RES) {
            menuInflater.inflate(getMenuRes(), menu)
            return true
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    @MenuRes protected open fun getMenuRes(): Int = Constants.NO_RES

    protected open fun getNavigationType() = NavigationController.NavigationType.BACK

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    fun setScreenTitle(title: String?) {
        supportActionBar?.title = title ?: getString(R.string.app_name)
    }

    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
    }

    fun initNavigation(navigationType: NavigationController.NavigationType) {
        when (navigationType) {
            NavigationController.NavigationType.BACK -> supportActionBar?.setDisplayHomeAsUpEnabled(true)
            NavigationController.NavigationType.ROOT -> supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}

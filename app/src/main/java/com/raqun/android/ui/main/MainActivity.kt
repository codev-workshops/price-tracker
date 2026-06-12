package com.raqun.android.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.fragment.app.Fragment
import android.view.MenuItem
import com.raqun.android.R
import com.raqun.android.ui.BaseActivity
import com.raqun.android.ui.add.AddProductActivity
import com.raqun.android.ui.main.favorites.FavoritesFragment
import com.raqun.android.ui.main.home.HomeFragment
import com.raqun.android.ui.main.more.MoreFragment
import com.raqun.android.ui.main.notifications.NotificationsFragment
import com.raqun.android.extensions.disableShiftingMode
import com.raqun.android.extensions.init
import com.raqun.android.extensions.openFragment
import com.raqun.android.fcm.RegisterTokenService
import com.raqun.android.ui.NavigationController

class MainActivity : BaseActivity(), FabProvider {

    private var fab: FloatingActionButton? = null

    @LayoutRes override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init(savedInstanceState, HomeFragment.newInstance())

        if (savedInstanceState == null) {
            startService(RegisterTokenService.newIntent(this))
        }

        fab = findViewById<FloatingActionButton>(R.id.fab_add)?.also {
            it.setOnClickListener {
                startActivity(AddProductActivity.newIntent(this))
            }
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
            setOnNavigationItemSelectedListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.action_home -> openFragment(HomeFragment.newInstance())
                    R.id.action_favs -> openFragment(FavoritesFragment.newInstance())
                    R.id.action_notifications -> openFragment(NotificationsFragment.newInstance())
                    R.id.action_more -> openFragment(MoreFragment.newInstance())
                    else -> false
                }
            }
        }.also {
            it.disableShiftingMode()
        }
    }

    override fun getNavigationType() = NavigationController.NavigationType.ROOT

    private fun openFragment(fragment: Fragment): Boolean {
        supportFragmentManager.openFragment(R.id.framelayout_main, fragment)
        return true
    }

    override fun showFab() {
        fab?.show()
    }

    override fun hideFab() {
        fab?.hide()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}

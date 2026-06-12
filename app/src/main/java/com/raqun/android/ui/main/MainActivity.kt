package com.raqun.android.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.raqun.android.R
import com.raqun.android.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import com.raqun.android.ui.add.AddProductActivity
import com.raqun.android.fcm.RegisterTokenService
import com.raqun.android.ui.NavigationController

@AndroidEntryPoint
class MainActivity : BaseActivity(), FabProvider {

    private var fab: FloatingActionButton? = null

    @LayoutRes override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            RegisterTokenService.enqueue(this)
        }

        fab = findViewById<FloatingActionButton>(R.id.fab_add)?.also {
            it.setOnClickListener {
                startActivity(AddProductActivity.newIntent(this))
            }
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setupWithNavController(navController)
    }

    override fun getNavigationType() = NavigationController.NavigationType.ROOT

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

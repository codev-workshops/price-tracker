package com.raqun.android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.raqun.android.Constants
import com.raqun.android.api.ApiConstants
import com.raqun.android.data.Error

abstract class BaseFragment : Fragment(), BaseView {

    var navigationController: NavigationController? = null

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @StringRes
    open fun getTitleRes(): Int = Constants.NO_RES

    open fun getMenuRes(): Int = Constants.NO_RES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationController = NavigationController(requireActivity())
        if (getMenuRes() != Constants.NO_RES) {
            @Suppress("DEPRECATION")
            setHasOptionsMenu(true)
        }
    }

    open fun setActivityTitle(title: String?) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).setScreenTitle(title)
        }
    }

    fun alert(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onError(e: Error?) {
        e?.let {
            if (it.code == ApiConstants.ERROR_CODE_AUTH) {
                navigationController?.navigateToLogoutForAuthError()
            }
        }
    }

    open fun initView() {}
}

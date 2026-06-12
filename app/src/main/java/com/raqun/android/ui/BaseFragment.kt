package com.raqun.android.ui

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import android.view.*
import android.widget.Toast
import com.raqun.android.Constants
import com.raqun.android.R
import com.raqun.android.api.ApiConstants
import com.raqun.android.data.Error
import com.raqun.android.extensions.getErrorMessage
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection


/**
 * Created by tyln on 22/07/2017.
 */

abstract class BaseFragment : Fragment(), BaseView {

    protected var navigationController: NavigationController? = null

    @LayoutRes protected abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        if (activity is HasAndroidInjector) {
            AndroidSupportInjection.inject(this)
        }
        super.onCreate(savedInstanceState)
        navigationController = NavigationController(requireActivity())
        if (getMenuRes() != Constants.NO_RES) {
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (getMenuRes() != Constants.NO_RES) {
            inflater.inflate(getMenuRes(), menu)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Toolbar>(R.id.toolbar)?.let {
            if (activity is BaseActivity) {
                (activity as BaseActivity).setToolbar(it)
            }
        }
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (getTitleRes() != Constants.NO_RES) {
            setActivityTitle(getString(getTitleRes()))
        }
    }

    override fun onDestroyView() {
        navigationController = null
        super.onDestroyView()
    }

    override fun onError(e: Error?) {
        e?.let {
            when (e.code) {
                ApiConstants.ERROR_CODE_AUTH -> navigationController?.navigateToLogoutForAuthError()
                else -> defaultErrorBehavior(e)
            }
        } ?: defaultErrorBehavior(e)
    }

    private fun defaultErrorBehavior(e: Error?) {
        val message: String = if (e == null) {
            getString(R.string.error_message_unknown)
        } else {
            when (e.code) {
                ApiConstants.ERROR_CODE_NETWORK -> getString(R.string.error_message_connection)
                else -> requireContext().getErrorMessage(e.code)
            }
        }
        alert(message)
    }

    protected fun alert(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun setActivityTitle(title: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).setScreenTitle(title)
        }
    }

    @MenuRes protected open fun getMenuRes(): Int = Constants.NO_RES

    @StringRes protected open fun getTitleRes(): Int = R.string.app_name

    fun getApplication(): Application = requireActivity().application

    fun getApplicationContext(): Context = getApplication().applicationContext
}

package com.raqun.android.ui.main.more

import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.util.Log
import com.raqun.android.Constants
import com.raqun.android.R
import com.raqun.android.data.DataBean
import com.raqun.android.databinding.FragmentMoreBinding
import com.raqun.android.model.UiDataBean
import com.raqun.android.model.User
import com.raqun.android.ui.BaseActivity
import com.raqun.android.ui.BinderFragment
import com.raqun.android.ui.BaseFragment
import com.raqun.android.ui.main.FabProvider
import com.raqun.android.util.Alert

/**
 * Created by tyln on 31/07/2017.
 */
@AndroidEntryPoint
class MoreFragment : BinderFragment<FragmentMoreBinding, MoreViewModel>(), MoreView {

    override fun getLayoutRes(): Int = R.layout.fragment_more

    override fun getModelClass(): Class<MoreViewModel> = MoreViewModel::class.java

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is FabProvider) {
            (activity as FabProvider).hideFab()
        }

        viewModel.getUserState().observe(this, Observer { bean: DataBean<Boolean>? ->
            binding.isLoggedIn = bean?.getData()
        })

        viewModel.getUserDetail().observe(this, Observer { bean: DataBean<User>? ->
            bean?.getData()?.run {
                if (activity is BaseActivity) {
                    (activity as BaseActivity).setScreenTitle(userName)
                }
            }
        })

        viewModel.init()
    }

    override fun initView() {
        binding.moreView = this
    }

    override fun showAbout() {
        navigationController?.navigateToContent(Constants.CONTENT_TYPE_ABOUT,
                getString(R.string.content_title_about))
    }

    override fun showContact() {
        navigationController?.navigateToContact()
    }

    override fun rateApp() {
        navigationController?.navigateToGooglePlay()
    }

    override fun login() {
        navigationController?.navigateToLogin()
    }

    override fun logout() {
        Alert.create(requireActivity(),
                null,
                getString(R.string.dialog_message_logout),
                true,
                R.string.button_logout,
                R.string.button_cancel,
                { navigationController?.navigateToLogout() },
                {}).show()
    }

    override fun register() {
        navigationController?.navigateToRegister()
    }

    companion object {
        fun newInstance() = MoreFragment()
    }

}
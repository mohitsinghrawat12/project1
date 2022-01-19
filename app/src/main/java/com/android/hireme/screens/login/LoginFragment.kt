package com.android.hireme.screens.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hireme.R
import com.android.hireme.activity.HireMeActivity
import com.android.hireme.base.BaseFragment
import com.android.hireme.databinding.FragmentLoginBinding
import com.android.hireme.factory.ViewModelsFactory
import com.android.hireme.util.Status
import com.android.hireme.util.TOAST_ERROR_TYPE
import com.android.hireme.util.TOAST_SUCCESS_TYPE
import com.android.hireme.util.showCustomToast
import com.android.hireme.viewmodels.LoginViewModel

class LoginFragment : BaseFragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var mBinding: FragmentLoginBinding
    lateinit var loginViewModel : LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginViewModel = ViewModelProviders.of(
        this,
        ViewModelsFactory()
        ).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { setProgressBar(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login, container, false)
        mBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = loginViewModel
        }
        return mBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        mBinding.loginButton.setOnClickListener(this)

        loginViewModel.getLoginResponse().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    dismissProgressBar()
                    it.data?.let { it1 ->


                        val intent = Intent(context, HireMeActivity::class.java).apply {
                            putExtra("loginResponse", it1)
                        }
                        startActivity(intent)
                    }
                }
                Status.LOADING -> {
                    showProgressBar()

                }
                Status.ERROR -> {
                    dismissProgressBar()
                    it.data?.let { it1 ->
                        Toast(context).showCustomToast(TOAST_ERROR_TYPE, it1.message,
                            context as Activity
                        )
                    }
                }
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.loginButton -> {
                loginViewModel.getLogin(mBinding.email.text.toString(), mBinding.password.text.toString())
            }
        }
    }

}
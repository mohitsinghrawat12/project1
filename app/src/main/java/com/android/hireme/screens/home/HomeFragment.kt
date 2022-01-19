package com.android.hireme.screens.home

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.navigation.fragment.navArgs
import com.android.hireme.R
import com.android.hireme.base.BaseFragment
import com.android.hireme.databinding.FragmentHomeBinding
import com.android.hireme.databinding.FragmentLoginBinding
import com.android.hireme.factory.ViewModelsFactory
import com.android.hireme.util.Status
import com.android.hireme.util.TOAST_ERROR_TYPE
import com.android.hireme.util.TOAST_SUCCESS_TYPE
import com.android.hireme.util.showCustomToast
import com.android.hireme.viewmodels.HomeViewModel
import com.android.hireme.viewmodels.LoginViewModel

class HomeFragment : BaseFragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var mBinding: FragmentHomeBinding
    lateinit var homeViewModel : HomeViewModel

    val args: HomeFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeViewModel = ViewModelProviders.of(
        this,
        ViewModelsFactory()
        ).get(HomeViewModel::class.java)
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
            R.layout.fragment_home, container, false)
        mBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = homeViewModel
        }
        return mBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        navController = Navigation.findNavController(view)
//        val arg = navController.currentDestination?.arguments?.get("loginResposnse")
//        val info = args.loginResponse
//        Log.d("mohit", "abc")

        val bundle = arguments
                if (bundle == null) {
                    Log.e("Confirmation", "ConfirmationFragment did not receive traveler information")
                    return
                }

        val args = HomeFragmentArgs.fromBundle(bundle)
        Log.d("mohit", "abc")
        val loginResponse = args.loginResponse
        loginResponse?.let {
            Toast(context).showCustomToast(
                TOAST_SUCCESS_TYPE, it.message,
                context as Activity
            )
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {

        }
    }

}
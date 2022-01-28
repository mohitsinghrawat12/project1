package com.android.hireme.screens.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hireme.R
import android.widget.Toast
import android.widget.AdapterView.OnItemClickListener

import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hireme.activity.HireMeActivity
import com.android.hireme.base.BaseFragment
import com.android.hireme.databinding.FragmentSignupBinding
import com.android.hireme.factory.ViewModelsFactory
import com.android.hireme.util.Status
import com.android.hireme.util.TOAST_ERROR_TYPE
import com.android.hireme.util.TOAST_SUCCESS_TYPE
import com.android.hireme.util.showCustomToast
import com.android.hireme.viewmodels.SignUpViewModel


class SignupFragment : BaseFragment(){
    lateinit var navController: NavController
    lateinit var mBinding: FragmentSignupBinding
    lateinit var signUpViewModel : SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { setProgressBar(it) }
    }

    val list = arrayOf(
        "Cristiano Ronaldo",
        "Messi",
        "Neymar",
        "Isco",
        "Hazard",
        "Mbappe",
        "Hazard",
        "Ziyech",
        "Suarez"
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signUpViewModel = ViewModelProviders.of(
            this,
            ViewModelsFactory()
        ).get(SignUpViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_signup, container, false)
        mBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = signUpViewModel
        }

        return mBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val arrayAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, it.resources.getStringArray(R.array.select_roles)) }
//            ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1, list
//        )
        var selection: String
        mBinding.selectRole.setAdapter(arrayAdapter)
        mBinding.selectRole.setThreshold(1)
        mBinding.selectRole.setCursorVisible(false)
        mBinding.selectRole.setOnClickListener {
            mBinding.selectRole.showDropDown()
        }
        mBinding.selectRole.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            mBinding.selectRole.showDropDown()
            selection = parent.getItemAtPosition(position) as String
            Toast.makeText(
                context, selection,
                Toast.LENGTH_SHORT
            )
        })

        signUpViewModel.getSignUpResponse().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    dismissProgressBar()
                    it.data?.let { it1 ->
                        Toast(context).showCustomToast(
                            TOAST_SUCCESS_TYPE, it1.message,
                            context as Activity
                        )

                        navController.popBackStack()
                    }
                }
                Status.LOADING -> {
                    showProgressBar()

                }
                Status.ERROR -> {
                    dismissProgressBar()
                    it.data?.let { it1 ->
                        Toast(context).showCustomToast(
                            TOAST_ERROR_TYPE, it1.message,
                            context as Activity
                        )
                    }
                }
            }
        })
        mBinding.signUpButton.setOnClickListener {
            if (signUpViewModel.isValidForm(mBinding.email.text.toString(), mBinding.password.text.toString(),mBinding.firstName.text.toString(), mBinding.lastName.text.toString(),mBinding.selectRole.text.toString())) {
                signUpViewModel.signUp(mBinding.email.text.toString(), mBinding.password.text.toString(),mBinding.firstName.text.toString(), mBinding.lastName.text.toString(),mBinding.selectRole.text.toString())
            }else{
                Toast(context).showCustomToast(
                    TOAST_ERROR_TYPE, "Please fill all the fields",
                    context as Activity
                )
            }
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() = SignupFragment()
    }


}
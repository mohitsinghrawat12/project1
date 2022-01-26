package com.android.hireme.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.hireme.viewmodels.HomeViewModel
import com.android.hireme.viewmodels.LoginViewModel
import com.android.hireme.viewmodels.SignUpViewModel

class ViewModelsFactory (): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel() as T
        } else if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel() as T
        }
        else if(modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            return SignUpViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}
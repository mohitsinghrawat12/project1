package com.android.hireme.screens.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hireme.R
import com.android.hireme.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

class SplashFragment : BaseFragment() {

    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


//        Timer().schedule(2000) {
//            navController.navigate(R.id.action_splashFragment_to_welcomeFragment)
//        }
//
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            navController.navigate(R.id.action_splashFragment_to_welcomeFragment)

        }
    }

    companion object {

        fun newInstance() = SplashFragment()

    }
}
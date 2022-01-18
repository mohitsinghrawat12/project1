package com.android.hireme.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hireme.R

class WelcomeFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        view.findViewById<ImageView>(R.id.signUpButton).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.linkedinButton).setOnClickListener(this)
        view.findViewById<TextView>(R.id.loginButton).setOnClickListener(this)
    }

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.signUpButton -> navController.navigate(R.id.action_welcomeFragment_to_signupFragment)
            R.id.linkedinButton -> navController.navigate(R.id.action_welcomeFragment_to_signupFragment)
            R.id.loginButton -> navController.navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
    }
}
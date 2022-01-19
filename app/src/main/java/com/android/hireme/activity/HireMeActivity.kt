package com.android.hireme.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.android.hireme.R
import com.android.hireme.model.LoginResponse

class HireMeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hire_me)
        var loginResponse = intent.getSerializableExtra("loginResponse")
        val navController = Navigation.findNavController(this, R.id.navFragment)
        val bundle = Bundle()
        bundle.putSerializable("loginResponse",loginResponse)
        navController.setGraph(navController.graph,bundle)

    }
}
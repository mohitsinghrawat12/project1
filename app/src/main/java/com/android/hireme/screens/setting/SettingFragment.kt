package com.android.hireme.screens.setting

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hireme.R
import com.android.hireme.base.BaseFragment
import com.android.hireme.util.TOAST_ERROR_TYPE
import com.android.hireme.util.TOAST_SUCCESS_TYPE
import com.android.hireme.util.showCustomToast

class SettingFragment:BaseFragment() {
    lateinit var navController: NavController

    val settingOptionName = arrayOf(
        "Change Password",
        "Faq",
        "Contact Us",
        "Terms Of Use",
        "Privacy Policy",
        "Delete Account"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val linearMain=view.findViewById<LinearLayout>(R.id.linear_main)
        for (optionName in settingOptionName){
            addSettingOptions(linearMain,optionName)
        }
    }


    private fun addSettingOptions(linearLayout: LinearLayout, optionName: String) {
       val childMain=layoutInflater.inflate(R.layout.layout_setting_option,null,false) as RelativeLayout
        val textView=childMain.findViewById<AppCompatTextView>(R.id.textView)
        textView.text = optionName
        childMain.tag = optionName
        childMain.setOnClickListener {
            openControlAccordingly(childMain.tag as String)
        }
        linearLayout.addView(childMain)
    }

    private fun openControlAccordingly(tagName: String) {
        when(tagName){
            "Change Password"->{
                showToast(tagName)
            }
            "Faq"->{
                showToast(tagName)
            }
            "Contact Us"->{
                showToast(tagName)
            }
            "Terms Of Use"->{
                showToast(tagName)
            }
            "Privacy Policy"->{
                showToast(tagName)
            }
            "Delete Account"->{
                showToast(tagName)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting,container,false)
    }
}
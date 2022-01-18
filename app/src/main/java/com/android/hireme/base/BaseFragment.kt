package com.android.hireme.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
open class BaseFragment : Fragment() {

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setProgressBar(context: Context){
        progressDialog = ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
    }

    fun showProgressBar(){
        progressDialog.let {
            it.show()
        }
    }

    fun dismissProgressBar(){
        progressDialog.let {
            it.dismiss()
        }
    }

}
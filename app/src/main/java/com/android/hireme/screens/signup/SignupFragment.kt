package com.android.hireme.screens.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hireme.R
import android.widget.Toast
import android.widget.AdapterView.OnItemClickListener

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView


class SignupFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        // Inflate the layout for this fragment
        val selectRole = view.findViewById<AutoCompleteTextView>(R.id.selectRole)
        val arrayAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, it.resources.getStringArray(R.array.select_roles)) }
//            ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1, list
//        )
        var selection: String
        selectRole.setAdapter(arrayAdapter)
        selectRole.setThreshold(1)
        selectRole.setCursorVisible(false)
        selectRole.setOnClickListener {
            selectRole.showDropDown()
        }
        selectRole.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            selectRole.showDropDown()
            selection = parent.getItemAtPosition(position) as String
            Toast.makeText(
                context, selection,
                Toast.LENGTH_SHORT
            )
        })
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignupFragment()
    }
}
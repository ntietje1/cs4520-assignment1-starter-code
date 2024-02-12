package com.cs4520.assignment1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cs4520.assignment1.R

class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_view, container, false)
        val loginButton = view.findViewById<View>(R.id.loginButton)
        val navController = findNavController()
        loginButton.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_productListFragment)
        }
        return view
    }
}
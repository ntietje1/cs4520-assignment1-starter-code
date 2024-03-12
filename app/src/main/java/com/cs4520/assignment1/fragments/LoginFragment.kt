package com.cs4520.assignment1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.cs4520.assignment1.R

class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_view, container, false)
        val loginButton = view.findViewById<View>(R.id.loginButton)
        val usernameField = view?.findViewById<TextView>(R.id.usernameField)
        val passwordField = view?.findViewById<TextView>(R.id.passwordField)
        loginButton.setOnClickListener {
            login(usernameField!!, passwordField!!, findNavController())
        }
        return view
    }

    private fun loginAuth(username: String, password: String): Boolean {
        return username == "admin" && password == "admin"
    }

    private fun login(usernameField: TextView, passwordField: TextView, navController: NavController) {
        if (loginAuth(usernameField.text.toString(), passwordField.text.toString())) {
            usernameField.text = ""
            passwordField.text = ""
            navController.navigate(R.id.action_loginFragment_to_productListFragment)
        }
        else {
            Toast.makeText(context, "Invalid username or password. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }
}
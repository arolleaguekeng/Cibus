package com.example.cibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cibs.model.User
import com.example.cibs.model.UserResponse
import com.example.cibs.viewModel.LoginActivityViewModel
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginActivityViewModel

    lateinit var email: TextInputEditText
    lateinit var password: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById<Button>(R.id.login)
        val register = findViewById<TextView>(R.id.register)
        initViewModel()
        loginUserObservable()
        register.setOnClickListener {
            Intent(applicationContext, SignUpActivity::class.java).also {
                startActivity(it)
            }
        }

        btnLogin.setOnClickListener {
            loginUser()
        }





    }

    private fun loginUser(){
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        viewModel.LoginUser(email.text.toString(), password.text.toString())

    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)

    }

    private fun loginUserObservable(){
        viewModel.getLoginUserNewObservable().observe(this, Observer<User?> {
            if (it == null) {
                Toast.makeText(applicationContext, "no result found...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "successffull result found...",
                    Toast.LENGTH_SHORT
                ).show()
                Intent(applicationContext, Home::class.java).also {
                    startActivity(it)
                }
                finish()
            }
        }
        )
    }
}
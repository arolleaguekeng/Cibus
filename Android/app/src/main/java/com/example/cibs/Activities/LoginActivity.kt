package com.example.cibs.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cibs.R
import com.example.cibs.model.User
import com.example.cibs.service.utils.LoadingDialog
import com.example.cibs.viewModel.LoginActivityViewModel
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    companion object{
       lateinit var CurrentUser: User
       lateinit var nom: String
       lateinit var email: String
       lateinit var phone: String
       lateinit var password: String
       lateinit var passwordConfirm: String
    }

    lateinit var viewModel: LoginActivityViewModel
    val loading = LoadingDialog(this)
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

        loading.startloading()
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
                loading.isDismiss()
            } else {
                Toast.makeText(
                    applicationContext,
                    "login success...",
                    Toast.LENGTH_SHORT
                ).show()
                loading.isDismiss()
                CurrentUser = it
                Intent(applicationContext, HomeActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }
        }
        )
    }
}
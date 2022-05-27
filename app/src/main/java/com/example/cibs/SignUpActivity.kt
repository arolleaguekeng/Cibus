package com.example.cibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cibs.model.User
import com.example.cibs.model.UserResponse
import com.example.cibs.viewModel.LoginActivityViewModel
import com.example.cibs.viewModel.SignUpActivityViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.zip.Inflater

class SignUpActivity : AppCompatActivity() {

    var phone: TextInputEditText? = null
    var email: TextInputEditText? = null
    var nom: TextInputEditText? = null
    var password: TextInputEditText? = null
    var passwordConfirm: TextInputEditText? = null
    lateinit var viewModel: SignUpActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        loadFragment(RegisterEmailFragment())


        initViewModel()
        createUserObservable()
        var i = 0;
        var btn = findViewById<Button>(R.id.sign)
        btn.setOnClickListener {




            if(i == 0){
                loadFragment(RegisterPasswordFragment())
                btn.text = "Submit"
                i++
            }
            else{
                createUser()
            }

        }

    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun createUser(){



        val user = User(LoginActivity.email, LoginActivity.password, LoginActivity.nom, LoginActivity.phone, null, null)

        viewModel.SignUpUser(user)
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(SignUpActivityViewModel::class.java)

    }

    private fun createUserObservable(){
        viewModel.getSignUpUserNewObservable().observe(this, Observer<UserResponse?> {
            if (it == null) {
                Toast.makeText(applicationContext, "no result found...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "successffull result found...",
                    Toast.LENGTH_SHORT
                ).show()
                Intent(applicationContext, MainActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }

        }
        )
    }
}
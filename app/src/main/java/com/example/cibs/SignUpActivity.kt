package com.example.cibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        loadFragment(RegisterEmailFragment())
        var i = 0;
        var btn = findViewById<Button>(R.id.sign)
        btn.setOnClickListener {
            if(i == 0){
                loadFragment(RegisterPasswordFragment())
                btn.text = "Submit"
                i++
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
}
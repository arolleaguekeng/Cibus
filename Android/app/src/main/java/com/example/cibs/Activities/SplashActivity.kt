package com.example.cibs.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.cibs.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val title = findViewById<TextView>(R.id.titleApp)
        title.alpha = 0f
        title.animate().setDuration(5000).alpha(1f).withEndAction{
            Intent(applicationContext, LoginActivity::class.java).also{
                startActivity(it)
                this.finish()
            }

        }
    }
}
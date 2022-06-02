package com.example.cibs.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.cibs.R

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        var btn = findViewById<Button>(R.id.buttonConfirm)

        btn.setOnClickListener {
            Toast.makeText(applicationContext, "commande ajouter avec success", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
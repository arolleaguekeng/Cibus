package com.example.cibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cibs.Fragments.fragment_home

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment_home(this))
        transaction.addToBackStack(null)//Pas de retour
        transaction.commit()
    }
}
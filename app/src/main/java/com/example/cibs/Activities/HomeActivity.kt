package com.example.cibs.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cibs.Fragments.fragment_home
import com.example.cibs.Fragments.fragment_orders
import com.example.cibs.Fragments.fragment_panier
import com.example.cibs.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadfragment(fragment_home(this))

        //importons le bottom navigation
        val button=findViewById<FloatingActionButton>(R.id.shop_page1)
        val navigationView=findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_page ->{
                    loadfragment(fragment_home(this))
                    return@setOnNavigationItemSelectedListener true
                }
                /*R.id.shop_page->{
                    loadfragment(fragment_panier(this))
                    return@setOnNavigationItemSelectedListener true

                }*/
                R.id.order_page ->{
                    loadfragment(fragment_orders(this))
                    return@setOnNavigationItemSelectedListener true
                }
                else ->false
            }
        }
        button.setOnClickListener{
            button.setBackgroundColor(R.color.Orangered.toInt())
            loadfragment(fragment_panier(this))
        }

    }
    private fun loadfragment(fragment: Fragment){

        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)//Pas de retour
        transaction.commit()
    }
}
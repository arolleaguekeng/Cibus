package com.example.cibs.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cibs.Adapters.CartAdapter
import com.example.cibs.Adapters.CategorieAdapter
import com.example.cibs.Adapters.Restaurant1Adapter
import com.example.cibs.Adapters.Restaurant2Adapter
import com.example.cibs.Fragments.fragment_home
import com.example.cibs.Fragments.fragment_orders
import com.example.cibs.Fragments.fragment_panier
import com.example.cibs.R
import com.example.cibs.model.*
import com.example.cibs.service.utils.LoadingDialog
import com.example.cibs.viewModel.HomeActivityViewModel
import com.example.cibs.viewModel.LoginActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    companion object{
        var CurrentPlat =  Plat(1,"Pizza Peperronie","description","https://st2.depositphotos.com/1177973/9248/i/950/depositphotos_92482426-stock-photo-pepperoni-pizza-with-olives-and.jpg",2.0f,7000.0, 12, 1)
        var CurrentAdd: Double = 0.0
        var ListPanier = mutableListOf<Panier>()
        var listProduitPanier = mutableListOf<ProduitPanier>()
        var listRest = mutableListOf<Restaurant>()
        var listCategorie = mutableListOf<Categorie>()
        var listPlat = mutableListOf<Plat>()
        var listLocalisation = mutableListOf<Localisation>()
    }
    lateinit var cartAdapter: CartAdapter

    lateinit var viewModel: HomeActivityViewModel
    val loading = LoadingDialog(this)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViewModel()
        loadfragment(fragment_home(this))

        //importons le bottom navigation
        val button=findViewById<FloatingActionButton>(R.id.shop_page1)
        val navigationView=findViewById<BottomNavigationView>(R.id.navigation_view)
        var it: MenuItem
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_page ->{
                    loadfragment(fragment_home(this))
                    button.setBackgroundColor(R.color.Orange)
                    return@setOnNavigationItemSelectedListener true
                }
                /*R.id.shop_page->{
                    loadfragment(fragment_panier(this))
                    return@setOnNavigationItemSelectedListener true

                }*/
                R.id.order_page ->{
                    loadfragment(fragment_orders(this))
                    button.setBackgroundColor(R.color.Orange)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.id_panier ->{
                    loadfragment(fragment_panier(this))
                    button.setBackgroundColor(R.color.Orange)
                    return@setOnNavigationItemSelectedListener true
                }
                else ->false
            }


        }

        button.setOnClickListener{
            navigationView.selectedItemId =  R.id.id_panier
            button.setBackgroundColor(R.color.Orange)
            loadfragment(fragment_panier(this))
        }




    }
    private fun loadfragment(fragment: Fragment){

        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)//Pas de retour
        transaction.commit()
    }


     fun initViewModel()
     {
        loading.startloading()


        /* viewModel.getLocationObservable().observe(this, Observer<MutableList<Localisation>?> {
             if (it == null) {
                 Toast.makeText(applicationContext, "Aucun plat", Toast.LENGTH_SHORT).show()
             } else {

                 HomeActivity.listLocalisation = it
                 Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()

             }
         })*/

         //viewModel.getLocalisation()

        loading.isDismiss()
    }



}
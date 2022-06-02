package com.example.cibs.Fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cibs.Activities.CheckoutActivity
import com.example.cibs.Activities.HomeActivity
import com.example.cibs.Activities.LoginActivity
import com.example.cibs.Adapters.CartAdapter
import com.example.cibs.Interfaces.CartClickListener
import com.example.cibs.R
import com.example.cibs.model.*
import com.example.cibs.viewModel.HomeActivityViewModel

class fragment_panier(
    private val context: HomeActivity
):Fragment(), CartClickListener{
    lateinit var cartAdapter: CartAdapter
    var priceT : Double = 0.0
    var total: Double = 0.0
    //lateinit var viewModel: HomeActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_pannier, container, false)
        val price = view.findViewById<TextView>(R.id.price)
        val addprice = view.findViewById<TextView>(R.id.addPrice)
        val totalPrice = view.findViewById<TextView>(R.id.priceTotal)
        val cart = view.findViewById<RecyclerView>(R.id.recycleCart)
        val swiper = view.findViewById<SwipeRefreshLayout>(R.id.swiper)
        val bouton = view.findViewById<Button>(R.id.buttonCart)


        swiper.setOnRefreshListener {
            Handler().postDelayed(Runnable {
                totalPrice.text = (total +  HomeActivity.CurrentAdd).toString()+ " XAF"
                swiper.isRefreshing = false
            }, 1000)
        }
        if(HomeActivity.ListPanier.isEmpty()){
        for(i in HomeActivity.listProduitPanier){

                if(i.user_id == LoginActivity.CurrentUser.user_id){
                    for(j in HomeActivity.listPlat){
                        if(j.repas_id == i.repas_id){
                            HomeActivity.ListPanier.add(Panier(j, i.quantite, j.price*i.quantite))
                        }
                    }
                }


        }
        }


        cartAdapter = CartAdapter(HomeActivity.ListPanier, this)
        cart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }

        for(i in HomeActivity.ListPanier){
            total += i.prix
        }

        price.text = "$total.toString() XAF"
        totalPrice.text = (total +  HomeActivity.CurrentAdd).toString()+ " XAF"
        addprice.text = HomeActivity.CurrentAdd.toString()  + " XAF"

        bouton.setOnClickListener {
            Intent(context, CheckoutActivity::class.java).also {
                startActivity(it)
            }
        }

        return view
    }

    override fun addClicked(panier: Panier, view: View) {
        panier.quantity += 1
        HomeActivity.CurrentAdd += panier.plat.price
        priceT +=  HomeActivity.CurrentAdd
        panier.prix = priceT
    }

    override fun removeClicked(panier: Panier, view: View) {
        if(panier.quantity > 1){
            panier.quantity -= 1
            HomeActivity.CurrentAdd -= panier.plat.price
            priceT -=  HomeActivity.CurrentAdd
            panier.prix = priceT
        }
        else{
            HomeActivity.ListPanier.remove(panier)
        }

    }





}
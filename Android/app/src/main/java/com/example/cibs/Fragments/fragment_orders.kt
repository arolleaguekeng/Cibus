package com.example.cibs.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cibs.Activities.HomeActivity
import com.example.cibs.Adapters.CartAdapter
import com.example.cibs.Adapters.CommandeAdapter
import com.example.cibs.R

class fragment_orders(
    private val context: HomeActivity
):Fragment(){
    lateinit var commandeAdapter: CommandeAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater?.inflate(R.layout.fragment_commande,container,false)
        val commandeView = view.findViewById<RecyclerView>(R.id.recyclerCommande)
        commandeAdapter = CommandeAdapter(HomeActivity.ListPanier)
        commandeView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commandeAdapter
        }

        return view
    }
}
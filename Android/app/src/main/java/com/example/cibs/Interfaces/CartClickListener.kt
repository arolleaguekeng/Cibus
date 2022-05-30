package com.example.cibs.Interfaces

import android.view.View
import com.example.cibs.model.Categorie
import com.example.cibs.model.Panier

interface CartClickListener {

    fun addClicked(panier: Panier, view: View)
    fun removeClicked(panier: Panier, view: View)
}
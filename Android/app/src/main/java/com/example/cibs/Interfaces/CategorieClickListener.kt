package com.example.cibs.Interfaces

import android.view.View
import com.example.cibs.model.Categorie

interface CategorieClickListener {

    fun onButtonClicked(categorie: Categorie, view: View)

    fun onItemClicked(categorie: Categorie, view: View)
}
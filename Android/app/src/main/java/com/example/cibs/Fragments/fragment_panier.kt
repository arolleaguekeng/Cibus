package com.example.cibs.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cibs.Activities.HomeActivity
import com.example.cibs.R

class fragment_panier(
    private val context: HomeActivity
):Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater?.inflate(R.layout.fragment_pannier,container,false)

        return view
    }
}
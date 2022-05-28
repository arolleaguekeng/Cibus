package com.example.cibs.Interfaces

import android.view.View
import com.example.cibs.model.Plat
import com.example.cibs.model.Restaurant

interface PlatClickListener {

    fun onButtonClicked(plat: Plat)
    fun onItemClicked(plat: Plat, view: View)
}
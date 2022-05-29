package com.example.cibs.Interfaces

import android.view.View
import com.example.cibs.model.Plat

interface PlatClickListener {

    fun onButtonClicked(plat: Plat)
    fun onItemClicked(plat: Plat, view: View)
}
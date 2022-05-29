package com.example.cibs.Interfaces

import android.view.View
import com.example.cibs.model.Restaurant

interface Restaurant1ClickListener {

    fun onButtonClicked(restaurant: Restaurant)

    fun onItemClicked(restaurant: Restaurant, view: View)

}
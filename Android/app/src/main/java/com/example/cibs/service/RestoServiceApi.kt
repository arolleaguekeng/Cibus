package com.example.cibs.service

import com.example.cibs.model.Restaurant
import com.example.cibs.model.User
import retrofit2.Call
import retrofit2.http.GET

interface RestoServiceApi {

    @GET("/Restaurants/")
    //@Headers("Accept:application/json", "Content-type:application/json")
    fun getAllRestaurant(): Call<MutableList<Restaurant>>
}
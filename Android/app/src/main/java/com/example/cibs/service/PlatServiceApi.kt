package com.example.cibs.service

import com.example.cibs.model.Plat
import com.example.cibs.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET

interface PlatServiceApi {
    @GET("/repas/")
    //@Headers("Accept:application/json", "Content-type:application/json")
    fun getAllRepas(): Call<MutableList<Plat>>
}
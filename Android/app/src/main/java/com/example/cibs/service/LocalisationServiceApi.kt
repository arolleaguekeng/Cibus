package com.example.cibs.service

import com.example.cibs.model.Localisation
import com.example.cibs.model.Plat
import retrofit2.Call
import retrofit2.http.GET

interface LocalisationServiceApi {

    @GET("/localisations")
    //@Headers("Accept:application/json", "Content-type:application/json")
    fun getAllLocalisation(): Call<MutableList<Localisation>>
}
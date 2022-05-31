package com.example.cibs.service

import com.example.cibs.model.Categorie
import com.example.cibs.model.Plat
import retrofit2.Call
import retrofit2.http.GET

interface CategorieServiceApi {

    @GET("/category")
    //@Headers("Accept:application/json", "Content-type:application/json")
    fun getAllCategorie(): Call<MutableList<Categorie>>
}
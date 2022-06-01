package com.example.cibs.service

import com.example.cibs.model.Plat
import com.example.cibs.model.ProductResponse
import com.example.cibs.model.ProduitPanier
import retrofit2.Call
import retrofit2.http.Body

import retrofit2.http.GET
import retrofit2.http.POST

interface ProductServiceApi {

    @GET("/produit_paniers")
    //@Headers("Accept:application/json", "Content-type:application/json")
    fun getAllProduitPanier(): Call<MutableList<ProduitPanier>>

    @POST("/produit_panier")
    //@Headers("Accept:application/json", "Content-type:application/json")
    fun AddProduitPanier(@Body param: ProduitPanier): Call<ProductResponse?>
}
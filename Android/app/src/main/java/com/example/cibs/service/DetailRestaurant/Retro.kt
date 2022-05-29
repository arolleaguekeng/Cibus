package com.example.cibs.service.DetailRestaurant

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetoClient(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.107:8000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
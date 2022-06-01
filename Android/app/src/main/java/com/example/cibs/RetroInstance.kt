package com.example.cibs

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{
        var baseAdresse = "http://192.168.43.254:8000/"
        fun getRetroInstance(): Retrofit{

            val loggin = HttpLoggingInterceptor()
            loggin.level = (HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
            client.addInterceptor(loggin)

            return Retrofit.Builder()
                .baseUrl(baseAdresse)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}
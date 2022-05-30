package com.example.cibs.service.DetailRestaurant

import com.example.cibs.model.detailRestaurant.RestaurantModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

interface RestaurantApi {
    @GET("Restaurants/")
    fun getRestaurant():Call<MutableList<RestaurantModel>>
    }

//interface RestaurantApi {
//    @GET("/restorant/")
//    fun getRestaurant():Deferred<Restorant>
//
//    companion object{
//        operator fun invoke(): RestaurantApi{
//            val requestInterceptor = Interceptor{chain ->
//                val url = chain.request()
//                    .url()
//                    .newBuilder()
//                    .build()
//                val request = chain.request()
//                    .newBuilder()
//                    .url(url)
//                    .build()
//                return@Interceptor chain.proceed(request)
//            }
//            val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(requestInterceptor)
//                .build()
//            return Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl("http://172.17.192.1:5000")
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(RestaurantApi::class.java)
//        }
//    }
//}
package com.example.cibs.service

import com.example.cibs.model.User
import com.example.cibs.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserServiceApi {

    @GET("/users")
    //@Headers("Accept:application/json", "Content-type:application/json")
    fun getUser(): Call<MutableList<User>>

    @GET("/login")
    //@Headers("Accept:application/json", "Content-type:application/json")
    fun getUserLogin(@Query("email") email: String, @Query("password") password: String): Call<User>

    @POST("/user")
    fun createUser(@Body params: User): Call<UserResponse>





}
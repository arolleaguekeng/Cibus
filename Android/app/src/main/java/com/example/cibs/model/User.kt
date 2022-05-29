package com.example.cibs.model

data class User(
    val email: String?,
    val password: String?,
    val nom: String?,
    val phone: String?,
    val image: String?,
    val idLocalisation: Int?
)


data   class UserResponse(
    val code: Int?,
    val meta: String?,
    val data: User
)

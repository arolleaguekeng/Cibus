package com.example.cibs.model

data class User(
    val user_id: Int?,
    val localisation_id: Int?,
    val email: String?,
    val password: String?,
    val nom: String?,
    val phone: Int?,
    val image: String?,

)


data   class UserResponse(
    val code: Int?,
    val meta: String?,
    val data: User
)

package com.example.cibs.model

data class Restaurant(
    val idRestaurant: Int,
    val name: String?,
    val description: String?,
    val image: String?,
    val rating: Float,
    val idLocalisation: Int
)
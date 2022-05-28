package com.example.cibs.model

data class Plat(
    val name: String?,
    val description: String?,
    val image: String?,
    val rating: Float,
    val prix: Double,
    val idRestaurant: Int,
    val idCategorie: Int
)
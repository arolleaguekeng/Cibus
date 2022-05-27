package com.example.cibs.model

data class Plat(
    val idPlat: Int,
    val name: String?,
    val description: String?,
    val image: String?,
    val rating: Float,
    val idRestaurant: Int,
    val idCategorie: Int
)
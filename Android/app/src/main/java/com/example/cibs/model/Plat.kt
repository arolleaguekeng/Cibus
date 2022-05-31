package com.example.cibs.model

data class Plat(
    val plat_id: Int,
    val nom: String?,
    val description: String?,
    val image: String?,
    val rating: Float,
    val price: Double,
    val restaurant_id: Int,
    val categorie_id: Int
)
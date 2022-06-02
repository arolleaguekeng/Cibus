package com.example.cibs.model

data class Plat(
    val repas_id: Int,
    val nom: String?,
    val description: String?,
    var image: String?,
    val rating: Float,
    val price: Double,
    val restaurant_id: Int,
    val categorie_id: Int
)
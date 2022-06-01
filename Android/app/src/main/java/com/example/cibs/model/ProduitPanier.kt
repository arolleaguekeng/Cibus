package com.example.cibs.model

data class ProduitPanier(val produit_panier_id: Int, val repas_id: Int,val quantite: Int, val user_id: Int)


data   class ProductResponse(
    val code: Int?,
    val meta: String?,
    val data: User
)
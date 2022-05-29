package com.example.cibs.model.detailRestaurant

import java.util.*

data class Repas(
    var id: Int ,
    var restaurant_id: Int,
    var nom:String,
    var image : String,
    var prix: Float
    ) {
}

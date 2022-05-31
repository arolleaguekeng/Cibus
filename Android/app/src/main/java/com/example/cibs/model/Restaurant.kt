package com.example.cibs.model

import com.example.cibs.model.detailRestaurant.Repas

open class Restaurant(
    var restaurant_id: Int,
    var nom: String?,
    var description: String?,
    var image: String?,
    var rating: Float,
    var idLocalisation: Int
){

}

class RestaurantModel(restaurant: Restaurant, var repas: MutableList<Repas>?)
    : Restaurant(restaurant.restaurant_id,
    restaurant.image,
    restaurant.nom,
    restaurant.description,
    restaurant.rating,
    restaurant.idLocalisation) {
}


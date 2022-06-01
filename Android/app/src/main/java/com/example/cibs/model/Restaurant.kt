package com.example.cibs.model

import com.example.cibs.model.detailRestaurant.Repas

open class Restaurant(
    var restaurant_id: Int,
    var nom: String?,
    var description: String?,
    var image: String?,
    var rating: Float,
    var localisation_id: Int
){

}

class RestaurantModel(restaurant: Restaurant, var plat: MutableList<Plat>?)
    : Restaurant(restaurant.restaurant_id,
    restaurant.image,
    restaurant.nom,
    restaurant.description,
    restaurant.rating,
    restaurant.localisation_id) {
}


//package com.example.cibs.model.detailRestaurant
//import com.google.gson.annotations.SerializedName
//import java.util.*
//
//open class RestaurantModel(
//    var restaurant_id: Int?,
//    var image : String?,
//    var nom : String? ,
//    var description : String?,
//    ) {
//
//    constructor(restaurant : RestaurantModel) : this(restaurant.restaurant_id,restaurant.image,restaurant.nom,restaurant.description) {
//    }
//}
//
//class Restaurant(restaurant: RestaurantModel, var repas: MutableList<Repas>?)
//    : RestaurantModel(restaurant.restaurant_id,
//    restaurant.image,
//    restaurant.nom,
//    restaurant.description) {
//}
//

package com.example.cibs.Adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cibs.HomeActivity
import com.example.cibs.Models.Restaurant
import com.example.cibs.R

class Accueiladapter(
    private val context: HomeActivity,
    private val restaurantList:List<Restaurant>,
    private val layoutId:Int
    ) :RecyclerView.Adapter<Accueiladapter.viewHolder>(){
    //rajouter tout les composants a controler depuis mon adapteur
    class viewHolder(view:View):RecyclerView.ViewHolder(view){
        val Image_categorie:ImageView?=view.findViewById(R.id.Image_categorie)
        val Nom_Categorie=view.findViewById<TextView>(R.id.Nom_categorie)


        val Image_restaurant:ImageView?=view.findViewById(R.id.Image_restaurant)
        val RestaurantName:TextView?=view.findViewById(R.id.Nom_restaurant)
        val Description_restaurant:TextView?=view.findViewById(R.id.Description_restaurant)
       // val Nbre_like_restaut:TextView?=view.findViewById(R.id.nbre_like_restaut)
        val Nbr=view.findViewById<RatingBar>(R.id.StarNote)

        val Image_plat:ImageView?=view.findViewById(R.id.Image_plat)
        val Nom_Plat:TextView?=view.findViewById(R.id.Nom_plat)
        val Prix_plat:TextView?=view.findViewById(R.id.Prix_plat)
        val nom_rest:TextView?=view.findViewById(R.id.Nom_restaurant)
        //val Nbre_like_plat:TextView?=view.findViewById(R.id.StarNotePlat)
        var nbr2:RatingBar?=view.findViewById(R.id.StarNote)
    }

    //Injecter le layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=LayoutInflater.from(parent.context).inflate(layoutId,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentRestaurant=restaurantList[position]

        //glide pour convertir l'image
        holder.Image_categorie?.let {
            Glide.with(context).load(Uri.parse(currentRestaurant.plat.Image)).into(
                it
            )
        }
        holder.Image_restaurant?.let {
            Glide.with(context).load(Uri.parse(currentRestaurant.ImageUrl)).into(
                it
            )
        }
        holder.Image_plat?.let {
            Glide.with(context).load(Uri.parse(currentRestaurant.plat.Image)).into(
                it
            )
        }

        //Mise a jour
        holder.Nom_Categorie?.text=currentRestaurant.plats[position].Categorie.Name


        holder.RestaurantName?.text=currentRestaurant.Name_Restaurant
        holder.Description_restaurant?.text=currentRestaurant.Description
        //holder.Nbre_like_restaut?.text=currentRestaurant.Nbre_like.toString()

        holder.Nom_Plat?.text=currentRestaurant.plats[position].Name_Plat
        //holder.Nbre_like_plat?.text=currentRestaurant.plats[position].Nbre_like.toString()
        holder.Prix_plat?.text=currentRestaurant.plats[position].Price.toString()
        holder.nom_rest?.text=currentRestaurant.Name_Restaurant


    }


    //Compter les items a renvoyer
    override fun getItemCount(): Int=restaurantList.size

}
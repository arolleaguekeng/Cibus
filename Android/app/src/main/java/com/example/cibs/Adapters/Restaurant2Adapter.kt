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
import com.example.cibs.Interfaces.Restaurant1ClickListener
import com.example.cibs.R
import com.example.cibs.model.Restaurant

class Restaurant2Adapter(val datas: MutableList<Restaurant>, private val itemClickListener: Restaurant1ClickListener): RecyclerView.Adapter<Restaurant2Adapter.viewHolder>() {

    class viewHolder(val view: View): RecyclerView.ViewHolder(view){
        val Image_restaurant:ImageView=view.findViewById(R.id.Image_restaurant)
        val RestaurantName:TextView=view.findViewById(R.id.Nom_restaurant)
        val Description_restaurant:TextView?=view.findViewById(R.id.Description_restaurant)

        val nbr=view.findViewById<RatingBar>(R.id.StarNote)


        fun bindData(restaurant: Restaurant) {

            Glide.with(view.context).load(Uri.parse(restaurant.image)).into(
                Image_restaurant
            )
            RestaurantName.text = restaurant.name
            nbr.rating = restaurant.rating

        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Restaurant2Adapter.viewHolder {
        return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal4, parent, false))
    }

    override fun onBindViewHolder(holder: Restaurant2Adapter.viewHolder, position: Int) {
        holder.bindData(datas[position])

        holder.itemView.setOnClickListener(){
            itemClickListener.onItemClicked(datas[position], it)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int = datas.size
}
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
import com.example.cibs.Interfaces.CartClickListener
import com.example.cibs.Interfaces.CategorieClickListener
import com.example.cibs.R
import com.example.cibs.model.Categorie
import com.example.cibs.model.Panier
import com.example.cibs.model.Plat


class CartAdapter(val datas: MutableList<Panier>, private val itemClickListener: CartClickListener): RecyclerView.Adapter<CartAdapter.viewHolder>() {

    class viewHolder(val view: View): RecyclerView.ViewHolder(view){

        val image_plat: ImageView =view.findViewById(R.id.image)
        val title: TextView =view.findViewById(R.id.repasName)
        val subtitle: TextView =view.findViewById(R.id.repasName)
        val rating: RatingBar =view.findViewById(R.id.rating)
        val prix: TextView =view.findViewById(R.id.price)
        val quantity: TextView =view.findViewById(R.id.Quantity)
        val add: ImageView =view.findViewById(R.id.addQuantity)
        val remove: ImageView =view.findViewById(R.id.remove)

        fun bindData(panier: Panier) {

            Glide.with(view.context).load(Uri.parse(panier.plat.image)).into(
                image_plat
            )
            title.text = panier.plat.name
            subtitle.text = panier.plat.name
            rating.rating = panier.plat.rating
            prix.text =  panier.plat.prix.toString()
            quantity.text = panier.quantity.toString()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plat_cart, parent, false))
    }

    override fun onBindViewHolder(holder: CartAdapter.viewHolder, position: Int) {
        holder.bindData(datas[position])

        holder.add.setOnClickListener(){
            itemClickListener.addClicked(datas[position], it)
            notifyDataSetChanged()
        }

        holder.remove.setOnClickListener(){
            itemClickListener.removeClicked(datas[position], it)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = datas.size
}
package com.example.cibs.Adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cibs.Interfaces.PlatClickListener
import com.example.cibs.R
import com.example.cibs.model.Plat

class PlatAdapter(val datas: MutableList<Plat>, private val itemClickListener: PlatClickListener): RecyclerView.Adapter<PlatAdapter.viewHolder>() {

    class viewHolder(val view: View): RecyclerView.ViewHolder(view){
        private val Image_plat: ImageView =view.findViewById(R.id.Image_plat)
        val nom_plat: TextView =view.findViewById<TextView>(R.id.Nom_plat)
        val price: TextView =view.findViewById<TextView>(R.id.Prix_plat)
        val add: ImageView = view.findViewById(R.id.Ajout)
        val nomRestaurant : TextView =view.findViewById<TextView>(R.id.Nom_restaurant)

        fun bindData(plat: Plat) {

            Glide.with(view.context).load(Uri.parse(plat.image)).into(
                Image_plat
            )
            nom_plat.text = plat.name
            price.text = plat.prix.toString()
            nomRestaurant.text = plat.idRestaurant.toString()

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal3, parent, false))
    }

    override fun onBindViewHolder(holder: PlatAdapter.viewHolder, position: Int) {
        holder.bindData(datas[position])

        holder.itemView.setOnClickListener(){
            itemClickListener.onItemClicked(datas[position], it)
            notifyDataSetChanged()
        }

        holder.add.setOnClickListener(){
            itemClickListener.onItemClicked(datas[position], it)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int = datas.size
}
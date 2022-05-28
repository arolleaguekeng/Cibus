package com.example.cibs.Adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cibs.HomeActivity
import com.example.cibs.Interfaces.CategorieClickListener
import com.example.cibs.R
import com.example.cibs.model.Categorie

class CategorieAdapter(val datas: MutableList<Categorie>, private val itemClickListener: CategorieClickListener): RecyclerView.Adapter<CategorieAdapter.viewHolder>() {

    class viewHolder(val view: View): RecyclerView.ViewHolder(view){
        private val Image_categorie: ImageView=view.findViewById(R.id.Image_categorie)
        val Nom_Categorie: TextView =view.findViewById<TextView>(R.id.Nom_categorie)

        fun bindData(categorie: Categorie) {

            Glide.with(view.context).load(Uri.parse(categorie.image)).into(
                Image_categorie
            )
            Nom_Categorie.text = categorie.name
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal1, parent, false))
    }

    override fun onBindViewHolder(holder: CategorieAdapter.viewHolder, position: Int) {
        holder.bindData(datas[position])

        holder.itemView.setOnClickListener(){
            itemClickListener.onItemClicked(datas[position], it)
            notifyDataSetChanged()
        }

        holder.Nom_Categorie.setOnClickListener(){
            itemClickListener.onItemClicked(datas[position], it)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int = datas.size
}
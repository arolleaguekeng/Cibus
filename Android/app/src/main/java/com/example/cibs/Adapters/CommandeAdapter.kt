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
import com.example.cibs.Interfaces.ItemClickListener
import com.example.cibs.R
import com.example.cibs.RetroInstance
import com.example.cibs.model.Panier

class CommandeAdapter(val datas: MutableList<Panier>): RecyclerView.Adapter<CommandeAdapter.viewHolder>() {

    class viewHolder(val view: View): RecyclerView.ViewHolder(view){

        val image: ImageView =view.findViewById(R.id.imageCommande)
        val title: TextView =view.findViewById(R.id.titleCommande)
        val montant: TextView =view.findViewById(R.id.montantCommande)
        val quantite: TextView =view.findViewById(R.id.QuantityCommande)

        fun bindData(panier: Panier) {
            var http = RetroInstance.baseAdresse+"static/"+panier.plat.image
            Glide.with(view.context).load(Uri.parse(http)).into(
               image
            )
            title.text = panier.plat.nom
            montant.text = (panier.plat.price * panier.quantity).toString()
            quantite.text = panier.quantity.toString()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandeAdapter.viewHolder {
        return CommandeAdapter.viewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_commande, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommandeAdapter.viewHolder, position: Int) {
        holder.bindData(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}
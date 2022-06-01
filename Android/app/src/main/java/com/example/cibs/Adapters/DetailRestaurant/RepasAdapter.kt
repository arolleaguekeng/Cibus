package com.example.cibs.Adapters.DetailRestaurant

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cibs.Activities.HomeActivity
import com.example.cibs.R
import com.example.cibs.RetroInstance
import com.example.cibs.model.Plat
import com.example.cibs.model.detailRestaurant.Repas
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso

class RepasAdapter(
    var  items: MutableList<Plat>,
    val listener: onItemClickListener
): RecyclerView.Adapter<RepasAdapter.RepasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepasViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_repas,parent,false)
        return RepasViewHolder(itemView)
    }
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    override fun onBindViewHolder(holder: RepasViewHolder, position: Int) {
        val client = items[position]
        Picasso.get().load(items[position].image!!).into(holder.itemImage)
        holder.bind(client)
    }

    override fun getItemCount()= items.size

    inner  class RepasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var repasNames : TextView = itemView.findViewById(R.id.txtrepasName)
        val btn : CardView = itemView.findViewById(R.id.btnAddPanier)
        val itemImage : ImageView = itemView.findViewById(R.id.ivRepas)
        var ctlItems : ConstraintLayout = itemView.findViewById(R.id.ctlItems)
        var price : TextView = itemView.findViewById(R.id.txtBigPrice)
        var description : TextView = itemView.findViewById(R.id.txtDescription)
        var imgUp : ImageView = itemView.findViewById(R.id.imgUp)
        var rating: RatingBar = itemView.findViewById(R.id.restoRating)
        var imageCart: ImageView = itemView.findViewById(R.id.image)
        var titleCart: TextView = itemView.findViewById(R.id.repasName)
        var descriptionCart: TextView = itemView.findViewById(R.id.descriptionCart)
        var button: AppCompatButton = itemView.findViewById(R.id.boutonAdd)
        var ratingCart: RatingBar = itemView.findViewById(R.id.ratingCart)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                btn.visibility = View.VISIBLE
                imgUp.visibility = View.VISIBLE
                ctlItems.visibility = View.GONE
                listener.onItemClick(position)
                if (itemView.getParent() != null) {
                    (itemView.getParent() as ViewGroup).removeView(itemView) // <- fix
                }
                val dialog = BottomSheetDialog(itemView.context)
                dialog.setContentView(itemView)
                dialog.show()

            }
        }
        fun bind(repas: Plat)
        {
            btn.visibility = View.GONE
            imgUp.visibility = View.GONE
            ctlItems.maxHeight = 400
            repasNames.text = repas.nom
            price.text = repas.price.toString()
            description.text = repas.description
            rating.rating = repas.rating
//            CountryFlag.setImageResource(country.Image)
            var http = RetroInstance.baseAdresse+"static/"+repas.image
            Glide.with(itemView.context).load(Uri.parse(http)).into(
                itemImage
            )

             rating.rating = repas.rating
    //            CountryFlag.setImageResource(country.Image)

                titleCart.text  = repas.nom
                descriptionCart.text = repas.description
                ratingCart.rating = repas.rating
            Glide.with(itemView.context).load(HomeActivity.CurrentPlat.image).into(
                imageCart
            )

        }
    }
}

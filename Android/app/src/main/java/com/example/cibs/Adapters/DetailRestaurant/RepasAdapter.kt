package com.example.cibs.Adapters.DetailRestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cibs.R
import com.example.cibs.model.detailRestaurant.Repas
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso

class RepasAdapter(
    var  items: MutableList<Repas>,
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
        val btn : Button = itemView.findViewById(R.id.btnAddPanier)
        val itemImage : ImageView = itemView.findViewById(R.id.ivRepas)
        var ctlItems : ConstraintLayout = itemView.findViewById(R.id.ctlItems)
        var price : TextView = itemView.findViewById(R.id.txtBigPrice)
        var description : TextView = itemView.findViewById(R.id.txtDescription)
        var imgUp : ImageView = itemView.findViewById(R.id.imgUp)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                btn.visibility = View.VISIBLE
                imgUp.visibility = View.VISIBLE
                ctlItems.maxHeight = 2000
                listener.onItemClick(position)
                if (itemView.getParent() != null) {
                    (itemView.getParent() as ViewGroup).removeView(itemView) // <- fix
                }

                val dialog = BottomSheetDialog(itemView.context)
                dialog.setContentView(itemView)
                dialog.show()
            }
        }
        fun bind(repas: Repas)
        {

            btn.visibility = View.GONE
            imgUp.visibility = View.GONE
            ctlItems.maxHeight = 400
            repasNames.text = repas.nom
            price.text = repas.prix.toString()
            description.text = "voici une description du repas"
//            CountryFlag.setImageResource(country.Image)
        }
    }
}

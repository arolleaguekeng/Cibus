package com.example.cibs.Adapters.DetailRestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cibs.R
import com.example.cibs.model.detailRestaurant.Repas
import com.example.cibs.model.Restaurant
import com.example.cibs.model.RestaurantModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso


class ViewPagerAdapter(private var restaux : MutableList<RestaurantModel>) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() ,
    RepasAdapter.onItemClickListener {
    companion object{
        lateinit var CurrentRepas : Repas
        lateinit var CurrentRestaurant : Restaurant
        var restauxPosition = 0
    }
    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val itemDetails : TextView = itemView.findViewById(R.id.tvAbout)
        val itemImage : ImageView = itemView.findViewById(R.id.ivImage)
        var btnSearch : ImageButton = itemView.findViewById(R.id.btnSearch)
        val repas : RecyclerView = itemView.findViewById(R.id.rvRepasSlide)


        init {
                itemImage.setOnClickListener { v: View ->
                    val position : Int = adapterPosition
                    Toast.makeText(itemView.context,"You cliked on item ${position + 1}",Toast.LENGTH_LONG).show()
                }

        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_slide,parent,false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        restauxPosition = position
        Picasso.get().load(restaux[position].image!!).into(holder.itemImage)
        holder.itemTitle.text = restaux[position].nom
        holder.itemDetails.text = restaux[position].description

        holder.btnSearch.setOnClickListener {
            CurrentRestaurant = restaux[position]
            val dialog =  BottomSheetDialog(holder.itemView.context)
            dialog.setContentView(R.layout.activity_detail_restaurant)
            dialog.show()
        }

        try{
            holder.repas.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = RepasAdapter(restaux[position].repas!!,this@ViewPagerAdapter)
            }
        }
        catch(e: Exception){
            Log.e("do not get repas !", e.message.toString())
        }

    }

    override fun getItemCount(): Int {
        return restaux.size
    }

    override fun onItemClick(position: Int) {
        CurrentRepas = restaux[restauxPosition].repas!![position]
    }
}
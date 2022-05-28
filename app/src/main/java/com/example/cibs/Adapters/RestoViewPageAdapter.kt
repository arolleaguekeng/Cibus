package com.example.cibs.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cibs.R

class RestoViewPageprivateAdapter (var title: List<String>, private var image: List<Int>) :
    RecyclerView.Adapter<RestoViewPageprivateAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {

        val itemTitle: TextView = itemView.findViewById(R.id.textTitle)
        val image: ImageView = itemView.findViewById(R.id.image)


        init {
            image.setOnClickListener{
                var position: Int = adapterPosition
                Toast.makeText(it.context, "you click image #${position+1}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagerViewHolder {
        return  PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page_resto, parent, false))
    }

    override fun getItemCount(): Int = title.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.image.setImageResource(image[position])
        holder.itemTitle.text = title[position]


    }

}
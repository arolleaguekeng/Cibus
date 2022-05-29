package com.example.cibs.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cibs.Interfaces.ItemClickListener
import com.example.cibs.R

class ViewPageAdapter(private var title: List<String>, private var textButton: List<String>, private var image: List<Int>, val itemClickListener: ItemClickListener):RecyclerView.Adapter<ViewPageAdapter.PagerViewHolder>()
{

    inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
        val button: Button = itemView.findViewById(R.id.buttonPanel)

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
        return  PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.image.setImageResource(image[position])
        holder.button.text = textButton[position]
        holder.itemTitle.text = title[position]

        holder.button.setOnClickListener(){
            itemClickListener.onButtonClicked(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = title.size

}
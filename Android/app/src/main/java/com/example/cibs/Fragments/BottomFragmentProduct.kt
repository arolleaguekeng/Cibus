package com.example.cibs.Fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.cibs.Activities.HomeActivity
import com.example.cibs.R
import com.example.cibs.RetroInstance
import com.example.cibs.model.Panier
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BottomFragmentProduct.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomFragmentProduct : BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_product, container, false)
    }

    var currentPrix: Double = 0.0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var currentQuantity = 1

        var isAdd = false
        var quantity = view.findViewById<TextView>(R.id.Quantity)
        var addQuantity = view.findViewById<ImageView>(R.id.addQuantity)
        var addRemove = view.findViewById<ImageView>(R.id.remove)
        var fav = view.findViewById<ImageView>(R.id.fav)
        var platImage: ImageView = view.findViewById<ImageView>(R.id.image)
        var title: TextView = view.findViewById<TextView>(R.id.repasName)
        var sub: TextView = view.findViewById<TextView>(R.id.subtitle)
        var price: TextView = view.findViewById<TextView>(R.id.price)
        var rating: RatingBar = view.findViewById<RatingBar>(R.id.rating)
        var button: Button = view.findViewById<Button>(R.id.addpanier)
        var subprice: TextView = view.findViewById<TextView>(R.id.subprice)
        var description: TextView = view.findViewById<TextView>(R.id.Description_repas)

        title.text = HomeActivity.CurrentPlat.nom
        sub.text = HomeActivity.CurrentPlat.nom
        price.text = HomeActivity.CurrentPlat.price.toString()+" XAF"
        currentPrix = HomeActivity.CurrentPlat.price
        rating.rating = HomeActivity.CurrentPlat.rating
        description.text = HomeActivity.CurrentPlat.description
        subprice.text = HomeActivity.CurrentPlat.price.toString()+" XAF"
        var http = RetroInstance.baseAdresse+"static/"+HomeActivity.CurrentPlat.image
        Glide.with(view.context).load(Uri.parse(http)).into(
            platImage
        )
        if(isAdd){
            button.isEnabled = false
            //button. = R.color.sableBlanc
        }
        addQuantity.setOnClickListener{
            currentQuantity++
            quantity.text = currentQuantity.toString()
            currentPrix += HomeActivity.CurrentPlat.price

        }

        addRemove.setOnClickListener{
            if(currentQuantity > 1)
                currentQuantity--
            quantity.text = currentQuantity.toString()
            currentPrix -= HomeActivity.CurrentPlat.price
        }

        button.setOnClickListener{
            var exist = false
            for(i in HomeActivity.ListPanier){
                if(i.plat == HomeActivity.CurrentPlat){
                    i.quantity += currentQuantity
                    exist = true
                }
            }
            if(exist == false){
                HomeActivity.ListPanier.add(Panier(HomeActivity.CurrentPlat, currentQuantity, currentPrix))
            }
            isAdd = true
        }


    }


}
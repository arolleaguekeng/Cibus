package com.example.cibs.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import cm.pam.retos.RepasApi
import com.example.cibs.Activities.DetailRestaurantActivity
import com.example.cibs.Activities.DetailRestaurantActivity.Companion.restauxList
import com.example.cibs.Adapters.*
import com.example.cibs.Activities.HomeActivity
import com.example.cibs.Adapters.DetailRestaurant.ViewPagerAdapter
import com.example.cibs.Interfaces.CategorieClickListener
import com.example.cibs.Interfaces.PlatClickListener
import com.example.cibs.Interfaces.Restaurant1ClickListener

import com.example.cibs.R
import com.example.cibs.model.Categorie
import com.example.cibs.model.Plat
import com.example.cibs.model.Restaurant
import com.example.cibs.model.RestaurantModel

class fragment_home(private val context: HomeActivity
): Fragment(), CategorieClickListener, Restaurant1ClickListener, PlatClickListener {
    val bottomSheetFragment = BottomFragmentProduct(context)
    val bottomFragmentParameter = BottomFragmentParameter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater?.inflate(R.layout.fragment_home,container,false)
        var view_allRestaurant : TextView = view.findViewById(R.id.view_allRestaurant)
        view_allRestaurant.setOnClickListener {
            var intent = Intent(context, DetailRestaurantActivity::class.java);
            startActivity(intent)
        }
        //Initialisation

        //ajout
        val parameter: ImageView = view.findViewById(R.id.parameter)
        parameter.setOnClickListener {
            bottomFragmentParameter.show(parentFragmentManager, "bottom sheet dialogue")
        }


        val horizontalrecycleView=view.findViewById<RecyclerView>(R.id.horizontal_recycleView1)
        horizontalrecycleView.adapter= CategorieAdapter(HomeActivity.listCategorie, this)

        val horizontalrecycleView2=view.findViewById<RecyclerView>(R.id.horizontal_recycleView2)
        horizontalrecycleView2.adapter=Restaurant1Adapter(HomeActivity.listRest, this)

        val horizontalrecycleView3=view.findViewById<RecyclerView>(R.id.horizontal_recycleView3)
        horizontalrecycleView3.adapter=PlatAdapter(HomeActivity.listPlat, this)

        val horizontalrecycleView4=view.findViewById<RecyclerView>(R.id.horizontal_recycleView4)
        horizontalrecycleView4.adapter=Restaurant2Adapter(HomeActivity.listRest, this)

        return view
    }

    override fun onButtonClicked(categorie: Categorie, view: View) {
        Toast.makeText(context, categorie.nom, Toast.LENGTH_SHORT).show()


    }

    override fun onItemClicked(categorie: Categorie, view: View) {
        Toast.makeText(context, categorie.nom, Toast.LENGTH_SHORT).show()

    }

    override fun onButtonClicked(plat: Plat) {
        Toast.makeText(context, plat.nom, Toast.LENGTH_SHORT).show()

    }

    override fun onItemClicked(plat: Plat, view: View) {
        HomeActivity.CurrentPlat = plat
        bottomSheetFragment.show(parentFragmentManager, "Bottom sheet dialog")
    }

    override fun onButtonClicked(restaurant: Restaurant) {
        var intent = Intent(context, DetailRestaurantActivity::class.java);
        startActivity(intent)

    }

    override fun onItemClicked(restaurant: Restaurant, view: View) {
        var intent = Intent(context, DetailRestaurantActivity::class.java);
        startActivity(intent)
    }
}
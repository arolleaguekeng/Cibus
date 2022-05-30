package com.example.cibs.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cibs.Adapters.*
import com.example.cibs.Activities.HomeActivity
import com.example.cibs.Interfaces.CategorieClickListener
import com.example.cibs.Interfaces.PlatClickListener
import com.example.cibs.Interfaces.Restaurant1ClickListener

import com.example.cibs.R
import com.example.cibs.model.Categorie
import com.example.cibs.model.Plat
import com.example.cibs.model.Restaurant

class fragment_home(private val context: HomeActivity
): Fragment(), CategorieClickListener, Restaurant1ClickListener, PlatClickListener {
    val bottomSheetFragment = BottomFragmentProduct()
    val bottomFragmentParameter = BottomFragmentParameter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater?.inflate(R.layout.fragment_home,container,false)

        //Initialisation

        val RestaurantList:MutableList<Restaurant> = mutableListOf(
            Restaurant("Restaurant le Fouquet","Repas sur place ", "https://kathalog.net/admin/images/419326830IMG_5157.JPG", 1.2f, 12 ),
            Restaurant("White House Restaurant","Repas a emporter ","https://www.parisgourmand.com/images/stories/17-restos/fouquet-s-restaurant-paris-s.jpg", 1.2f, 12),
            Restaurant("Murano Lounge","Repas sur place ","https://media-cdn.tripadvisor.com/media/photo-s/12/44/7f/a4/white-house.jpg", 4.5f, 12),
            Restaurant("Mama Mia Lounge","Repas a emporter","https://kathalog.net/admin/images/eee2b040387c4b80b806098ab1f861a7.jpeg", 3.5f, 12),
            Restaurant("Restaurant le Fouquet","Repas sur place ", "https://kathalog.net/admin/images/419326830IMG_5157.JPG", 3.2f, 12)
                )
        val CategorieList: MutableList<Categorie> = mutableListOf(
            Categorie("Burger","https://st.depositphotos.com/1020618/2013/i/950/depositphotos_20136185-stock-photo-delicious-italian-pizza.jpg","Des burger pour tous"),
            Categorie("Pizza","https://st2.depositphotos.com/1177973/9248/i/950/depositphotos_92482426-stock-photo-pepperoni-pizza-with-olives-and.jpg","Des burger pour tous"),
            Categorie("Soupe","https://st.depositphotos.com/1020618/2013/i/950/depositphotos_20136185-stock-photo-delicious-italian-pizza.jpg","Des burger pour tous"),
            Categorie("Laitu","https://st4.depositphotos.com/13349494/21832/i/1600/depositphotos_218326124-stock-photo-top-view-bowl-delicious-pumpkin.jpg","Des burger pour tous"),
            Categorie("Autre","https://st4.depositphotos.com/13349494/21832/i/1600/depositphotos_218326124-stock-photo-top-view-bowl-delicious-pumpkin.jpg","Des burger pour tous"),
            Categorie("Burger","https://st.depositphotos.com/1020618/2013/i/950/depositphotos_20136185-stock-photo-delicious-italian-pizza.jpg","Des burger pour tous"),
            Categorie("Pizza","https://st2.depositphotos.com/1177973/9248/i/950/depositphotos_92482426-stock-photo-pepperoni-pizza-with-olives-and.jpg","Des burger pour tous"),
            Categorie("Soupe","https://st.depositphotos.com/1020618/2013/i/950/depositphotos_20136185-stock-photo-delicious-italian-pizza.jpg","Des burger pour tous")
        )
        val PlatList: MutableList<Plat> = mutableListOf(
            Plat(1,"Pizza Magentha","description","https://st.depositphotos.com/1020618/2013/i/950/depositphotos_20136185-stock-photo-delicious-italian-pizza.jpg",2.0f,7000.0, 12, 1),
            Plat(2,"Pizza Peperronie","description","https://st2.depositphotos.com/1177973/9248/i/950/depositphotos_92482426-stock-photo-pepperoni-pizza-with-olives-and.jpg",2.0f,7000.0, 12, 1),
            Plat(3,"Hamburger traditionnel","description","https://st.depositphotos.com/1588534/3151/i/950/depositphotos_31516383-stock-photo-traditional-hamburger-and-french-fries.jpg",2.0f,7000.0, 12, 1),
            Plat(4,"Soupe de cirtrouille","description","https://st4.depositphotos.com/13349494/21832/i/1600/depositphotos_218326124-stock-photo-top-view-bowl-delicious-pumpkin.jpg",2.0f,7000.0, 12, 1),
            Plat(5,"Autre","description","https://st4.depositphotos.com/13349494/21832/i/1600/depositphotos_218326124-stock-photo-top-view-bowl-delicious-pumpkin.jpg",2.0f,7000.0, 12, 1),

            )

        //ajout
        val parameter: ImageView = view.findViewById(R.id.parameter)
        parameter.setOnClickListener {
            bottomFragmentParameter.show(parentFragmentManager, "bottom sheet dialogue")
        }


        val horizontalrecycleView=view.findViewById<RecyclerView>(R.id.horizontal_recycleView1)
        horizontalrecycleView.adapter= CategorieAdapter(CategorieList, this)

        val horizontalrecycleView2=view.findViewById<RecyclerView>(R.id.horizontal_recycleView2)
        horizontalrecycleView2.adapter=Restaurant1Adapter(RestaurantList, this)

        val horizontalrecycleView3=view.findViewById<RecyclerView>(R.id.horizontal_recycleView3)
        horizontalrecycleView3.adapter=PlatAdapter(PlatList, this)

        val horizontalrecycleView4=view.findViewById<RecyclerView>(R.id.horizontal_recycleView4)
        horizontalrecycleView4.adapter=Restaurant2Adapter(RestaurantList, this)

        return view
    }

    override fun onButtonClicked(categorie: Categorie, view: View) {
        Toast.makeText(context, categorie.name, Toast.LENGTH_SHORT).show()


    }

    override fun onItemClicked(categorie: Categorie, view: View) {
        Toast.makeText(context, categorie.name, Toast.LENGTH_SHORT).show()

    }

    override fun onButtonClicked(plat: Plat) {
        Toast.makeText(context, plat.name, Toast.LENGTH_SHORT).show()

    }

    override fun onItemClicked(plat: Plat, view: View) {
        HomeActivity.CurrentPlat = plat
        bottomSheetFragment.show(parentFragmentManager, "Bottom sheet dialog")
    }

    override fun onButtonClicked(restaurant: Restaurant) {
        Toast.makeText(context, restaurant.name, Toast.LENGTH_SHORT).show()

    }

    override fun onItemClicked(restaurant: Restaurant, view: View) {
        Toast.makeText(context, restaurant.name, Toast.LENGTH_SHORT).show()
    }
}
package com.example.cibs.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cibs.Adapters.Accueiladapter
import com.example.cibs.HomeActivity
import com.example.cibs.Models.Categorie
import com.example.cibs.Models.Plat
import com.example.cibs.Models.Restaurant
import com.example.cibs.R

class fragment_home(private val context:HomeActivity): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater?.inflate(R.layout.fragment_home,container,false)


        //Initialisation

        val RestaurantList=ArrayList<Restaurant>()
        val CategorieList=ArrayList<Categorie>()
        val PlatList=ArrayList<Plat>()

        //ajout
        CategorieList.add(
            Categorie("Burger","Des burger pour tous")
        )
        CategorieList.add(
            Categorie("Pizza","Pizza pour tous")
        )
        CategorieList.add(
            Categorie("Soupe","Soupe pour tous")
        )
        CategorieList.add(
            Categorie("Laitu","Laitu pour tous")
        )

        CategorieList.add(
            Categorie("Autre","Tout pour tous")
        )

        PlatList.add(
            Plat("Pizza Magentha",7000.0,"https://st.depositphotos.com/1020618/2013/i/950/depositphotos_20136185-stock-photo-delicious-italian-pizza.jpg",2.0,CategorieList[1])
        )
        PlatList.add(
            Plat("Pizza Peperronie",6000.0,"https://st2.depositphotos.com/1177973/9248/i/950/depositphotos_92482426-stock-photo-pepperoni-pizza-with-olives-and.jpg",3.0,CategorieList[1])
        )
        PlatList.add(
            Plat("Hamburger traditionnel",1500.0,"https://st.depositphotos.com/1588534/3151/i/950/depositphotos_31516383-stock-photo-traditional-hamburger-and-french-fries.jpg",4.0,CategorieList[0])
        )
        PlatList.add(
            Plat("Soupe de cirtrouille",300.0,"https://st4.depositphotos.com/13349494/21832/i/1600/depositphotos_218326124-stock-photo-top-view-bowl-delicious-pumpkin.jpg",3.0,CategorieList[2])
        )
        PlatList.add(
            Plat("Autre",3000.0,"https://st4.depositphotos.com/13349494/21832/i/1600/depositphotos_218326124-stock-photo-top-view-bowl-delicious-pumpkin.jpg",3.0,CategorieList[3])
        )

        RestaurantList.add(
            Restaurant("White House Restaurant","Repas sur place ",2.0,
                "https://www.parisgourmand.com/images/stories/17-restos/fouquet-s-restaurant-paris-s.jpg",

                PlatList[0],
                PlatList
                )
        )
        RestaurantList.add(
            Restaurant("Restaurant le Fouquet","A emporter",4.0,
                "https://kathalog.net/admin/images/419326830IMG_5157.JPG",

                PlatList[1],
                PlatList
            )
        )
        RestaurantList.add(
            Restaurant("Murano Lounge","A emporter",3.5,
                "https://media-cdn.tripadvisor.com/media/photo-s/12/44/7f/a4/white-house.jpg",
                PlatList[2],
                PlatList
            )
        )
        RestaurantList.add(
            Restaurant("Mama Mia Lounge","Tous a votre porte",4.5,
            "https://kathalog.net/admin/images/eee2b040387c4b80b806098ab1f861a7.jpeg",
            PlatList[3],
                PlatList
            )
        )
        RestaurantList.add(
            Restaurant("Mama Mia Lounge","Tous a votre porte",4.5,
                "https://kathalog.net/admin/images/eee2b040387c4b80b806098ab1f861a7.jpeg",
                PlatList[4],
                PlatList
            )
        )
        val horizontalrecycleView=view.findViewById<RecyclerView>(R.id.horizontal_recycleView1)
        horizontalrecycleView.adapter= Accueiladapter(context,RestaurantList,R.layout.item_horizontal1)

        val horizontalrecycleView2=view.findViewById<RecyclerView>(R.id.horizontal_recycleView2)
        horizontalrecycleView2.adapter=Accueiladapter(context,RestaurantList,R.layout.item_horizontal2)

        val horizontalrecycleView3=view.findViewById<RecyclerView>(R.id.horizontal_recycleView3)
        horizontalrecycleView3.adapter=Accueiladapter(context,RestaurantList,R.layout.item_horizontal3)

        val horizontalrecycleView4=view.findViewById<RecyclerView>(R.id.horizontal_recycleView4)
        horizontalrecycleView4.adapter=Accueiladapter(context,RestaurantList,R.layout.item_horizontal4)

        return view
    }
}
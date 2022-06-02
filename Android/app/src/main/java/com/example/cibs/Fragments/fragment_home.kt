package com.example.cibs.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.example.cibs.RetroInstance
import com.example.cibs.model.*
import com.example.cibs.service.CategorieServiceApi
import com.example.cibs.viewModel.HomeActivityViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class fragment_home(private val context: HomeActivity
): Fragment(), CategorieClickListener, Restaurant1ClickListener, PlatClickListener {
    val bottomSheetFragment = BottomFragmentProduct(context)
    val bottomFragmentParameter = BottomFragmentParameter()

    lateinit var restaurant1Adapter: Restaurant1Adapter
    lateinit var restaurant2Adapter: Restaurant2Adapter
    lateinit var categorieAdapter: CategorieAdapter
    lateinit var platAdapter: PlatAdapter
    lateinit var viewModel: HomeActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =inflater?.inflate(R.layout.fragment_home,container,false)
        var view_allRestaurant : TextView = view.findViewById(R.id.view_allRestaurant)


        view_allRestaurant.setOnClickListener {
            var intent = Intent(context, DetailRestaurantActivity::class.java);
            startActivity(intent)
        }
        //Initialisation
        initCategorieRecycleView(view)
        initRestaurant1RecycleView(view)
        initRepasRecycleView(view)
        initRestaurant2RecycleView(view)
        initViewModelCategorie()
        initViewModelRestaurant1()
        initViewModelRepas()
        initViewModelProduitPanier()

        //ajout
        val parameter: ImageView = view.findViewById(R.id.parameter)
        parameter.setOnClickListener {
            bottomFragmentParameter.show(parentFragmentManager, "bottom sheet dialogue")
        }








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


    fun initCategorieRecycleView(view: View){
        val categorierecycleView=view.findViewById<RecyclerView>(R.id.horizontal_recycleView1)
        categorierecycleView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            categorieAdapter = CategorieAdapter(this@fragment_home)
            adapter = categorieAdapter
        }
       // horizontalrecycleView.adapter= CategorieAdapter(HomeActivity.listCategorie, this)
    }



    fun initViewModelCategorie(){
        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
        viewModel.getCategoryObservable().observe(viewLifecycleOwner, Observer<MutableList<Categorie>?> {
                if (it == null) {
                    Toast.makeText(context, "Aucune Categories", Toast.LENGTH_SHORT).show()
                } else {
                    HomeActivity.listCategorie = it
                    categorieAdapter.datas = it.toMutableList()
                    categorieAdapter.notifyDataSetChanged()
                }
            })
        viewModel.GetCategorie()
    }


    fun initRestaurant1RecycleView(view: View){
        val restaurant=view.findViewById<RecyclerView>(R.id.horizontal_recycleView2)
        restaurant.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            restaurant1Adapter = Restaurant1Adapter(this@fragment_home)
            adapter = restaurant1Adapter
        }
        // horizontalrecycleView.adapter= CategorieAdapter(HomeActivity.listCategorie, this)
    }
    fun initRestaurant2RecycleView(view: View){
        val restaurant=view.findViewById<RecyclerView>(R.id.horizontal_recycleView4)
        restaurant.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            restaurant2Adapter = Restaurant2Adapter(this@fragment_home)
            adapter = restaurant2Adapter
        }
        // horizontalrecycleView.adapter= CategorieAdapter(HomeActivity.listCategorie, this)
    }



    fun initViewModelRestaurant1(){
        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
        viewModel.getRestaurantNewObservable().observe(viewLifecycleOwner, Observer<MutableList<Restaurant>?> {
            if (it == null) {
                Toast.makeText(context, "Aucun Restaurant", Toast.LENGTH_SHORT).show()
            } else {
                HomeActivity.listRest = it
                restaurant1Adapter.datas = it.toMutableList()
                restaurant2Adapter.datas = it.toMutableList()
                restaurant1Adapter.notifyDataSetChanged()
            }
        })
        viewModel.GetRestaurant()
    }

    fun initRepasRecycleView(view: View){
        val plat=view.findViewById<RecyclerView>(R.id.horizontal_recycleView3)
        plat.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            platAdapter = PlatAdapter(this@fragment_home)
            adapter = platAdapter
        }
        // horizontalrecycleView.adapter= CategorieAdapter(HomeActivity.listCategorie, this)
    }





    fun initViewModelRepas(){
        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
        viewModel.getRepasNewObservable().observe(viewLifecycleOwner, Observer<MutableList<Plat>?> {
            if (it == null) {
                Toast.makeText(context, "Aucun Restaurant", Toast.LENGTH_SHORT).show()
            } else {
                HomeActivity.listPlat = it
                platAdapter.datas = it.toMutableList()
                platAdapter.notifyDataSetChanged()
            }
        })
        viewModel.GetPlat()
    }


    fun initViewModelProduitPanier(){
        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
        viewModel.getAllProductpanierObservable().observe(viewLifecycleOwner, Observer<MutableList<ProduitPanier>?>{
            if (it == null) {
                Toast.makeText(context, "Aucun produit dans le panier", Toast.LENGTH_SHORT).show()
            } else {

                HomeActivity.listProduitPanier = it


            }
        })

        viewModel.GetAllProductPanier()
    }


}